import request from '@/utils/request'

// 查询企业照片列表
export function listCompanyPic(query) {
  return request({
    url: '/service/companyPic/list',
    method: 'get',
    params: query
  })
}

// 查询企业照片详细
export function getCompanyPic(eduId) {
  return request({
    url: '/service/companyPic/' + eduId,
    method: 'get'
  })
}

// 新增企业照片
export function addCompanyPic(data) {
  return request({
    url: '/service/companyPic',
    method: 'post',
    data: data
  })
}

// 修改企业照片
export function updateCompanyPic(data) {
  return request({
    url: '/service/companyPic',
    method: 'put',
    data: data
  })
}

// 删除企业照片
export function delCompanyPic(eduId) {
  return request({
    url: '/service/companyPic/' + eduId,
    method: 'delete'
  })
}
export function getPicByCompany(eduId){
  return request({
    url: '/service/companyPic/getPicByCompany/' + eduId,
    method: 'get'
  })
}
