import request from '@/utils/request'

// 查询新闻列表
export function listNews(query) {
  return request({
    url: '/service/news/list',
    method: 'get',
    params: query
  })
}

// 查询新闻详细
export function getNews(eduId) {
  return request({
    url: '/service/news/' + eduId,
    method: 'get'
  })
}

// 新增新闻
export function addNews(data) {
  return request({
    url: '/service/news',
    method: 'post',
    data: data
  })
}

// 修改新闻
export function updateNews(data) {
  return request({
    url: '/service/news',
    method: 'put',
    data: data
  })
}

// 删除新闻
export function delNews(eduId) {
  return request({
    url: '/service/news/' + eduId,
    method: 'delete'
  })
}

export function getCurrentNews(eduId) {
  return request({
    url: '/service/news/u/getCurrentNews',
    method: 'get'
  })
}
export function getNewsPageBySearchText(params){
  return request({
    url: '/service/news/u/getNewsByKeyword',
    method: 'get',
    params: params
  })
}
