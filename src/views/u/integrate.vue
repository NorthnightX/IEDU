<script setup>
import {getRecruitDetails} from "@/api/service/recruit.js";
import {onMounted, ref} from "vue";
import router from "@/router/index.js";
import {getIntegrateDetails} from "@/api/service/integrate.js";
import top from  "../../components/header/header.vue";
import Bottom from "@/components/bottom/bottom.vue";
const detail = ref("")
function getIntegrate(eduId){
  getIntegrateDetails(eduId).then((res) => {
    detail.value = res.data
  })
}

onMounted(() => {
  var eduId = router.currentRoute.value.query.eduId;
  getIntegrate(eduId)
})
</script>

<template>
<div style=";height: auto;min-width:1500px;background: linear-gradient(#E0F1F3, #F8F8F8);">
  <top></top>
  <div style="margin: 20px 7%;">
    <el-card style="background-color: white;min-height: 250px;margin: 0 20px;padding: 0 20px
    ;display: flex;justify-content: left;align-items: center">
      <div>
        <div>
          <el-text size="large" style="font-weight: bolder;font-size: 19px">{{detail.eduProjectName}}</el-text>
        </div>
        <div style="margin-top: 20px">
          <el-tag>项目类型：{{detail.eduProjectTypeName}}</el-tag>
          <el-tag style="margin-left: 10px">技术领域：{{detail.eduProjectField}}</el-tag>
          <el-tag style="margin-left: 10px">开始时间：{{detail.eduStartTime}}</el-tag>
          <el-tag style="margin-left: 10px">结束时间：{{detail.eduEndTime}}</el-tag>
        </div>
        <div style="margin-top: 20px">
          <el-text size="large" style="color: grey">发布方：{{detail.eduPublisher}}</el-text>
          <el-text size="large" style="margin-left: 20px;color: grey">官方网站：{{detail.eduOfficialWebsite}}</el-text>
        </div>
        <div style="margin-top: 20px">
          <el-text size="large" style="color: grey">联系电话：{{detail.eduContact}}</el-text>
        </div>
      </div>
    </el-card>
    <el-card style="background-color: white; border-radius: 15px;min-height: 500px;margin: 20px;padding: 20px 20px;
      display: flex;justify-content: left;">
      <div>
        <el-text size="large" style="font-weight: bolder">项目简介：</el-text>
      </div>
      <div style="margin-top: 20px">
        <el-text>
          {{detail.eduProjectDescription}}
        </el-text>
      </div>
    </el-card>
  </div>
  <bottom></bottom>
</div>
</template>

<style scoped lang="scss">

</style>
