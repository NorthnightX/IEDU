<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="名称" prop="eduName">
        <el-input
          v-model="queryParams.eduName"
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
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="Plus"
          @click="handleAdd"
          v-hasPermi="['service:integrateType:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['service:integrateType:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['service:integrateType:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['service:integrateType:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="integrateTypeList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="eduId" />
      <el-table-column label="名称" align="center" prop="eduName" />
      <el-table-column label="创建时间" align="center" prop="eduCreateTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.eduCreateTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="修改时间" align="center" prop="eduModifyTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.eduModifyTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="创建人" align="center" prop="eduCreateUser" />
      <el-table-column label="修改人" align="center" prop="eduModifyUser" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['service:integrateType:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['service:integrateType:remove']">删除</el-button>
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

    <!-- 添加或修改产教融合类型管理对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="integrateTypeRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="名称" prop="eduName">
          <el-input v-model="form.eduName" placeholder="请输入名称" />
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

<script setup name="IntegrateType">
import { listIntegrateType, getIntegrateType, delIntegrateType, addIntegrateType, updateIntegrateType } from "@/api/service/integrateType";

const { proxy } = getCurrentInstance();

const integrateTypeList = ref([]);
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
    eduName: null,
    eduCreateTime: null,
    eduModifyTime: null,
    eduCreateUser: null,
    eduModifyUser: null
  },
  rules: {
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询产教融合类型管理列表 */
function getList() {
  loading.value = true;
  listIntegrateType(queryParams.value).then(response => {
    integrateTypeList.value = response.rows;
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
    eduName: null,
    eduCreateTime: null,
    eduModifyTime: null,
    eduCreateUser: null,
    eduModifyUser: null
  };
  proxy.resetForm("integrateTypeRef");
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
  title.value = "添加产教融合类型管理";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _eduId = row.eduId || ids.value
  getIntegrateType(_eduId).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改产教融合类型管理";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["integrateTypeRef"].validate(valid => {
    if (valid) {
      if (form.value.eduId != null) {
        updateIntegrateType(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addIntegrateType(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除产教融合类型管理编号为"' + _eduIds + '"的数据项？').then(function() {
    return delIntegrateType(_eduIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('service/integrateType/export', {
    ...queryParams.value
  }, `integrateType_${new Date().getTime()}.xlsx`)
}

getList();
</script>
