import React, {useState} from 'react'
import {useNavigate} from 'react-router-dom'
import {loginUser} from '../../api/acess-user/login-user.api'
import useGlobalUser from '../../context/user/user.context'
import {useToastr} from '../../hook/toastr/toastr.hook'
import {
  InputComponent,
  ActionButtonComponent,
  CreateUserComponent,
} from '../../components'
import scrollLogin from '../../assets/images/pergaminho-login-screen.png'
import './login.screen.css'

const USER_MIN = 4
const PASSWORD_MIN = 3

export function LoginScreen() {
  const [loginInput, setLoginInput] = useState({
    username: '',
    password: '',
  })
  const [user, setUser] = useGlobalUser()
  const navigate = useNavigate()
  const showToastr = useToastr()
  const [openForm, setOpenForm] = useState(false)

  function open() {
    setOpenForm(a => !a)
  }

  function handleChange(event) {
    const {name, value} = event.target
    setLoginInput(oldLoginInput => ({
      ...oldLoginInput,
      [name]: value,
    }))
  }

  async function handleSubmit(event) {
    event.preventDefault()
    if (loginInput.username.length < USER_MIN) {
      showToastr('Campo de usuario muito curto, min: 4')
      return
    }
    if (loginInput.password.length < PASSWORD_MIN) {
      showToastr('Campo de senha muito curto, min: 6')
      return
    }

    userLogin()
  }

  async function userLogin() {
    try {
      const response = await loginUser({
        username: loginInput.username,
        password: loginInput.password,
      })
      setUser(response)
      navigate('/eu')
    } catch (error) {
      showToastr(error.response.request.statusText, 'error')
    }
  }

  return (
    <>
      <CreateUserComponent requireData={open} stateForm={openForm} />
      <div className="login_screen">
        <form className="form_login_screen" onSubmit={handleSubmit}>
          <div className="background_login_Screeen">
            <img
              className="image_scroll_login_screen"
              src={scrollLogin}
              alt="scroll login"
            />
            <div className="information_login_screen user_login_screen">
              <InputComponent
                type="text"
                label="Username"
                id="username"
                name="username"
                value={loginInput.username}
                handleChange={handleChange}
                placeholder="Username"
              />
            </div>
            <div className="information_login_screen password_login_screen">
              <InputComponent
                type="password"
                label="Password"
                id="password"
                name="password"
                value={loginInput.password}
                handleChange={handleChange}
                placeholder="Password"
              />
            </div>
            <div className="button_login_screen">
              <ActionButtonComponent
                textButton="Login"
                screenComponent="login_screen_button"
              />
              <button onClick={open}>Create</button>
            </div>
          </div>
        </form>
      </div>
    </>
  )
}
