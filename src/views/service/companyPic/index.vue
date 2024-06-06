<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="企业ID" prop="eduCompanyId">
        <el-input
          v-model="queryParams.eduCompanyId"
          placeholder="请输入企业ID"
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
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="primary"-->
<!--          plain-->
<!--          icon="Plus"-->
<!--          @click="handleAdd"-->
<!--          v-hasPermi="['service:companyPic:add']"-->
<!--        >新增</el-button>-->
<!--      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['service:companyPic:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['service:companyPic:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['service:companyPic:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="companyPicList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="eduId" />
      <el-table-column label="企业ID" align="center" prop="eduCompanyId" />
      <el-table-column label="企业logo" align="center" prop="eduLogo">
        <template #default="scope">
          <image-preview style="width: 100px" :src="scope.row.eduLogo"></image-preview>
        </template>
      </el-table-column>
      <el-table-column label="企业照片墙" align="center" prop="eduImages">
        <template #default="scope">
          <image-preview  style="width: 100px"  :src="scope.row.eduImages"></image-preview>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['service:companyPic:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['service:companyPic:remove']">删除</el-button>
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

    <!-- 添加或修改企业照片对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="companyPicRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="企业ID" prop="eduCompanyId">
          <el-input v-model="form.eduCompanyId" placeholder="请输入企业ID" />
        </el-form-item>
        <el-form-item label="企业logo" prop="eduLogo">
          <el-input v-model="form.eduLogo" placeholder="请输入企业logo" />
        </el-form-item>
        <el-form-item label="企业照片墙" prop="eduImages">
          <el-input v-model="form.eduImages" placeholder="请输入企业照片墙" />
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

<script setup name="CompanyPic">
import { listCompanyPic, getCompanyPic, delCompanyPic, addCompanyPic, updateCompanyPic } from "@/api/service/companyPic";
import ImagePreview from "@/components/ImagePreview/index.vue";

const { proxy } = getCurrentInstance();

const companyPicList = ref([]);
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
    eduCompanyId: null,
  },
  rules: {
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询企业照片列表 */
function getList() {
  loading.value = true;
  listCompanyPic(queryParams.value).then(response => {
    companyPicList.value = response.rows;
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
    eduLogo: null,
    eduImages: null
  };
  proxy.resetForm("companyPicRef");
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
  title.value = "添加企业照片";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _eduId = row.eduId || ids.value
  getCompanyPic(_eduId).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改企业照片";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["companyPicRef"].validate(valid => {
    if (valid) {
      if (form.value.eduId != null) {
        updateCompanyPic(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addCompanyPic(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除企业照片编号为"' + _eduIds + '"的数据项？').then(function() {
    return delCompanyPic(_eduIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('service/companyPic/export', {
    ...queryParams.value
  }, `companyPic_${new Date().getTime()}.xlsx`)
}

getList();
</script>
