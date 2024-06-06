import request from '@/utils/request'

// 查询轮播图管理列表
export function getTop(query) {
    return request({
        url: '/service/chart/top',
        method: 'get',
        params: query
    })
}
export function getAge() {
    return request({
        url: '/service/chart/ra',
        method: 'get',
    })
}
export function getIndustry() {
    return request({
        url: '/service/chart/ci',
        method: 'get',
    })
}
export function getWorkType(){
    return request({
        url: '/service/chart/jt',
        method: 'get',
    })
}
export function getRType(){
    return request({
        url: '/service/chart/rt',
        method: 'get',
    })
}
