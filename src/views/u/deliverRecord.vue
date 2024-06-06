<script setup>

import Bottom from "@/components/bottom/bottom.vue";
import top from "@/components/header/header.vue"
import {ref} from "vue";
import useUserStore from "@/store/modules/user.js";
import {getRecordsByUser} from "@/api/service/record.js";
const pageNum = ref(1)
const pageSize = ref(10)
const totalNum = ref(1)
const records = ref([])
function changePage(newPage){
  pageNum.value = newPage
  getRecordList()
}

function getRecordList(){
  getRecordsByUser({id: useUserStore().id,
    pageSize: pageSize.value, pageNum : pageNum.value}).then((res) => {
      records.value = res.data.data
      totalNum.value = res.data.total
  })
}
getRecordList()
</script>

<template>
  <div style=";min-width:1500px;background: linear-gradient(#E0F1F3, #F8F8F8);">
    <top></top>
    <div style="margin: 20px 20%;">
      <div style="margin: 20px">
        <div style="height: 750px;background-color: white;border-radius: 15px;padding: 40px;
        display: flex;flex-direction: column;position: relative">
          <div v-for="(item, idx) in records" style="width: 100%">
            <div style="display: flex;justify-content: space-between">
              <div>
                <div>
                  <el-text size="large">{{item.eduRecruitName}}</el-text>
                  <el-text size="large" style="color: red;font-weight: bolder;font-size: 18px;margin-left: 10px">{{item.job.eduSalary}}</el-text>
                </div>
                <div style="margin-top: 20px">
                  <el-tag>{{item.job.expName}}</el-tag>
                  <el-tag style="margin-left: 10px">{{item.job.jobTypeName}}</el-tag>
                  <el-tag style="margin-left: 10px">{{item.job.industryName}}</el-tag>
                </div>
              </div>
              <div style="display: flex;justify-content: left;flex-direction: column">
                <div>
                  <el-text size="large" style="font-size: 19px">{{item.eduCompanyName}}</el-text>
                </div>
                <div style="margin-top: 10px">
                  <el-text size="small" style="color: grey">投递时间：{{item.eduDeliveryTime}}</el-text>
                </div>
              </div>
            </div>
            <div v-if="idx !== records.length-1">
              <el-divider></el-divider>
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
    <bottom></bottom>
  </div>
</template>

<style scoped lang="scss">

</style>
