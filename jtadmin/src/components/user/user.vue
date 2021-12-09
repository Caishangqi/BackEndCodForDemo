<template>
  <div>

    <!-- 1.定义面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>用户管理</el-breadcrumb-item>
      <el-breadcrumb-item>用户列表</el-breadcrumb-item>
    </el-breadcrumb>

    <!-- 2.定义卡片视图 -->
    <el-card class="box-card">

       <!-- 3.定义栅格的一行 :gutter="20"定义行间距   :span="9" 占位符  -->
       <el-row :gutter="20">
         <el-col :span="9">
            <!-- 3.定义搜索框 -->
            <el-input placeholder="请输入内容" v-model="queryInfo.query" clearable  @clear="getUserList">
                <el-button slot="append" icon="el-icon-search" @click="getUserList"></el-button>
            </el-input>
         </el-col>
         <el-col :span="4">
            <!-- 定义添加按钮-->
            <el-button type="primary" @click="dialogVisible = true">添加用户</el-button>
         </el-col>
       </el-row>

       <!-- 用户数据表格展现  border 边框线属性  -->
       <el-table :data="userList" border stripe>
           <el-table-column type="index" label="序号"></el-table-column>
           <el-table-column prop="username" label="用户名"></el-table-column>
           <el-table-column prop="phone" label="电话"></el-table-column>
           <el-table-column prop="email" label="邮箱"></el-table-column>
           <el-table-column prop="status" label="状态">
             <template slot-scope="scope">
                <el-switch v-model="scope.row.status" @change="updateStatus(scope.row)"
                  active-color="#13ce66" inactive-color="#ff4949">
                </el-switch>
             </template>
           </el-table-column>
           <el-table-column label="操作">
             <template slot-scope="scope">
                <el-button type="primary" icon="el-icon-edit" size="small" @click="updateUserBtn(scope.row)"></el-button>
                <el-button type="danger" icon="el-icon-delete" size="small" @click="deleteUser(scope.row)"></el-button>
             </template>

           </el-table-column>
       </el-table>

       <!-- 定义分页组件 关于分页插件的属性说明
            1.@size-change 如果分页中每页实现的条数发生变化 则会触发这个该事件
            2.@current-change 当页数发生改变时,触发该函数
            3.current-page 当前选中的页
            4.page-sizes   页面数据展现的选项
            5.page-size    默认选中的页数
            6.layout    分页中展现的数据种类
            7.total     记录总数
       -->
        <el-pagination
             @size-change="handleSizeChange"
             @current-change="handleCurrentChange"
             :current-page="queryInfo.pageNum"
             :page-sizes="[5, 10, 20, 40]"
             :page-size="queryInfo.pageSize"
             layout="total, sizes, prev, pager, next, jumper"
             :total="total">
        </el-pagination>
    </el-card>

    <!-- 编辑用户新增对话框 visible.sync 控制对话框的显示-->
    <el-dialog title="添加用户" :visible.sync="dialogVisible" width="50%" @close="closeDialog">

      <!-- 定义用户提交表单数据-->
      <el-form :model="addUserModel" :rules="rules" ref="addUserRef" label-width="100px" class="demo-ruleForm">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="addUserModel.username"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="addUserModel.password" type="password"></el-input>
        </el-form-item>
        <el-form-item label="密码确认" prop="password2">
          <el-input v-model="addUserModel.password2" type="password"></el-input>
        </el-form-item>
        <el-form-item label="电话" prop="phone">
          <el-input v-model="addUserModel.phone"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="addUserModel.email"></el-input>
        </el-form-item>
      </el-form>

      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addUserBtn">确 定</el-button>
      </span>
    </el-dialog>

    <!-- 定义修改页面 当对话框关闭时,将表格数据重置-->
    <el-dialog title="修改用户" :visible.sync="updateDialogVisible" width="50%" @close="closeUpdateDialog">
      <!-- 定义用户提交表单数据-->
      <el-form :model="updateUserModel" :rules="rules" ref="updateUserRef" label-width="100px">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="updateUserModel.username"  disabled ></el-input>
        </el-form-item>
        <el-form-item label="电话" prop="phone">
          <el-input v-model="updateUserModel.phone"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="updateUserModel.email"></el-input>
        </el-form-item>
      </el-form>

      <span slot="footer" class="dialog-footer">
        <el-button @click="updateDialogVisible = false" >取 消</el-button>
        <el-button type="primary" @click="updateUser">确 定</el-button>
      </span>
    </el-dialog>


  </div>
</template>

<script>

  export default {
    data(){
      //校验邮箱规则 rule校验规则   value校验的数据   callback回调函数
      const checkEmail = (rule, value, callback) => {
        //定义邮箱的正则表达式  JS中用/来表示正则表达式的开始和结束
        const emailRege = /^[a-zA-Z0-9-_]+@[a-zA-Z0-9-_]+\.[a-zA-Z0-9-_]+$/
        //正则表达式语法校验  test(xx) 校验成功 返回true  校验失败返回false
        if (emailRege.test(value)) {
          //表示邮箱合法 正确返回
          return callback()
        }
        callback(new Error('请填写正确的邮箱地址'))
      }

      //校验手机号的邮箱规则
      const checkPhone = (rule, value, callback) => {

        //定义校验手机号的正则语法
        const phoneRege = /^1[34578][0-9]{9}$/
        if (phoneRege.test(value)) {

          return callback()
        }
        callback(new Error('请填写正确的手机号'))

      }

      const checkPassword = (rule, value, callback) => {
        if(this.addUserModel.password !== value) return callback(new Error('2次密码输入不一致'))
        //否则校验成功
        callback()
      }

      return {
        queryInfo: {
          query: '',
          pageNum: 1,
          pageSize: 5
        },
        userList: [],
        //记录总数是数值类型
        total: 0,
        dialogVisible: false,
        addUserModel: {
          username: '',
          password: '',
          password2: '',
          email:  '',
          phone:  ''
        },
        //数据校验规则
        rules: {
          username: [
                  { required: true, message: '请输入用户名', trigger: 'blur' },
                  { min: 3, max: 30, message: '长度在 3 到 30 个字符', trigger: 'blur' }
                ],
          password: [
                  { required: true, message: '请输入密码', trigger: 'blur' },
                  { min: 3, max: 30, message: '长度在 3 到 30 个字符', trigger: 'blur' }
                ],
          password2: [
                  { required: true, message: '请输入密码', trigger: 'blur' },
                  { min: 3, max: 30, message: '长度在 3 到 30 个字符', trigger: 'blur' },
                  { validator: checkPassword , trigger: 'blur' }
                ],
          phone: [
                  { required: true, message: '请输入手机号', trigger: 'blur' },
                  { min: 3, max: 30, message: '长度在 3 到 30 个字符', trigger: 'blur' },
                  { validator: checkPhone , trigger: 'blur' }
                ],
          email: [
                  { required: true, message: '请输入邮箱', trigger: 'blur' },
                  { min: 3, max: 30, message: '长度在 3 到 30 个字符', trigger: 'blur' },
                  { validator: checkEmail , trigger: 'blur' }
                ]
        },
        updateDialogVisible: false,
        //定义修改用户数据封装
        updateUserModel: {
        }
      }

    },
    methods: {
      async getUserList(){
        const {data: result} = await this.$http.get('/user/list',{
           params: this.queryInfo
        })
        if(result.status !== 200) return this.$message.error("用户列表查询失败")
        this.userList = result.data.rows
        this.total = result.data.total
        console.log("总记录数:"+this.total)
      },
      async updateStatus(user){
         //实现用户状态修改  注意使用模版字符串  ES6中提出的新用法 ${key}
        //const {data: result} = await this.$http.put('/user/status/'+user.id+'/'+user.status)
        const {data: result} = await this.$http.put(`/user/status/${user.id}/${user.status}`)
        if(result.status !== 200) return this.$message.error("用户状态修改失败!")
        this.$message.success("用户状态修改成功!")
      },
      handleSizeChange(pageSize){
        //console.log("每页展现的条数"+pageSize)
        this.queryInfo.pageSize = pageSize
        this.getUserList()
      },
      handleCurrentChange(pageNum){
        //console.log("页数:"+pageNum)
        this.queryInfo.pageNum = pageNum
        this.getUserList()
      },
      closeDialog(){
        //重置表格数据
        this.$refs.addUserRef.resetFields()
      },
      //校验用户数据
      addUserBtn(){
        this.$refs.addUserRef.validate(async valid => {
          //如果校验失败 则停止数据
          if(!valid) return
          //console.log(this.addUserModel)
          const {data: result} = await this.$http.post('/user/addUser',this.addUserModel)
          if(result.status !== 200) return this.$message.error("用户新增失败")
          this.$message.success("用户新增成功")
          //关闭对话框
          this.dialogVisible = false
          //重新获取用户列表
          this.getUserList()

        })
      },
      async updateUserBtn(user){
        this.updateDialogVisible = true
        const {data: result} = await this.$http.get("/user/"+user.id)
        if(result.status !== 200) return this.$message.error("用户查询失败")
        this.updateUserModel = result.data
      },
      closeUpdateDialog(){
        //重置表格数据
        this.$refs.updateUserRef.resetFields()
      },
      updateUser(){
        //1.预校验数据
        this.$refs.updateUserRef.validate(async valid => {
           if(!valid)  return this.$message.error("表单验证没有通过")
           //根据接口文档要求封装数据
           let user = {}
           user.id = this.updateUserModel.id
           user.phone = this.updateUserModel.phone
           user.email = this.updateUserModel.email
           const {data: result} = await this.$http.put(`/user/updateUser`,user)
           if(result.status !== 200) return this.$message.error("用户修改失败")
           this.$message.success("用户更新成功")
           this.updateDialogVisible = false
           this.getUserList()
        })
      },
      async deleteUser(user){
         //1.消息确认框
         const result =  await this.$confirm('此操作将永久删除 '+user.username+', 是否继续?', '提示', {
                   confirmButtonText: '确定',
                   cancelButtonText: '取消',
                   type: 'warning'
                 }).catch(error => error)

         //如果确认  confirm  如果取消 cancel
         if(result !== 'confirm'){
            this.$message.info("删除取消")
         }
         const {data: result2} = await this.$http.delete(`/user/${user.id}`)
         if(result2.status !== 200) return this.$message.error("删除失败")
         this.$message.success("删除成功")
         //重新加载 数据
         this.getUserList()
      }
    },
    //利用钩子函数实现数据查询
    mounted(){
      this.getUserList()
    }
  }
</script>


<style lang="less" scoped>

</style>
