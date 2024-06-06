<script setup>
import {getAge, getIndustry, getRType, getTop, getWorkType} from '@/api/service/charts.js';
import { use } from 'echarts/core';
import { CanvasRenderer } from 'echarts/renderers';
import { PieChart, BarChart } from 'echarts/charts';
import {
  TitleComponent,
  TooltipComponent,
  LegendComponent,
} from 'echarts/components';
import VChart, { THEME_KEY } from 'vue-echarts';
import { ref, provide } from 'vue';

use([
  CanvasRenderer,
  PieChart,
  BarChart,
  TitleComponent,
  TooltipComponent,
  LegendComponent,
]);

const recordAge = ref({
  title: {
    text: '投递者年龄比例',
    left: 'center',
  },
  tooltip: {
    trigger: 'item',
    formatter: '{b} : {c} ({d}%)',
  },

  series: [
    {
      type: 'pie',
      radius: '55%',
      data: [
      ],
      emphasis: {
        itemStyle: {
          shadowBlur: 10,
          shadowOffsetX: 0,
          shadowColor: 'rgba(0, 0, 0, 0.5)',
        },
      },
    },
  ],
});
function getRecordAge(){
  var arr = []
  getAge().then((res) => {
    res.data.forEach(item => {
      arr.push({name: item.span, value: item.v})
    })
    recordAge.value.series[0].data = arr;
  })
}
getRecordAge()
const jobType = ref({
  title: {
    text: '岗位类型比例',
    left: 'center',
  },
  tooltip: {
    trigger: 'item',
    formatter: '{b} : {c} ({d}%)',
  },

  series: [
    {
      type: 'pie',
      radius: '55%',

      data: [
        { value: 335, name: 'Direct' },
        { value: 310, name: 'Email' },
        { value: 234, name: 'Ad Networks' },
        { value: 135, name: 'Video Ads' },
        { value: 1548, name: 'Search Engines' },
      ],
      emphasis: {
        itemStyle: {
          shadowBlur: 10,
          shadowOffsetX: 0,
          shadowColor: 'rgba(0, 0, 0, 0.5)',
        },
      },
    },
  ],
});

function getJobType(){
  var arr = []
  getWorkType().then((res) => {
    res.data.forEach(item => {
      arr.push({name: item.span, value: item.v})
    })
    jobType.value.series[0].data = arr;
  })
}
getJobType()
const recordType = ref({
  title: {
    text: '投递岗位类型比例',
    left: 'center',
  },
  tooltip: {
    trigger: 'item',
    formatter: '{b} : {c} ({d}%)',
  },

  series: [
    {
      type: 'pie',
      radius: '55%',

      data: [
        { value: 335, name: 'Direct' },
        { value: 310, name: 'Email' },
        { value: 234, name: 'Ad Networks' },
        { value: 135, name: 'Video Ads' },
        { value: 1548, name: 'Search Engines' },
      ],
      emphasis: {
        itemStyle: {
          shadowBlur: 10,
          shadowOffsetX: 0,
          shadowColor: 'rgba(0, 0, 0, 0.5)',
        },
      },
    },
  ],
});
function getRecordType(){
  var arr = []
  getRType().then((res) => {
    res.data.forEach(item => {
      arr.push({name: item.span, value: item.v})
    })
    recordType.value.series[0].data = arr;
  })
}
getRecordType()
const companyIndustry = ref({
  title: {
    text: '企业行业比例',
    left: 'center',
  },
  tooltip: {
    trigger: 'item',
    formatter: '{b} : {c} ({d}%)',
  },

  series: [
    {
      type: 'pie',
      radius: '55%',

      data: [
      ],
      emphasis: {
        itemStyle: {
          shadowBlur: 10,
          shadowOffsetX: 0,
          shadowColor: 'rgba(0, 0, 0, 0.5)',
        },
      },
    },
  ],
});
function getCompanyIndustry(){
  var arr = []
  getIndustry().then((res) => {
    res.data.forEach(item => {
      arr.push({name: item.span, value: item.v})
    })
    companyIndustry.value.series[0].data = arr;
  })
}
getCompanyIndustry()

const todayRecruitNum = ref(0)
const articleProportion = ref([])
const operateProportion = ref([])
function getTopData(){
  getTop().then((res) => {
    todayRecruitNum.value = res.data[2][0]
    articleProportion.value = res.data[0]
    operateProportion.value = res.data[1]
  })

}
getTopData()
</script>

<template>
  <div style="width: 100%;display: flex;align-items: center;justify-content: center;margin-top: 40px">
    <el-card style="width: 80%;display: flex;justify-content: space-around;">
      <div>
        <el-statistic style="text-align: center" title="今日简历投递数量" :value="todayRecruitNum" />
      </div>
      <div>
        <el-statistic  style="text-align: center"  :value="operateProportion[0]">
          <template #title>
            <div style="display: inline-flex; align-items: center">
              合作资源数量比(企业，院校)
            </div>
          </template>
          <template #suffix>/ {{operateProportion[1]}}</template>
        </el-statistic>
      </div>
      <div>
        <el-statistic style="text-align: center"  :value="articleProportion[0]">
          <template #title>
            <div style="display: inline-flex; align-items: center">
              新闻动态数量比
            </div>
          </template>
          <template #suffix>/ {{ articleProportion[1] }}</template>
        </el-statistic>
      </div>
    </el-card>
  </div>
  <div style="display:flex;margin-top: 50px">
    <v-chart class="chart" :option="companyIndustry" autoresize />
    <v-chart class="chart" :option="jobType" autoresize />
  </div>
  <div style="display:flex;margin-top: 50px;margin-bottom: 50px">
    <v-chart class="chart" :option="recordAge" autoresize />
    <v-chart class="chart" :option="recordType" autoresize />

  </div>
</template>


<style scoped lang="scss">
.chart {
  height: 300px;
}
</style>
