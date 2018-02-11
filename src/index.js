import React from 'react'
import ReactDOM from 'react-dom'
import { BrowserRouter, Switch, Route } from 'react-router-dom'
import { CookiesProvider } from 'react-cookie'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap/dist/css/bootstrap-theme.css'
import './styles/index.css'
import App from './App'
import Login from './Login'
import Signup from './Signup'
import registerServiceWorker from './registerServiceWorker'

const Auth = () => (
  <div>
    <Switch>
      <Route exact path='/' component={App}/>
      <Route path='/login' component={Login}/>
      <Route path='/signup' component={Signup}/>
    </Switch>
  </div>
)

ReactDOM.render((
  <BrowserRouter>
    <CookiesProvider>
      <Auth />
    </CookiesProvider>
  </BrowserRouter>
), document.getElementById('root'))
registerServiceWorker()
