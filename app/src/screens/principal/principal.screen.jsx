import React, {useEffect, useState} from 'react'
import {useNavigate} from 'react-router-dom'
import {useToastr} from '../../hook/toastr/toastr.hook'
import {
  AppBarComponent,
  CardUserComponent,
  LoadComponent,
} from '../../components'
import {logoutUser} from '../../api/acess-user/logout-user.api'
import './principal.screen.css'
import useGlobalUser from '../../context/user/user.context'
import {findRequests} from '../../api/acess-user'
import Card from '@mui/material/Card'
import CardContent from '@mui/material/CardContent'
import CardMedia from '@mui/material/CardMedia'
import Typography from '@mui/material/Typography'

export function PrincipalScreen() {
  const navigate = useNavigate()
  const showToastr = useToastr()
  const [requests, setSolicitacoes] = useState()

  const [userGlobal] = useGlobalUser()

  async function findRequestsUser() {
    try {
      const response = await findRequests(userGlobal.id)
      setSolicitacoes(response)
    } catch (error) {
      showToastr(error.response.data.message, 'error')
    }
  }

  useEffect(() => {
    findRequestsUser()
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
        LEAVING
      </button>
      <div className="card_list">
        <div className="infos">
          <Card sx={{width: 400}}>
            <CardMedia
              sx={{height: 140}}
              image={userGlobal.foto}
              title="image user"
            />
            <CardContent>
              <Typography gutterBottom variant="h5" component="div">
                {userGlobal.nomeCompleto}
              </Typography>
              <Typography gutterBottom variant="h5" component="div">
                Email: {userGlobal.email}
              </Typography>
              <Typography gutterBottom variant="h5" component="div">
                Idade: {userGlobal.idade}
              </Typography>
              <Typography gutterBottom variant="h5" component="div">
                Apelido: {userGlobal.apelido}
              </Typography>
            </CardContent>
          </Card>
        </div>
        <div className="requests">
          requests
          {requests ? (
            requests.map(request => (
              <CardUserComponent
                key={request.id}
                user={request}
                refresh={findRequestsUser}
                type="confirm"
              />
            ))
          ) : (
            <LoadComponent />
          )}
        </div>
      </div>
    </div>
  )
}
