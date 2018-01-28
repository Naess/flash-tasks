import React, { Component } from 'react'
import ReactModal from 'react-modal'
import TaskDetail from './TaskDetail.js'

class EditTaskBtn extends Component {
  constructor(props) {
    super(props)
    this.state = {
      showTaskModal: false
    }
    ReactModal.setAppElement('#root')
    this.handleOpenModal = this.handleOpenModal.bind(this)
    this.handleCloseModal = this.handleCloseModal.bind(this)
  }

  render() {
    return (
      <span>
        <button onClick={this.handleOpenModal}>
          {this.props.display}
        </button>
        <ReactModal
           isOpen={this.state.showTaskModal}
           contentLabel="Task Modal">
           <TaskDetail
             data={this.props.data || {}}
             onSave={this.props.onSave}
             onClose={this.handleCloseModal}
           />
        </ReactModal>
      </span>
    )
  }

  handleOpenModal () {
    this.setState({ showTaskModal: true })
  }

  handleCloseModal () {
    this.setState({ showTaskModal: false })
  }


}

export default EditTaskBtn
