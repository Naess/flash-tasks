import React, { Component } from 'react'

class TaskDetail extends Component {
  constructor(props) {
    super(props)
    this.state = {
      title: props.data.title || '',
      description: props.data.description || '',
      estimate: props.data.estimate || '',
      taskId: props.data.taskId || '',
      status: props.data.status || 'Not Started'
    }
    console.log(props.data);
    this.statuses = ['Not Started', 'In Progress', 'Done']
    this.handleChange = this.handleChange.bind(this)
  }
  render() {
    return (
      <div className="create-task">
        <label>Title</label><br/>
        <input name="title"
          onChange={this.handleChange}
          value={this.state.title}
          className="form-control"
        />
        <br/><label>Description</label><br/>
        <textarea name="description"
          onChange={this.handleChange}
          value={this.state.description}
          className="form-control"
        />
        <br/><label>Estimate</label><br/>
        <input name="estimate"
          onChange={this.handleChange}
          value={this.state.estimate}
          className="form-control"
        />
        <br/><label>Status</label><br/>
        <select name="status"
          onChange={this.handleChange}
          value={this.state.status}
          className="form-control"
        >
          {this.statuses.map(status =>
            <option
              value={status}
              key={status}>{status}</option>
          )}
        </select>
        <br/><br/>
        <button
          onClick={() => {
            this.props.onSave(this.state)
            this.props.onClose()
          }}
          className="btn">
          Save
        </button>
        <button
          onClick={() => this.props.onClose()}
          className="btn"
          style={{marginLeft: "3px"}}>
          Close
        </button>
      </div>
    )
  }

  handleChange(e) {
    this.setState({ [e.target.name]:  e.target.value })
  }

}

export default TaskDetail
