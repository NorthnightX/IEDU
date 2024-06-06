<script setup>
import {onMounted} from "vue";
import router from "@/router/index.js";
import top from "@/components/header/header.vue"
import Bottom from "@/components/bottom/bottom.vue";
import {getAcademyDetail} from "@/api/service/academy.js";
const detail = ref({
  eduAcademyPic:{
    eduLogo: null
  },
  academyAddressVO:{
    eduDetailedAddress: null
  },
})
const id = ref()
onMounted(() => {
  id.value = router.currentRoute.value.query.eduId
  getDetail()
})
function getDetail(){
  getAcademyDetail(id.value).then((res) => {
    detail.value = res.data
  })
}
function getImage(link){
  return import.meta.env.VITE_APP_BASE_API + link
}
</script>

<template>
  <top></top>
  <div style="background: url('../../../public/cbe691f8d1711cb41f8a.png') 0% 0% / 100% 100% no-repeat;; height: 218px;min-width:1500px;">
    <div style="padding: 20px 7%;width: 100%;height: 80%">
      <div style="padding: 20px;width: 50%;height: 100%;display: flex;align-items: center">
        <el-avatar :src="getImage(detail.eduAcademyPic.eduLogo)" :size="100"/>
        <div style="display: flex;flex-direction: column;margin-left: 20px">
          <div>
            <h2 style="color: white">{{detail.eduAcademyName}}</h2>
          </div>
          <div>
            <el-tag>{{detail.eduAcademyTag}}</el-tag>
          </div>
        </div>
      </div>
      <div style="padding: 20px;width: 50%;height: 20%;display: flex;align-items: center">
        <el-text style="margin-left: 10px;color: silver">{{detail.academyAddressVO.eduDetailedAddress}}</el-text>
      </div>
    </div>
  </div>
  <div style=";background: linear-gradient(#E0F1F3, #F8F8F8)">
    <div style="margin: 0 7%;padding: 20px;display: flex;justify-content: space-between;min-height: 400px;">
      <div style="background-color: white;border-radius: 10px;margin-top: 10px;padding: 20px;width: 100%">
        <div>
          <el-text style="font-size: large;font-weight: bold">简介</el-text>
        </div>
        <div style="margin-top: 10px">
          <el-text>{{detail.eduAcademyIntroduce}}</el-text>
        </div>
      </div>
    </div>
  </div>
  <bottom></bottom>
</template>

<style scoped lang="scss">

</style>
