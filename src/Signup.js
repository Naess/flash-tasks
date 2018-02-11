import React, { Component } from 'react'
import { Link } from 'react-router-dom'
import { withCookies } from 'react-cookie'
import axios from 'axios'

class Login extends Component {
  constructor(props) {
    super(props)
    this.state = {}
  }

  onSignUp = () => {
    const { cookies } = this.props
    const { history } = this.props
    const newUser = {
      'active': true,
      'email': this.emailInput.value,
      'lastname': this.lastNameInput.value,
      'firstname': this.firstNameInput.value,
      'password': this.passwordInput.value
    }
    const proxyurl = "https://cors-anywhere.herokuapp.com/"
    axios.post(proxyurl + 'http://ec2-18-219-37-238.us-east-2.compute.amazonaws.com:8080/api/v1/users/sign-up', newUser)
      .then((response) => {
        console.log(response.data)
        cookies.set('userFirst', response.data.firstname, { path: '/' })
        cookies.set('userLast', response.data.lastname, { path: '/' })
        cookies.set('userEmail', response.data.email, { path: '/' })
        cookies.set('userId', response.data.userId, { path: '/' })
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
          <h2>Sign up</h2>
          <form>
            <div className="form-group">
              <label htmlFor="inputEmail">Email address</label>
              <input type="email"
                     className="form-control"
                     id="inputEmail1"
                     placeholder="Email"
                     ref={ref => this.emailInput = ref}></input>
            </div>
            <div className="form-group">
              <label htmlFor="firstName">First Name</label>
              <input type="text"
                     className="form-control"
                     id="firstName"
                     placeholder="First Name"
                     ref={ref => this.firstNameInput = ref}></input>
            </div>
            <div className="form-group">
              <label htmlFor="lastName">Last Name</label>
              <input type="text"
                     className="form-control"
                     id="lastName"
                     placeholder="Last Name"
                     ref={ref => this.lastNameInput = ref}></input>
            </div>
            <div className="form-group">
              <label htmlFor="inputPassword">Password</label>
              <input type="password"
                     className="form-control"
                     id="inputPassword"
                     placeholder="Password"
                     ref={ref => this.passwordInput = ref}></input>
            </div>
            <button type="button" onClick={() => this.onSignUp()} className="btn btn-default">Sign up</button>
          </form>
          <br />
          <Link to="/login">...or log in</Link>
        </div>
      </div>
    )
  }
}

export default withCookies(Login)
