<script setup>
import top from "@/components/header/header.vue"
import Bottom from "@/components/bottom/bottom.vue";
import {getRecruitDetails} from "@/api/service/recruit.js";
const { proxy } = getCurrentInstance();
import {onMounted, ref} from "vue";
import router from "@/router/index.js";
import FileUpload from "@/components/FileUpload/index.vue";
import useUserStore from "@/store/modules/user.js";
import {ElMessage} from "element-plus";
import {addRecord} from "@/api/service/record.js";
const detail = ref("")
const form = ref({
  eduRecruitId: "",
  eduUserId: "",
  eduDelivererSex: null,
  eduResumeLink: ""
})
const rules = ref({
  eduDelivererName: [
    { required: true, message: "投递者姓名不能为空", trigger: "blur" }
  ],
  eduDelivererAge: [
    { required: true, message: "投递者年龄不能为空", trigger: "blur" }
  ],
  eduDelivererSex: [
    { required: true, message: "投递者性别不能为空", trigger: "change" }
  ],
  eduDelivererIdCard: [
    { required: true, message: "投递者身份证号不能为空", trigger: "blur" }
  ],
  eduDelivererMail: [
    { required: true, message: "投递者邮箱不能为空", trigger: "blur" }
  ],
  eduDelivererPhone: [
    { required: true, message: "投递者手机号不能为空", trigger: "blur" }
  ]
})

function getRecruit(eduId){
  getRecruitDetails(eduId).then((res) => {
    detail.value = res.data
  })
}
onMounted(() => {
  var eduId = router.currentRoute.value.query.recruit;
  form.value.eduRecruitId = eduId
  form.value.eduUserId = useUserStore().id
  getRecruit(eduId)
})
function submitResume() {
  if(form.value.eduResumeLink === "") {
    ElMessage({
      message: '请上传简历',
      type: 'warning'
    });
    return
  }
  proxy.$refs["resumeRef"].validate((valid) => {
    if (valid) {
      addRecord(form.value).then((res) => {
        ElMessage({
          message: res.msg,
          type: 'success'
        });
      })
    } else {
      ElMessage({
        message: '请填写个人信息',
        type: 'warning'
      });
    }
  });
}
</script>

<template>
  <top></top>
  <div style="display: flex;justify-content: center;align-items: center;flex-direction: column;min-width: 1500px">
    <div style="width: 100%;height: 130px;background-color: #FAFAFA;display: flex;justify-content: center;align-items: center;flex-direction: column">
      <div style="width: 50%;display: flex">
        <el-text size="large" style="font-size: 20px;color: black">你正在投递{{detail.companyName}}的{{detail.eduJobName}}岗位</el-text>
      </div>
      <div style="width: 50%;display: flex">
        <el-text size="large" style="color: grey;margin-top: 20px">祝您应聘成功</el-text>
      </div>
    </div>
    <div style="width: 100%;height: auto;margin: 30px 0;background-color: white;display: flex;align-items: center;flex-direction: column">
      <div style="width: 50%;">
        <div style="display: flex">
          <div style="width: 8px;height: 20px;background-color: #1ab394">
          </div>
          <div>
            <el-text size="large" style="margin-left: 10px">上传简历</el-text>
          </div>
        </div>
        <div style="margin-top: 20px;height: 80px">
          <file-upload limit="1" v-model="form.eduResumeLink"></file-upload>
        </div>
      </div>
      <div style="width: 50%;margin-top: 30px">
        <div style="display: flex">
          <div style="width: 8px;height: 20px;background-color: #1ab394">
          </div>
          <div>
            <el-text size="large" style="margin-left: 10px">基本信息</el-text>
          </div>
        </div>
        <div style="margin-top: 20px">
          <div>
            <el-form :model="form" label-width="auto" style="max-width: 600px" label-position="left" :rules="rules" ref="resumeRef">
              <el-form-item label="姓名" prop="eduDelivererName">
                <el-input v-model="form.eduDelivererName" />
              </el-form-item>
              <el-form-item label="手机号" prop="eduDelivererPhone">
                <el-input v-model="form.eduDelivererPhone" />
              </el-form-item>
              <el-form-item label="邮箱" prop="eduDelivererMail">
                <el-input v-model="form.eduDelivererMail" />
              </el-form-item>
              <el-form-item label="身份证号" prop="eduDelivererIdCard">
                <el-input v-model="form.eduDelivererIdCard" />
              </el-form-item>
              <el-form-item label="性别" prop="eduDelivererSex">
                <el-radio-group v-model="form.eduDelivererSex">
                  <el-radio value=0 label="女"></el-radio>
                  <el-radio value=1 label="男"></el-radio>
                </el-radio-group>
              </el-form-item>
              <el-form-item label="年龄" prop="eduDelivererAge">
                <el-input-number v-model="form.eduDelivererAge" :min="18" :max="50" />
              </el-form-item>
            </el-form>
          </div>
          <div style="width: 100%;display: flex;justify-content: right">
            <el-button type="success" style="width: 200px" @click="submitResume()">投递</el-button>
          </div>
        </div>
      </div>
    </div>
  </div>
  <bottom></bottom>
</template>

<style scoped lang="scss">

</style>
