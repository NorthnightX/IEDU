import request from '@/utils/request'

// 查询公司用户关系列表
export function listUser(query) {
  return request({
    url: '/service/cu/list',
    method: 'get',
    params: query
  })
}

// 查询公司用户关系详细
export function getUser(eduCompanyId) {
  return request({
    url: '/service/cu/' + eduCompanyId,
    method: 'get'
  })
}

// 新增公司用户关系
export function addUser(data) {
  return request({
    url: '/service/cu',
    method: 'post',
    data: data
  })
}

// 修改公司用户关系
export function updateUser(data) {
  return request({
    url: '/service/cu',
    method: 'put',
    data: data
  })
}

// 删除公司用户关系
export function delUser(eduCompanyId) {
  return request({
    url: '/service/cu/' + eduCompanyId,
    method: 'delete'
  })
}
export function getUserCompany(eduUserId) {
  return request({
    url: '/service/cu/getUserCompany/' + eduUserId,
    method: 'get'
  })
}
