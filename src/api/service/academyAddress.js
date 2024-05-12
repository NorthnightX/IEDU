import request from '@/utils/request'

// 查询学校地址列表
export function listAcademyAddress(query) {
  return request({
    url: '/service/academyAddress/list',
    method: 'get',
    params: query
  })
}

// 查询学校地址详细
export function getAcademyAddress(eduId) {
  return request({
    url: '/service/academyAddress/' + eduId,
    method: 'get'
  })
}

// 新增学校地址
export function addAcademyAddress(data) {
  return request({
    url: '/service/academyAddress',
    method: 'post',
    data: data
  })
}

// 修改学校地址
export function updateAcademyAddress(data) {
  return request({
    url: '/service/academyAddress',
    method: 'put',
    data: data
  })
}

// 删除学校地址
export function delAcademyAddress(eduId) {
  return request({
    url: '/service/academyAddress/' + eduId,
    method: 'delete'
  })
}
export function getAddressByAcademy(eduId){
  return request({
    url: '/service/academyAddress/getByAcademy/' + eduId,
    method: 'get',
  })
}
