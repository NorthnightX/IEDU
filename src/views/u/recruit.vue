<script setup>
import {onMounted, ref} from "vue";
import router from "@/router/index.js";
import {getRecruitDetails} from "@/api/service/recruit.js";
import top from "@/components/header/header.vue"
import Bottom from "@/components/bottom/bottom.vue";
const detail = ref("")
function getRecruit(eduId){
  getRecruitDetails(eduId).then((res) => {
    detail.value = res.data
  })
}

onMounted(() => {
  var eduId = router.currentRoute.value.query.eduId;
  getRecruit(eduId)
})

</script>

<template>
  <top></top>
  <div style="display: flex;justify-content: center;align-items: center;flex-direction: column;background: linear-gradient(#E0F1F3, #F8F8F8)">
    <el-card style="height: 200px;width: 80%;background-color: white;border-radius: 10px;margin-top: 20px">
      <div style="margin: 20px 20px 0 20px;display: flex;justify-content: space-between">
        <div>
          <span style="font-size: 20px;font-weight: bolder;color: #1ab394">{{detail.eduJobName}}</span>
          <el-text size="large" style="color: red;margin-left: 20px;font-size: large;font-weight: bold">{{detail.eduSalary}}</el-text>
        </div>
        <div>
          <el-button style="background-color: #32CA99;color: white;border-radius: 10px" size="large">立即申请</el-button>
        </div>
      </div>
      <div style="display: flex;align-items: center;justify-content: left;margin-left: 20px">
        <span style="font-size: 13px;color: silver">  {{ detail.cityName ? detail.cityName.split(",")[1] : '' }}</span>
        <el-divider direction="vertical"></el-divider>
        <span style="font-size: 13px;color: silver">{{detail.eduName}}</span>
      </div>
      <div style="display: flex;align-items: center;justify-content: left;margin-left: 20px;margin-top: 20px">
        <el-tag>{{detail.expName}}</el-tag>
        <el-tag style="margin-left: 10px">{{detail.jobTypeName}}</el-tag>
        <el-tag style="margin-left: 10px">{{detail.eduWelfare}}</el-tag>
      </div>
      <div style="margin: 20px 20px 0 20px;;display: flex;align-items: center;justify-content: space-between;">
        <span>{{detail.companyName}}</span>
        <span style="font-size: 13px">{{detail.industryName}}</span>
      </div>
    </el-card>
    <el-card style="min-height: 500px;width: 80%;background-color: white;border-radius: 15px;margin-top: 20px;margin-bottom: 20px">
      <div style="display: flex;flex-direction: column;margin: 20px">
        <span style="font-size: 18px;font-weight: bolder;margin-bottom: 20px">岗位详情</span>
        <el-tag type="info" style="width: 40px">{{detail.eduIntroduceTag}}</el-tag>
        <div style="margin-top: 20px">
          <el-text style="margin-top: 20px;">{{ detail.eduIntroduce }}</el-text>
        </div>
      </div>
    </el-card>
  </div>
  <bottom></bottom>
</template>

<style scoped lang="scss">

</style>
