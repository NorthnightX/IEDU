<script setup>
import {Search} from '@element-plus/icons-vue'
import {ref} from 'vue'
import useUserStore from '@/store/modules/user'

const userStore = useUserStore()
const route = useRoute();
const router = useRouter();
const redirect = ref(undefined);
// 登录
const loginFormVisible = ref(false)
const pwd_form_vis = ref(true)
const searchText = ref('')
const showLoginForm = function () {
  loginFormVisible.value = true
}
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
  pwd_form_vis.value = false
}

const loginForm = ref({
  username: "",
  password: "",
  phone: '',
  sms: '',
});

function handleLogin() {
  userStore.login(loginForm.value).then(() => {
    const query = route.query;
    Object.keys(query).reduce((acc, cur) => {
      if (cur !== "redirect") {
        acc[cur] = query[cur];
      }
      return acc;
    }, {});
    loginFormVisible.value = false;
    userStore.getInfo()
  }).catch(() => {
    loading.value = false;
  });
}

function toSearch() {
  router.push({path: '/u/search', query: {text: searchText.value}});
}

userStore.getInfo()
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
        <el-button style="font-size: 16px;color: #181818" type="text">首页</el-button>
        <el-button class="home_menu_button" type="text">新闻</el-button>
        <el-button class="home_menu_button" type="text">动态</el-button>
        <el-button class="home_menu_button" type="text">求职</el-button>
        <el-button class="home_menu_button" type="text">产教融合</el-button>
        <el-button class="home_menu_button" type="text">互助交流</el-button>
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
      <div v-if="userStore.name.length === 0">
        <el-button class="home-login-button" type="text" @click="showLoginForm">登录</el-button>
      </div>
      <div v-else style="align-items: center;display: flex;justify-content: center;">
        <el-avatar :src="userStore.avatar">
        </el-avatar>
      </div>
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
        <el-button style="width: 50%;height: 40px;border-radius: 10px;">注册</el-button>
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

/*抽屉*/
:deep(.el-drawer__body) {
  padding: 0;
}


</style>
