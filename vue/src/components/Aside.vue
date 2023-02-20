<template>
  <el-menu
      default-active="2"
      class="el-menu-vertical-demo"
      style="width: 150px; min-height: calc(100vh - 50px)">
    <el-menu-item index="1">
      <el-icon><Location /></el-icon>
      <span>首页</span>
    </el-menu-item>
    <el-menu-item index="2">
      <el-icon><icon-menu /></el-icon>
      <span>电影</span>
    </el-menu-item>
    <el-menu-item index="3" disabled>
      <el-icon><document /></el-icon>
      <span>美剧</span>
    </el-menu-item>
    <el-menu-item index="4">
      <el-icon><setting /></el-icon>
      <span>韩剧</span>
    </el-menu-item>
  </el-menu>
</template>

<script>
import {Document,Menu as IconMenu, Location, Setting} from "@element-plus/icons"
export default {
  name: "Aside",
  components: {
    IconMenu,Location,Setting,Document
  }
}
</script>

<style scoped>

</style>