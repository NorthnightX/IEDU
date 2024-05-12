<script setup>
import top from  '@/components/header/header.vue'
import bottom from '@/components/bottom/bottom.vue'
import {onMounted} from "vue";
import router from "@/router/index.js";
import {useRoute} from "vue-router";
const text = ref("")
onMounted(() => {
  text.value = router.currentRoute.value.query.text;

})
const route = useRoute();
watch(() => route.query.text, (newValue, oldValue) => {
  text.value = newValue
});

function searchMessage(){

}
</script>

<template>
  <div style="min-width: 1500px;height: auto;background-color: #EFF9F9">
    <top></top>
    <div style="width: 100%;display: flex;justify-content: space-around;;margin: 20px 0;">
      <div style="width: 76%;">
        <div style="display: flex;justify-content: right">
          <div style="height: 250px;width: 88%;background-color: white;margin: 5px;border-radius: 10px">
            <div >

            </div>
          </div>
        </div>
        <div v-for="i in 10" style="display: flex;justify-content: right">
          <div style="height: 100px;width: 88%;background-color: white;margin: 5px;border-radius: 10px">

          </div>
        </div>
      </div>
      <div style="flex-direction: column;width: 24%;margin: 5px 0 0 2%;">
        <div style="height: 300px;width: 70%;border-radius: 10px;background-color: white">
          <div>
            <img src="@/assets/background/search_right1.png" alt="" style="width: 100%">
          </div>
        </div>
        <div style="height: 300px;width: 70%;border-radius: 10px;background-color: white;margin-top: 20px">
          <div>
            <img src="@/assets/background/search_right2.png" alt="" style="width: 100%">
          </div>
        </div>
      </div>
    </div>
    <div style="display: flex;align-items: center;justify-content: center">
      <el-pagination background layout="prev, pager, next" :total="1000" :pager-count="10" style="margin: 10px 0"/>
    </div>
    <bottom></bottom>
  </div>
</template>

<style scoped>

</style>
