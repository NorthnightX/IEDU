import request from '@/utils/request'

// 查询融资阶段列表
export function listStage(query) {
  return request({
    url: '/service/stage/list',
    method: 'get',
    params: query
  })
}

// 查询融资阶段详细
export function getStage(eduId) {
  return request({
    url: '/service/stage/' + eduId,
    method: 'get'
  })
}

// 新增融资阶段
export function addStage(data) {
  return request({
    url: '/service/stage',
    method: 'post',
    data: data
  })
}

// 修改融资阶段
export function updateStage(data) {
  return request({
    url: '/service/stage',
    method: 'put',
    data: data
  })
}

// 删除融资阶段
export function delStage(eduId) {
  return request({
    url: '/service/stage/' + eduId,
    method: 'delete'
  })
}
