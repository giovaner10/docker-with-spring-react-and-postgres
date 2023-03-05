import * as React from 'react'
import Button from '@mui/material/Button'
import TextField from '@mui/material/TextField'
import Dialog from '@mui/material/Dialog'
import DialogActions from '@mui/material/DialogActions'
import DialogContent from '@mui/material/DialogContent'
import DialogTitle from '@mui/material/DialogTitle'
import {useToastr} from '../../hook/toastr/toastr.hook'
import {createUser} from '../../api/acess-user'

export function CreateUserComponent({stateForm, requireData}) {
  const showTostr = useToastr()

  const [user, setUser] = React.useState({
    nomeCompleto: '',
    email: '',
    senha: '',
    dataNasimento: '',
    foto: '',
    apelido: '',
  })

  function handleChange(event) {
    const {name, value} = event.target
    setUser(olduser => ({
      ...olduser,
      [name]: value,
    }))
  }

  async function criar() {
    try {
      await createUser({
        nomeCompleto: user.nomeCompleto,
        email: user.email,
        senha: user.senha,
        dataNasimento: user.dataNasimento,
        foto: user.foto,
        apelido: user.apelido,
      })
      requireData()
      showTostr('Usuario criado com sucesso', 'sucess')
    } catch (error) {
      if (error.response.data.campos) {
        showTostr(error.response.data.campos, 'error')
      } else {
        showTostr(error.response.data.message, 'error')
      }
    }
  }

  return (
    <div>
      <Dialog open={stateForm}>
        <DialogTitle>Selecione suas coordenadas</DialogTitle>
        <DialogContent>
          <TextField
            autoFocus
            margin="dense"
            id="nomeCompleto"
            name="nomeCompleto"
            label="nomeCompleto"
            type="text"
            fullWidth
            variant="outlined"
            defaultValue={user.nomeCompleto}
            onChange={handleChange}
          />

          <TextField
            autoFocus
            margin="dense"
            id="email"
            name="email"
            label="email"
            type="text"
            fullWidth
            variant="outlined"
            defaultValue={user.email}
            onChange={handleChange}
          />

          <TextField
            autoFocus
            margin="dense"
            id="senha"
            name="senha"
            label="senha"
            type="text"
            fullWidth
            variant="outlined"
            defaultValue={user.senha}
            onChange={handleChange}
          />

          <TextField
            autoFocus
            margin="dense"
            id="foto"
            name="foto"
            label="foto"
            type="text"
            fullWidth
            variant="outlined"
            defaultValue={user.foto}
            onChange={handleChange}
          />

          <TextField
            autoFocus
            margin="dense"
            id="dataNasimento"
            name="dataNasimento"
            label="dataNasimento"
            type="date"
            fullWidth
            variant="outlined"
            defaultValue={user.nomeCompleto}
            onChange={handleChange}
          />

          <TextField
            autoFocus
            margin="dense"
            id="apelido"
            name="apelido"
            label="apelido"
            type="text"
            fullWidth
            variant="outlined"
            defaultValue={user.apelido}
            onChange={handleChange}
          />
        </DialogContent>
        <DialogActions>
          <Button onClick={requireData}>Cancel</Button>
          <Button onClick={criar}>Create</Button>
        </DialogActions>
      </Dialog>
    </div>
  )
}
