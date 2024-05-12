<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="院校名" prop="eduAcademyName">
        <el-input
          v-model="queryParams.eduAcademyName"
          placeholder="请输入院校名"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="院校标签" prop="eduAcademyTag">
        <el-input
          v-model="queryParams.eduAcademyTag"
          placeholder="请输入院校标签"
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
          v-hasPermi="['service:academy:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['service:academy:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['service:academy:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['service:academy:export']"
        >导出</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="success"
            plain
            icon="Edit"
            :disabled="single"
            @click="handleAddress"
        >编辑学校地址
        </el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="academyList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="eduId" />
      <el-table-column label="院校名" align="center" prop="eduAcademyName" />
      <el-table-column label="院校标签" align="center" prop="eduAcademyTag" />
      <el-table-column label="院校介绍" align="center" prop="eduAcademyIntroduce" />
      <el-table-column label="院校地址" align="center" prop="eduAddressId" />
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
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['service:academy:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['service:academy:remove']">删除</el-button>
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

    <!-- 添加或修改院校管理对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="academyRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="院校名" prop="eduAcademyName">
          <el-input v-model="form.eduAcademyName" placeholder="请输入院校名" />
        </el-form-item>
        <el-form-item label="院校标签" prop="eduAcademyTag">
          <el-input v-model="form.eduAcademyTag" placeholder="请输入院校标签" />
        </el-form-item>
        <el-form-item label="院校介绍" prop="eduAcademyIntroduce">
          <el-input v-model="form.eduAcademyIntroduce" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>

    <!-- 添加或修改院校地址对话框 -->
    <el-dialog :title="addressTitle" v-model="addressOpen" width="600px" append-to-body>
      <el-form-item label="地区" prop="districtIds" label-width="80px">
        <el-cascader
            v-model="districtIds"
            :options="districtList"
        />
      </el-form-item>
      <el-form ref="addressRef" :model="addressForm" :rules="addressRules" label-width="80px">
        <el-form-item label="详细地址" prop="eduDetailedAddress">
          <el-input v-model="addressForm.eduDetailedAddress" placeholder="请输入详细地址"/>
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitAddressFormForm">确 定</el-button>
          <el-button @click="cancelAddressForm">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="Academy">
import { listAcademy, getAcademy, delAcademy, addAcademy, updateAcademy } from "@/api/service/academy";
import {getDistrictList} from "@/api/service/provinces.js";
import {addAddress, updateAddress} from "@/api/service/address.js";
import {addAcademyAddress, getAddressByAcademy, updateAcademyAddress} from "@/api/service/academyAddress.js";

const { proxy } = getCurrentInstance();
const districtList = ref([]);
const addressForm = ref({});
const districtIds = ref("");
const addressTitle = ref("")
const addressOpen = ref(false)
const academyList = ref([]);
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
    eduAcademyName: null,
    eduAcademyTag: null,
    eduAcademyIntroduce: null,
    eduAddressId: null,
    eduCreateUser: null,
    eduModifyUser: null,
    eduCreateTime: null,
    eduModifyTime: null
  },
  rules: {
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询院校管理列表 */
function getList() {
  loading.value = true;
  listAcademy(queryParams.value).then(response => {
    academyList.value = response.rows;
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
    eduAcademyName: null,
    eduAcademyTag: null,
    eduAcademyIntroduce: null,
    eduAddressId: null,
    eduCreateUser: null,
    eduModifyUser: null,
    eduCreateTime: null,
    eduModifyTime: null
  };
  proxy.resetForm("academyRef");
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
  title.value = "添加院校管理";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _eduId = row.eduId || ids.value
  getAcademy(_eduId).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改院校管理";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["academyRef"].validate(valid => {
    if (valid) {
      if (form.value.eduId != null) {
        updateAcademy(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addAcademy(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除院校管理编号为"' + _eduIds + '"的数据项？').then(function() {
    return delAcademy(_eduIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('service/academy/export', {
    ...queryParams.value
  }, `academy_${new Date().getTime()}.xlsx`)
}
function handleAddress(row) {
  getDistrictList().then((res) => {
    districtList.value = res.data
  })
  getAddressByAcademy(ids.value[0]).then((res) => {
    const having = res.data
    addressForm.value.eduAcademyId = ids.value[0]
    if (!having) {
      addressOpen.value = true;
      addressTitle.value = "添加地址信息";
      return;
    }
    addressForm.value = res.data
    addressOpen.value = true;
    addressTitle.value = "修改地址信息";
  })
}
function submitAddressFormForm() {
  FillAddress();
  proxy.$refs["addressRef"].validate(valid => {
    if (valid) {
      if (addressForm.value.eduId != null) {
        updateAcademyAddress(addressForm.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          addressOpen.value = false;
          getList();
        });
      } else {
        addAcademyAddress(addressForm.value).then(response => {
          proxy.$modal.msgSuccess("新增成功");
          addressOpen.value = false;
          getList();
        });
      }
    }
  });
}
function FillAddress(){
  addressForm.value.eduCountryId = districtIds.value[0]
  addressForm.value.eduProvinceId = districtIds.value[1]
  addressForm.value.eduCityId = districtIds.value[2]
  addressForm.value.eduDistrictId = districtIds.value[3]
}

function cancelAddressForm() {
  addressOpen.value = false;
  resetAddressForm();
}

function resetAddressForm(){
  addressForm.value = {
    eduCompanyId: null,
    eduCountryId: null,
    eduProvinceId: null,
    eduCityId: null,
    eduDistrictId: null,
    eduDetailedAddress: null
  }
}

getList();
</script>
