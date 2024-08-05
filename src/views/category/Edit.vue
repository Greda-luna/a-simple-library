<template>
  <div style="padding: 20px">
    <h2 style="text-align: center;padding:30px">编辑分类</h2>
    <el-form :inline="true" :model="form" style="width: 80%" label-width="200px" :rules="rules">
      <el-form-item label="分类名" prop="name">
        <el-input v-model="form.name" placeholder="请输入分类名称"></el-input>
      </el-form-item>
      <el-form-item label="备注">
        <el-input v-model="form.remark" placeholder="请输入备注"></el-input>
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
  name: 'EditCategory',
  data() {
    return{
      form:{},
    }
  },
  created() {
    const id = this.$route.query.id
    request.get('/category/'+id).then(res =>{
      this.form = res.data
    })
  },
  methods: {
    save() {
      request.put('category/updata', this.form).then(res =>{
        if(res.code === '200'){
          //一个弹窗
          this.$notify.success('更新成功')
          this.$router.push('/categorylist')
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