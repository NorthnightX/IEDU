<script setup>
import top from  '@/components/header/header.vue'
import bottom from '@/components/bottom/bottom.vue'
import {onMounted} from "vue";
import router from "@/router/index.js";
import {useRoute} from "vue-router";
import {Search} from "@element-plus/icons-vue";
import {getNewsPageBySearchText} from "@/api/service/news.js";
import {getArticlePageBySearchText} from "@/api/service/article.js";
import {getRecruitByKeyWord} from "@/api/service/recruit.js";
import {getIntegrateByKeyWord} from "@/api/service/integrate.js";
const searchText = ref("")
const pageNum = ref(1)
const pageSize = ref(10)
const totalNum = ref(1)
const route = useRoute();
const pageList = ref([])
const pageType = ref("")
onMounted(() => {
  searchText.value = router.currentRoute.value.query.text;
  searchNews()
})
watch(() => route.query.text, (newValue, oldValue) => {
  searchText.value = newValue
});

function searchNews(isFirst){
  pageType.value = "news"
  ResetPage(isFirst)
  if(searchText.value.length === 0){
    return
  }
  getNewsPageBySearchText({text: searchText.value, pageSize: pageSize.value, pageNum : pageNum.value}).then((res) => {
    pageList.value = res.data.data
    totalNum.value = res.data.total
  })
}

function searchArticle(isFirst){
  pageType.value = "article"
  ResetPage(isFirst)
  getArticlePageBySearchText({text: searchText.value, pageSize: pageSize.value, pageNum : pageNum.value}).then((res) => {
    pageList.value = res.data.data
    totalNum.value = res.data.total
  })
}
function searchRecruit(isFirst){
  pageType.value = "recruit"
  ResetPage(isFirst)
  getRecruitByKeyWord({text: searchText.value, pageSize: pageSize.value, pageNum : pageNum.value}).then((res) => {
    pageList.value =res.data.data
    totalNum.value = res.data.total
  })
}
function searchIntegrate(isFirst){
  pageType.value = "integrate"
  ResetPage(isFirst)
  getIntegrateByKeyWord({text: searchText.value, pageSize: pageSize.value, pageNum : pageNum.value}).then((res) => {
    pageList.value =res.data.data
    totalNum.value = res.data.total
  })
}
function ResetPage(isFirst){
  if (isFirst) {
    pageNum.value = 1
  }
}
function changePage(newPage){
  pageNum.value = newPage
  switch (pageType.value){
    case "news":
      searchNews(false);
      break;
    case "article":
      searchArticle(false);
      break;
    case "recruit":
      searchRecruit(false);
      break;
    case "integrate":
      searchIntegrate(false);
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
      searchNews(false);
      break;
    case "article":
      searchArticle(false);
      break;
    case "recruit":
      searchRecruit(false);
      break;
    case "integrate":
      searchIntegrate(false);
      break;
  }
}
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
                <template #append>
                  <el-button :icon="Search" style="color:#1ab394" @click="searchInformation()">搜索</el-button>
                </template>
              </el-input>
              <div style="margin: 5px 0 20px 0">
                <el-menu
                    class="search-menu"
                    mode="horizontal"
                    default-active="1"
                >
                  <el-menu-item index="1" style="font-size: 18px" @click="searchNews(true)">新闻</el-menu-item>
                  <el-menu-item index="2" style="font-size: 18px" @click="searchArticle(true)">动态</el-menu-item>
                  <el-menu-item index="3" style="font-size: 18px" @click="searchRecruit(true)">招聘</el-menu-item>
                  <el-menu-item index="4" style="font-size: 18px" @click="searchIntegrate(true)">产教融合</el-menu-item>
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
                      <el-text>{{item.eduProjectName}}</el-text>
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
        <div style="height: 300px;width: 70%;border-radius: 10px;background-color: white">
          <div>
            <img src="@/assets/background/search_right1.png" alt="" style="width: 100%">
          </div>
        </div>
        <div style="height: 300px;width: 70%;border-radius: 10px;background-color: white;margin-top: 20px">
          <div>
            <img src="@/assets/background/search_right2.png" alt="" style="width: 100%">
          </div>
        </div>
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


  & :deep(.el-input__wrapper) {
    background-color: #F8F8F8;
    box-shadow: 0 -1px 0 0 #F8F8F8,
    -1px 0 0 0 #F8F8F8,
    0 1px 0 0 #F8F8F8;
  }

}
.search-menu {

}
</style>
