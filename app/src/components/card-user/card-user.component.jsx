import * as React from 'react'
import Card from '@mui/material/Card'
import CardActions from '@mui/material/CardActions'
import CardContent from '@mui/material/CardContent'
import CardMedia from '@mui/material/CardMedia'
import Button from '@mui/material/Button'
import Typography from '@mui/material/Typography'
import {useToastr} from '../../hook/toastr/toastr.hook'
import useGlobalUser from '../../context/user/user.context'
import {requireFriendly} from '../../api/acess-user'
import {confirmUser} from '../../api/acess-user/confirm-user.api'

export function CardUserComponent({user, refresh, type}) {
  const showTostr = useToastr()
  const [userGlobal] = useGlobalUser()

  async function require() {
    try {
      await requireFriendly(userGlobal.id, user.id)
      showTostr('Solicitacao enviada com sucesso', 'sucess')
    } catch (error) {
      showTostr(error.response?.data?.message, 'error')
    }
  }

  async function confirm() {
    try {
      await confirmUser(userGlobal.id, user.id)
      refresh()
      showTostr('Amizade confirmada', 'sucess')
    } catch (error) {
      showTostr(error.response?.data?.message, 'error')
    }
  }

  return (
    <>
      <Card sx={{width: 300, margin: 5, height: 310}}>
        <CardMedia sx={{height: 140}} image={user.foto} title="image user" />

        <CardContent>
          <Typography gutterBottom variant="h5" component="div">
            Nome: {user.nomeCompleto}
          </Typography>
        </CardContent>
        <CardActions>
          <Button
            style={{display: type === 'require' ? 'blok' : 'none'}}
            size="small"
            onClick={() => require()}
          >
            Solicicitar
          </Button>

          <Button
            style={{display: type === 'confirm' ? 'blok' : 'none'}}
            size="small"
            onClick={() => confirm()}
          >
            Confirmar
          </Button>

          <Button size="small" href={`/corridas/${user.idCorrida}`}>
            detalhes
          </Button>
        </CardActions>
      </Card>
    </>
  )
}
