import React, { Component } from 'react'
import Task from './Task.js'
import EditTaskBtn from './EditTaskBtn.js'
import './styles/list.css'
import Reorder from 'react-reorder';

class List extends Component {
  constructor(props) {
    super(props)
    this.saveTask = this.saveTask.bind(this)
  }

  render() {
    return (
      <div className="list-container">
        <span className="list-title"> {this.props.name} </span>
        <a href="#"> Share </a>
        <Reorder
          className="list"
          reorderId="task-list"
          component="ul"
          holdTime={100}
          onReorder={this.props.onListReorder.bind(this)}>
          {this.props.tasks.map(item => (
            <li
              key={item.taskId}
              className="grabbable">
              <Task
                data={item}
                onEdit={this.saveTask} />
            </li>
          )).toArray()}
        </Reorder>
        <EditTaskBtn
         display={<span><span className="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;&nbsp;Create Task</span>}
         onSave={this.saveTask}
         className="btn" />
      </div>
    )
  }

  saveTask(data) {
    if (!data.title.length) {
      return
    }

    if (data.taskId) {
      this.props.updateTask(data)
    } else {
      const newItem = {
        title: data.title,
        description: data.description,
        estimate: data.estimate,
        status: data.status,
        taskId: Date.now()
      }
      this.props.createTask(newItem)
    }
  }
}

export default List
