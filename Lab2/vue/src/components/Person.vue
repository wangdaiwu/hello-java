<template>
  <div v-loading="loading">
    <el-button icon="el-icon-plus" type="primary" size="mini" @click="handleCreate">添加 Person</el-button>

    <el-table :data="personList" stripe width="200">
      <el-table-column prop="username" label="用户名" min-width="100" align="center"/>
      <el-table-column prop="name" label="姓名" min-width="100" align="center"/>
      <el-table-column prop="age" label="年龄" min-width="100" align="center"/>
      <el-table-column prop="phone" label="手机号码" min-width="120" align="center"/>
      <el-table-column label="操作" width="260" align="center" fixed="right">
        <template v-slot="{row}">
          <el-button plain type="primary" size="mini" icon="el-icon-edit" @click="handleUpdate(row)">编辑</el-button>
          <el-button plain type="danger" size="mini" icon="el-icon-delete" @click="handleDelete(row.name)">删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form :model="dataForm" status-icon :rules="rules" ref="dataForm">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="dataForm.username" :disabled="dialogStatus==='update'"></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="name">
          <el-input v-model="dataForm.name" :disabled="dialogStatus==='update'"></el-input>
        </el-form-item>
        <el-form-item label="年龄" prop="age">
          <el-input v-model.number.trim="dataForm.age"></el-input>
        </el-form-item>
        <el-form-item label="手机号" prop="phone">
          <el-input v-model.number.trim="dataForm.phone"></el-input>
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
      @current-change="getPersonList(page.current)"
    />
  </div>
</template>

<script>
import PersonApi from '@/api/person'
import UserApi from '@/api/user'

export default {
  name: 'Person',
  data () {
    const validateUsername = (rule, value, callback) => {
      UserApi.checkUsername(value).then(response => {
        if (response.data.existUsername) {
          callback(new Error('用户名已存在'))
        } else {
          callback()
        }
      })
    }
    const validateName = (rule, value, callback) => {
      PersonApi.checkName(value).then(response => {
        if (response.data.existName) {
          callback(new Error('姓名已存在'))
        } else {
          callback()
        }
      })
    }
    return {
      loading: true,
      personList: null,
      dialogStatus: '',
      textMap: {
        create: '添加 Person',
        update: '编辑 Person'
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
        name: [
          {required: true, message: '请输入姓名', trigger: 'blur'},
          {max: 20, message: '不超过 20 个字符', trigger: 'blur'},
          {validator: validateName, trigger: 'blur'}
        ]
      }
    }
  },
  mounted () {
    this.getPersonList()
  },
  methods: {
    getPersonList (current = 1) {
      this.loading = true
      this.$nextTick(() => {
        PersonApi.getPersonList(current, this.page.size).then(response => {
          this.loading = false
          this.page.total = response.data.total
          this.personList = response.data.personList
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
          PersonApi.addPerson(this.dataForm).then(() => {
            this.$message.success('添加成功')
            this.getPersonList()
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
      PersonApi.updatePerson(this.dataForm).then(() => {
        this.$message.success('修改成功')
        this.getPersonList()
        this.dialogFormVisible = false
      })
    },
    handleDelete (name) {
      this.$confirm('将删除该数据, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        PersonApi.deletePerson(name).then(() => {
          this.$message.success('删除成功')
          this.getPersonList()
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
