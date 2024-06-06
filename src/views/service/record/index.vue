<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="岗位ID" prop="eduRecruitId">
        <el-input
          v-model="queryParams.eduRecruitId"
          placeholder="请输入岗位ID"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="用户ID" prop="eduUserId">
        <el-input
          v-model="queryParams.eduUserId"
          placeholder="请输入用户ID"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="投递者姓名" prop="eduDelivererName">
        <el-input
          v-model="queryParams.eduDelivererName"
          placeholder="请输入投递者姓名"
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
          v-hasPermi="['service:record:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['service:record:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['service:record:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['service:record:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="recordList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="eduId" />
      <el-table-column label="岗位" align="center" prop="eduRecruitName" />
      <el-table-column label="公司" align="center" prop="eduCompanyName" />
      <el-table-column label="用户ID" align="center" prop="eduUserId" />
      <el-table-column label="简历链接" align="center" prop="eduResumeLink">
        <template #default="scope">
          <el-button type="text" @click="downloadResume(scope.row.eduResumeLink)">下载简历</el-button>
        </template>
      </el-table-column>
      <el-table-column label="投递者姓名" align="center" prop="eduDelivererName" />
      <el-table-column label="投递者年龄" align="center" prop="eduDelivererAge" />
      <el-table-column label="投递者性别" align="center" prop="eduDelivererSex" />
      <el-table-column label="投递者身份证号" align="center" prop="eduDelivererIdCard" />
      <el-table-column label="投递者邮箱" align="center" prop="eduDelivererMail" />
      <el-table-column label="投递者手机号" align="center" prop="eduDelivererPhone" />
      <el-table-column label="投递时间" align="center" prop="eduDeliveryTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.eduDeliveryTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['service:record:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['service:record:remove']">删除</el-button>
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

    <!-- 添加或修改投递记录对话框 -->
    <el-dialog :title="title" v-model="open" width="700px" append-to-body>
      <el-form ref="recordRef" :model="form" :rules="rules" label-width="120px">
        <el-form-item label="岗位ID" prop="eduRecruitId">
          <el-input v-model="form.eduRecruitId" placeholder="请输入岗位ID" />
        </el-form-item>
        <el-form-item label="用户ID" prop="eduUserId">
          <el-input v-model="form.eduUserId" placeholder="请输入用户ID" />
        </el-form-item>
        <el-form-item label="简历链接" prop="eduResumeLink">
          <el-input v-model="form.eduResumeLink" placeholder="请输入简历链接" />
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
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="Record">
import { listRecord, getRecord, delRecord, addRecord, updateRecord } from "@/api/service/record";
const { proxy } = getCurrentInstance();

const recordList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    eduRecruitId: null,
    eduUserId: null,
    eduResumeLink: null,
    eduDelivererName: null,
    eduDelivererAge: null,
    eduDelivererSex: null,
    eduDelivererIdCard: null,
    eduDelivererMail: null,
    eduDelivererPhone: null,
    eduDeliveryTime: null
  },
  rules: {
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询投递记录列表 */
function getList() {
  loading.value = true;
  listRecord(queryParams.value).then(response => {
    recordList.value = response.rows;
    total.value = response.total;
    loading.value = false;
  });
}

// 取消按钮
function cancel() {
  open.value = false;
  reset();
}
function downloadResume(url) {
  proxy.$download.resource(url)
}
// 表单重置
function reset() {
  form.value = {
    eduId: null,
    eduRecruitId: null,
    eduUserId: null,
    eduResumeLink: null,
    eduDelivererName: null,
    eduDelivererAge: null,
    eduDelivererSex: null,
    eduDelivererIdCard: null,
    eduDelivererMail: null,
    eduDelivererPhone: null,
    eduDeliveryTime: null
  };
  proxy.resetForm("recordRef");
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
  title.value = "添加投递记录";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _eduId = row.eduId || ids.value
  getRecord(_eduId).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改投递记录";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["recordRef"].validate(valid => {
    if (valid) {
      if (form.value.eduId != null) {
        updateRecord(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addRecord(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除投递记录编号为"' + _eduIds + '"的数据项？').then(function() {
    return delRecord(_eduIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('service/record/export', {
    ...queryParams.value
  }, `record_${new Date().getTime()}.xlsx`)
}

getList();
</script>
