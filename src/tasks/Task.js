import React, { Component } from 'react'
import EditTaskBtn from './EditTaskBtn.js'

class Task extends Component {
  render() {
    return (
      <span>
        <p>{this.props.data.title}</p>
        <p>{this.props.data.description}</p>
        <p>{this.props.data.estimate}</p>
        <EditTaskBtn
          data={this.props.data}
          display="Edit Task"
          onSave={this.props.onEdit} />
      </span>
    )
  }
}

export default Task
