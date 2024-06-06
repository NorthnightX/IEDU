<script setup>
import {onMounted} from "vue";
import router from "@/router/index.js";
import {getCompanyDetail} from "@/api/service/company.js";
import top from "@/components/header/header.vue"
import Bottom from "@/components/bottom/bottom.vue";
const id = ref()
const detail = ref({
  eduCompanyPic:{
    eduLogo: ""
  },
  companyAddressVO:{
    eduDetailedAddress: null
  },
  eduCompanyInformation:{
    eduLegalRepresentative: null,
    eduEnterpriseType: null,
    eduRegisteredCapital: null,
    eduSocialCreditCode: null,
    eduOperatingPeriod: null,
    eduArea: null,
    eduOperatingStatus: null,
    eduRegistrationAuthority: null,
    eduRegisteredAddress: null,
    eduEstablishmentDate: null,
    eduBusinessScope: null,
  }
})
onMounted(() => {
  var eduId = router.currentRoute.value.query.eduId;
  id.value = eduId
  getDetail()
})
function getDetail(){
  getCompanyDetail(id.value).then((res) => {
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
        <el-avatar :src="getImage(detail.eduCompanyPic.eduLogo)" :size="100"/>
        <div style="display: flex;flex-direction: column;margin-left: 20px">
          <div>
            <h2 style="color: white">{{detail.eduCompanyName}}</h2>
          </div>
          <div>
            <el-tag>{{detail.eduFinancingStageName}}</el-tag>
            <el-tag style="margin-left: 10px">{{detail.eduCompanyScaleName}}</el-tag>
            <el-tag style="margin-left: 10px">{{detail.eduCompanyIndustryName}}</el-tag>
          </div>
        </div>
      </div>
      <div style="padding: 20px;width: 50%;height: 20%;display: flex;align-items: center">
        <el-text style="margin-left: 10px;color: silver">{{detail.companyAddressVO.eduDetailedAddress}}</el-text>
      </div>
    </div>
  </div>
  <div style=";background: linear-gradient(#E0F1F3, #F8F8F8)">
    <div style="margin: 0 7%;padding: 20px;display: flex;justify-content: space-between;min-height: 400px;">
      <div style="background-color: white;border-radius: 10px;margin-top: 10px;padding: 20px;width: 70%">
        <div>
          <el-text style="font-size: large;font-weight: bold">简介</el-text>
        </div>
        <div style="margin-top: 10px">
          <el-text>{{detail.eduCompanyIntroduce}}</el-text>
        </div>
      </div>
      <div style="background-color: white;border-radius: 10px;margin-top: 10px;padding: 20px;width: 25%">
        <div>
          <el-text style="font-size: large;font-weight: bold">工商信息</el-text>
        </div>
        <div style="margin-top: 10px">
<br/>
          <el-text size="small">法定代表人</el-text><br/>
          <el-text>{{detail.eduCompanyInformation.eduLegalRepresentative}}</el-text><br/><br/>
          <el-text size="small">企业类型</el-text><br/>
          <el-text>{{detail.eduCompanyInformation.eduEnterpriseType}}</el-text><br/><br/>
          <el-text size="small">注册资本</el-text><br/>
          <el-text>{{detail.eduCompanyInformation.eduRegisteredCapital}}</el-text><br/><br/>
          <el-text size="small">社会统一信用码</el-text><br/>
          <el-text>{{detail.eduCompanyInformation.eduSocialCreditCode}}</el-text><br/><br/>
          <el-text size="small">经营期限</el-text><br/>
          <el-text>{{detail.eduCompanyInformation.eduOperatingPeriod}}</el-text><br/><br/>
          <el-text size="small">所属地区</el-text><br/>
          <el-text>{{detail.eduCompanyInformation.eduArea}}</el-text><br/><br/>
          <el-text size="small">经营状态</el-text><br/>
          <el-text >{{detail.eduCompanyInformation.eduOperatingStatus}}</el-text><br/><br/>
          <el-text size="small">登记机关</el-text><br/>
          <el-text >{{detail.eduCompanyInformation.eduRegistrationAuthority}}</el-text><br/><br/>
          <el-text size="small">注册地址</el-text><br/>
          <el-text >{{detail.eduCompanyInformation.eduRegisteredAddress}}</el-text><br/><br/>
          <el-text size="small">经营范围</el-text><br/>
          <el-text>{{detail.eduCompanyInformation.eduBusinessScope}}</el-text><br/><br/>

        </div>
      </div>
    </div>
  </div>
  <bottom></bottom>
</template>

<style scoped lang="scss">

</style>
