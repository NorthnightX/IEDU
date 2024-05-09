<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="规模" prop="eduScale">
        <el-input
          v-model="queryParams.eduScale"
          placeholder="请输入规模"
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
          v-hasPermi="['service:scale:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['service:scale:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['service:scale:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['service:scale:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="scaleList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="公司规模ID" align="center" prop="eduId" />
      <el-table-column label="规模" align="center" prop="eduScale" />
      <el-table-column label="创建人" align="center" prop="eduCreateUser" />
      <el-table-column label="修改人" align="center" prop="eduModifyUser" />
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
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['service:scale:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['service:scale:remove']">删除</el-button>
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

    <!-- 添加或修改公司规模对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="scaleRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="规模" prop="eduScale">
          <el-input v-model="form.eduScale" placeholder="请输入规模" />
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

<script setup name="Scale">
import { listScale, getScale, delScale, addScale, updateScale } from "@/api/service/scale";

const { proxy } = getCurrentInstance();

const scaleList = ref([]);
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
    eduScale: null,
    eduCreateUser: null,
    eduModifyUser: null,
    eduCreateTime: null,
    eduModifyTime: null
  },
  rules: {
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询公司规模列表 */
function getList() {
  loading.value = true;
  listScale(queryParams.value).then(response => {
    scaleList.value = response.rows;
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
    eduScale: null,
    eduCreateUser: null,
    eduModifyUser: null,
    eduCreateTime: null,
    eduModifyTime: null
  };
  proxy.resetForm("scaleRef");
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
  title.value = "添加公司规模";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _eduId = row.eduId || ids.value
  getScale(_eduId).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改公司规模";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["scaleRef"].validate(valid => {
    if (valid) {
      if (form.value.eduId != null) {
        updateScale(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addScale(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除公司规模编号为"' + _eduIds + '"的数据项？').then(function() {
    return delScale(_eduIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('service/scale/export', {
    ...queryParams.value
  }, `scale_${new Date().getTime()}.xlsx`)
}

getList();
</script>
