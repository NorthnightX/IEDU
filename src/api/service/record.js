import request from '@/utils/request'

// 查询投递记录列表
export function listRecord(query) {
  return request({
    url: '/service/record/list',
    method: 'get',
    params: query
  })
}

// 查询投递记录详细
export function getRecord(eduId) {
  return request({
    url: '/service/record/' + eduId,
    method: 'get'
  })
}

// 新增投递记录
export function addRecord(data) {
  return request({
    url: '/service/record',
    method: 'post',
    data: data
  })
}

// 修改投递记录
export function updateRecord(data) {
  return request({
    url: '/service/record',
    method: 'put',
    data: data
  })
}

// 删除投递记录
export function delRecord(eduId) {
  return request({
    url: '/service/record/' + eduId,
    method: 'delete'
  })
}
export function getRecordsByUser(query) {
  return request({
    url: '/service/record/getRecordsByUser',
    method: 'get',
    params: query
  })
}

