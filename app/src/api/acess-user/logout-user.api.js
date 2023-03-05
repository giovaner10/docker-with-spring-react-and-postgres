import {instanceAxios} from '../_base/axios.instance'
import {USER_KEY} from '../../constants/index'
const PATH_LOGOUT = '/logout'

export async function logoutUser() {
  await instanceAxios.post(PATH_LOGOUT)
  localStorage.removeItem(USER_KEY)
}
