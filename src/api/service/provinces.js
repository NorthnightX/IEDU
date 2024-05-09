import request from '@/utils/request'

// 查询全国省市列表
export function listProvinces(query) {
  return request({
    url: '/service/provinces/list',
    method: 'get',
    params: query
  })
}

// 查询全国省市详细
export function getProvinces(id) {
  return request({
    url: '/service/provinces/' + id,
    method: 'get'
  })
}

// 新增全国省市
export function addProvinces(data) {
  return request({
    url: '/service/provinces',
    method: 'post',
    data: data
  })
}

// 修改全国省市
export function updateProvinces(data) {
  return request({
    url: '/service/provinces',
    method: 'put',
    data: data
  })
}

// 删除全国省市
export function delProvinces(id) {
  return request({
    url: '/service/provinces/' + id,
    method: 'delete'
  })
}
export function getDistrictList(){
  return request({
    url: '/service/provinces/getListOfHierarchy',
    method: 'get'
  })
}
export function initDistrict(){
  return request({
    url: '/service/provinces/init',
    method: 'get'
  })
}
