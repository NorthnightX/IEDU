<script setup>
import {Search} from '@element-plus/icons-vue'
import {ref} from 'vue'
import useUserStore from '@/store/modules/user'
import {getCodeImg, register} from "@/api/login.js";
import {ElMessage, ElMessageBox} from "element-plus";
import {useRoute, useRouter} from "vue-router";
const { proxy } = getCurrentInstance();
const userStore = useUserStore()
const route = useRoute();
const router = useRouter();
const redirect = ref(undefined);
// 登录
const loginFormVisible = ref(false)
const regFormVisible = ref(false)
const pwd_form_vis = ref(true)
const searchText = ref('')
const showLoginForm = function () {
  loginFormVisible.value = true
  regFormVisible.value = false
}
const registerForm = ref({
  username: "",
  password: "",
  confirmPassword: "",
  code: "",
  uuid: ""
});
// 验证码输入检查
const handleInputSMS = function () {
  loginForm.sms = loginForm.sms.replace(/\D/g, '');
  loginForm.sms = loginForm.sms.substring(0, 6);
}
// 手机号输入检查
const handleInputPhone = function () {
  loginForm.phone = loginForm.phone.replace(/\D/g, '');
}
let changeLoginFormPWD = function () {
  pwd_form_vis.value = true
}
let changeLoginFormSMS = function () {
  ElMessage({
    message: '暂不支持手机登录',
    type: 'success'
  });
}

const loginForm = ref({
  username: "",
  password: "",
  phone: '',
  sms: '',
});
const avatar = ref("")
 function handleLogin() {
  userStore.login(loginForm.value).then(async () => {
    await userStore.getInfo()
    loginFormVisible.value = false;
    showLoginButton.value = false
    avatar.value = userStore.avatar
  }).catch(() => {
    loading.value = false;
  });
}

function toSearch() {
  if(searchText.value.length === 0){
    return
  }
  router.push({path: '/u/search', query: {text: searchText.value}});
}
function toHome(){
  router.push({path: '/u/home'})
}
function accountLogout(){
  userStore.logOut().then(() => {
    location.href = '/u/home';
  })
}
function toProfile(){
  const timestamp = Date.now();
  router.push({path: '/u/profile', query:{t: timestamp}});
}
function toAcademy(){
  const timestamp = Date.now();
  router.push({path: '/u/cooperate', query: {type: '院校', t: timestamp}});
}
function toCompany(){
  const timestamp = Date.now();
  router.push({path: '/u/cooperate', query: {type: '企业',t: timestamp}});
}
const showLoginButton = ref(true)
async function isLogin()  {
  let cookieArr = document.cookie.split(";");
  for (let i = 0; i < cookieArr.length; i++) {
    let cookiePair = cookieArr[i].split("=");
    if ("Admin-Token" === cookiePair[0].trim()) {
      showLoginButton.value = false
      await userStore.getInfo()
      avatar.value = userStore.avatar
      return
    }
  }
  showLoginButton.value = true
}
function reg() {
  loginFormVisible.value = false
  regFormVisible.value = true
}
const equalToPassword = (rule, value, callback) => {
  if (registerForm.value.password !== value) {
    callback(new Error("两次输入的密码不一致"));
  } else {
    callback();
  }
};

const registerRules = {
  username: [
    { required: true, trigger: "blur", message: "请输入您的账号" },
    { min: 2, max: 20, message: "用户账号长度必须介于 2 和 20 之间", trigger: "blur" }
  ],
  password: [
    { required: true, trigger: "blur", message: "请输入您的密码" },
    { min: 5, max: 20, message: "用户密码长度必须介于 5 和 20 之间", trigger: "blur" },
    { pattern: /^[^<>"'|\\]+$/, message: "不能包含非法字符：< > \" ' \\\ |", trigger: "blur" }
  ],
  confirmPassword: [
    { required: true, trigger: "blur", message: "请再次输入您的密码" },
    { required: true, validator: equalToPassword, trigger: "blur" }
  ],
  code: [{ required: true, trigger: "change", message: "请输入验证码" }]
};
const loading = ref(false);

function handleRegister() {
  proxy.$refs.registerRef.validate(valid => {
    if (valid) {
      loading.value = true;
      register(registerForm.value).then(() => {
        ElMessage({
          message: '注册成功',
          type: 'success'
        })
        regFormVisible.value = false
        loginForm.value.password = registerForm.value.password
        loginForm.value.username = registerForm.value.username
        handleLogin()
      })
    }
  });
}
function toDeliverRecord(){
  const timestamp = Date.now();
  router.push({path: '/u/deliverRecord', query:{t: timestamp}});
}
isLogin()
</script>

<template>
  <!--    页头-->
  <div class="home-header" style="min-width: 1500px;
  display: flex;align-items: center;justify-content: space-around">
    <div style="display: flex;margin-left: 4%;align-items: center;justify-content: center">
      <div style=";margin-top: 5px;;width: 100%;display: flex;justify-content: center">
        <div>
          <img src="@/assets/background/logo.png" alt="" style="width: 55px;">
        </div>
      </div>
    </div>
    <div style="align-items: center;justify-content: left;display: flex;">
      <div style="margin: 0 20px;">
        <el-button style="font-size: 16px;color: #181818" type="text" @click="toHome()">首页</el-button>
        <el-button class="home_menu_button" type="text" @click="toAcademy()">院校</el-button>
        <el-button class="home_menu_button" type="text" @click="toCompany()">企业</el-button>
<!--        <el-button class="home_menu_button" type="text">互助交流</el-button>-->
      </div>
    </div>
    <div style="align-items: center;justify-content: center;display: flex;">
      <div style="margin: 4% 2%">
        <el-input
            v-model="searchText"
            class="home_input"
            placeholder="这是输入框"
            style="height: 35px;border:0;border-radius: 5%;outline:none;background-color:#F8F8F8;"
        >
          <template #append>
            <el-button :icon="Search" @click="toSearch()"/>
          </template>
        </el-input>
      </div>
    </div>
    <div style="align-items: center;display: flex;justify-content: center;margin-right: 3%">
      <el-button v-if="showLoginButton" class="home-login-button" type="text" @click="showLoginForm">登录</el-button>
      <el-dropdown
          v-else
          trigger="click"
      >
        <el-avatar :src="avatar"/>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item @click="toProfile()">个人中心</el-dropdown-item>
            <el-dropdown-item @click="toDeliverRecord()">投递记录</el-dropdown-item>
            <el-dropdown-item  @click="accountLogout()">账号登出</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>
  </div>
  <el-dialog width="550px" v-model="loginFormVisible" style="border-radius: 10px">
    <div style="width: 100%;height: 40px;margin-bottom: 20px;align-items: center;justify-content: center;display:flex;">
      <div>
        <el-button type="text" style="font-size: 18px;color: #6C6C6C" @click="changeLoginFormPWD()">密码登录</el-button>
      </div>
      <div style="width: 1px;height: 20px;margin-right: 20px;margin-left: 20px;background-color: #E2E4E7"></div>
      <div>
        <el-button type="text" style="font-size: 18px;color: #6C6C6C" @click="changeLoginFormSMS()">短信登录</el-button>
      </div>
    </div>
    <el-form class="login_pwd" :model="loginForm" v-if="pwd_form_vis">
      <el-form-item>
        <div style="width: 100%;align-items: center;justify-content: center;display: flex">
          <el-input class="login_input_1" v-model="loginForm.username" autocomplete="off" placeholder="请输入账号"
                    style="height: 40px;width: 70%">
            <template #prepend><p style="user-select: none">账号</p></template>
            <template #append></template>
          </el-input>
        </div>
        <div style="width: 100%;align-items: center;justify-content: center;display: flex">
          <el-input class="login_input_2" v-model="loginForm.password" autocomplete="off" placeholder="请输入密码"
                    type="password"
                    show-password style="height: 40px;width: 70%">
            <template #prepend><p style="user-select: none">密码</p></template>
            <template #append>
              <el-button type="text" style="color: #00AEEC;margin-top: 1px;font-size: 13.5px">忘记密码？</el-button>
            </template>
          </el-input>
        </div>
      </el-form-item>
    </el-form>
    <el-form class="login_sms" :model="loginForm" v-if="!pwd_form_vis">
      <el-form-item>
        <div style="width: 100%;align-items: center;justify-content: center;display: flex">
          <el-input class="login_input_1" v-model="loginForm.phone" autocomplete="off" placeholder="请输入手机号"
                    style="height: 40px;width: 70%" @input="handleInputPhone()">
            <template #prepend><p style="user-select: none">手机号</p></template>
            <template #append></template>
          </el-input>
        </div>
        <div style="width: 100%;align-items: center;justify-content: center;display: flex">
          <el-input class="login_input_2" v-model="loginForm.sms" autocomplete="off" placeholder="请输入验证码"
                    @input="handleInputSMS()"
                    style="height: 40px;width: 70%">
            <template #prepend><p style="user-select: none">验证码</p></template>
            <template #append>
            </template>
          </el-input>
        </div>
      </el-form-item>
    </el-form>
    <div style="align-items: center;justify-content: space-around;display: flex;width: 100%;margin-top: 20px">
      <div style="align-items: center;display: flex;justify-content: center;width: 70%;">
        <el-button style="width: 50%;height: 40px;border-radius: 10px;" @click="reg()">注册</el-button>
        <el-button style="width: 50%;height: 40px;border-radius: 10px;background-color: #00AEEC;color: white;"
                   @click="handleLogin()">登录
        </el-button>
      </div>
    </div>
    <div style="display: flex;justify-content: center;align-items: center;flex-direction: column;margin-top: 20px">
      <div>
        <p style="color: #999;font-size: 13.5px;user-select: none">未注册过的手机号，我们将自动为您注册账号</p>
      </div>
      <div>
        <p style="color: #999;font-size: 13.5px;user-select: none">
          登录或完成注册即代表您同意<span style="color:#00AEEC;"> 用户协议 </span>和<span
            style="color:#00AEEC;"> 隐私政策 </span></p>
      </div>
    </div>
  </el-dialog>
  <el-dialog width="550px" v-model="regFormVisible" style="border-radius: 10px">
    <div style="width: 100%;height: 40px;margin-bottom: 20px;align-items: center;justify-content: center;display:flex;">
      <div style="width: 70%;display: flex;justify-content: right">
        <el-text style="font-size: 24px;font-weight: bolder;color: #6C6C6C">注册</el-text>
      </div>
    </div>
    <el-form class="login_pwd" :model="registerForm"  :rules="registerRules" ref="registerRef" >
      <el-form-item prop="username">
        <div style="width: 100%;align-items: center;justify-content: center;display: flex">
          <el-input class="login_input_3" v-model="registerForm.username" autocomplete="off" placeholder="请输入账号"
                    style="height: 40px;width: 70%">
            <template #prepend><el-icon><User /></el-icon></template>
            <template #append></template>
          </el-input>
        </div>
      </el-form-item>
      <el-form-item prop="password">
        <div style="width: 100%;align-items: center;justify-content: center;display: flex">
          <el-input class="login_input_3" v-model="registerForm.password" autocomplete="off" placeholder="请输入密码"
                    type="password" @keyup.enter="handleRegister"
                    show-password style="height: 40px;width: 70%">
            <template #prepend><el-icon><Lock /></el-icon></template>
            <template #append>
            </template>
          </el-input>
        </div>
      </el-form-item>
      <el-form-item prop="confirmPassword">
        <div style="width: 100%;align-items: center;justify-content: center;display: flex">
          <el-input class="login_input_3" v-model="registerForm.confirmPassword" autocomplete="off" placeholder="请确认密码"
                    type="password" @keyup.enter="handleRegister"
                    show-password style="height: 40px;width: 70%">
            <template #prepend><el-icon><Lock /></el-icon></template>
            <template #append>
            </template>
          </el-input>
        </div>
      </el-form-item>
    </el-form>
    <div style="align-items: center;justify-content: space-around;display: flex;width: 100%;margin-top: 20px">
      <div style="align-items: center;display: flex;justify-content: center;width: 70%;">
        <el-button type="primary" style="width: 100%;height: 40px;border-radius: 10px;" @click="handleRegister">注册</el-button>
      </div>
    </div>
    <div style="display: flex;justify-content: center;align-items: center;flex-direction: column;margin-top: 20px">
      <div>
        <p style="color: #999;font-size: 13.5px;user-select: none">
          完成注册即代表您同意<span style="color:#00AEEC;"> 用户协议 </span>和<span
            style="color:#00AEEC;"> 隐私政策 </span></p>
      </div>
    </div>
  </el-dialog>
</template>

<style scoped>
/*主页顶部按钮*/
.home-login-button {
  color: #717171;
  font-size: 16px;
}


.home_menu_button {
  font-size: 16px;
  margin-left: 20px;
  color: #181818
}

.home-header {
  background-color: white;
  box-shadow: 0 0 0 0 transparent,
  0 0 0 0 transparent,
  0 1px 4px 0 rgba(0, 0, 0, .02),
  0 2px 12px 0 rgba(0, 0, 0, .04),
  0 2px 6px 0 rgba(0, 0, 0, .02);

}

.avatar-dropDown {

}
/*登录输入框1*/
.login_input_1 {
  & :deep(.el-input-group__prepend) {
    background-color: white;
    box-shadow: 0 -1px 0 0 #E2E4E7,
    -1px 0 0 0 #E2E4E7,
    0 1px 0 0 #E2E4E7;
    border-bottom-right-radius: 0;
    border-bottom-left-radius: 0;
  }

  & :deep(.el-input-group__append) {
    background-color: white;
    box-shadow: 0 -1px 0 0 #E2E4E7,
    1px 0 0 0 #E2E4E7,
    0 1px 0 0 #E2E4E7;
    border-bottom-right-radius: 0;
    border-bottom-left-radius: 0;
  }

  & :deep(.el-input__wrapper) {
    box-shadow: 0 -1px 0 0 #E2E4E7,
    0 1px 0 0 #E2E4E7;
  }
}

/*登录输入框2*/
.login_input_2 {
  & :deep(.el-input-group__prepend) {
    background-color: white;
    box-shadow: 0 -1px 0 0 #E2E4E7,
    -1px 0 0 0 #E2E4E7,
    0 1px 0 0 #E2E4E7;
    border-top-right-radius: 0;
    border-top-left-radius: 0;
  }

  & :deep(.el-input-group__append) {
    background-color: white;
    box-shadow: 0 -1px 0 0 #E2E4E7,
    1px 0 0 0 #E2E4E7,
    0 1px 0 0 #E2E4E7;
    border-top-right-radius: 0;
    border-top-left-radius: 0;
  }

  & :deep(.el-input__wrapper) {
    box-shadow: 0 -1px 0 0 #E2E4E7,
    0 1px 0 0 #E2E4E7;
  }
}

/*主页搜索框*/
.home_input {
  & :deep(.el-input-group__append) {
    background-color: white;
    box-shadow: 0 -1px 0 0 #E2E4E7,
    1px 0 0 0 #E2E4E7,
    0 1px 0 0 #E2E4E7;
  }

  & :deep(.el-input-group__prepend) {
    background-color: white;
    box-shadow: 0 -1px 0 0 #E2E4E7,
    -1px 0 0 0 #E2E4E7,
    0 1px 0 0 #E2E4E7;
    border-top-right-radius: 0;
    border-top-left-radius: 0;
  }

  & :deep(.el-input__wrapper) {
    box-shadow: 0 -1px 0 0 #E2E4E7,
    -1px 0 0 0 #E2E4E7,
    0 1px 0 0 #E2E4E7;
  }
}
.login_input_3 {
  & :deep(.el-input-group__append) {
    background-color: white;
    box-shadow: 0 -1px 0 0 #E2E4E7,
    1px 0 0 0 #E2E4E7,
    0 1px 0 0 #E2E4E7;
  }

  & :deep(.el-input-group__prepend) {
    background-color: white;
    box-shadow: 0 -1px 0 0 #E2E4E7,
    -1px 0 0 0 #E2E4E7,
    0 1px 0 0 #E2E4E7;
  }

  & :deep(.el-input__wrapper) {
    box-shadow: 0 -1px 0 0 #E2E4E7,
    0 1px 0 0 #E2E4E7;
  }
}

/*抽屉*/
:deep(.el-drawer__body) {
  padding: 0;
}


</style>
