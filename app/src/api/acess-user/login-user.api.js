import {instanceAxios} from '../_base/axios.instance'
const PATH_LOGIN = '/login'

export async function loginUser({username, password}) {
  const response = await instanceAxios.post(
    PATH_LOGIN,
    {},
    {
      auth: {
        username,
        password,
      },
      withCredentials: true,
    }
  )
  return response.data
}
