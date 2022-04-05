import request from '@/utils/request'

export default {
  getUserList (current, size) {
    return request({
      url: `/user/${current}/${size}`,
      method: 'get'
    })
  },
  checkUsername (username) {
    return request({
      url: `/user/checkUsername/${username}`,
      method: 'get'
    })
  },
  addUser (user) {
    return request({
      url: `/user/`,
      method: 'post',
      data: user
    })
  },
  getUser (username) {
    return request({
      url: `/user/${username}`,
      method: 'get'
    })
  },
  updateUser (user) {
    return request({
      url: `/user/`,
      method: 'put',
      data: user
    })
  },
  deleteUser (username) {
    return request({
      url: `/user/${username}`,
      method: 'delete'
    })
  }
}
