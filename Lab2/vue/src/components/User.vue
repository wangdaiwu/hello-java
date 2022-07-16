<template>
  <div v-loading="loading">
    <el-button icon="el-icon-plus" type="primary" size="mini" @click="handleCreate">添加 User</el-button>

    <el-table :data="userList" stripe>
      <el-table-column prop="username" label="用户名" min-width="100" align="center"/>
      <el-table-column prop="password" label="密码" min-width="100" align="center"/>
      <el-table-column label="操作" width="260" align="center" fixed="right">
        <template v-slot="{row}">
          <el-button plain type="primary" size="mini" icon="el-icon-edit" @click="handleUpdate(row)">编辑</el-button>
          <el-button plain type="danger" size="mini" icon="el-icon-delete" @click="handleDelete(row.username)">删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form :model="dataForm" status-icon :rules="rules" ref="dataForm">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="dataForm.username" :disabled="dialogStatus==='update'"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="dataForm.password"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button icon="el-icon-check" type="success" @click="dialogStatus==='create'?createData():updateData()">完成
        </el-button>
      </div>
    </el-dialog>

    <el-pagination
      background
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
import UserApi from '@/api/user'

export default {
  name: 'User',
  data () {
    const validateUsername = (rule, value, callback) => {
      if (this.dialogStatus === 'create') {
        UserApi.checkUsername(value).then(response => {
          if (response.data.existUsername) {
            callback(new Error('用户名已存在'))
          } else {
            callback()
          }
        })
      } else {
        callback()
      }
    }
    return {
      loading: true,
      userList: null,
      dialogStatus: '',
      textMap: {
        create: '添加 User',
        update: '编辑 User'
      },
      dialogFormVisible: false,
      dataForm: {},
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
        UserApi.getUserList(current, this.page.size).then(response => {
          this.loading = false
          this.page.total = response.data.total
          this.userList = response.data.userList
        }).catch(() => {
          this.loading = false
        })
      })
    },
    handleCreate () {
      this.dialogStatus = 'create'
      this.dataForm = {}
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs.dataForm.clearValidate()
      })
    },
    createData () {
      this.$refs.dataForm.validate(valid => {
        if (valid) {
          UserApi.addUser(this.dataForm).then(() => {
            this.$message.success('添加成功')
            this.getUserList()
            this.dialogFormVisible = false
          })
        }
      })
    },
    handleUpdate (row) {
      this.dialogStatus = 'update'
      this.dataForm = Object.assign({}, row)
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs.dataForm.clearValidate()
      })
    },
    updateData () {
      this.$refs.dataForm.validate(valid => {
        if (valid) {
          UserApi.updateUser(this.dataForm).then(() => {
            this.$message.success('修改成功')
            this.getUserList()
            this.dialogFormVisible = false
          })
        }
      })
    },
    handleDelete (username) {
      this.$confirm('将删除该数据, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        UserApi.deleteUser(username)
          .then(() => {
            this.$message.success('删除成功')
            this.getUserList()
          })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
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
