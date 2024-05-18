import request from '@/utils/request'

// 查询动态列表
export function listArticle(query) {
  return request({
    url: '/service/article/list',
    method: 'get',
    params: query
  })
}

// 查询动态详细
export function getArticle(eduId) {
  return request({
    url: '/service/article/' + eduId,
    method: 'get'
  })
}

// 新增动态
export function addArticle(data) {
  return request({
    url: '/service/article',
    method: 'post',
    data: data
  })
}

// 修改动态
export function updateArticle(data) {
  return request({
    url: '/service/article',
    method: 'put',
    data: data
  })
}

// 删除动态
export function delArticle(eduId) {
  return request({
    url: '/service/article/' + eduId,
    method: 'delete'
  })
}
export function getCurrentArticle(){
  return request({
    url: '/service/article/u/getCurrentArticle',
    method: 'get'
  })
}
export function getArticlePageBySearchText(params){
  return request({
    url: '/service/article/u/getArticleByKeyword',
    method: 'get',
    params: params
  })
}
