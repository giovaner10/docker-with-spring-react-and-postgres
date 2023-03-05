import {instanceAxios} from '../_base/axios.instance'

export async function findComentsPost() {
  const response = await instanceAxios.get('comentarios/1')
  return response.data
}
