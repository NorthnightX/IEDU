<template>

  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="公司名" prop="eduCompanyName">
        <el-input
            v-model="queryParams.eduCompanyName"
            placeholder="请输入公司名"
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
            v-hasPermi="['service:company:add']"
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="success"
            plain
            icon="Edit"
            :disabled="single"
            @click="handleUpdate"
            v-hasPermi="['service:company:edit']"
        >修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="danger"
            plain
            icon="Delete"
            :disabled="multiple"
            @click="handleDelete"
            v-hasPermi="['service:company:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="warning"
            plain
            icon="Download"
            @click="handleExport"
            v-hasPermi="['service:company:export']"
        >导出
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="success"
            plain
            icon="Edit"
            :disabled="single"
            @click="handleAddress"
        >编辑公司地址
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="success"
            plain
            icon="Edit"
            :disabled="single"
            @click="handleInformation"
        >编辑工商信息
        </el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="companyList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="id" align="center" prop="eduId"/>
      <el-table-column label="公司名" align="center" prop="eduCompanyName"/>
      <el-table-column label="公司规模" align="center" prop="eduCompanyScaleId"/>
      <el-table-column label="公司行业" align="center" prop="eduCompanyIndustryId"/>
      <el-table-column label="融资阶段" align="center" prop="eduFinancingStageId"/>
      <el-table-column label="公司简介" align="center" prop="eduCompanyIntroduce"/>
      <el-table-column label="公司详细信息" width="100" align="center" prop="eduInformationId"/>
      <el-table-column label="公司地址" align="center" prop="eduAddressId"/>
      <el-table-column label="创建人" align="center" prop="eduCreateUser"/>
      <el-table-column label="修改人" align="center" prop="eduModifyUser"/>
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
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)"
                     v-hasPermi="['service:company:edit']">修改
          </el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)"
                     v-hasPermi="['service:company:remove']">删除
          </el-button>
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

    <!-- 添加或修改公司对话框 -->
    <el-dialog :title="title" v-model="open" width="600px" append-to-body>
      <el-form ref="companyRef" :model="form" :rules="rules" label-width="90px">
        <el-form-item label="公司名" prop="eduCompanyName">
          <el-input v-model="form.eduCompanyName" placeholder="请输入公司名"/>
        </el-form-item>
        <el-form-item label="公司规模" prop="eduCompanyScaleId">
          <el-select
              v-model="form.eduCompanyScaleId"
              placeholder="请选择公司规模"
              style="width: 240px"
          >
            <el-option
                v-for="item in scaleList"
                :key="item.eduId"
                :label="item.eduScale"
                :value="item.eduId"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="公司行业" prop="eduCompanyIndustryId">
          <el-cascader
              v-model="form.eduCompanyIndustryId"
              :options="industryList"
          />
        </el-form-item>
        <el-form-item label="融资阶段" prop="eduFinancingStageId">
          <el-select
              v-model="form.eduFinancingStageId"
              placeholder="请输入融资阶段"
              style="width: 240px"
          >
            <el-option
                v-for="item in stageList"
                :key="item.eduId"
                :label="item.eduStage"
                :value="item.eduId"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="公司简介" prop="eduCompanyIntroduce">
          <el-input v-model="form.eduCompanyIntroduce" type="textarea" placeholder="请输入内容"/>
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>
    <!-- 添加或修改公司信息对话框 -->
    <el-dialog :title="informationTitle" v-model="informationOpen" width="600px" append-to-body>
      <el-form ref="informationRef" :model="informationForm" :rules="informationRules" label-width="100px">
        <el-form-item label="法定代表人" prop="eduLegalRepresentative">
          <el-input v-model="informationForm.eduLegalRepresentative" placeholder="请输入法定代表人"/>
        </el-form-item>
        <el-form-item label="企业类型" prop="eduEnterpriseType">
          <el-input v-model="informationForm.eduEnterpriseType" placeholder="请输入企业类型"/>
        </el-form-item>
        <el-form-item label="注册资本" prop="eduRegisteredCapital">
          <el-input v-model="informationForm.eduRegisteredCapital" type="number" placeholder="请输入注册资本"/>
        </el-form-item>
        <el-form-item label="社会统一信用码" prop="eduSocialCreditCode">
          <el-input v-model="informationForm.eduSocialCreditCode" placeholder="请输入社会统一信用码"/>
        </el-form-item>
        <el-form-item label="经营期限" prop="eduOperatingPeriod">
          <el-input v-model="informationForm.eduOperatingPeriod" placeholder="请输入经营期限 "/>
        </el-form-item>
        <el-form-item label="所属地区" prop="eduArea">
          <el-input v-model="informationForm.eduArea" placeholder="请输入所属地区"/>
        </el-form-item>
        <el-form-item label="经营状态" prop="eduOperatingStatus">
          <el-input v-model="informationForm.eduOperatingStatus" placeholder="请输入经营状态"/>
        </el-form-item>
        <el-form-item label="登记机关" prop="eduRegistrationAuthority">
          <el-input v-model="informationForm.eduRegistrationAuthority" placeholder="请输入登记机关"/>
        </el-form-item>
        <el-form-item label="注册地址" prop="eduRegisteredAddress">
          <el-input v-model="informationForm.eduRegisteredAddress" placeholder="请输入注册地址"/>
        </el-form-item>
        <el-form-item label="成立时间" prop="eduEstablishmentDate">
          <el-date-picker clearable
                          v-model="informationForm.eduEstablishmentDate"
                          type="datetime"
                          value-format="YYYY-MM-DD HH:mm:ss"
                          placeholder="请选择成立时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="经营范围 " prop="eduBusinessScope">
          <el-input v-model="informationForm.eduBusinessScope" type="textarea" placeholder="请输入内容"/>
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitInformationForm">确 定</el-button>
          <el-button @click="cancelInformationForm">取 消</el-button>
        </div>
      </template>
    </el-dialog>
    <!-- 添加或修改公司地址对话框 -->
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

<script setup name="Company">
import {listCompany, getCompany, delCompany, addCompany, updateCompany} from "@/api/service/company.js";
import {listScale} from "@/api/service/scale.js";
import {listStage} from "@/api/service/stage.js";
import {listIndustryHaveChild} from "@/api/service/industry.js";
import {addInformation, getInformationByCompany, updateInformation} from "@/api/service/information.js";
import {addAddress, getAddressByCompany, updateAddress} from "@/api/service/address.js";
import {getDistrictList} from "@/api/service/provinces.js";
const districtIds = ref("");
const districtList = ref([]);
const addressTitle = ref("");
const addressRules = ref({});
const addressForm = ref({});
const addressOpen = ref(false);
const informationTitle = ref("")
const {proxy} = getCurrentInstance();
const informationForm = ref({});
const companyList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");
const industryList = ref([]);
const scaleList = ref([]);
const stageList = ref([]);
const informationOpen = ref(false);
const informationRules = ref({});
const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    eduCompanyName: null,
    eduCompanyScaleId: null,
    eduCompanyIndustryId: null,
    eduFinancingStageId: null,
    eduCompanyIntroduce: null,
    eduInformationId: null,
    eduAddressId: null,
    eduCreateUser: null,
    eduModifyUser: null,
    eduCreateTime: null,
    eduModifyTime: null
  },
  rules: {}
});

const {queryParams, form, rules} = toRefs(data);

/** 查询公司列表 */
function getList() {
  loading.value = true;
  listCompany(queryParams.value).then(response => {
    companyList.value = response.rows;
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
    eduCompanyName: null,
    eduCompanyScaleId: null,
    eduCompanyIndustryId: null,
    eduFinancingStageId: null,
    eduCompanyIntroduce: null,
    eduInformationId: null,
    eduAddressId: null,
    eduCreateUser: null,
    eduModifyUser: null,
    eduCreateTime: null,
    eduModifyTime: null
  };
  proxy.resetForm("companyRef");
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
  title.value = "添加公司";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _eduId = row.eduId || ids.value
  getCompany(_eduId).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改公司";
  });
}

/** 提交按钮 */
function submitForm() {
  const arr = form.value.eduCompanyIndustryId;
  form.value.eduCompanyIndustryId = arr[arr.length - 1]
  proxy.$refs["companyRef"].validate(valid => {
    if (valid) {
      if (form.value.eduId != null) {
        updateCompany(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addCompany(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除公司编号为"' + _eduIds + '"的数据项？').then(function () {
    return delCompany(_eduIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {
  });
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('service/company/export', {
    ...queryParams.value
  }, `company_${new Date().getTime()}.xlsx`)
}

function getScaleList() {
  listScale().then((res) => {
    scaleList.value = res.rows;
  });
}

function getStageList() {
  listStage().then((res) => {
    stageList.value = res.rows;
  });
}

function getIndustryList() {
  listIndustryHaveChild().then((res) => {
    industryList.value = convertToCascaderFormat(res.data);
  })
}

function convertToCascaderFormat(entities) {
  return entities.map(entity => ({
    value: entity.eduId,
    label: entity.eduIndustry,
    children: entity.child ? convertToCascaderFormat(entity.child) : []
  }));
}

function handleAddress(row) {
  getDistrictList().then((res) => {
    districtList.value = res.data
  })
  getAddressByCompany(ids.value[0]).then((res) => {
    const having = res.data
    addressForm.value.eduCompanyId = ids.value[0]
    if (!having) {
      addressOpen.value = true;
      addressTitle.value = "添加地址信息";
      return;
    }
    addressForm.value = res.data
    addressOpen.value = true;
    addressTitle.value = "修改地址信息";
    console.log(addressForm.value)
  })

}

function submitInformationForm() {
  proxy.$refs["informationRef"].validate(valid => {
    if (valid) {
      if (informationForm.value.eduId != null) {
        updateInformation(informationForm.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          informationOpen.value = false;
          getList();
        });
      } else {
        addInformation(informationForm.value).then(response => {
          proxy.$modal.msgSuccess("新增成功");
          informationOpen.value = false;
          getList();
        });
      }
    }
  });
}

function cancelInformationForm() {
  informationOpen.value = false;
  resetInformationForm();
}

function resetInformationForm() {
  informationForm.value = {
    eduCompanyId: null,
    eduLegalRepresentative: null,
    eduEnterpriseType: null,
    eduOperatingStatus: null,
    eduRegisteredCapital: null,
    eduSocialCreditCode: null,
    eduOperatingPeriod: null,
    eduArea: null,
    eduRegistrationAuthority: null,
    eduRegisteredAddress: null,
    eduEstablishmentDate: null,
    eduBusinessScope: null
  }
}

function handleInformation() {

  getInformationByCompany(ids.value[0]).then((res) => {
    const having = res.data
    informationForm.value.eduCompanyId = ids.value[0]
    if (having == null) {
      informationOpen.value = true;
      informationTitle.value = "添加工商信息";
      return;
    }
    informationForm.value = res.data
    informationOpen.value = true;
    informationTitle.value = "修改工商信息";
  })
}

function submitAddressFormForm() {
  FillAddress();
  proxy.$refs["addressRef"].validate(valid => {
    if (valid) {
      if (addressForm.value.eduId != null) {
        updateAddress(addressForm.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          addressOpen.value = false;
          getList();
        });
      } else {
        addAddress(addressForm.value).then(response => {
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

getScaleList();
getStageList();
getIndustryList();
getList();
</script>
