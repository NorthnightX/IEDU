import request from '@/utils/request'

// 查询招聘信息列表
export function listRecruit(query) {
  return request({
    url: '/service/recruit/list',
    method: 'get',
    params: query
  })
}

// 查询招聘信息详细
export function getRecruit(eduId) {
  return request({
    url: '/service/recruit/' + eduId,
    method: 'get'
  })
}

// 新增招聘信息
export function addRecruit(data) {
  return request({
    url: '/service/recruit',
    method: 'post',
    data: data
  })
}

// 修改招聘信息
export function updateRecruit(data) {
  return request({
    url: '/service/recruit',
    method: 'put',
    data: data
  })
}

// 删除招聘信息
export function delRecruit(eduId) {
  return request({
    url: '/service/recruit/' + eduId,
    method: 'delete'
  })
}
export function getRecruitToExhibit(query) {
  return request({
    url: '/service/recruit/exhibit',
    params: query,
    method: 'get'
  })
}
export function getRecruitDetails(eduId) {
  return request({
    url: '/service/recruit/details/' + eduId,
    method: 'get'
  })
}
