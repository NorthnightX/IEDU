import request from '@/utils/request'

// 查询公司列表
export function listCompany(query) {
  return request({
    url: '/service/company/list',
    method: 'get',
    params: query
  })
}

// 查询公司详细
export function getCompany(eduId) {
  return request({
    url: '/service/company/' + eduId,
    method: 'get'
  })
}

// 新增公司
export function addCompany(data) {
  return request({
    url: '/service/company',
    method: 'post',
    data: data
  })
}

// 修改公司
export function updateCompany(data) {
  return request({
    url: '/service/company',
    method: 'put',
    data: data
  })
}

// 删除公司
export function delCompany(eduId) {
  return request({
    url: '/service/company/' + eduId,
    method: 'delete'
  })
}
export function listAllCompany() {
  return request({
    url: '/service/company/all',
    method: 'get'
  })
}
export function listUCompany(query) {
  return request({
    url: '/service/company/u/list',
    method: 'get',
    params: query
  })
}
export function getCompanyDetail(eduId) {
  return request({
    url: '/service/company/u/detail/' + eduId,
    method: 'get'
  })
}
