<template>
  <div v-loading="loading">
    <el-button icon="el-icon-plus" type="primary" @click="handleClickAddButton">添加 User</el-button>

    <el-table
      :data="userList"
      stripe
      width="200px">
      <el-table-column
        prop="username"
        label="用户名">
      </el-table-column>
      <el-table-column
        prop="password"
        label="密码">
      </el-table-column>
      <el-table-column
        fixed="right"
        label="操作"
        width="200px"
        align="center">
        <template v-slot="scope">
          <el-button plain type="primary" @click="handleClickEditButton(scope.row.username)">编辑</el-button>
          <el-button plain type="danger" @click="handleClickDeleteButton(scope.row.username)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog :title="dialogTitle" :visible.sync="dialogFormVisible">
      <el-form :model="userForm" status-icon :rules="rules" ref="userForm">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="userForm.username" :disabled="isUpdating"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="userForm.password"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button icon="el-icon-check" type="success" @click="beforeSubmitForm">完成</el-button>
      </div>
    </el-dialog>

    <el-pagination
      :current-page.sync="page.current"
      :page-size="page.size"
      :total="page.total"
      style="padding: 30px 0; text-align: center;"
      layout="total, prev, pager, next, jumper"
      @current-change="getUserList(page.current)"
    />
  </div>
</template>

<script>
import userApi from '@/api/user'

export default {
  name: 'User',
  data () {
    const validateUsername = (rule, value, callback) => {
      if (userApi.checkUsername(value)) {
        callback(new Error('用户名已存在'))
      }
    }
    return {
      loading: true,
      userList: null,
      dialogTitle: '',
      dialogFormVisible: false,
      isUpdating: false,
      userForm: {},
      page: {
        current: 1,
        size: 5,
        total: 0
      },
      rules: {
        username: [
          {required: true, message: '请输入用户名', trigger: 'blur'},
          {max: 10, message: '不超过 10 个字符', trigger: 'blur'},
          {validator: validateUsername, trigger: 'blur'}
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          {max: 10, message: '不超过 12 个字符', trigger: 'blur'}
        ]
      }
    }
  },
  mounted () {
    this.getUserList()
  },
  methods: {
    getUserList (current = 1) {
      this.loading = true
      this.$nextTick(() => {
        userApi.getUserList(current, this.page.size)
          .then(response => {
            this.loading = false
            this.page.total = response.data.total
            this.userList = response.data.userList
          })
      })
    },
    handleClickAddButton () {
      this.dialogTitle = '添加 User'
      this.userForm = {}
      this.isUpdating = false
      this.dialogFormVisible = true
    },
    handleClickEditButton (username) {
      this.dialogTitle = '编辑 User'
      userApi.getUser(username)
        .then(response => {
          this.userForm = response.data.user
        })
      this.isUpdating = true
      this.dialogFormVisible = true
    },
    beforeSubmitForm () {
      this.$refs.userForm.validate(valid => {
        if (valid) {
          this.submitForm()
        } else {
          return false
        }
      })
    },
    submitForm () {
      this.dialogFormVisible = false
      if (this.isUpdating) {
        userApi.updateUser(this.userForm)
          .then(() => {
            this.$message.success('修改成功')
            this.getUserList()
          })
      } else {
        userApi.addUser(this.userForm)
          .then(() => {
            this.$message.success('添加成功')
            this.getUserList()
          })
      }
    },
    handleClickDeleteButton (username) {
      this.$confirm('将删除该数据, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        userApi.deleteUser(username)
          .then(() => {
            this.$message.success('删除成功')
            this.getUserList()
          })
      })
        .catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          })
        })
    }
  },
  watch: {
    dialogFormVisible () {
      this.$refs.userForm.clearValidate()
    }
  }
}
</script>

<style>

el-footer {
  display: flex;
  height: 4vh;
  width: 100%;
  align-items: center;
}

</style>
