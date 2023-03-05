import {instanceAxios} from '../_base/axios.instance'
const PATH_USER = '/amizades'
const PATH_ACTION = 'solicitar'

export async function requireFriendly(idUsuario, idSolicitante) {
  const response = await instanceAxios.put(
    `${PATH_USER}/${idUsuario}/${PATH_ACTION}`,
    {id: idSolicitante}
  )
  return response.data
}
