import request from '@/utils/request'

// 查询公司行业列表
export function listIndustry(query) {
  return request({
    url: '/service/industry/list',
    method: 'get',
    params: query
  })
}

// 查询公司行业详细
export function getIndustry(eduId) {
  return request({
    url: '/service/industry/' + eduId,
    method: 'get'
  })
}

// 新增公司行业
export function addIndustry(data) {
  return request({
    url: '/service/industry',
    method: 'post',
    data: data
  })
}

// 修改公司行业
export function updateIndustry(data) {
  return request({
    url: '/service/industry',
    method: 'put',
    data: data
  })
}

// 删除公司行业
export function delIndustry(eduId) {
  return request({
    url: '/service/industry/' + eduId,
    method: 'delete'
  })
}
export function listIndustryHaveChild(query) {
  return request({
    url: '/service/industry/listHaveChild',
    method: 'get',
    params: query
  })
}
