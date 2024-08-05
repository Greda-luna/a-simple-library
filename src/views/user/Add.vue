<template>
  <div style="padding: 20px">
    <h2 style="text-align: center;padding:30px">新增会员</h2>
    <el-form :inline="true" :model="form" style="width: 80%" label-width="200px" :rules="rules">
      <el-form-item label="姓名" prop="name">
        <el-input v-model="form.name" placeholder="请输入姓名"></el-input>
      </el-form-item>
      <el-form-item label="年龄">
        <el-input v-model="form.age" placeholder="请输入年龄"></el-input>
      </el-form-item>
      <el-form-item label="性别">
        <el-radio label="男" v-model="form.sex">男</el-radio>
        <el-radio label="女" v-model="form.sex">女</el-radio>
      </el-form-item>
      <el-form-item label="地址">
        <el-input v-model="form.address" placeholder="请输入地址"></el-input>
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
          {required: true, message: '请输入姓名', trigger: 'blur'},
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