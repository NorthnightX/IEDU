import request from '@/utils/request'

// 查询产教融合管理列表
export function listIntegrate(query) {
  return request({
    url: '/service/integrate/list',
    method: 'get',
    params: query
  })
}

// 查询产教融合管理详细
export function getIntegrate(eduId) {
  return request({
    url: '/service/integrate/' + eduId,
    method: 'get'
  })
}

// 新增产教融合管理
export function addIntegrate(data) {
  return request({
    url: '/service/integrate',
    method: 'post',
    data: data
  })
}

// 修改产教融合管理
export function updateIntegrate(data) {
  return request({
    url: '/service/integrate',
    method: 'put',
    data: data
  })
}

// 删除产教融合管理
export function delIntegrate(eduId) {
  return request({
    url: '/service/integrate/' + eduId,
    method: 'delete'
  })
}
export function getIntegrateList(query){
  return request({
    url: '/service/integrate/u/list',
    method: 'get',
    params: query
  })
}

export function getIntegrateByKeyWord(params){
  return request({
    url: '/service/integrate/u/getIntegrateByKeyWord',
    method: 'get',
    params: params
  })
}
export function getIntegrateDetails(eduId){
  return request({
    url: '/service/integrate/u/getIntegrateById/' + eduId,
    method: 'get',
  })
}
