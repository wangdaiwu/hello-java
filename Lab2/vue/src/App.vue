<template>
  <div id="app">
    <el-container>
<!--      <el-header>-->
        <!--        <Header/>-->
<!--        <div>main header</div>-->
<!--      </el-header>-->
      <el-main>
        <router-view/>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import Header from '@/components/Header'

export default {
  name: 'App',
  components: {Header}
}
</script>
