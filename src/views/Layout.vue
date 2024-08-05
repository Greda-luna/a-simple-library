<template>
  <div>
    <!--    头部区域-->
    <div style="height: 60px;line-height: 60px;background-color: white;margin-bottom: 2px;display: flex">
      <div style="width: 300px">
        <img src="@/assets/pictures/libary_logo.png" alt="" style="width: 70px;position: relative;top: 10px;left: 20px">
        <span style="margin-left: 20px;font-size: 24px">图书管理系统</span>
      </div>
      <div style="flex: 1;text-align: right;padding-right: 20px" >
        <el-dropdown size="small">
          <span class="el-dropdown-link">
            {{admin.username}}
            <i class="el-icon-arrow-down el-icon--right"></i>
          </span>
          <el-dropdown-menu slot="dropdown" style="margin-top: -10px">
            <el-dropdown-item>
              <div style="width: 50px;text-align: center;" @click="logout">
                退出
              </div>
            </el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
    </div>
    <!--    侧边栏和主体-->
    <div style="display: flex ">
      <!--    侧边栏导航-->
      <div style="width: 200px;min-height:calc(100vh - 62px);overflow: hidden;margin-right: 2px;background-color: white">
        <el-menu :default-active="$route.path"  router class="el-menu-demo">
          <el-menu-item index="home">
            <i class="el-icon-house"></i>
            <span>首页</span>
          </el-menu-item>
          <el-submenu index="2">
            <template slot="title">
              <i class="el-icon-eleme"></i>
              <span>会员管理</span>
            </template>
            <el-menu-item index="/userlist">会员列表</el-menu-item>
            <el-menu-item index="/adduser">会员添加</el-menu-item>
          </el-submenu>

          <el-submenu index="3">
            <template slot="title">
              <i class="el-icon-eleme"></i>
              <span>管理员</span>
            </template>
            <el-menu-item index="/adminlist">管理员列表</el-menu-item>
            <el-menu-item index="/addadmin">管理员添加</el-menu-item>
          </el-submenu>

          <el-submenu index="4">
            <template slot="title">
              <i class="el-icon-eleme"></i>
              <span>图书分类</span>
            </template>
            <el-menu-item index="/categorylist">分类列表</el-menu-item>
            <el-menu-item index="/addcategory">分类添加</el-menu-item>
          </el-submenu>
        </el-menu>
      </div>
      <div style="flex: 1;background-color: white;padding: 10px">
        <router-view/>
      </div>
    </div>
</div>
</template>

<script>
import Cookies from "js-cookie";

export default {
  name: 'Layout',
  data() {
    return {
      admin: Cookies.get('admin') ? JSON.parse(Cookies.get('admin')) : {}
    }
  },
  methods: {
    logout() {
      //清除浏览器信息
      Cookies.remove('admin')
      this.$router.push('/login')
    }
  }
}
</script>


<style scoped>

</style>