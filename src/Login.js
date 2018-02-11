import React, { Component } from 'react'
import { Link } from 'react-router-dom'
import { withCookies } from 'react-cookie'
import axios from 'axios'

class Login extends Component {
  constructor(props) {
    super(props)
    this.state = {
    }
  }

  onLogin = () => {
    const { cookies } = this.props
    const { history } = this.props

    const proxyurl = "https://cors-anywhere.herokuapp.com/"
    axios.post(proxyurl + 'http://ec2-18-219-37-238.us-east-2.compute.amazonaws.com:8080/api/v1/users/'+
      encodeURIComponent(this.emailInput.value)+'/'+
      encodeURIComponent(this.passwordInput.value)
      ,{email:this.emailInput.value, password:this.passwordInput.value})
      .then((response) => {
        console.log(response.data)
        cookies.set('userEmail', this.emailInput.value, { path: '/' })
        cookies.set('userFirst', 'Caleb', { path: '/' })
        cookies.set('userLast', 'Naess', { path: '/' })
        cookies.set('userId', 2, { path: '/' })
        history.push('/')
      })
      .catch((error) => {
        console.log(error)
      })
  }

  render() {
    return (
      <div className="container">
        <div className="row">
          <h2>Log in</h2>
          <form>
            <div className="form-group">
              <label htmlFor="inputEmail1">Email address</label>
              <input type="email"
                     className="form-control"
                     id="inputEmail1"
                     placeholder="Email"
                     ref={ref => this.emailInput = ref}></input>
            </div>
            <div className="form-group">
              <label htmlFor="inputPassword1">Password</label>
              <input type="password"
                     className="form-control"
                     id="inputPassword1"
                     placeholder="Password"
                     ref={ref => this.passwordInput = ref}></input>
            </div>
            <button type="button" onClick={() => this.onLogin()} className="btn btn-default">Log in</button>
          </form>
          <br />
          <Link to="/signup">...or sign up</Link>
        </div>
      </div>
    )
  }
}

export default withCookies(Login)
