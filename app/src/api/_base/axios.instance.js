import axios from 'axios'
import {API_URL} from '../../constants/api-url'

export const instanceAxios = axios.create({
  baseURL: API_URL,
  timeout: 5000,
  withCredentials: true,
})
