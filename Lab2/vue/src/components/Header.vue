<template>
  <el-menu :default-active="$route.path"
           class="el-menu-demo"
           mode="horizontal"
           router>
    <el-menu-item index="/user">User Table</el-menu-item>
    <el-menu-item index="/person">Person Table</el-menu-item>
  </el-menu>
</template>

<script>
export default {
  name: 'Header',
  data () {
    return {}
  },
  methods: {
    // handleSelect () {
    //   this.$router.push({path: 'person'})
    // },
    // handleClick1 () {
    //   this.$router.push({path: '/user'})
    // },
    // handleClick2 () {
    //   this.$router.push({path: '/person'})
    // }
  }
}
</script>
