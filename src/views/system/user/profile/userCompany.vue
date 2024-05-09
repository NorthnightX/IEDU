<template>
  <el-form ref="pwdRef" :model="user" label-width="80px">
    <el-form-item label="公司" prop="oldPassword">
      <el-select v-model="user.eduCompanyId" placeholder="请选择公司">
        <el-option
            v-for="item in companyList"
            :key="item.eduId"
            :label="item.eduCompanyName"
            :value="item.eduId">
        </el-option>
      </el-select>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="submit">保存</el-button>
      <el-button type="danger" @click="close">关闭</el-button>
    </el-form-item>
  </el-form>
</template>

<script setup>


import {listAllCompany} from "@/api/service/company.js";
import {addUser} from "@/api/service/user.js";
import useUserStore from "@/store/modules/user.js";

const { proxy } = getCurrentInstance();
const companyList = ref([])
const user = reactive({
  eduCompanyId: '',
  eduUserId: ''
});

function getAllCompany(){
  listAllCompany().then((res) => {
    companyList.value = res.data
  })
}

/** 提交按钮 */
function submit() {
  user.eduUserId = useUserStore().id
  proxy.$refs.pwdRef.validate(valid => {
    if (valid) {
      addUser(user).then(response => {
        proxy.$modal.msgSuccess("添加成功");
      });
    }
  });
};
/** 关闭按钮 */
function close() {
  proxy.$tab.closePage();
};
getAllCompany()
</script>
