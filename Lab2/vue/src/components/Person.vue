<template>
  <div v-loading="loading">
    <el-button icon="el-icon-plus" type="primary" @click="handleClickAddButton">添加 Person</el-button>

    <el-table
      :data="personList"
      stripe
      width="200px">
      <el-table-column
        prop="username"
        label="用户名">
      </el-table-column>
      <el-table-column
        prop="name"
        label="姓名">
      </el-table-column>
      <el-table-column
        prop="age"
        label="年龄">
      </el-table-column>
      <el-table-column
        prop="phone"
        label="手机号码">
      </el-table-column>
      <el-table-column
        fixed="right"
        label="操作"
        width="200px"
        align="center">
        <template v-slot="scope">
          <el-button plain type="primary" @click="handleClickEditButton(scope.row.name)">编辑</el-button>
          <el-button plain type="danger" @click="handleClickDeleteButton(scope.row.name)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog :title="dialogTitle" :visible.sync="dialogFormVisible">
      <el-form :model="personForm" status-icon :rules="rules" ref="personForm">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="personForm.username" :disabled="isUpdating"></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="name">
          <el-input v-model="personForm.name" :disabled="isUpdating"></el-input>
        </el-form-item>
        <el-form-item label="年龄" prop="age">
          <el-input v-model.number.trim="personForm.age"></el-input>
        </el-form-item>
        <el-form-item label="手机号" prop="phone">
          <el-input v-model.number.trim="personForm.phone"></el-input>
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
      @current-change="getPersonList(page.current)"
    />
  </div>
</template>

<script>
import personApi from '@/api/person'
import userApi from '@/api/user'

export default {
  name: 'Person',
  data () {
    const validateUsername = (rule, value, callback) => {
      if (userApi.checkUsername(value)) {
        callback(new Error('用户名已存在'))
      }
    }
    const validateName = (rule, value, callback) => {
      if (personApi.checkName(value)) {
        callback(new Error('姓名已存在'))
      }
    }
    return {
      loading: true,
      personList: null,
      dialogTitle: '',
      dialogFormVisible: false,
      isUpdating: false,
      personForm: {},
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
        personApi.getPersonList(current, this.page.size)
          .then(response => {
            this.loading = false
            this.page.total = response.data.total
            this.personList = response.data.personList
          })
      })
    },
    handleClickAddButton () {
      this.dialogTitle = '添加 Person'
      this.personForm = {}
      this.isUpdating = false
      this.dialogFormVisible = true
    },
    handleClickEditButton (name) {
      this.dialogTitle = '编辑 Person'
      personApi.getPerson(name)
        .then(response => {
          this.personForm = response.data.person
        })
      this.isUpdating = true
      this.dialogFormVisible = true
    },
    beforeSubmitForm () {
      this.$refs.personForm.validate(valid => {
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
        personApi.updatePerson(this.personForm)
          .then(() => {
            this.$message.success('修改成功')
            this.getPersonList()
          })
      } else {
        personApi.addPerson(this.personForm)
          .then(() => {
            this.$message.success('添加成功')
            this.getPersonList()
          })
      }
    },
    handleClickDeleteButton (name) {
      this.$confirm('将删除该数据, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        personApi.deletePerson(name)
          .then(() => {
            this.$message.success('删除成功')
            this.getPersonList()
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
      this.$refs.personForm.clearValidate()
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
