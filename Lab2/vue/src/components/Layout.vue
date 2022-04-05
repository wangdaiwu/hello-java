<template>
  <el-container>
    <el-header>
      <Header/>
    </el-header>
    <el-main>
      <router-view/>
    </el-main>
  </el-container>
</template>

<script>
import Header from '@/components/Header'

export default {
  name: 'Layout',
  components: {Header}
}
</script>
