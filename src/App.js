import React, { Component } from 'react'
import List from './tasks/List.js'
import Sidebar from 'react-sidebar'
import MenuIcon from './menu-icon.svg'
import SidebarContent from './SidebarContent.js'
import './styles/app.css'
import './styles/sidebar.css'

class App extends Component {
  constructor(props) {
    super(props)
    this.state = {
      sidebarOpen: false,
      lists: [
        {
          name: 'First list',
          tasks: []
        },
        {
          name: 'Second list',
          tasks: []
        }
      ],
      currentListIndex: 0
    }
    this.onSetSidebarOpen = this.onSetSidebarOpen.bind(this)
    this.addList = this.addList.bind(this)
    this.setCurrentList = this.setCurrentList.bind(this)
    this.createTask = this.createTask.bind(this)
    this.updateTask = this.updateTask.bind(this)
  }

  onSetSidebarOpen(open) {
    this.setState({sidebarOpen: open})
  }

  addList(list) {
    this.setState(prevState => ({
      lists: prevState.lists.concat(list)
    }))
  }

  setCurrentList(index) {
    this.setState({currentListIndex: index})
  }

  createTask(data) {
    this.setState(prevState => {
      let newState = prevState
      newState.lists[prevState.currentListIndex].tasks.push(data)
      return newState
    })
  }

  updateTask(data) {
    this.setState(prevState => {
      let currentList = prevState.lists[prevState.currentListIndex]
      const index = currentList.tasks.findIndex(task => task.id === data.id)
      let newState = prevState
      newState.lists[prevState.currentListIndex].tasks[index] = data
      return newState
    })
  }

  render() {
    const sidebarContent =
      <div>
        <SidebarContent
          lists={this.state.lists}
          createList={this.addList}
          openList={this.setCurrentList}/>
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
         </header>
         <List
           name={this.state.lists[this.state.currentListIndex].name}
           tasks={ this.state.lists[this.state.currentListIndex].tasks}
           updateTask={this.updateTask}
           createTask={this.createTask}/>
       </div>
      </Sidebar>
    )
  }
}

export default App
