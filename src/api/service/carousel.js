import request from '@/utils/request'

// 查询轮播图管理列表
export function listCarousel(query) {
  return request({
    url: '/service/carousel/list',
    method: 'get',
    params: query
  })
}

// 查询轮播图管理详细
export function getCarousel(eduId) {
  return request({
    url: '/service/carousel/' + eduId,
    method: 'get'
  })
}

// 新增轮播图管理
export function addCarousel(data) {
  return request({
    url: '/service/carousel',
    method: 'post',
    data: data
  })
}

// 修改轮播图管理
export function updateCarousel(data) {
  return request({
    url: '/service/carousel',
    method: 'put',
    data: data
  })
}

// 删除轮播图管理
export function delCarousel(eduId) {
  return request({
    url: '/service/carousel/' + eduId,
    method: 'delete'
  })
}
export function getAllCarousel(){
  return request({
    url: '/service/carousel/u/list',
    method: 'get',
  })
}
