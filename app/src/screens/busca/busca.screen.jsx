import React, {useEffect, useState} from 'react'
import {useNavigate} from 'react-router-dom'
import {useToastr} from '../../hook/toastr/toastr.hook'
import {
  AppBarComponent,
  CardUserComponent,
  LoadComponent,
} from '../../components'
import {logoutUser} from '../../api/acess-user/logout-user.api'
import './busca.screen.css'
import useGlobalUser from '../../context/user/user.context'
import {findNoFriends} from '../../api/acess-user'

export function BuscaScreen() {
  const navigate = useNavigate()
  const showToastr = useToastr()
  const [user, setUser] = useState()
  const [userGlobal] = useGlobalUser()

  async function getAllUserAvaible() {
    try {
      const response = await findNoFriends(userGlobal.id)
      setUser(response)
    } catch (error) {
      showToastr(error.response.data.message, 'error')
    }
  }

  useEffect(() => {
    getAllUserAvaible()
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
        <div className="requests_find">
          {user ? (
            user.map(use => (
              <CardUserComponent key={use.id} user={use} type="require" />
            ))
          ) : (
            <LoadComponent />
          )}
        </div>
      </div>
    </div>
  )
}
