<template>

  <!-- 定义布局容器 -->
  <el-container class="home-container">

    <!-- 定义头标签 -->
    <el-header>
      <div>
        <img src="../assets/images/logo.png" />
        <span>京淘电商后台管理系统</span>
      </div>
      <el-button type="info" @click="logout">退出</el-button>
    </el-header>

    <!-- 定义中间区域-->
    <el-container>
      <!-- 当打开左侧菜单时 宽度为200, 当不打开时为默认值-->
      <el-aside :width="isCollapse ? '64px' : '200px' ">
        <!-- 这是左侧菜单-->

        <!--定义折叠项-->
        <div class="leftCollapse" @click="collspseClick">|||</div>

        <!--
          background-color 定义背景色
          text-color="#fff"  定义文字颜色
          active-text-color="#4094ff" 选中元素的颜色
          unique-opened 是否只保持一个子菜单的展开
          collapse      是否水平折叠收起菜单（仅在 mode 为 vertical 时可用）
          collapse-transition	是否开启折叠动画 默认不要展现
          router 是否使用 vue-router 的模式，启用该模式会在激活导航时以 index 作为 path 进行路由跳转
        -->
        <el-menu background-color="#2C3E50" text-color="#fff" active-text-color="#4094ff" unique-opened
        :collapse="isCollapse" :collapse-transition="isCollapseTransition" router  :default-active="defaultActive">

          <!-- 定义一级菜单 -->
          <el-submenu :index="menu.id+''" v-for="menu in menuList" :key="menu.id">
            <!-- 定义一级菜单模版 -->
            <template slot="title">
              <!-- 定义左侧图标-->
              <i :class="menuIcon[menu.id]"></i>
              <!-- 定义菜单名称-->
              <span>{{menu.name}}</span>
            </template>
            <!-- 定义二级菜单 -->
            <el-menu-item :index="childrenMenu.path" v-for="childrenMenu in menu.children" :key="childrenMenu.id"
            @click="defaultActiveMenu(childrenMenu.path)">
              <template slot="title">
                <i class="el-icon-menu"></i>
                <span>{{childrenMenu.name}}</span>
              </template>
            </el-menu-item>
          </el-submenu>
        </el-menu>
      </el-aside>

      <!-- 定义主页面结构-->
      <el-main>
        <!-- 定义路由展现页面-->
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
  export default {
    //初始化函数
    created() {
      //动态获取左侧菜单信息
      this.getMenuList()
      //设定模式选中按钮
      this.defaultActive = window.sessionStorage.getItem("activeMenu")
    },
    data() {
      return {
        //左侧菜单数据
        menuList: [],
        menuIcon: {
          '1': 'iconfont iconuser',
          '3': 'iconfont iconshangpin',
          '5': 'iconfont iconicon--copy',
          '7': 'iconfont iconquanxian',
          '8': 'iconfont iconziyuan'
        },
        //定义是否折叠
        isCollapse: false,
        //是否展现折叠动态效果
        isCollapseTransition: false,
        //定义默认高亮
        defaultActive: ''
      }
    },
    methods: {
      logout() {
        //1.删除session中的数据
        window.sessionStorage.clear()
        //2.用户访问登录页面
        this.$router.push('/login')
      },
      async getMenuList() {
       const {data: result} =  await this.$http.get('/rights/getRightsList')
       if(result.status !== 200) return this.$message.error("左侧菜单查询失败")
       this.menuList = result.data

      },
      //设定左侧折叠展开效果
      collspseClick() {
        this.isCollapse = !this.isCollapse
      },
      defaultActiveMenu(activeMenu){
        //为了实现返回之后的选中效果,应该将数据保存到第三方中sessionStory
        window.sessionStorage.setItem("activeMenu",activeMenu)
        this.defaultActive = activeMenu
      }
    }
  }
</script>

<!-- 防止样式重叠 -->
<style lang="less" scoped>
  .el-header {
    background-color: #2B4B6B;
    display: flex; //灵活的盒子容器
    justify-content: space-between; //左右对齐
    align-items: center; //文本元素居中对齐
    color: #FFFFFF; //设定字体颜色
    padding-left: 1px;

    >div {
      display: flex;
      align-items: center;

      span {
        margin-left: 15px;
        font-size: 25px; //设定字体高度
      }
    }
  }

  .el-aside {
    background-color: #2C3E50;

    .el-menu {
      border-right: none;
    }
  }

  .el-main {
    background-color: #EEEEEE;
  }

  .home-container {
    height: 100%;
    width: 100%;
  }

  .iconfont {
    margin-right: 8px;
  }

  //定义折叠项
  .leftCollapse {
    //设定背景色
    background-color: #708090;
    //定义字体大小
    font-size: 10px;
    //定义行高
    line-height: 30px;
    //定义颜色
    color: #FFFFFF;
    //设置居中
    text-align: center;
    //设定字符间距
    letter-spacing: 4px;
    //鼠标放上之后设置为小手
    cursor: pointer;
  }
</style>
