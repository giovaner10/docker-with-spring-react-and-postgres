import {instanceAxios} from '../_base/axios.instance'

export async function findDemoPost() {
  const response = await instanceAxios.get('postagens/1')
  return response.data
}
