<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="项目名" prop="eduProjectName">
        <el-input
          v-model="queryParams.eduProjectName"
          placeholder="请输入项目名"
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
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="Plus"
          @click="handleAdd"
          v-hasPermi="['service:integrate:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['service:integrate:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['service:integrate:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['service:integrate:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="integrateList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="eduId" />
      <el-table-column label="项目名" align="center" prop="eduProjectName" />
      <el-table-column label="项目介绍" align="center" prop="eduProjectDescription" width="100px" >
        <template #default="{ row }">
          <el-button type="text" @click="showDetailDialog(row.eduProjectDescription)">
            点击查看详情
          </el-button>
        </template>
      </el-table-column>
      <el-table-column label="项目类型" align="center" prop="eduProjectType" />
      <el-table-column label="项目领域" align="center" prop="eduProjectField" />
      <el-table-column label="发布方" align="center" prop="eduPublisher" />
      <el-table-column label="联系方式" align="center" prop="eduContact" />
      <el-table-column label="官网" align="center" prop="eduOfficialWebsite" />
      <el-table-column label="项目开始时间" align="center" prop="eduStartTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.eduStartTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="项目结束时间" align="center" prop="eduEndTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.eduEndTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="创建者" align="center" prop="eduCreateUser" />
      <el-table-column label="修改者" align="center" prop="eduModifyUser" />
      <el-table-column label="创建时间" align="center" prop="eduCreateTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.eduCreateTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="修改时间" align="center" prop="modifyTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.eduModifyTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['service:integrate:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['service:integrate:remove']">删除</el-button>
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

    <!-- 添加或修改产教融合管理对话框 -->
    <el-dialog :title="title" v-model="open" width="700px" append-to-body>
      <el-form ref="integrateRef" :model="form" :rules="rules" label-width="120px">
        <el-form-item label="项目名" prop="eduProjectName">
          <el-input v-model="form.eduProjectName" placeholder="请输入项目名" />
        </el-form-item>
        <el-form-item label="项目介绍" prop="eduProjectDescription">
          <el-input v-model="form.eduProjectDescription" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="项目领域" prop="eduProjectField">
          <el-input v-model="form.eduProjectField" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="项目类型" prop="eduProjectType">
          <el-select
              v-model="form.eduProjectType"
              placeholder="项目类型"
              size="large"
              style="width: 240px"
          >
            <el-option
                v-for="item in typeList"
                :key="item.eduId"
                :label="item.eduName"
                :value="item.eduId"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="发布方" prop="eduPublisher">
          <el-input v-model="form.eduPublisher" placeholder="请输入发布方" />
        </el-form-item>
        <el-form-item label="联系方式" prop="eduContact">
          <el-input v-model="form.eduContact" placeholder="请输入联系方式" />
        </el-form-item>
        <el-form-item label="官网" prop="eduOfficialWebsite">
          <el-input v-model="form.eduOfficialWebsite" placeholder="请输入官网" />
        </el-form-item>
        <el-form-item label="项目开始时间" prop="eduStartTime">
          <el-date-picker clearable
            v-model="form.eduStartTime"
            type="datetime"
            value-format="YYYY-MM-DD HH:mm:ss"
            placeholder="请选择项目开始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="项目结束时间" prop="eduEndTime">
          <el-date-picker clearable
            v-model="form.eduEndTime"
            type="datetime"
            value-format="YYYY-MM-DD HH:mm:ss"
            placeholder="请选择项目结束时间">
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

<script setup name="Integrate">
import { listIntegrate, getIntegrate, delIntegrate, addIntegrate, updateIntegrate } from "@/api/service/integrate";
import {getAllIntegrateType, listIntegrateType} from "@/api/service/integrateType.js";
import {ElMessageBox} from "element-plus";

const { proxy } = getCurrentInstance();
const integrateList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");
const typeList = ref([])
const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    eduProjectName: null,
    eduProjectDescription: null,
    eduProjectType: null,
    eduProjectField: null,
    eduPublisher: null,
    eduContact: null,
    eduOfficialWebsite: null,
    eduStartTime: null,
    eduEndTime: null,
    eduCreateUser: null,
    eduModifyUser: null,
    eduCreateTime: null,
    eduModifyTime: null
  },
  rules: {
    eduProjectName: [
      { required: true, message: "项目名不能为空", trigger: "blur" }
    ],
    eduProjectDescription: [
      { required: true, message: "项目介绍不能为空", trigger: "blur" }
    ],
    eduProjectType: [
      { required: true, message: "项目类型不能为空", trigger: "change" }
    ],
    eduProjectField: [
      { required: true, message: "项目领域不能为空", trigger: "blur" }
    ],
    eduPublisher: [
      { required: true, message: "发布方不能为空", trigger: "blur" }
    ],
    eduContact: [
      { required: true, message: "联系方式不能为空", trigger: "blur" }
    ],
    eduStartTime: [
      { required: true, message: "项目开始时间不能为空", trigger: "blur" }
    ],
    eduEndTime: [
      { required: true, message: "项目结束时间不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询产教融合管理列表 */
function getList() {
  loading.value = true;
  listIntegrate(queryParams.value).then(response => {
    integrateList.value = response.rows;
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
    eduProjectName: null,
    eduProjectDescription: null,
    eduProjectType: null,
    eduProjectField: null,
    eduPublisher: null,
    eduContact: null,
    eduOfficialWebsite: null,
    eduStartTime: null,
    eduEndTime: null,
    eduCreateUser: null,
    eduModifyUser: null,
    eduCreateTime: null,
    eduModifyTime: null
  };
  proxy.resetForm("integrateRef");
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

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加产教融合管理";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _eduId = row.eduId || ids.value
  getIntegrate(_eduId).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改产教融合管理";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["integrateRef"].validate(valid => {
    if (valid) {
      if (form.value.eduId != null) {
        updateIntegrate(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addIntegrate(form.value).then(response => {
          proxy.$modal.msgSuccess("新增成功");
          open.value = false;
          getList();
        });
      }
    }
  });
}

/** 删除按钮操作 */
function handleDelete(row) {
  const _eduIds = row.eduId || ids.value;
  proxy.$modal.confirm('是否确认删除产教融合管理编号为"' + _eduIds + '"的数据项？').then(function() {
    return delIntegrate(_eduIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
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
/** 导出按钮操作 */
function handleExport() {
  proxy.download('service/integrate/export', {
    ...queryParams.value
  }, `integrate_${new Date().getTime()}.xlsx`)
}
function getTypeList(){
  getAllIntegrateType().then((res) => {
    typeList.value = res.data
  })
}
getTypeList()
getList();
</script>
<style>

</style>
