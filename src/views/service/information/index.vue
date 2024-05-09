<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="公司Id
" prop="eduCompanyId">
        <el-input
          v-model="queryParams.eduCompanyId"
          placeholder="请输入公司Id
"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="法定代表人" prop="eduLegalRepresentative">
        <el-input
          v-model="queryParams.eduLegalRepresentative"
          placeholder="请输入法定代表人"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="社会统一信用码" prop="eduSocialCreditCode">
        <el-input
          v-model="queryParams.eduSocialCreditCode"
          placeholder="请输入社会统一信用码"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="所属地区" prop="eduArea">
        <el-input
          v-model="queryParams.eduArea"
          placeholder="请输入所属地区"
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
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['service:information:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['service:information:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['service:information:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="informationList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="Id" align="center" prop="eduId" />
      <el-table-column label="公司Id" align="center" prop="eduCompanyId" />
      <el-table-column label="法定代表人" width="90" align="center" prop="eduLegalRepresentative" />
      <el-table-column label="企业类型" align="center" prop="eduEnterpriseType" />
      <el-table-column label="注册资本" align="center" prop="eduRegisteredCapital" />
      <el-table-column label="社会统一信用码" width="110" align="center" prop="eduSocialCreditCode" />
      <el-table-column label="经营期限 " align="center" prop="eduOperatingPeriod" />
      <el-table-column label="所属地区" align="center" prop="eduArea" />
      <el-table-column label="经营状态" align="center" prop="eduOperatingStatus" />
      <el-table-column label="登记机关" align="center" prop="eduRegistrationAuthority" />
      <el-table-column label="注册地址" align="center" prop="eduRegisteredAddress" />
      <el-table-column label="成立时间" align="center" prop="eduEstablishmentDate" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.eduEstablishmentDate, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="经营范围 " align="center" prop="eduBusinessScope" />
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
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['service:information:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['service:information:remove']">删除</el-button>
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

    <!-- 添加或修改公司信息对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="informationRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="法定代表人" prop="eduLegalRepresentative">
          <el-input v-model="form.eduLegalRepresentative" placeholder="请输入法定代表人" />
        </el-form-item>
        <el-form-item label="企业类型" prop="eduEnterpriseType">
          <el-input v-model="form.eduEnterpriseType" placeholder="请输入企业类型"/>
        </el-form-item>
        <el-form-item label="注册资本" prop="eduRegisteredCapital">
          <el-input v-model="form.eduRegisteredCapital" placeholder="请输入注册资本" />
        </el-form-item>
        <el-form-item label="社会统一信用码" prop="eduSocialCreditCode">
          <el-input v-model="form.eduSocialCreditCode" placeholder="请输入社会统一信用码" />
        </el-form-item>
        <el-form-item label="经营期限 " prop="eduOperatingPeriod">
          <el-input v-model="form.eduOperatingPeriod" placeholder="请输入经营期限 " />
        </el-form-item>
        <el-form-item label="所属地区" prop="eduArea">
          <el-input v-model="form.eduArea" placeholder="请输入所属地区" />
        </el-form-item>
        <el-form-item label="经营状态" prop="eduOperatingStatus">
          <el-input v-model="form.eduOperatingStatus" placeholder="请输入经营状态"/>
        </el-form-item>
        <el-form-item label="登记机关" prop="eduRegistrationAuthority">
          <el-input v-model="form.eduRegistrationAuthority" placeholder="请输入登记机关" />
        </el-form-item>
        <el-form-item label="注册地址" prop="eduRegisteredAddress">
          <el-input v-model="form.eduRegisteredAddress" placeholder="请输入注册地址" />
        </el-form-item>
        <el-form-item label="成立时间" prop="eduEstablishmentDate">
          <el-date-picker clearable
            v-model="form.eduEstablishmentDate"
            type="datetime"
            value-format="YYYY-MM-DD HH:mm:ss"
            placeholder="请选择成立时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="经营范围 " prop="eduBusinessScope">
          <el-input v-model="form.eduBusinessScope" type="textarea" placeholder="请输入内容" />
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

<script setup name="Information">
import { listInformation, getInformation, delInformation, addInformation, updateInformation } from "@/api/service/information";

const { proxy } = getCurrentInstance();

const informationList = ref([]);
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
    eduLegalRepresentative: null,
    eduEnterpriseType: null,
    eduRegisteredCapital: null,
    eduSocialCreditCode: null,
    eduOperatingPeriod: null,
    eduArea: null,
    eduOperatingStatus: null,
    eduRegistrationAuthority: null,
    eduRegisteredAddress: null,
    eduEstablishmentDate: null,
    eduBusinessScope: null,
    eduCreateUser: null,
    eduModifyUser: null,
    eduCreateTime: null,
    eduModifyTime: null
  },
  rules: {
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询公司信息列表 */
function getList() {
  loading.value = true;
  listInformation(queryParams.value).then(response => {
    informationList.value = response.rows;
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
    eduLegalRepresentative: null,
    eduEnterpriseType: null,
    eduRegisteredCapital: null,
    eduSocialCreditCode: null,
    eduOperatingPeriod: null,
    eduArea: null,
    eduOperatingStatus: null,
    eduRegistrationAuthority: null,
    eduRegisteredAddress: null,
    eduEstablishmentDate: null,
    eduBusinessScope: null,
    eduCreateUser: null,
    eduModifyUser: null,
    eduCreateTime: null,
    eduModifyTime: null
  };
  proxy.resetForm("informationRef");
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
  title.value = "添加公司信息";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _eduId = row.eduId || ids.value
  getInformation(_eduId).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改公司信息";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["informationRef"].validate(valid => {
    if (valid) {
      if (form.value.eduId != null) {
        updateInformation(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addInformation(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除公司信息编号为"' + _eduIds + '"的数据项？').then(function() {
    return delInformation(_eduIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('service/information/export', {
    ...queryParams.value
  }, `information_${new Date().getTime()}.xlsx`)
}

getList();
</script>
