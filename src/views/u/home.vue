<script setup>
import top from '@/components/header/header.vue'
import bottom from '@/components/bottom/bottom.vue'
import {getCurrentNews} from "@/api/service/news.js";
import {ref} from "vue";
import router from "@/router/index.js";
import {getRecruitToExhibit} from "@/api/service/recruit.js";
import {listAllType} from "@/api/service/type.js";
import {getDistrictList} from "@/api/service/provinces.js";
import {Search} from "@element-plus/icons-vue";
import {getCurrentArticle} from "@/api/service/article.js";

const newsList = ref([])
const articleList = ref([])
const searchForm = ref({
  eduCityId:"",
  eduJobTypeId:"",

})
const cityList = ref([])
const typeList = ref([])
const jobList = ref([])
function getNews() {
  getCurrentNews().then((res) => {
    newsList.value = res.data
  })
}
function getArticle(){
  getCurrentArticle().then((res) => {
    articleList.value = res.data
  })
}
function getCity() {
  getDistrictList().then((res) => {
    cityList.value = res.data
  })
}
function getJobType() {
  listAllType().then((res) => {
    typeList.value = res.data
  })
}
function getJob(){
  getRecruitToExhibit().then((res) => {
    jobList.value = res.data
  })
}

function toArticle(id, type) {
  router.push({ path: '/u/article', query: { eduId: id, type: type } });
}
function toRecruit(id){
  router.push({ path: '/u/recruit', query: { eduId: id } });
}
getNews();
getJob();
getCity();
getJobType();
getArticle();
</script>

<template>
  <div style=";height: auto;min-width:1500px;background: linear-gradient(#E0F1F3, #F8F8F8);">
    <top></top>
    <div style="margin: 20px 7%;">
      <!--    轮播图   -->
      <el-carousel indicator-position="outside" height="350px" style="margin: 20px 20px">
        <el-carousel-item v-for="item in 4" :key="item">
          <h3>这是轮播图</h3>
        </el-carousel-item>
      </el-carousel>
      <!--       新闻-->
      <el-row style="margin: 20px 20px;display: flex;justify-content: space-between">
        <div style="display: flex;align-items: center;width: 49%;justify-content: center">
          <el-card class="news" shadow="hover"
               style="height: 400px;width: 100%;
               padding: 20px 0 20px 0;
               background-color: white;
               border-radius: 15px;">
            <el-table :show-header="false" :cell-style="{}" :data="newsList"
                      style="--el-table-border-color: none;" size="large">
              <el-table-column prop="eduPublishTime" width="140px" align="center">
                <template #default="{ row }">
                  <el-tag style="height: 27px" round effect="light">{{
                      parseTime(row.eduPublishTime, '{y}-{m}-{d}')
                    }}
                  </el-tag>
                </template>
              </el-table-column>
              <el-table-column prop="eduTitle" align="left" @click="toArticle()">
                <template #default="{ row }">
                  <a @click="toArticle(row.eduId, 'news')"
                     style="display: -webkit-box;text-overflow: ellipsis;
                      overflow: hidden;-webkit-line-clamp: 1;-webkit-box-orient: vertical;
                      white-space: pre-line;">
                    {{ row.eduTitle }}
                  </a>
                </template>
              </el-table-column>
            </el-table>
          </el-card>
        </div>
<!--        动态文章-->
        <div style="display: flex;align-items: center;width: 49%;justify-content: center">
          <el-card class="news" shadow="hover"
               style="height: 400px;width: 100%;padding: 20px 0 20px 0;
               background-color: white;border-radius: 15px;">
            <el-table :show-header="false" :cell-style="{}" :data="articleList"
                      style="--el-table-border-color: none;" size="large">
              <el-table-column prop="eduPublishTime" width="140px" align="center">
                <template #default="{ row }">
                  <el-tag style="height: 27px" round effect="light">{{
                      parseTime(row.eduPublishTime, '{y}-{m}-{d}')
                    }}
                  </el-tag>
                </template>
              </el-table-column>
              <el-table-column prop="eduTitle" align="left">
                <template #default="{ row }">
                  <a style="display: -webkit-box;text-overflow: ellipsis;
                      overflow: hidden;-webkit-line-clamp: 1;-webkit-box-orient: vertical;
                      white-space: pre-line;" @click="toArticle(row.eduId, 'article')">
                    {{ row.eduTitle }}
                  </a>
                </template>
              </el-table-column>
            </el-table>
          </el-card>
        </div>
      </el-row>
      <!--        招聘信息-->
      <div style="background-color: white;border-radius: 15px;margin: 20px 20px">
        <h3 style="padding: 20px 0 0 20px;font-weight: bold">在招岗位</h3>
        <el-row style="padding: 0 0 0 20px">
          <div>
            <el-cascader
                size="default"
                placeholder="全部职位"
                :show-all-levels="false"
                v-model="searchForm.eduJobTypeId"
                :options="typeList">
            </el-cascader>
          </div>
          <div style="margin-left: 10px">
            <el-cascader
                size="default"
                placeholder="全部地区"
                :show-all-levels="false"
                v-model="searchForm.eduCityId"
                :options="cityList">
            </el-cascader>
          </div>
          <div style="margin-left: 10px">
            <el-input
                v-model="input1"
                size="default"
                class="recruit_input"
                placeholder="请输入公司或职位名"
            >
              <template #append>
                <el-button :icon="Search"/>
              </template>
            </el-input>
          </div>
        </el-row>
        <!-- 分割线-->
        <el-row style="width: 100%;padding: 20px 20px 0 20px">
          <el-col style="background-color: whitesmoke;height: 2px">
          </el-col>
        </el-row>
        <div style="display: flex;flex-wrap: wrap;align-items: center;justify-content: center">
          <el-card class="box-card" shadow="hover"
                   v-for="item in jobList"
                   @click="toRecruit(item.eduId)"
                   style="flex: 1 0 calc(20% - 40px);
                   max-width: calc(20% - 40px);background-color: #F8F8F8;
                   border-radius: 10px;
                   min-width: calc(20% - 40px); height: 100px;margin: 20px">
            <div slot="header" class="clearfix">
              <span>{{ item.eduJobName }}</span>
              <span style="float: right; padding: 3px 0;color: red">{{ item.eduSalary }}</span>
            </div>
            <div style="display: flex;align-items: center;justify-content: left">
              <span style="font-size: 13px;color: silver">{{item.cityName.split(",")[1]}}</span>
              <el-divider direction="vertical"></el-divider>
              <span style="font-size: 13px;color: silver">{{item.eduName}}</span>
            </div>
            <div style="display: flex;align-items: center;justify-content: space-between;margin-top: 10px">
              <span style="font-size: 13px;">{{item.companyName}}</span>
              <span style="font-size: 13px;">{{item.industryName}}</span>
            </div>
          </el-card>
        </div>
      </div>
      <!--        合作资源-->
      <div style="margin: 20px 20px;height: 400px;background-color: white;border-radius: 15px">
        这是合作资源
      </div>
    </div>
    <bottom></bottom>
  </div>
</template>


<style scoped>
.el-table-column__body {
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

/*轮播图*/
.el-carousel__item h3 {
  display: flex;
  color: red;
  opacity: 0.75;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}
.el-table {
  --el-table-row-hover-bg-color: white;
}
.recruit_input {
  & :deep(.el-input-group__append) {
    background-color: white;
    box-shadow: 0 -1px 0 0 #E2E4E7,
    1px 0 0 0 #E2E4E7,
    0 1px 0 0 #E2E4E7;
  }

  & :deep(.el-input__wrapper) {
    box-shadow: 0 -1px 0 0 #E2E4E7,
    -1px 0 0 0 #E2E4E7,
    1px 0 0 0 #E2E4E7,
    0 1px 0 0 #E2E4E7;
  }
}

</style>
