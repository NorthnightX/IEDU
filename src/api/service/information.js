import request from '@/utils/request'

// 查询公司信息列表
export function listInformation(query) {
  return request({
    url: '/service/information/list',
    method: 'get',
    params: query
  })
}

// 查询公司信息详细
export function getInformation(eduId) {
  return request({
    url: '/service/information/' + eduId,
    method: 'get'
  })
}

// 新增公司信息
export function addInformation(data) {
  return request({
    url: '/service/information',
    method: 'post',
    data: data
  })
}

// 修改公司信息
export function updateInformation(data) {
  return request({
    url: '/service/information',
    method: 'put',
    data: data
  })
}

// 删除公司信息
export function delInformation(eduId) {
  return request({
    url: '/service/information/' + eduId,
    method: 'delete'
  })
}
export function getInformationByCompany(id) {
  return request({
    url: '/service/information/getInfo/' + id,
    method: 'get'
  })
}
