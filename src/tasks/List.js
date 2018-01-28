import React, { Component } from 'react'
import Task from './Task.js'
import EditTaskBtn from './EditTaskBtn.js'
import './css/List.css'

class List extends Component {
  constructor(props) {
    super(props)
    this.state = {
      items: []
    }
    this.saveTask = this.saveTask.bind(this)
  }

  render() {
    return (
      <div className="list-container">
        <span className="list-title"> {this.props.name} </span>
        <a href="http://localhost:3000/"> Share </a>
        <ul className="list">
          {this.state.items.map(item => (
            <li
              key={item.id}
              className="list-item">
              <Task
                data={item}
                onEdit={this.saveTask}/>
            </li>
          ))}
        </ul>
        <EditTaskBtn
         display="Create Task"
         onSave={this.saveTask} />
      </div>
    )
  }

  saveTask(data) {
    if (!data.title.length) {
      return
    }
    if (data.id) {
      this.updateTask(data)
    } else {
      this.createTask(data)
    }
  }

  createTask(data) {
    const newItem = {
      title: data.title,
      description: data.description,
      estimate: data.estimate,
      id: Date.now()
    }

    this.setState(prevState => ({
      items: prevState.items.concat(newItem)
    }))
  }

  updateTask(data) {
    this.setState(prevState => {
      let index = prevState.items.findIndex(item => item.id === data.id)
      let newState = prevState
      newState.items[index] = data
      return { items: newState.items}
    })
  }

}

export default List
