import request from '@/utils/request'

// 查询学历要求列表
export function listRequirement(query) {
  return request({
    url: '/service/requirement/list',
    method: 'get',
    params: query
  })
}

// 查询学历要求详细
export function getRequirement(eduId) {
  return request({
    url: '/service/requirement/' + eduId,
    method: 'get'
  })
}

// 新增学历要求
export function addRequirement(data) {
  return request({
    url: '/service/requirement',
    method: 'post',
    data: data
  })
}
export function listAllRequirement() {
  return request({
    url: '/service/requirement/all',
    method: 'get'
  })
}
// 修改学历要求
export function updateRequirement(data) {
  return request({
    url: '/service/requirement',
    method: 'put',
    data: data
  })
}

// 删除学历要求
export function delRequirement(eduId) {
  return request({
    url: '/service/requirement/' + eduId,
    method: 'delete'
  })
}
