import React, { Component } from 'react'

class SidebarContent extends Component {
  constructor(props) {
    super(props)
    this.state = {
      showCreateBtn: false,
      listName: ''
    }
    this.handleChange = this.handleChange.bind(this)
    this.createList = this.createList.bind(this)
    this.openList = this.openList.bind(this)
  }

  showCreateList(show) {
    this.setState({showCreateBtn: show})
  }

  handleChange(e) {
    this.setState({ [e.target.name]:  e.target.value })
  }

  createList() {
    if (!this.state.listName.length) {
      return
    }

    this.props.createList({
      title: this.state.listName
    })
    this.setState({ listName: '' })
  }

  openList(index) {
    this.props.openList(index)
  }

  render() {
    return (
      <div>
        <h4 className="sidebar-heading">My Lists</h4>
        <ul className="sidebar-lists">
          {this.props.lists.map((list, index) => (
            <li
              onClick={() => this.openList(index)}
              key={list.id}>
              {list.title}
            </li>
          ))}
        </ul>
        <div>
          <span
            className="sidebar-create-list-btn"
            onClick={() => this.showCreateList(true)}>
            + Create List
          </span>
          {this.state.showCreateBtn &&
            <div className="sidebar-create-list-input">
              <input
                name="listName"
                onChange={this.handleChange}
                value={this.state.listName}
                placeholder="List name"/>
              <br />
              <button
                 onClick={() => this.createList()}
                 className="btn">
                 Create
              </button>
              <button
                onClick={() => this.showCreateList(false)}
                className="btn">
                Cancel
              </button>
            </div>
          }
        </div>
      </div>
    )
  }
}

export default SidebarContent
