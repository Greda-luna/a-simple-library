<template>
  <div>
    <!--    搜索表单-->
    <div>
      <el-input style="width: 240px;" placeholder="请输入分类名称" v-model="params.username"></el-input>
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
      <el-table-column prop="name" label="书名"></el-table-column>
      <el-table-column prop="remark" label="备注"></el-table-column>

      <el-table-column label="操作" width="350">
        <template v-slot="scope">
          <el-button type="success" @click="handleAdd(scope.row)">添加二级分类</el-button>
          <el-button type="primary" @click="$router.push('/editcategory?id=' + scope.row.id)" >编辑</el-button>
          <el-popconfirm
              style="margin-left: 15px"
              title="是否确定删除这行信息"
              @confirm="del(scope.row.id)"
          >
            <el-button type="warning" slot="reference">删除</el-button>
          </el-popconfirm>
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

    <el-dialog title="添加二级分类" :visible.sync="dialogFormVisible">
      <el-form :model="form" label-width="100px" ref="formRef" :rules="rules">
        <el-form-item label="分类名称" prop="name">
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="分类备注" prop="remark">
          <el-input v-model="form.remark" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import request from "@/utils/request";
import Cookies from 'js-cookie';

export default {
  name: 'Category',
  data() {
    return {
      category: Cookies.get('category') ? JSON.parse(Cookies.get('category')) : {},
      tableData:[],
      total:0,
      pid:null,
      dialogFormVisible:false,
      form:{},
      params: {
        pageNum:1,
        pageSize:10,
        name : '',
      },
      rules: {
        newPassword: [
          {required: true, message: '输入分类名称', trigger: 'blur'},
        ]
      }
    }
  },
  created() {
    this.load()
  },
  methods:{
    load(){
      request.get('category/page',{
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
      request.delete("category/delete/"+id).then(res =>{
        if(res.code === '200'){
          //一个弹窗
          this.$notify.success('删除成功')
          this.load()
        }else {
          this.$notify.error('删除失败')
        }
      })
    },
    handleAdd(row) {
      this.pid = row.id
      this.dialogFormVisible = true
    },
    save() {
      request.post('category/save', this.form).then(res =>{
        if(res.code === '200'){
          //一个弹窗
          this.$notify.success('保存成功')
          this.form = {}
        }else {
          this.$notify.error('保存失败')
        }
      })
    }

  }

}
</script>

<style scoped>

</style>