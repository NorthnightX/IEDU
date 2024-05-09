<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="城市名" prop="cityName">
        <el-input
          v-model="queryParams.cityName"
          placeholder="请输入城市名"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="简称" prop="shortName">
        <el-input
          v-model="queryParams.shortName"
          placeholder="请输入简称"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="城市编码" prop="cityCode">
        <el-input
          v-model="queryParams.cityCode"
          placeholder="请输入城市编码"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="邮编" prop="zipCode">
        <el-input
          v-model="queryParams.zipCode"
          placeholder="请输入邮编"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="全称" prop="mergerName">
        <el-input
          v-model="queryParams.mergerName"
          placeholder="请输入全称"
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
          v-hasPermi="['service:provinces:add']"
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
      :data="provincesList"
      row-key="id"
      :default-expand-all="isExpandAll"
      :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
    >
      <el-table-column label="城市名" prop="cityName" width="200px" />
      <el-table-column label="上级id" align="center" prop="parentId" />
      <el-table-column label="简称" align="center" prop="shortName" />
      <el-table-column label="层级" align="center" prop="depth" />
      <el-table-column label="城市编码" align="center" prop="cityCode" />
      <el-table-column label="邮编" align="center" prop="zipCode" />
      <el-table-column label="全称" align="center" prop="mergerName" />
      <el-table-column label="经度" align="center" prop="longitude" />
      <el-table-column label="纬度" align="center" prop="latitude" />
      <el-table-column label="拼音" align="center" prop="pinyin" />
      <el-table-column label="是否使用" align="center" prop="isUse" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['service:provinces:edit']">修改</el-button>
          <el-button link type="primary" icon="Plus" @click="handleAdd(scope.row)" v-hasPermi="['service:provinces:add']">新增</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['service:provinces:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 添加或修改全国省市对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="provincesRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="id" prop="id">
          <el-input v-model="form.id" placeholder="请输入id" />
        </el-form-item>
        <el-form-item label="城市名" prop="cityName">
          <el-input v-model="form.cityName" placeholder="请输入城市名" />
        </el-form-item>
        <el-form-item label="上级id" prop="parentId">
          <el-tree-select
            v-model="form.parentId"
            :data="provincesOptions"
            :props="{ value: 'id', label: 'cityName', children: 'children' }"
            value-key="id"
            placeholder="请选择上级id"
            check-strictly
          />
        </el-form-item>
        <el-form-item label="简称" prop="shortName">
          <el-input v-model="form.shortName" placeholder="请输入简称" />
        </el-form-item>
        <el-form-item label="层级" prop="depth">
          <el-input v-model="form.depth" placeholder="请输入层级" />
        </el-form-item>
        <el-form-item label="城市编码" prop="cityCode">
          <el-input v-model="form.cityCode" placeholder="请输入城市编码" />
        </el-form-item>
        <el-form-item label="邮编" prop="zipCode">
          <el-input v-model="form.zipCode" placeholder="请输入邮编" />
        </el-form-item>
        <el-form-item label="全称" prop="mergerName">
          <el-input v-model="form.mergerName" placeholder="请输入全称" />
        </el-form-item>
        <el-form-item label="经度" prop="longitude">
          <el-input v-model="form.longitude" placeholder="请输入经度" />
        </el-form-item>
        <el-form-item label="纬度" prop="latitude">
          <el-input v-model="form.latitude" placeholder="请输入纬度" />
        </el-form-item>
        <el-form-item label="拼音" prop="pinyin">
          <el-input v-model="form.pinyin" placeholder="请输入拼音" />
        </el-form-item>
        <el-form-item label="是否使用" prop="isUse">
          <el-input v-model="form.isUse" placeholder="请输入是否使用" />
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

<script setup name="Provinces">
import { listProvinces, getProvinces, delProvinces, addProvinces, updateProvinces } from "@/api/service/provinces";

const { proxy } = getCurrentInstance();

const provincesList = ref([]);
const provincesOptions = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const title = ref("");
const isExpandAll = ref(true);
const refreshTable = ref(true);

const data = reactive({
  form: {},
  queryParams: {
    cityName: null,
    parentId: null,
    shortName: null,
    depth: null,
    cityCode: null,
    zipCode: null,
    mergerName: null,
    longitude: null,
    latitude: null,
    pinyin: null,
    isUse: null
  },
  rules: {
    id: [
      { required: true, message: "id不能为空", trigger: "blur" }
    ],
    cityName: [
      { required: true, message: "城市名不能为空", trigger: "blur" }
    ],
    parentId: [
      { required: true, message: "上级id不能为空", trigger: "blur" }
    ],
    shortName: [
      { required: true, message: "简称不能为空", trigger: "blur" }
    ],
    depth: [
      { required: true, message: "层级不能为空", trigger: "blur" }
    ],
    cityCode: [
      { required: true, message: "城市编码不能为空", trigger: "blur" }
    ],
    zipCode: [
      { required: true, message: "邮编不能为空", trigger: "blur" }
    ],
    mergerName: [
      { required: true, message: "全称不能为空", trigger: "blur" }
    ],
    longitude: [
      { required: true, message: "经度不能为空", trigger: "blur" }
    ],
    latitude: [
      { required: true, message: "纬度不能为空", trigger: "blur" }
    ],
    pinyin: [
      { required: true, message: "拼音不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询全国省市列表 */
function getList() {
  loading.value = true;
  listProvinces(queryParams.value).then(response => {
    provincesList.value = proxy.handleTree(response.data, "id", "parentId");
    loading.value = false;
  });
}

/** 查询全国省市下拉树结构 */
function getTreeselect() {
  listProvinces().then(response => {
    provincesOptions.value = [];
    const data = { id: 0, cityName: '顶级节点', children: [] };
    data.children = proxy.handleTree(response.data, "id", "parentId");
    provincesOptions.value.push(data);
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
    id: null,
    cityName: null,
    parentId: null,
    shortName: null,
    depth: null,
    cityCode: null,
    zipCode: null,
    mergerName: null,
    longitude: null,
    latitude: null,
    pinyin: null,
    isUse: null
  };
  proxy.resetForm("provincesRef");
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
  if (row != null && row.id) {
    form.value.parentId = row.id;
  } else {
    form.value.parentId = 0;
  }
  open.value = true;
  title.value = "添加全国省市";
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
    form.value.parentId = row.parentId;
  }
  getProvinces(row.id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改全国省市";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["provincesRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateProvinces(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addProvinces(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除全国省市编号为"' + row.id + '"的数据项？').then(function() {
    return delProvinces(row.id);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

getList();
</script>
