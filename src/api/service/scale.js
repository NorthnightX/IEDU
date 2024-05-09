import request from '@/utils/request'

// 查询公司规模列表
export function listScale(query) {
  return request({
    url: '/service/scale/list',
    method: 'get',
    params: query
  })
}

// 查询公司规模详细
export function getScale(eduId) {
  return request({
    url: '/service/scale/' + eduId,
    method: 'get'
  })
}

// 新增公司规模
export function addScale(data) {
  return request({
    url: '/service/scale',
    method: 'post',
    data: data
  })
}

// 修改公司规模
export function updateScale(data) {
  return request({
    url: '/service/scale',
    method: 'put',
    data: data
  })
}

// 删除公司规模
export function delScale(eduId) {
  return request({
    url: '/service/scale/' + eduId,
    method: 'delete'
  })
}
