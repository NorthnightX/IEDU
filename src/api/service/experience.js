import request from '@/utils/request'

// 查询工作经验列表
export function listExperience(query) {
  return request({
    url: '/service/experience/list',
    method: 'get',
    params: query
  })
}

// 查询工作经验详细
export function getExperience(eduId) {
  return request({
    url: '/service/experience/' + eduId,
    method: 'get'
  })
}

// 新增工作经验
export function addExperience(data) {
  return request({
    url: '/service/experience',
    method: 'post',
    data: data
  })
}

// 修改工作经验
export function updateExperience(data) {
  return request({
    url: '/service/experience',
    method: 'put',
    data: data
  })
}

// 删除工作经验
export function delExperience(eduId) {
  return request({
    url: '/service/experience/' + eduId,
    method: 'delete'
  })
}
export function listAllExperience() {
  return request({
    url: '/service/experience/all',
    method: 'get'
  })
}
