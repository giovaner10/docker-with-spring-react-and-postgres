import {instanceAxios} from '../_base/axios.instance'
const PATH_USER = '/amizades'
const PATH_ACTION = '/solicitacoes'

export async function findRequests(idUsuario) {
  const response = await instanceAxios.get(
    `${PATH_USER}/${idUsuario}/${PATH_ACTION}`
  )
  return response.data
}
