<script setup>
import top from '@/components/header/header.vue'
import bottom from '@/components/bottom/bottom.vue'
import {onMounted} from "vue";
import router from "@/router/index.js";
import {Search} from "@element-plus/icons-vue";
import {listUCompany} from "@/api/service/company.js";
import {listUAcademy} from "@/api/service/academy.js";
const active = ref()
const coList = ref([])
onMounted(() => {
  switch (router.currentRoute.value.query.type) {
    case "院校" :
      active.value = 1
      getAcademy()
      break
    case "企业":
      active.value = 2
      getCompany()
      break
  }
})
const total = ref(0);
const cP = ref({
  pageNum: 1,
  pageSize: 10,
  eduCompanyName: null,
})
const aP = ref({
  pageNum: 1,
  pageSize: 10,
  eduAcademyName: null,
})
function getAcademy(){
  listUAcademy(aP.value).then(response => {
    coList.value = response.rows;
    total.value = response.total;
  });
}
function getCompany(){
  listUCompany(cP.value).then(response => {
    coList.value = response.rows;
    total.value = response.total;
  });
}
function toA(){
  active.value = 1
  searchInformation()
}
function toC(){
  active.value = 2
  searchInformation()
}
function searchInformation(){
  switch (active.value) {
    case 1 :
      getAcademy()
      cP.value.pageNum = 1
      cP.value.eduCompanyName = null
      break
    case 2:
      aP.value.pageNum = 1
      aP.value.eduAcademyName = null
      getCompany()
      break
  }
}
function getImage(link){
  return import.meta.env.VITE_APP_BASE_API + link
}
function toAcademy(id){
  router.push({ path: '/u/academy', query: { eduId: id } });
}
function toCompany(id){
  router.push({ path: '/u/company', query: { eduId: id } });
}
</script>

<template>

  <div style=";min-width:1500px;background: linear-gradient(#E0F1F3, #F8F8F8)">
    <top></top>
    <div style="margin: 20px 20%;">
      <div style="margin: 20px;background-color: white;border-radius: 15px;padding: 20px 40px 10px 40px;min-height: 500px;">
        <div style="width: 100%;display: flex;align-items: center;justify-content: center;flex-direction: column;">
          <el-input v-if="active===1" class="cooperate-input" size="large" v-model="aP.eduAcademyName">
            <template #append>
              <el-button :icon="Search" style="color:#1ab394" @click="searchInformation()">搜索</el-button>
            </template>
          </el-input>
          <el-input v-else class="cooperate-input" size="large" v-model="cP.eduCompanyName">
            <template #append>
              <el-button :icon="Search" style="color:#1ab394" @click="searchInformation()">搜索</el-button>
            </template>
          </el-input>
          <el-menu
              style="width: 100%;margin: 5px 0 20px 0"
              :default-active=active
              mode="horizontal"
          >
            <el-menu-item index="1" @click="toA()">院校</el-menu-item>
            <el-menu-item index="2" @click="toC()">企业</el-menu-item>
          </el-menu>
          <div style="display: flex;flex-wrap: wrap;align-items: center;justify-content: center;width: 100%">
            <div class="box-card"
                 @click="toCompany(item.eduId)"
                     v-if="active === 2"
                     v-for="(item,idx) in coList"
                     style="border-radius: 10px;;width: 100%;">
              <div style="display: flex;justify-content: space-between;align-items: center">
                <div>
                  <div>
                    <span style="font-size: larger;color: #1ab394">{{ item.eduCompanyName }}</span>
                  </div>
                  <div style="margin-top: 20px">
                    <el-tag>{{item.eduCompanyIndustryName}}</el-tag>
                    <el-tag style="margin-left: 10px">{{item.eduFinancingStageName}}</el-tag>
                    <el-tag style="margin-left: 10px">{{item.eduCompanyScaleName}}</el-tag>
                  </div>
                </div>
                <div>
                  <el-avatar v-if="item.eduCompanyPic !== null" :src="getImage(item.eduCompanyPic.eduLogo)" style="height: 100px;width: 100px"/>
                </div>
              </div>
              <el-divider v-if="idx !== coList.length-1"></el-divider>
            </div>
            <div class="box-card"
                 v-else
                 @click="toAcademy(item.eduId)"
                 v-for="(item,idx) in coList"
                 style="border-radius: 10px;width: 100%;display: flex;justify-content: space-between;align-items: center">
              <div>
                <div>
                  <span style="font-size: larger;color: #1ab394">{{ item.eduAcademyName }}</span>
                </div>
                <div style="margin-top: 20px">
                  <el-tag>{{item.eduAcademyTag}}</el-tag>
                </div>
              </div>
              <div>
                <el-avatar v-if="item.eduAcademyPic !== null" :src="getImage(item.eduAcademyPic.eduLogo)" style="height: 100px;width: 100px"/>
              </div>
              <el-divider v-if="idx !== coList.length-1"></el-divider>
            </div>
          </div>
          <pagination
              background layout="prev, pager, next"
              v-if="active === 1"
              v-show="total>0"
              :total="total"
              v-model:page="aP.pageNum"
              v-model:limit="aP.pageSize"
              @pagination="getAcademy"
          />
          <pagination
              background layout="prev, pager, next"
              v-else
              v-show="total>0"
              :total="total"
              v-model:page="cP.pageNum"
              v-model:limit="cP.pageSize"
              @pagination="getCompany"
          />
        </div>
      </div>
    </div>
    <bottom></bottom>
  </div>

</template>

<style scoped lang="scss">
.cooperate{
  color: #1ab394;
  font-size: 20px;
  font-weight: bolder;
}
.cooperate-input{
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
</style>
