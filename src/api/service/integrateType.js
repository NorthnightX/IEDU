import request from '@/utils/request'

// 查询产教融合类型管理列表
export function listIntegrateType(query) {
  return request({
    url: '/service/integrateType/list',
    method: 'get',
    params: query
  })
}

// 查询产教融合类型管理详细
export function getIntegrateType(eduId) {
  return request({
    url: '/service/integrateType/' + eduId,
    method: 'get'
  })
}

// 新增产教融合类型管理
export function addIntegrateType(data) {
  return request({
    url: '/service/integrateType',
    method: 'post',
    data: data
  })
}

// 修改产教融合类型管理
export function updateIntegrateType(data) {
  return request({
    url: '/service/integrateType',
    method: 'put',
    data: data
  })
}

// 删除产教融合类型管理
export function delIntegrateType(eduId) {
  return request({
    url: '/service/integrateType/' + eduId,
    method: 'delete'
  })
}
export function getAllIntegrateType(){
  return request({
    url: '/service/integrateType/u/allType',
    method: 'get'
  })
}
