import request from '@/utils/request'

export default {
  getPersonList (current, size) {
    return request({
      url: `/person/${current}/${size}`,
      method: 'get'
    })
  },
  checkName (name) {
    return request({
      url: `/person/checkName/${name}`,
      method: 'get'
    })
  },
  addPerson (person) {
    return request({
      url: `/person/`,
      method: 'post',
      data: person
    })
  },
  getPerson (name) {
    return request({
      url: `/person/${name}`,
      method: 'get'
    })
  },
  updatePerson (person) {
    return request({
      url: `/person/`,
      method: 'put',
      data: person
    })
  },
  deletePerson (name) {
    return request({
      url: `/deletePerson/${name}`,
      method: 'delete'
    })
  }
}
