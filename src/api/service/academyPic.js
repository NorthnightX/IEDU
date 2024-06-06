import request from '@/utils/request'

// 查询院校照片列表
export function listAcademyPic(query) {
  return request({
    url: '/service/academyPic/list',
    method: 'get',
    params: query
  })
}

// 查询院校照片详细
export function getAcademyPic(eduId) {
  return request({
    url: '/service/academyPic/' + eduId,
    method: 'get'
  })
}

// 新增院校照片
export function addAcademyPic(data) {
  return request({
    url: '/service/academyPic',
    method: 'post',
    data: data
  })
}

// 修改院校照片
export function updateAcademyPic(data) {
  return request({
    url: '/service/academyPic',
    method: 'put',
    data: data
  })
}

// 删除院校照片
export function delAcademyPic(eduId) {
  return request({
    url: '/service/academyPic/' + eduId,
    method: 'delete'
  })
}
export function getPicByAcademy(eduId){
  return request({
    url: '/service/academyPic/getPicByAcademy/' + eduId,
    method: 'get'
  })
}
