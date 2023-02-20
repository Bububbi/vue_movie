<template>
  <div>
<!--    顶端导航栏-->
    <Header />
<!--      主体-->
    <div style="display: flex">
<!--      旁侧菜单栏-->
      <Aside />
<!--     内容区域-->
      <router-view style="flex: 1"/>
    </div>
  </div>

</template>

<style>

</style>
<script>
import Header from "@/components/Header";
import Home from "@/views/Home";
import Aside from "@/components/Aside";

export default {
  name: "Layout",
  components: {
    Header,
    Aside,
    Home
  }
}
</script>