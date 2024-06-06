<script setup>
import top from  '@/components/header/header.vue'
import bottom from '@/components/bottom/bottom.vue'
import {onMounted, ref} from "vue";
import router from "@/router/index.js";
import {useRoute} from "vue-router";
import {Search} from "@element-plus/icons-vue";
import {getNewsPageBySearchText} from "@/api/service/news.js";
import {getArticlePageBySearchText} from "@/api/service/article.js";
import {getRecruitByCondition,} from "@/api/service/recruit.js";
import {getIntegratesByCondition} from "@/api/service/integrate.js";
import {getAllIntegrateType} from "@/api/service/integrateType.js";
import {listAllType} from "@/api/service/type.js";
import {getAdvertiseOfSearch} from "@/api/service/advertise.js";
import ImagePreview from "@/components/ImagePreview/index.vue";
import API from "../../api/axios.js";
const menuIdx = ref(1)
const searchText = ref("")
const pageNum = ref(1)
const pageSize = ref(10)
const totalNum = ref(1)
const route = useRoute();
const jobTypeList = ref([]) // 工作类型集合
const pageList = ref([])
const pageType = ref("")
const integrateTypeList = ref([]) // 产教融合类型集合
const eduProjectType= ref(0) // 默认选中产教融合类型
const eduJobTypeId = ref([1]) // 默认选中工作类型id
const advertiseList = ref([])
function getIntegrateTypeList(){
  getAllIntegrateType().then((res) => {
    integrateTypeList.value = res.data
  })
}
onMounted(() => {
  searchText.value = router.currentRoute.value.query.text;
  switch (router.currentRoute.value.query.type) {
    case "integrate" :
      menuIdx.value = 4
      getIntegrateTypeList()
      searchIntegrate(1, router.currentRoute.value.query.integrateId)
      break
    case "recruit":
      menuIdx.value = 3
      eduJobTypeId.value = router.currentRoute.value.query.jobTypeIds
      searchRecruit(1)
      break
    default:
      menuIdx.value = 1
      searchNews(1)
  }
})
watch(() => route.query.text, (newValue, oldValue) => {
  searchText.value = newValue
});

function searchNews(page){
  pageType.value = "news"
  pageNum.value = page
  pageList.value = null
  if(searchText.value.length === 0){
    return
  }
  getNewsPageBySearchText({text: searchText.value, pageSize: pageSize.value, pageNum : pageNum.value}).then((res) => {
    pageList.value = res.data.data
    totalNum.value = res.data.total
  })
}

function searchArticle(page){
  pageType.value = "article"
  pageNum.value = page
  pageList.value = null
  getArticlePageBySearchText({text: searchText.value, pageSize: pageSize.value, pageNum : pageNum.value}).then((res) => {
    pageList.value = res.data.data
    totalNum.value = res.data.total
  })
}
function searchRecruit(page){
  pageType.value = "recruit"
  pageNum.value = page
  pageList.value = null
  if(searchText.value.length === 0 || eduJobTypeId.value.length === 0){
    return
  }
  let tIds = eduJobTypeId.value
  getRecruitByCondition({jobTypeId: tIds[tIds.length-1], text: searchText.value,
    pageSize: pageSize.value, pageNum : pageNum.value}).then((res) => {
    pageList.value =res.data.data
    totalNum.value = res.data.total
  })
}
function searchIntegrate(page, id){
  pageType.value = "integrate"
  pageList.value = null
  pageNum.value = page
  if(searchText.value.length === 0 || eduProjectType.value.length === 0){
    return
  }
  if(id === undefined ){
    id = eduProjectType.value
  }
  getIntegratesByCondition({keyword: searchText.value, typeId: id ,
    pageSize: pageSize.value, pageNum : pageNum.value}).then((res) => {
    pageList.value =res.data.data
    totalNum.value = res.data.total
  })
}
function changePage(newPage){
  pageNum.value = newPage
  switch (pageType.value){
    case "news":
      searchNews(newPage);
      break;
    case "article":
      searchArticle(newPage);
      break;
    case "recruit":
      searchRecruit(newPage);
      break;
    case "integrate":
      searchIntegrate(newPage);
      break;
  }
}
function toArticle(id, type){
  router.push({ path: '/u/article', query: { eduId: id, type: type } });
}
function toRecruit(id, type){
  router.push({ path: '/u/recruit', query: { eduId: id} });
}
function toIntegrate(id, type){
  router.push({ path: '/u/integrate', query: { eduId: id } });
}
function searchInformation(){
  pageNum.value = 1
  switch (pageType.value){
    case "news":
      searchNews(1);
      break;
    case "article":
      searchArticle(1);
      break;
    case "recruit":
      searchRecruit(1);
      break;
    case "integrate":
      searchIntegrate(1);
      break;
  }
}
function getJobType() {
  listAllType().then((res) => {
    jobTypeList.value = res.data
  })
}

function getAdvertise(){
  getAdvertiseOfSearch().then((res) => {
    advertiseList.value = res.data
  })
}
function toTarget(link){
  if (!/^https?:\/\//i.test(link)) {
    link = 'http://' + link;
  }
  window.open(link, '_blank');
}
function getAdvertiseImage(link){
  return import.meta.env.VITE_APP_BASE_API + link
}
getAdvertise()
getIntegrateTypeList()
getJobType()
</script>

<template>
  <div style=";height: auto;min-width:1500px;background: linear-gradient(#E0F1F3, #F8F8F8);">
    <top></top>
    <div style="width: 100%;display: flex;justify-content: space-around;;margin: 20px 0;">
      <div style="width: 76%;">
        <div style="display: flex;justify-content: right;min-height: 700px">
          <div style=";width: 88%;background-color: white;margin: 5px;border-radius: 10px;display: flex;justify-content: center">
            <div style="margin-top: 20px;width: 95%;flex-direction: column;padding: 5px 0;position: relative;">
              <el-input size="large" class="searchPage-input" :placeholder="searchText" v-model="searchText">
                <template v-if="pageType === 'integrate'" #prepend>
                  <el-select
                      v-model="eduProjectType"
                      placeholder="项目类型"
                      style="width: 240px;border: none"
                  >
                    <el-option
                        v-for="item in integrateTypeList"
                        :key="item.eduId"
                        :label="item.eduName"
                        :value="item.eduId"
                    />
                  </el-select>
                </template>
                <template v-if="pageType === 'recruit'" #prepend>
                  <el-cascader
                      size="default"
                      placeholder="全部职位"
                      :show-all-levels="false"
                      v-model="eduJobTypeId"
                      :options="jobTypeList">
                  </el-cascader>
                </template>
                <template #append>
                  <el-button :icon="Search" style="color:#1ab394" @click="searchInformation()">搜索</el-button>
                </template>
              </el-input>
              <div style="margin: 5px 0 20px 0">
                <el-menu
                    class="search-menu"
                    mode="horizontal"
                    :default-active=menuIdx
                >
                  <el-menu-item :index=1 style="font-size: 18px" @click="searchNews(1)">新闻</el-menu-item>
                  <el-menu-item :index=2 style="font-size: 18px" @click="searchArticle(1)">动态</el-menu-item>
                  <el-menu-item :index=3 style="font-size: 18px" @click="searchRecruit(1)">招聘</el-menu-item>
                  <el-menu-item :index=4 style="font-size: 18px" @click="searchIntegrate(1)">产教融合</el-menu-item>
                </el-menu>
              </div>
<!--              新闻，动态-->
              <div v-if="pageType === 'news' || pageType === 'article'" v-for="(item, idx) in pageList" style="display: flex;margin-top: 20px">
                <div style="height: 50px;width: 100%;;border-radius: 10px;">
                  <div>
                    <div style="display: flex;justify-content: space-between;" @click="toArticle(item.eduId, pageType)">
                      <el-text size="large" style="font-weight: bolder">{{item.eduTitle}}</el-text>
                      <el-tag>{{item.eduPublishTime}}</el-tag>
                    </div>
                    <div v-if="idx !== pageList.length-1">
                      <el-divider></el-divider>
                    </div>
                  </div>
                </div>
              </div>
<!--              招聘-->
              <div v-if="pageType === 'recruit'" v-for="(item, idx) in pageList" style="display: flex;  ">
                <div style="height: auto;width: 100%;;border-radius: 10px;">
                  <div>
                    <div style="display: flex;justify-content: space-between" @click="toRecruit(item.eduId, pageType)">
                      <div style="width: 50%">
                        <div style="width: 400px;">
                          <el-text size="large" style="font-weight: bolder">{{item.eduJobName}}</el-text>
                          <el-text size="large" style="margin-left: 30px;font-weight: bolder;font-size: large;color: red">{{item.eduSalary}}</el-text>
                        </div>
                        <div style="margin-top: 10px">
                          <el-text size="small">{{item.eduName}}</el-text>
                          <el-divider direction="vertical" />
                          <el-text size="small">{{item.expName}}</el-text>
                          <el-divider direction="vertical" />
                          <el-text size="small">{{item.jobTypeName}}</el-text>
                          <el-divider direction="vertical" />
                          <el-text size="small">{{ item.cityName ? item.cityName.split(",")[1] : '' }}</el-text>
                        </div>
                      </div>
                      <div style="width: 50%;;display: flex;justify-content: center;align-items: center">
                        <div style="display: flex;flex-direction: column">
                          <div>
                            <el-text size="large" style="font-weight: bolder;font-size: large">{{item.companyName}}</el-text>
                          </div>
                          <div style="margin-top: 10px">
                            <el-tag size="small">{{item.industryName}}</el-tag>
                          </div>
                        </div>
                      </div>
                    </div>
                    <div v-if="idx !== pageList.length-1">
                      <el-divider></el-divider>
                    </div>
                  </div>
                </div>
              </div>
<!--              融合-->
              <div v-if="pageType === 'integrate'" v-for="(item, idx) in pageList" style="display: flex;margin-top: 20px">
                <div style="height: auto;width: 100%;;border-radius: 10px;" @click="toIntegrate(item.eduId, pageType)">
                  <div style="display: flex;justify-content: space-between">
                    <div>
                      <el-tag>{{item.eduProjectTypeName}}</el-tag>
                      <el-text style="margin-left: 5px">{{item.eduProjectName}}</el-text>
                    </div>
                    <div>
                      <el-text size="large" style="color: grey">{{item.eduPublisher}}</el-text>
                    </div>
                  </div>
                  <div style="margin-top: 5px">
                    <el-text size="small" style="color: #606266">{{item.eduProjectField}}</el-text>
                    <el-divider direction="vertical"></el-divider>
                    <el-text size="small" style="color: #606266">{{item.eduStartTime}} - {{item.eduEndTime}}</el-text>
                  </div>
                  <div v-if="idx !== pageList.length-1">
                    <el-divider></el-divider>
                  </div>
                </div>
              </div>
              <el-pagination background layout="prev, pager, next"
                             :current-page="pageNum"
                             :page-size="pageSize"
                             :total="totalNum"
                             style="display: flex;margin: 10px 0;position: absolute; bottom: 0;left: 50%; transform: translateX(-50%);align-items: center;justify-content: center;"
                             @current-change="changePage"/>
            </div>
          </div>
        </div>
      </div>
      <div style="flex-direction: column;width: 24%;margin: 5px 0 0 2%;">
        <img :src="getAdvertiseImage(advertiseList[0].eduLink)" class="search_advertise" @click="toTarget(advertiseList[0].eduTarget)"/>
        <img :src="getAdvertiseImage(advertiseList[1].eduLink)" class="search_advertise" @click="toTarget(advertiseList[1].eduTarget)" style="margin-top: 20px"/>
      </div>
    </div>
    <bottom></bottom>
  </div>
</template>

<style scoped>
.searchPage-input{
  & :deep(.el-input-group__append) {
    background-color: #F8F8F8;
    box-shadow: 0 -1px 0 0 #F8F8F8,
    1px 0 0 0 #F8F8F8,
    0 1px 0 0 #F8F8F8;
  }
  & :deep(.el-input-group__prepend) {
    background-color: #F8F8F8;
    box-shadow: 0 -1px 0 0 #F8F8F8,
    1px 0 0 0 #F8F8F8,
    0 1px 0 0 #F8F8F8;
  }
  & :deep(.el-input__wrapper) {
    background-color: #F8F8F8;
    box-shadow: 0 -1px 0 0 #F8F8F8,
    -1px 0 0 0 #F8F8F8,
    0 1px 0 0 #F8F8F8;
  }

}
.search-menu {

}
.el-select {
  --el-select-input-focus-border-color: none
}
.search_advertise{
  width: 200px;
  height: 300px;
}
</style>
