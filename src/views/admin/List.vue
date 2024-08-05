<template>
  <div>
    <!--    搜索表单-->
    <div>
      <el-input style="width: 240px;" placeholder="请输入用户名" v-model="params.username"></el-input>
      <el-input style="width: 240px; margin-left: 10px" placeholder="请输入联系方式" v-model="params.phone"></el-input>
      <el-input style="width: 240px;" placeholder="请输入邮箱" v-model="params.email"></el-input>
      <el-button style="margin-left: 5px" type="primary" @click="load">
        <i class="el-icon-search"></i>
        搜索
      </el-button>
      <el-button style="margin-left: 5px" type="warning" @click="reset">
        <i class="el-icon-refresh"></i>
        重置
      </el-button>
    </div>
    <el-table :data="tableData" stripe>
      <el-table-column prop="id" label="编号"></el-table-column>
      <el-table-column prop="username" label="卡号"></el-table-column>
      <el-table-column prop="email" label="邮箱"></el-table-column>
      <el-table-column prop="phone" label="联系方式"></el-table-column>

      <el-table-column>
        <template v-slot="scope">
          <el-button type="primary" @click="$router.push('/editadmin?id=' + scope.row.id)" >编辑</el-button>
          <el-popconfirm
              style="margin-left: 15px"
              title="是否确定删除这行信息"
              @confirm="del(scope.row.id)"
          >
            <el-button type="warning" slot="reference">删除</el-button>
          </el-popconfirm>
          <el-button type="danger" slot="reference" @click="handlechangepassword(scope.row)" style="margin-left: 15px">修改密码</el-button>
        </template>
      </el-table-column>
    </el-table>


    <!--    分页-->
    <div style="margin-top: 20px">
      <el-pagination
          @current-change="handleCurrentChange"
          :page-size="params.pageSize"
          :current-page="params.pageNum"
          background
          layout="prev, pager, next"
          :total="total">
      </el-pagination>
    </div>

    <el-dialog title="修改密码" :visible.sync="dialogFormVisible">
      <el-form :model="form" label-width="100px" ref="formRef" :rules="rules">
        <el-form-item label="新密码" prop="newPassword">
          <el-input v-model="form.newPassword" autocomplete="off" show-password></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="savePassword">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import request from "@/utils/request";
import Cookies from 'js-cookie';

export default {
  name: 'User',
  data() {
    return {
      admin: Cookies.get('admin') ? JSON.parse(Cookies.get('admin')) : {},
      tableData:[],
      total:0,
      form:{},
      dialogFormVisible:false,
      params: {
        pageNum:1,
        pageSize:10,
        name : '',
        phone: '',
        email:''
      },
      rules: {
        newPassword: [
          {required: true, message: '请输入新密码', trigger: 'blur'},
        ]
      }
    }
  },
  created() {
    this.load()
  },
  methods:{
    handlechangepassword(row){
      this.form = JSON.parse(JSON.stringify(row))
      this.dialogFormVisible = true
    },
    savePassword(){
      this.$refs['formRef'].validate((valid) => {
        if (valid) {
          request.put('/admin/password',this.form).then(res => {
            if (this.code === '200') {
              this.$notify.success('修改成功')
              if (this.form.id === this.id) {
                //只能修改当前登录的管理员账号，且修改之后需要重新登录
                Cookies.remove('admin')
                this.$router.push('login')
              }
            }else {
              this.$notify.error('修改失败')
            }
          })
        }
    })
    },
    load(){
      request.get('admin/page',{
        params:this.params
      }).then(res => {
        this.tableData = res.data.list
        this.total = res.data.total
      })
    },
    reset(){
      this.params = {
        pageNum:1,
        pageSize:10,
        name : '',
        phone: '',
      }
      this.load()
    },
    handleCurrentChange(pageNum){
      //触发分页
      this.params.pageNum = pageNum
      this.load()
    },
    del(id){
      request.delete("admin/delete/"+id).then(res =>{
        if(res.code === '200'){
          //一个弹窗
          this.$notify.success('删除成功')
          this.load()
        }else {
          this.$notify.error('删除失败')
        }
      })
    },

  }

}
</script>

<style scoped>

</style>