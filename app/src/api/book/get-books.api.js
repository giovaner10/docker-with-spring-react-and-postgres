import {instanceAxios} from '../_base/axios.instance'
const PATH_LOGIN = '/livros'

export async function getBooks() {
  const response = await instanceAxios.get(PATH_LOGIN)

  return response.data.content
}
