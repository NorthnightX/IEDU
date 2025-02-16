<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="新闻标题" prop="eduTitle">
        <el-input
          v-model="queryParams.eduTitle"
          placeholder="请输入新闻标题"
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
          v-hasPermi="['service:news:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['service:news:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['service:news:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['service:news:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="newsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="新闻ID" align="center" prop="eduId" />
      <el-table-column label="新闻标题" align="center" prop="eduTitle">
        <template #default="{ row }">
          <el-tooltip effect="dark" :content="row.eduTitle" placement="top-start" trigger="hover">
            <span>{{ truncateText(row.eduTitle, 5) }}</span>
          </el-tooltip>
        </template>
      </el-table-column>
      <el-table-column label="发布者" align="center" prop="eduPublisher" />
      <el-table-column label="发布时间" align="center" prop="eduPublishTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.eduPublishTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="内容" align="center" prop="eduContent">
        <template #default="{ row }">
          <el-button type="text" @click="showDetailDialog(row.eduContent)">
            点击查看详情
          </el-button>
        </template>
      </el-table-column>
      <el-table-column label="创建者" align="center" prop="eduCreateUser" />
      <el-table-column label="修改者" align="center" prop="eduModifyUser" />
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
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['service:news:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['service:news:remove']">删除</el-button>
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

    <!-- 添加或修改新闻对话框 -->
    <el-dialog :title="title" v-model="open" width="700px" append-to-body>
      <el-form ref="newsRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="新闻标题" prop="eduTitle">
          <el-input v-model="form.eduTitle" placeholder="请输入新闻标题" />
        </el-form-item>
        <el-form-item label="发布者" prop="eduPublisher">
          <el-input v-model="form.eduPublisher" placeholder="请输入发布者" />
        </el-form-item>
        <el-form-item label="发布时间" prop="eduPublishTime">
          <el-date-picker clearable
            v-model="form.eduPublishTime"
            type="datetime"
            value-format="YYYY-MM-DD HH:mm:ss"
            placeholder="请选择发布时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="内容">
          <editor v-model="form.eduContent" :min-height="192"/>
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
<script setup name="News">
import { listNews, getNews, delNews, addNews, updateNews } from "@/api/service/news";
import {ElMessageBox} from "element-plus";

const { proxy } = getCurrentInstance();

const newsList = ref([]);
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
    eduTitle: null,
    eduPublisher: null,
    eduPublishTime: null,
    eduContent: null,
    eduCreateUser: null,
    eduModifyUser: null,
    eduCreateTime: null,
    eduModifyTime: null
  },
  rules: {
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询新闻列表 */
function getList() {
  loading.value = true;
  listNews(queryParams.value).then(response => {
    newsList.value = response.rows;
    total.value = response.total;
    loading.value = false;
  });
}

/** 显示新闻详情*/
function showDetailDialog(content){
  ElMessageBox.alert(
      content,
      "新闻内容",
      {
        dangerouslyUseHTMLString: true,
        customStyle: {'max-width': '70%'}
      }
  )
}
const truncateText = (text, maxLength) => {
  if (text.length > maxLength) {
    return text.slice(0, maxLength) + '...';
  } else {
    return text;
  }
};

// 取消按钮
function cancel() {
  open.value = false;
  reset();
}

// 表单重置
function reset() {
  form.value = {
    eduId: null,
    eduTitle: null,
    eduPublisher: null,
    eduPublishTime: null,
    eduContent: null,
    eduCreateUser: null,
    eduModifyUser: null,
    eduCreateTime: null,
    eduModifyTime: null
  };
  proxy.resetForm("newsRef");
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
  title.value = "添加新闻";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _eduId = row.eduId || ids.value
  getNews(_eduId).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改新闻";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["newsRef"].validate(valid => {
    if (valid) {
      if (form.value.eduId != null) {
        updateNews(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addNews(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除新闻编号为"' + _eduIds + '"的数据项？').then(function() {
    return delNews(_eduIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('service/news/export', {
    ...queryParams.value
  }, `news_${new Date().getTime()}.xlsx`)
}

getList();
</script>
