<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="位置" prop="eduLocation">
        <el-select
            v-model="queryParams.eduLocation"
            placeholder="请选择位置"
            size="default"
            style="width: 120px"
        >
          <el-option
              v-for="item in locationList"
              :key="item.value"
              :label="item.label"
              :value="item.value"
          />
        </el-select>
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
<!--          v-hasPermi="['service:advertise:add']"-->
<!--        >新增</el-button>-->
<!--      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['service:advertise:edit']"
        >修改</el-button>
      </el-col>
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="danger"-->
<!--          plain-->
<!--          icon="Delete"-->
<!--          :disabled="multiple"-->
<!--          @click="handleDelete"-->
<!--          v-hasPermi="['service:advertise:remove']"-->
<!--        >删除</el-button>-->
<!--      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['service:advertise:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="advertiseList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="eduId" />
      <el-table-column label="广告图片" align="center" prop="eduLink">
        <template #default="scope">
          <image-preview style="width: 100px" :src="scope.row.eduLink"></image-preview>
        </template>
      </el-table-column>
      <el-table-column label="目标网址" align="center" prop="eduTarget">
        <template #default="scope">
          <el-link style="width: 100px" @click="toTarget(scope.row.eduTarget)">{{scope.row.eduTarget}}</el-link>
        </template>
      </el-table-column>
      <el-table-column label="位置" align="center" prop="eduLocation">
        <template #default="scope">
          <el-text style="width: 100px">{{getLocation(scope.row.eduLocation)}}</el-text>
        </template>
      </el-table-column>
      <el-table-column label="过期时间" align="center" prop="eduExpiredTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.eduExpiredTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['service:advertise:edit']">修改</el-button>
<!--          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['service:advertise:remove']">删除</el-button>-->
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

    <!-- 添加或修改广告管理对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="advertiseRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="图片链接" prop="eduLink">
          <image-upload v-model="form.eduLink" limit="1" ></image-upload>
        </el-form-item>
        <el-form-item label="目标网址" prop="eduTarget">
          <el-input v-model="form.eduTarget" placeholder="请输入目标网址" />
        </el-form-item>
<!--        <el-form-item label="位置" prop="eduLocation">-->
<!--          <el-select v-model="form.eduLocation" placeholder="请选择位置" style="width: 240px">-->
<!--            <el-option-->
<!--                v-for="item in locationList"-->
<!--                :key="item.value"-->
<!--                :label="item.label"-->
<!--                :value="item.value"-->
<!--            />-->
<!--          </el-select>-->
<!--        </el-form-item>-->
        <el-form-item label="过期时间" prop="eduExpiredTime">
          <el-date-picker clearable
                          v-model="form.eduExpiredTime"
                          type="datetime"
                          value-format="YYYY-MM-DD hh:mm:ss"
                          placeholder="请选择过期时间">
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

<script setup name="Advertise">
import { listAdvertise, getAdvertise, delAdvertise, addAdvertise, updateAdvertise } from "@/api/service/advertise";
import ImageUpload from "@/components/ImageUpload/index.vue";
import ImagePreview from "@/components/ImagePreview/index.vue";

const { proxy } = getCurrentInstance();

const advertiseList = ref([]);
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
    eduLink: null,
    eduTarget: null,
    eduLocation: null,
    eduExpiredTime: null
  },
  rules: {
    eduLocation: [
      { required: true, message: "位置不能为空", trigger: "blur" }
    ]
  }
});
const locationList = ref([
  { label: '主页', value: 0 },
  { label: '搜索页', value: 1 },
])
const { queryParams, form, rules } = toRefs(data);

/** 查询广告管理列表 */
function getList() {
  loading.value = true;
  listAdvertise(queryParams.value).then(response => {
    advertiseList.value = response.rows;
    total.value = response.total;
    loading.value = false;
  });
}

// 取消按钮
function cancel() {
  open.value = false;
  reset();
}

function getLocation(value) {
  // 解引用 locationList 以获取其值
  const locations = locationList.value;

  for (let item of locations) {
    if (value === item.value) {
      return item.label;
    }
  }
  return "";
}
// 表单重置
function reset() {
  form.value = {
    eduId: null,
    eduLink: null,
    eduTarget: null,
    eduLocation: null,
    eduExpiredTime: null
  };
  proxy.resetForm("advertiseRef");
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
function toTarget(link) {
  if (!/^https?:\/\//i.test(link)) {
    link = 'http://' + link;
  }
  window.open(link, '_blank');
}
/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加广告管理";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _eduId = row.eduId || ids.value
  getAdvertise(_eduId).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改广告管理";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["advertiseRef"].validate(valid => {
    if (valid) {
      if (form.value.eduId != null) {
        updateAdvertise(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addAdvertise(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除广告管理编号为"' + _eduIds + '"的数据项？').then(function() {
    return delAdvertise(_eduIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('service/advertise/export', {
    ...queryParams.value
  }, `advertise_${new Date().getTime()}.xlsx`)
}

getList();
</script>
