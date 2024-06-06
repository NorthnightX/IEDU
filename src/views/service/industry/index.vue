<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="产业名" prop="eduIndustry">
        <el-input
          v-model="queryParams.eduIndustry"
          placeholder="请输入产业名"
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
          v-hasPermi="['service:industry:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="info"
          plain
          icon="Sort"
          @click="toggleExpandAll"
        >展开/折叠</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table
      v-if="refreshTable"
      v-loading="loading"
      :data="industryList"
      row-key="eduId"
      :default-expand-all="isExpandAll"
      :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
    >
      <el-table-column label="父行业" prop="eduFaId" />
      <el-table-column label="产业名" align="center" prop="eduIndustry" />
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
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['service:industry:edit']">修改</el-button>
          <el-button link type="primary" icon="Plus" @click="handleAdd(scope.row)" v-hasPermi="['service:industry:add']">新增</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['service:industry:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 添加或修改公司行业对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="industryRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="父行业" prop="eduFaId">
          <el-tree-select
            v-model="form.eduFaId"
            :data="industryOptions"
            :props="{ value: 'eduId', label: 'eduIndustry', children: 'children' }"
            value-key="eduId"
            placeholder="请选择父行业"
            check-strictly
          />
        </el-form-item>
        <el-form-item label="产业名" prop="eduIndustry">
          <el-input v-model="form.eduIndustry" placeholder="请输入产业名" />
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

<script setup name="Industry">
import { listIndustry, getIndustry, delIndustry, addIndustry, updateIndustry } from "@/api/service/industry";

const { proxy } = getCurrentInstance();

const industryList = ref([]);
const industryOptions = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const title = ref("");
const isExpandAll = ref(true);
const refreshTable = ref(true);

const data = reactive({
  form: {},
  queryParams: {
    eduFaId: null,
    eduIndustry: null,
    eduCreateTime: null,
    eduModifyTime: null,
    eduCreateUser: null,
    eduModifyUser: null
  },
  rules: {
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询公司行业列表 */
function getList() {
  loading.value = true;
  listIndustry(queryParams.value).then(response => {
    industryList.value = proxy.handleTree(response.data, "eduId", "eduFaId");
    loading.value = false;
  });
}

/** 查询公司行业下拉树结构 */
function getTreeselect() {
  listIndustry().then(response => {
    industryOptions.value = [];
    const data = { eduId: 0, eduIndustry: '顶级节点', children: [] };
    data.children = proxy.handleTree(response.data, "eduId", "eduFaId");
    industryOptions.value.push(data);
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
    eduFaId: null,
    eduIndustry: null,
    eduCreateTime: null,
    eduModifyTime: null,
    eduCreateUser: null,
    eduModifyUser: null
  };
  proxy.resetForm("industryRef");
}

/** 搜索按钮操作 */
function handleQuery() {
  getList();
}

/** 重置按钮操作 */
function resetQuery() {
  proxy.resetForm("queryRef");
  handleQuery();
}

/** 新增按钮操作 */
function handleAdd(row) {
  reset();
  getTreeselect();
  if (row != null && row.eduId) {
    form.value.eduFaId = row.eduId;
  } else {
    form.value.eduFaId = 0;
  }
  open.value = true;
  title.value = "添加企业行业";
}

/** 展开/折叠操作 */
function toggleExpandAll() {
  refreshTable.value = false;
  isExpandAll.value = !isExpandAll.value;
  nextTick(() => {
    refreshTable.value = true;
  });
}

/** 修改按钮操作 */
async function handleUpdate(row) {
  reset();
  await getTreeselect();
  if (row != null) {
    form.value.eduFaId = row.eduFaId;
  }
  getIndustry(row.eduId).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改企业行业";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["industryRef"].validate(valid => {
    if (valid) {
      if (form.value.eduId != null) {
        updateIndustry(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addIndustry(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除公司行业编号为"' + row.eduId + '"的数据项？').then(function() {
    return delIndustry(row.eduId);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

getList();
</script>
