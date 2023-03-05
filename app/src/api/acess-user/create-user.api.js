import {instanceAxios} from '../_base/axios.instance'
const PATH_LOGIN = '/usuarios'

export async function createUser({
  nomeCompleto,
  email,
  senha,
  dataNasimento,
  foto,
  apelido,
}) {
  const response = await instanceAxios.post(PATH_LOGIN, {
    nomeCompleto,
    email,
    senha,
    dataNasimento,
    foto,
    apelido,
  })
  return response.data
}
