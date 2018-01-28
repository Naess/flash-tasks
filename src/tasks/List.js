import React, { Component } from 'react'
import Task from './Task.js'
import EditTaskBtn from './EditTaskBtn.js'
import './styles/list.css'

class List extends Component {
  constructor(props) {
    super(props)
    this.saveTask = this.saveTask.bind(this)
  }

  render() {
    return (
      <div className="list-container">
        <span className="list-title"> {this.props.name} </span>
        <a href="http://localhost:3000/"> Share </a>
        <ul className="list">
          {this.props.tasks.map(item => (
            <li
              key={item.id}
              className="list-item">
              <Task
                data={item}
                onEdit={this.saveTask} />
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
      this.props.updateTask(data)
    } else {
      const newItem = {
        title: data.title,
        description: data.description,
        estimate: data.estimate,
        id: Date.now()
      }
      this.props.createTask(newItem)
    }
  }
}

export default List
