<template>
  <div style="padding: 20px">
    <h2 style="text-align: center;padding:30px">新增管理员</h2>
    <el-form :inline="true" :model="form" style="width: 80%" label-width="200px" :rules="rules">
      <el-form-item label="用户名" prop="username">
        <el-input v-model="form.username" placeholder="请输入用户名"></el-input>
      </el-form-item>
      <el-form-item label="邮箱">
        <el-input v-model="form.email" placeholder="请输入邮箱"></el-input>
      </el-form-item>
      <el-form-item label="联系方式">
        <el-input v-model="form.phone" placeholder="请输入联系方式"></el-input>
      </el-form-item>
    </el-form>

    <div style="text-align: center">
      <el-button type="primary" @click="save">提交</el-button>
      <el-button type="warning" @click="reset">重置</el-button>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";
export default {
  name: 'AddUser',
  data() {
    return {
      form: {},
      rules: {
        name: [
          {required: true, message: '请输入用户名', trigger: 'blur'},
        ]
      }
    }
  },
  methods: {
    save() {
      request.post('admin/save', this.form).then(res =>{
        if(res.code === '200'){
          //一个弹窗
          this.$notify.success('新增成功')
          this.form = {}
        }else {
          this.$notify.error('新增失败')
        }
      })
    },
    reset(){
      this.form = {}
    }
  }
}
</script>

<style scoped>

</style>