import React, {useEffect, useState} from 'react'
import {useNavigate} from 'react-router-dom'
import {useToastr} from '../../hook/toastr/toastr.hook'
import {AppBarComponent, LoadComponent} from '../../components'
import {logoutUser} from '../../api/acess-user/logout-user.api'
import './demo-post.screen.css'
import useGlobalUser from '../../context/user/user.context'
import {findDemoPost} from '../../api/acess-user/find-post.api'
import {CardPostComponent} from '../../components/card-post/card-post.component'

export function DemoPostScreen() {
  const navigate = useNavigate()
  const showToastr = useToastr()
  const [user, setUser] = useState()
  const [userGlobal] = useGlobalUser()

  async function getPost() {
    try {
      const response = await findDemoPost(userGlobal.id)
      setUser(response[0])
    } catch (error) {
      showToastr(error.response.data.message, 'error')
    }
  }

  useEffect(() => {
    getPost()
  }, [])

  async function logout() {
    try {
      await logoutUser()
      showToastr('Leaving...', 'sucess')
      navigate('/')
    } catch (error) {
      showToastr(error.response.request.statusText, 'error')
    }
  }

  return (
    <div className="book_screen">
      <AppBarComponent />

      <button className="button_leaving" onClick={logout}>
        {' '}
        LEAVING
      </button>

      <div className="card_list">
        <div className="requests_find_post">
          {user ? <CardPostComponent post={user} /> : <LoadComponent />}
        </div>
      </div>
    </div>
  )
}
