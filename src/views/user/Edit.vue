<template>
  <div style="padding: 20px">
    <h2 style="text-align: center;padding:30px">编辑会员</h2>
    <el-form :inline="true" :model="form" style="width: 80%" label-width="200px">
      <el-form-item label="卡号">
        <el-input v-model="form.username" disabled placeholder="请输入卡号"></el-input>
      </el-form-item>
      <el-form-item label="姓名">
        <el-input v-model="form.name" placeholder="请输入姓名"></el-input>
      </el-form-item>
      <el-form-item label="年龄">
        <el-input v-model="form.age" placeholder="请输入年龄"></el-input>
      </el-form-item>
      <el-form-item label="性别">
        <el-input v-model="form.sex" placeholder="请输入性别"></el-input>
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
  name: 'EditUser',
  data() {
    return{
      form:{}
    }
  },
  created() {
    const id = this.$route.query.id
    request.get('/user/'+id).then(res =>{
      this.form = res.data
    })
  },
  methods: {
    save() {
      request.put('user/updata', this.form).then(res =>{
        if(res.code === '200'){
          //一个弹窗
          this.$notify.success('更新成功')
          this.$router.push('/user')
        }else {
          this.$notify.error('更新失败')
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