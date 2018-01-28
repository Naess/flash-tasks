import React, { Component } from 'react'
import List from './tasks/List.js'
import './css/App.css'

class App extends Component {
  render() {
    return (
      <div className="App">
        <header className="App-header">
          <h2 className="App-title">Flash Tasks</h2>
        </header>
        <List name="List Name"/>
      </div>
    )
  }
}

export default App
