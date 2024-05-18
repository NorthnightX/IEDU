<script setup>
import {onMounted} from "vue";
import router from "@/router/index.js";
import {getNews} from "@/api/service/news.js";
import {getArticle} from "@/api/service/article.js";
import top from "../../components/header/header.vue"
import Bottom from "@/components/bottom/bottom.vue";
const article = ref("")
function getMessage(eduId, type){
  if(type === 'news'){
    getNews(eduId).then((res) =>{
      article.value = res.data
    })
  } else {
    getArticle(eduId).then((res) => {
      article.value = res.data
    })
  }

}
onMounted(() => {
  var eduId = router.currentRoute.value.query.eduId;
  var type = router.currentRoute.value.query.type;
  getMessage(eduId, type)
})

</script>

<template>

  <div style=";height: auto;min-width:1500px;background: linear-gradient(#E0F1F3, #F8F8F8);">
    <top></top>
    <div style="display: flex;align-items: center;justify-content: center">
      <div style=";background-color: white;
    margin: 20px;min-height: 700px;
    display: flex;align-items: center;flex-direction: column;
    min-width: 65%;overflow: hidden;max-width: 68%;
    box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);border-radius: 10px;">
        <el-col  :xs="20" :sm="20" :md="16" :lg="20" :xl="20" style="padding-top: 20px">
          <el-text  style="font-size: 1.5rem" v-html="article.eduTitle"></el-text>
        </el-col>
        <div style="width: 90%;padding-bottom: 20px;padding-top: 40px">
          <el-text size="large" v-html="article.eduContent" class="mx-1"></el-text>
          <el-text size="large" v-html="article.eduPublishTime" class="mx-1"></el-text>
        </div>
        <div style="width: 90%;padding-bottom: 20px">

        </div>
      </div>
    </div>
    <bottom></bottom>
  </div>
</template>

<style scoped lang="scss">

</style>
