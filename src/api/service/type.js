import request from '@/utils/request'

// 查询工作类型列表
export function listType(query) {
  return request({
    url: '/service/type/list',
    method: 'get',
    params: query
  })
}

// 查询工作类型详细
export function getType(eduId) {
  return request({
    url: '/service/type/' + eduId,
    method: 'get'
  })
}

// 新增工作类型
export function addType(data) {
  return request({
    url: '/service/type',
    method: 'post',
    data: data
  })
}

// 修改工作类型
export function updateType(data) {
  return request({
    url: '/service/type',
    method: 'put',
    data: data
  })
}

// 删除工作类型
export function delType(eduId) {
  return request({
    url: '/service/type/' + eduId,
    method: 'delete'
  })
}
export function listAllType() {
  return request({
    url: '/service/type/all',
    method: 'get'
  })
}
