import React, { Component } from 'react'

class TaskDetail extends Component {
  constructor(props) {
    super(props)
    this.state = {
      title: props.data.title || '',
      description: props.data.description || '',
      estimate: props.data.estimate || '',
      id: props.data.id || ''
    }
    this.handleChange = this.handleChange.bind(this)
  }
  render() {
    return (
      <div>
        <label>Title</label><br/>
        <input name="title"
          onChange={this.handleChange}
          value={this.state.title}
        />
        <br/><label>Description</label><br/>
        <input name="description"
          onChange={this.handleChange}
          value={this.state.description}
        />
        <br/><label>Estimate</label><br/>
        <input name="estimate"
          onChange={this.handleChange}
          value={this.state.estimate}
        />
        <br/><br/>
        <button onClick={() => {
          this.props.onSave(this.state)
          this.props.onClose()
        }}>
          Save
        </button>
        <button onClick={() => this.props.onClose()}>
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
