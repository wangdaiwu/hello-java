import axios from 'axios'

const service = axios.create({
  baseURL: 'http://localhost:8001/lab2',
  timeout: 10000
})

service.interceptors.response.use(
  response => {
    const res = response.data
    return res
  }
)

export default service
