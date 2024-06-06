<script setup>
import {onMounted, ref} from "vue";
import router from "@/router/index.js";
import {getRecruitDetails} from "@/api/service/recruit.js";
import top from "@/components/header/header.vue"
import Bottom from "@/components/bottom/bottom.vue";
import FileUpload from "@/components/FileUpload/index.vue";
const detail = ref("")
const recruitDialog = ref(false)
const form = ref({})
const recruitId = ref()
function getRecruit(eduId){
  getRecruitDetails(eduId).then((res) => {
    detail.value = res.data
  })
}

onMounted(() => {
  var eduId = router.currentRoute.value.query.eduId;
  recruitId.value = eduId
  getRecruit(eduId)
})

function toResume() {
  router.push({ path: '/u/resume', query: { recruit: recruitId.value } });
}
function submitResume(){

}
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
          <el-button style="background-color: #32CA99;color: white;border-radius: 10px" size="large" @click="toResume()">立即申请</el-button>
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
  <el-dialog
      v-model="recruitDialog"
      title="简历投递"
      width="700"
  >
    <el-form ref="recordRef" :model="form" :rules="rules" label-width="120px">
      <el-form-item label="公司ID" prop="eduCompanyId">
        <el-input v-model="form.eduCompanyId" placeholder="请输入公司ID" />
      </el-form-item>
      <el-form-item label="用户ID" prop="eduUserId">
        <el-input v-model="form.eduUserId" placeholder="请输入用户ID" />
      </el-form-item>
      <el-form-item label="简历链接" prop="eduResumeLink">
        <file-upload v-model="form.eduResumeLink"></file-upload>
      </el-form-item>
      <el-form-item label="投递者姓名" prop="eduDelivererName">
        <el-input v-model="form.eduDelivererName" placeholder="请输入投递者姓名" />
      </el-form-item>
      <el-form-item label="投递者年龄" prop="eduDelivererAge">
        <el-input v-model="form.eduDelivererAge" placeholder="请输入投递者年龄" />
      </el-form-item>
      <el-form-item label="投递者身份证号" prop="eduDelivererIdCard">
        <el-input v-model="form.eduDelivererIdCard" placeholder="请输入投递者身份证号" />
      </el-form-item>
      <el-form-item label="投递者邮箱" prop="eduDelivererMail">
        <el-input v-model="form.eduDelivererMail" placeholder="请输入投递者邮箱" />
      </el-form-item>
      <el-form-item label="投递者手机号" prop="eduDelivererPhone">
        <el-input v-model="form.eduDelivererPhone" placeholder="请输入投递者手机号" />
      </el-form-item>
      <el-form-item label="投递时间" prop="eduDeliveryTime">
        <el-date-picker clearable
                        v-model="form.eduDeliveryTime"
                        type="datetime"
                        value-format="YYYY-MM-DD hh:mm:ss"
                        placeholder="请选择投递时间">
        </el-date-picker>
      </el-form-item>
    </el-form>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="recruitDialog = false">取消</el-button>
        <el-button type="primary" @click="submitResume()">
          确定
        </el-button>
      </div>
    </template>
  </el-dialog>
</template>

<style scoped lang="scss">

</style>
