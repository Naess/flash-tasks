import React, { Component } from 'react'
import List from './tasks/List.js'
import Sidebar from 'react-sidebar'
import MenuIcon from './menu-icon.svg'
import SidebarContent from './SidebarContent.js'
import Immutable from 'immutable'
import axios from 'axios'
import './styles/app.css'
import './styles/sidebar.css'
import {reorderImmutable} from 'react-reorder'
import { withCookies } from 'react-cookie'

class App extends Component {
  constructor(props) {
    super(props)
    const { cookies } = props
    const { history } = this.props
    if (!cookies.get('userId'))
    {
      history.push('/login')
    }

    this.state = {
      userFirst: cookies.get('userFirst'),
      userLast: cookies.get('userLast'),
      userId: cookies.get('userId'),
      userEmail: cookies.get('userEmail'),
      sidebarOpen: false,
      lists: [
        {
          id: 100000,
          title: 'My First List',
          tasks: Immutable.List()
        }
      ],
      currentListIndex: 0
    }
    this.onSetSidebarOpen = this.onSetSidebarOpen.bind(this)
    this.addList = this.addList.bind(this)
    this.setCurrentList = this.setCurrentList.bind(this)
    this.createTask = this.createTask.bind(this)
    this.updateTask = this.updateTask.bind(this)
    this.onListReorder = this.onListReorder.bind(this)
  }

  componentWillMount() {
    const proxyurl = "https://cors-anywhere.herokuapp.com/"
    axios.get(proxyurl + 'http://ec2-18-219-37-238.us-east-2.compute.amazonaws.com:8080/api/v1/lists/'/*+this.state.userId*/,{
      listId: this.state.userId
    })
      .then((response) => {
        console.log(response.data)
        for (let i = 0; i < response.data.length; i++) {
          for (let j = 0; j < response.data[i].tasks.length; j++) {
            response.data[i].tasks[j].description = response.data[i].tasks[j].discription
            delete response.data[i].tasks[j].discription
          }
          response.data[i].tasks = Immutable.List(response.data[i].tasks)
        }
        if (response.data.length > 0) {
            this.setState({lists: response.data})
        }
      })
      .catch((error) => {
        console.log(error)
      })
  }

  onSetSidebarOpen(open) {
    this.setState({sidebarOpen: open})
  }

  addList(list) {
    console.log(list)
    const proxyurl = "https://cors-anywhere.herokuapp.com/"
    axios.post(proxyurl +
      'http://ec2-18-219-37-238.us-east-2.compute.amazonaws.com:8080/api/v1/list?title='
      +encodeURIComponent(list.title),
      list)
      .then((response) => {
        console.log('response');
        console.log(response.data)
        list.tasks = Immutable.List()
        list.id = response.data.id
        this.setState(prevState => ({
          lists: prevState.lists.concat(list)
        }))
      })
      .catch((error) => {
        console.log(error)
      })
  }

  setCurrentList(index) {
    this.setState({currentListIndex: index})
  }

  createTask(data) {

     const newTask = {
        "created": Date.now(),
        "createdBy": this.state.userId.toString(),
        "discription": data.description,
        "estimate": data.estimate,
        "modified": Date.now(),
        "modifiedBy": this.state.userId.toString(),
        "status": data.status,
        "title": data.title,
        "userId": this.state.userId,
        "list": this.state.lists[this.state.currentListIndex]
    }
    console.log('createTask');
    console.log(newTask);
    const proxyurl = "https://cors-anywhere.herokuapp.com/"
    axios.post(proxyurl + 'http://ec2-18-219-37-238.us-east-2.compute.amazonaws.com:8080/api/v1/tasks', newTask)
      .then((response) => {
        console.log(response.data)
      })
      .catch((error) => {
        console.log(error)
      })
    this.setState(prevState => {
      let newState = prevState
      const newTaskList = newState.lists[prevState.currentListIndex].tasks.push(data)
      newState.lists[prevState.currentListIndex].tasks = newTaskList
      return newState
    })
  }

  updateTask(data) {
    console.log('updateTask');
    console.log(data);
    data.discription = data.description
    const proxyurl = "https://cors-anywhere.herokuapp.com/"
    axios.put(proxyurl + 'http://ec2-18-219-37-238.us-east-2.compute.amazonaws.com:8080/api/v1/tasks/'+data.taskId, data)
      .then((response) => {
        console.log(response.data)
      })
      .catch((error) => {
        console.log(error)
      })
    this.setState(prevState => {
      let currentList = prevState.lists[prevState.currentListIndex]
      const index = currentList.tasks.findIndex(task => task.taskId === data.taskId)
      let newState = prevState
      const updatedTasks = newState.lists[prevState.currentListIndex].tasks.set(index, data)
      newState.lists[prevState.currentListIndex].tasks = updatedTasks
      return newState
    })
  }

  onListReorder(event, previousIndex, newIndex, fromId, toId) {
    this.setState(prevState => {
      let newState = prevState
      const newList = reorderImmutable(newState.lists[prevState.currentListIndex].tasks, previousIndex, newIndex)
      newState.lists[prevState.currentListIndex].tasks = newList
      return newState
    })
  }

  logout() {
    const { cookies } = this.props
    const { history } = this.props
    cookies.remove('userFirst')
    cookies.remove('userLast')
    cookies.remove('userEmail')
    cookies.remove('userId')
    history.push('/login')
  }

  render() {
    const sidebarContent =
      <div>
        <SidebarContent
          lists={this.state.lists}
          createList={this.addList}
          openList={this.setCurrentList} />
      </div>
    return (
      <Sidebar sidebar={sidebarContent}
               open={this.state.sidebarOpen}
               onSetOpen={this.onSetSidebarOpen}
               styles={require('./styles/sidebar-styles.json')}
               children=''>
       <div className='App'>
         <header className='App-header'>
           <img
             src={MenuIcon}
             onClick={() => this.onSetSidebarOpen(true)}
             alt='Menu'
             className='menu-icon'>
           </img>
           <h2 className='App-title'>Flash Tasks</h2>
           <span>{this.state.userFirst} {this.state.userLast}&nbsp;
             - {this.state.userEmail}</span>&nbsp;
          {this.state.userId &&
          <span className="btn btn-xs" onClick={() => this.logout()}>Log out</span>}
         </header>
         <List
           name={this.state.lists[this.state.currentListIndex].title}
           tasks={this.state.lists[this.state.currentListIndex].tasks}
           updateTask={this.updateTask}
           createTask={this.createTask}
           onListReorder={this.onListReorder}/>
       </div>
      </Sidebar>
    )
  }
}

export default withCookies(App)
