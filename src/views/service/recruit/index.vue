<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="公司id" prop="eduCompanyId">
        <el-input
            v-model="queryParams.eduCompanyId"
            placeholder="请输入公司id"
            clearable
            @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="名称" prop="eduJobName">
        <el-input
            v-model="queryParams.eduJobName"
            placeholder="请输入名称"
            clearable
            @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5" v-show="useUserStore().roles.some(role => role === 'industry')">
        <el-button
            type="primary"
            plain
            icon="Plus"
            @click="handleAdd"
            v-hasPermi="['service:recruit:add']"
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="success"
            plain
            icon="Edit"
            :disabled="single"
            @click="handleUpdate"
            v-hasPermi="['service:recruit:edit']"
        >修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="danger"
            plain
            icon="Delete"
            :disabled="multiple"
            @click="handleDelete"
            v-hasPermi="['service:recruit:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="warning"
            plain
            icon="Download"
            @click="handleExport"
            v-hasPermi="['service:recruit:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="recruitList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="Id" align="center" prop="eduId"/>
      <el-table-column label="企业名" align="center" prop="companyName"/>
      <el-table-column label="名称" align="center" prop="eduJobName"/>
      <el-table-column label="工作类型" align="center" prop="jobTypeName"/>
      <el-table-column label="城市" align="center" prop="cityName"/>
      <el-table-column label="学历要求" align="center" prop="eduName"/>
      <el-table-column label="经验要求" align="center" prop="expName"/>
      <el-table-column label="薪资范围" align="center" prop="eduSalary"/>
      <el-table-column label="福利待遇" align="center" prop="eduWelfare"/>
      <el-table-column label="职位tag" align="center" prop="eduIntroduceTag"/>
      <el-table-column label="职位详情" align="center" prop="eduIntroduce" width="120px">
        <template #default="{ row }">
          <el-button type="text" @click="showDetailDialog(row.eduIntroduce)">
            点击查看详情
          </el-button>
        </template>
      </el-table-column>
      <el-table-column label="开始时间" align="center" prop="eduStartTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.eduStartTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="截止时间" align="center" prop="eduEndTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.eduEndTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)"
                     v-hasPermi="['service:recruit:edit']">修改
          </el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)"
                     v-hasPermi="['service:recruit:remove']">删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
        v-show="total>0"
        :total="total"
        v-model:page="queryParams.pageNum"
        v-model:limit="queryParams.pageSize"
        @pagination="getList"
    />

    <!-- 添加或修改招聘信息对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="recruitRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="名称" prop="eduJobName">
          <el-input v-model="form.eduJobName" placeholder="请输入名称"/>
        </el-form-item>
        <el-form-item label="工作类型" prop="eduJobTypeId">
          <el-cascader
              placeholder="请输入工作类型"
              v-model="form.eduJobTypeId"
              :options="jonTypeList">
          </el-cascader>
        </el-form-item>
        <el-form-item label="城市" prop="eduCityId">
          <el-cascader
              placeholder="请输入城市"
              v-model="form.eduCityId"
              :options="provincesList">
          </el-cascader>
        </el-form-item>
        <el-form-item label="学历要求" prop="eduEduId">
          <el-select v-model="form.eduEduId" placeholder="请输入学历要求">
            <el-option
                v-for="item in educationList"
                :key="item.eduId"
                :label="item.eduEdu"
                :value="item.eduId">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="经验要求" prop="eduExpId">
          <el-select v-model="form.eduExpId" placeholder="请输入经验要求">
            <el-option
                v-for="item in experienceList"
                :key="item.eduId"
                :label="item.eduExp"
                :value="item.eduId">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="薪资范围" prop="eduSalary">
          <el-input v-model="form.eduSalary" placeholder="请输入薪资范围"/>
        </el-form-item>
        <el-form-item label="福利待遇" prop="eduWelfare">
          <el-input v-model="form.eduWelfare" placeholder="请输入福利待遇"/>
        </el-form-item>
        <el-form-item label="职位tag" prop="eduIntroduceTag">
          <el-input v-model="form.eduIntroduceTag" placeholder="请输入职位tag"/>
        </el-form-item>
        <el-form-item label="职位详情" prop="eduIntroduce">
          <el-input v-model="form.eduIntroduce" type="textarea" placeholder="请输入内容"/>
        </el-form-item>
        <el-form-item label="开始时间" prop="eduStartTime">
          <el-date-picker clearable
                          v-model="form.eduStartTime"
                          type="datetime"
                          value-format="YYYY-MM-DD HH:mm:ss"
                          placeholder="请选择开始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="截止时间" prop="eduEndTime">
          <el-date-picker clearable
                          v-model="form.eduEndTime"
                          type="datetime"
                          value-format="YYYY-MM-DD HH:mm:ss"
                          placeholder="请选择截止时间">
          </el-date-picker>
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="Recruit">
import {listRecruit, getRecruit, delRecruit, addRecruit, updateRecruit} from "@/api/service/recruit";
import useUserStore from "@/store/modules/user.js";
import {listAllExperience} from "@/api/service/experience.js";
import {listAllRequirement} from "@/api/service/requirement.js";
import {listAllType} from "@/api/service/type.js";
import {getDistrictList} from "@/api/service/provinces.js";
import {getUserCompany} from "@/api/service/user.js";
import {ElMessage, ElMessageBox} from "element-plus";

const {proxy} = getCurrentInstance();
const provincesList = ref([]);
const recruitList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");
const experienceList = ref([])
const educationList = ref([])
const jonTypeList = ref([])
const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    eduCompanyId: null,
    eduJobName: null,
    eduJobTypeId: null,
    eduCityId: null,
    eduEduId: null,
    eduExpId: null,
    eduSalary: null,
    eduWelfare: null,
    eduIntroduceTag: null,
    eduIntroduce: null,
    eduStartTime: null,
    eduEndTime: null,
    eduCreateUser: null,
    eduModifyUser: null,
    eduCreateTime: null,
    eduModifyTime: null
  },
  rules: {}
});

const {queryParams, form, rules} = toRefs(data);

/** 查询招聘信息列表 */
function getList() {
  loading.value = true;
  listRecruit(queryParams.value).then(response => {
    recruitList.value = response.rows;
    total.value = response.total;
    loading.value = false;
  });
}

// 取消按钮
function cancel() {
  open.value = false;
  reset();
}

// 表单重置
function reset() {
  form.value = {
    eduId: null,
    eduCompanyId: null,
    eduJobName: null,
    eduJobTypeId: null,
    eduCityId: null,
    eduEduId: null,
    eduExpId: null,
    eduSalary: null,
    eduWelfare: null,
    eduIntroduceTag: null,
    eduIntroduce: null,
    eduStartTime: null,
    eduEndTime: null,
    eduCreateUser: null,
    eduModifyUser: null,
    eduCreateTime: null,
    eduModifyTime: null
  };
  proxy.resetForm("recruitRef");
}

/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1;
  getList();
}

/** 重置按钮操作 */
function resetQuery() {
  proxy.resetForm("queryRef");
  handleQuery();
}

// 多选框选中数据
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.eduId);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}
function showDetailDialog(content){
  ElMessageBox.alert(
      content,
      "内容",
      {
        dangerouslyUseHTMLString: true,
        customStyle: {'max-width': '70%'}
      }
  )
}
/** 新增按钮操作 */
function handleAdd() {
  reset();
  getUserCompany(useUserStore().id).then((res) => {
    if(res.data === -1){
      ElMessage({
        message: '请先添加企业',
        type: 'warning'
      });
      return
    }
    open.value = true;
    title.value = "添加招聘信息";
  })
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _eduId = row.eduId || ids.value
  getRecruit(_eduId).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改招聘信息";
  });
}

/** 提交按钮 */
function submitForm() {
  getUserCompany(useUserStore().id).then((res) => {
    form.value.eduCompanyId = res.data
  }).then(() => {
    if(form.value.eduCompanyId === -1){
      ElMessage({
        message: '你没有修改权限',
        type: 'warning'
      });
      return
    }
    form.value.eduCityId = form.value.eduCityId[form.value.eduCityId.length-1]
    form.value.eduJobTypeId = form.value.eduJobTypeId[form.value.eduJobTypeId.length-1]
    proxy.$refs["recruitRef"].validate(valid => {
      if (valid) {
        if (form.value.eduId != null) {
          updateRecruit(form.value).then(response => {
            proxy.$modal.msgSuccess("修改成功");
            open.value = false;
            getList();
          });
        } else {
          addRecruit(form.value).then(response => {
            proxy.$modal.msgSuccess("新增成功");
            open.value = false;
            getList();
          });
        }
      }
    });
  })
}

/** 删除按钮操作 */
function handleDelete(row) {
  const _eduIds = row.eduId || ids.value;
  proxy.$modal.confirm('是否确认删除招聘信息编号为"' + _eduIds + '"的数据项？').then(function () {
    return delRecruit(_eduIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {
  });
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('service/recruit/export', {
    ...queryParams.value
  }, `recruit_${new Date().getTime()}.xlsx`)
}

getList();


function getExperienceList() {
  listAllExperience().then((res) => {
    experienceList.value = res.data
  })
}

function getEducationList() {
  listAllRequirement().then((res) => {
    educationList.value = res.data
  })
}

function getJonTypeList() {
  listAllType().then((res) => {
    jonTypeList.value = res.data
  })
}
function getProvincesList(){
  getDistrictList().then((res) => {
    provincesList.value = res.data
  })
}

getProvincesList()
getJonTypeList()
getEducationList()
getExperienceList()
</script>
