import request from '@/utils/request'

// 查询公司地址列表
export function listAddress(query) {
  return request({
    url: '/service/address/list',
    method: 'get',
    params: query
  })
}

// 查询公司地址详细
export function getAddress(eduId) {
  return request({
    url: '/service/address/' + eduId,
    method: 'get'
  })
}

// 新增公司地址
export function addAddress(data) {
  return request({
    url: '/service/address',
    method: 'post',
    data: data
  })
}

// 修改公司地址
export function updateAddress(data) {
  return request({
    url: '/service/address',
    method: 'put',
    data: data
  })
}

// 删除公司地址
export function delAddress(eduId) {
  return request({
    url: '/service/address/' + eduId,
    method: 'delete'
  })
}
export function getAddressByCompany(companyId) {
  return request({
    url: '/service/address/getAddressByCompany/' + companyId,
    method: 'get'
  })
}
export function getAddressVOAsListByCompany(companyId) {
  return request({
    url: '/service/address/getAddressVOAsListByCompany/' + companyId,
    method: 'get'
  })
}
