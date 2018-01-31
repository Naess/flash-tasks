import React, { Component } from 'react'
import EditTaskBtn from './EditTaskBtn.js'

class Task extends Component {

  getStatusClass() {
    switch (this.props.data.status) {
      case 'Not Started':
        return 'status-not-started'
      case 'In Progress':
        return 'status-in-progress'
      case 'Done':
        return 'status-done'
      default:
        return 'status-not-started'
    }
  }

  render() {
    return (
      <div className={'list-item ' + this.getStatusClass()}>
        <div className="task-title">{this.props.data.title}</div>
        <div className="task-description">{this.props.data.description}</div>
        <div className="task-details">
          <label>Estimate:&nbsp;</label>
          <span>{this.props.data.estimate}</span><br/>
          <label>Status:&nbsp;</label>
          <span>{this.props.data.status}</span>
          <div className="task-edit">
            <EditTaskBtn
              data={this.props.data}
              display={<span>Edit&nbsp;&nbsp;<span className="glyphicon glyphicon-pencil" aria-hidden="true"></span></span>}
              onSave={this.props.onEdit} />
          </div>
        </div>
      </div>
    )
  }
}

export default Task
