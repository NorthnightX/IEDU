<script setup>
import top from "../../components/header/header.vue"
import Bottom from "@/components/bottom/bottom.vue";
import {ref} from "vue";
import useUserStore from "@/store/modules/user.js";
import {updateUserProfile, updateUserPwd, uploadAvatar} from "@/api/system/user.js";
import UserAvatar from "@/views/system/user/profile/userAvatar.vue";
import {EditPen} from "@element-plus/icons-vue";
import {ElMessage} from "element-plus";
const info = ref("")
const userStore = useUserStore();
const newPassword = ref("")
const oldPassword = ref("")
const userForm = ref({
  userId: "",
  nickName: '',
  email: '',
  phonenumber: '',
})
function getInfo() {
  userStore.getInfo().then((res) => {
    const user = res.user;
    info.value = user;
    userForm.value = user
  })
}
function updateProfile() {
  updateUserProfile(userForm.value).then((res) => {
    ElMessage({
      message: res.msg,
      type: 'success'
    });
  })
}

function updatePassword(){
  if(oldPassword === null || newPassword === null || oldPassword.value.length === 0 || newPassword.value.length === 0){
    ElMessage({
      message: '请输入完整',
      type: 'warning'
    });
    return
  }
  updateUserPwd(oldPassword.value, newPassword.value).then((res) => {
    ElMessage({
      message: res.msg,
      type: 'success'
    });
  })
}
function disposeAccount(){
  ElMessage({
    message: '注销账号请联系服务热线：110',
    type: 'warning'
  });
}


getInfo()
</script>

<template>
  <div style=";min-width:1500px;background: linear-gradient(#E0F1F3, #F8F8F8)">
    <top></top>
    <div style="margin: 20px 7%;">
      <div style="margin: 20px;background-color: white;border-radius: 15px;padding: 40px 40px 10px 40px">
        <div>
          <div style="display: flex">
            <div style="width: 8px;height: 20px;background-color: #1ab394"></div>
            <div>
              &nbsp;&nbsp;&nbsp;&nbsp;基本信息
            </div>
          </div>
          <div style="height: auto;margin: 20px 0">
            <div>
              <div style="width: 50%;display: flex;justify-content: space-between">
                <div style="width: 50%;display: flex;;justify-content: left">
                  <el-text>账号：{{ info.userName }}</el-text>
                </div>
                <div style="width: 50%;display: flex;;justify-content: left">
                  <el-text>昵称：{{ info.nickName }}</el-text>
                </div>
              </div>
              <div style="width: 50%;margin-top: 20px;display: flex;justify-content: space-between">
                <div style="width: 50%;display: flex;;justify-content: left">
                  <el-text>用户类型：{{ info.remark }}</el-text>
                </div>
                <div style="width: 50%;display: flex;;justify-content: left">
                  <el-text>入站时间：{{ info.createTime }}</el-text>
                </div>
              </div>
            </div>
          </div>
          <div style="display: flex">
            <div style="width: 8px;height: 20px;background-color: #1ab394"></div>
            <div>
              &nbsp;&nbsp;&nbsp;&nbsp;账号与安全
            </div>
          </div>
          <div style=";margin: 20px 0">
            <div class="input-container" style="width: 70%">
              <span class="input-label">头像</span>
              <div style="height: 10px"></div>
              <user-avatar style="width: 50px;height: 50px"></user-avatar>
            </div>
            <div class="input-container" style="width: 70%">
              <span class="input-label">昵称</span>
              <div style="height: 10px"></div>
              <el-input class="my_input"
                        style="width: 100%; padding-bottom: 10px;" v-model="userForm.nickName">
                <template #append>
                  <el-button :icon="EditPen" @click="updateProfile()"/>
                </template>
              </el-input>
            </div>
            <div class="input-container" style="width: 70%">
              <span class="input-label">邮箱</span>
              <div style="height: 10px"></div>
              <el-input class="my_input"
                        style="width: 100%; padding-bottom: 10px;" v-model="userForm.email">
                <template #append>
                  <el-button :icon="EditPen" @click="updateProfile()"/>
                </template>
              </el-input>
            </div>
            <div class="input-container" style="width: 70%">
              <span class="input-label">手机号</span>
              <div style="height: 10px"></div>
              <el-input class="my_input"
                        style="width: 100%; padding-bottom: 10px;" v-model="userForm.phonenumber">
                <template #append>
                  <el-button :icon="EditPen" @click="updateProfile()"/>
                </template>
              </el-input>
            </div>
            <div class="input-container" style="width: 70%">
              <span class="input-label">密码</span>
              <div style="height: 10px"></div>
              <el-input class="my_input" type="password" placeholder="旧密码"
                        style="width: 100%; padding-bottom: 10px;margin-top: 10px" v-model="oldPassword">
              </el-input>
              <el-input class="my_input" type="password" placeholder="新密码"
                        style="width: 100%; padding-bottom: 10px;margin-top: 10px" v-model="newPassword">
                <template #append>
                  <el-button :icon="EditPen" @click="updatePassword()"/>
                </template>
              </el-input>
            </div>
          </div>
          <div style="display: flex">
            <div style="width: 8px;height: 20px;background-color: #1ab394"></div>
            <div>
              &nbsp;&nbsp;&nbsp;&nbsp;其他
            </div>
          </div>
          <div style="height: 50px;margin: 20px 0 10px 0;display: flex;">
            <div style="width: 30%;display: flex;justify-content: space-between;">
              <div style="width: 70%;display: flex;;justify-content: space-between;align-items: center">
                <el-text>注销账号</el-text>
                <el-button style="color: #00AEEC;border: none" @click="disposeAccount()">注销</el-button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <bottom></bottom>
  </div>
</template>

<style scoped lang="scss">
.input-container {
  position: relative;
  transition: border 0.3s;
  margin-top: 20px;
}

.input-container:focus-within {
  width: 70%;
  border: 1px solid #1890ff;
  border-radius: 4px;
  padding: 8px;
}

.my_input {
  & :deep(.el-input__wrapper) {
    box-shadow:  0 1px 0 0 #E2E4E7;
    border-radius: 0;
  }
  & :deep(.el-input-group__append) {
    background-color: white;
    border-radius: 0;
    box-shadow: 0 1px 0 0 #E2E4E7;
  }
  & :deep(.el-input-group__prepend) {
    background-color: white;
    border-radius: 0;
    box-shadow: 0 1px 0 0 #E2E4E7;
  }
}

.input-container >>> .el-input__inner {
  width: 70%;
  border-radius: 0;
  border-top-width: 0;
  border-left-width: 0;
  border-right-width: 0;
  border-bottom-width: 1px;
}

.input-label {
  color: dimgrey;
  font-size: 14px;
}
</style>
