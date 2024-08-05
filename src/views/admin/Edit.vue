<template>
  <div style="padding: 20px">
    <h2 style="text-align: center;padding:30px">编辑管理员</h2>
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
  name: 'EditUser',
  data() {
    return{
      form:{}
    }
  },
  created() {
    const id = this.$route.query.id
    request.get('/admin/'+id).then(res =>{
      this.form = res.data
    })
  },
  methods: {
    save() {
      request.put('admin/updata', this.form).then(res =>{
        if(res.code === '200'){
          //一个弹窗
          this.$notify.success('更新成功')
          this.$router.push('/adminlist')
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