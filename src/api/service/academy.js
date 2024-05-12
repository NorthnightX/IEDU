import request from '@/utils/request'

// 查询院校管理列表
export function listAcademy(query) {
  return request({
    url: '/service/academy/list',
    method: 'get',
    params: query
  })
}

// 查询院校管理详细
export function getAcademy(eduId) {
  return request({
    url: '/service/academy/' + eduId,
    method: 'get'
  })
}

// 新增院校管理
export function addAcademy(data) {
  return request({
    url: '/service/academy',
    method: 'post',
    data: data
  })
}

// 修改院校管理
export function updateAcademy(data) {
  return request({
    url: '/service/academy',
    method: 'put',
    data: data
  })
}

// 删除院校管理
export function delAcademy(eduId) {
  return request({
    url: '/service/academy/' + eduId,
    method: 'delete'
  })
}
