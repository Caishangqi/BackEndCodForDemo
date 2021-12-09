<template>
  <div>

    <!-- 1.定义面包屑导航-->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>商品管理</el-breadcrumb-item>
      <el-breadcrumb-item>商品分类</el-breadcrumb-item>
    </el-breadcrumb>

    <!-- 2.定义卡片视图 -->
    <el-card class="box-card">

      <!-- 2.1定义一行 使用栅格-->
      <el-row>
        <el-col :span="24">
          <el-button type="primary" @click="showAddItemCatDialog">新增分类</el-button>
        </el-col>
      </el-row>

      <!-- 2.2定义表格数据-->
      <el-table :data="itemCatList" style="width: 100%;margin-bottom: 20px;" row-key="id" border stripe>
        <el-table-column type="index" label="序号">
        </el-table-column>
        <el-table-column prop="name" label="分类名称">
        </el-table-column>
        <el-table-column prop="status" label="状态">
          <!-- 定义作用域插槽 展现数据     scope.row展现行级元素 -->
          <template slot-scope="scope">
            <el-switch v-model="scope.row.status" active-color="#13ce66" inactive-color="#ff4949"
              @change="updateStatus(scope.row)"></el-switch>
          </template>
        </el-table-column>
        <el-table-column prop="level" label="等级">
          <!-- 定义作用域插槽 定义标签等级-->
          <template slot-scope="scope">
            <el-tag effect="dark" v-if="scope.row.level == 1">一级分类</el-tag>
            <el-tag effect="dark" type="warning" v-if="scope.row.level == 2">二级分类</el-tag>
            <el-tag effect="dark" type="danger" v-if="scope.row.level == 3">三级分类</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <!-- 定义作用域插槽 定义标签等级-->
          <template slot-scope="scope">
            <el-button type="success" icon="el-icon-edit" @click="updateItemCatBtn(scope.row)">编辑</el-button>
            <el-button type="danger" icon="el-icon-delete" @click="deleteItemCatBtn(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

    </el-card>

    <!-- 添加新增分类对话框-->
    <el-dialog title="新增商品分类" :visible.sync="addItemCatDialogVisible" width="50%" @close="closeAddItemCatDialog">
      <!-- 定义分类表单 -->
      <el-form :model="itemCatForm" :rules="rules" ref="itemCatFormRef" label-width="100px">
        <el-form-item label="分类名称:" prop="name">
          <el-input v-model="itemCatForm.name"></el-input>
        </el-form-item>
        <!-- 定义父级分类选项 -->
        <el-form-item label="父级分类名称:">
          <!-- 通过级联选择器定义1/2级商品分类
             步骤:  1.注意标签导入
                    2.options 表示数据的来源
                    3.指定props属性指定数据配置
          -->
          <el-cascader v-model="selectedKeys" :props="props" :options="parentItemCatList" clearable
            @change="parentItemCatChange"></el-cascader>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="addItemCatDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addItemCatForm">确 定</el-button>
      </span>
    </el-dialog>

    <!-- 添加修改分类对话框 -->
    <el-dialog title="修改商品分类" :visible.sync="updateItemCatDialogVisible" width="50%">
      <!-- 定义分类表单 -->
      <el-form :model="updateItemCatForm" :rules="rules" ref="upDateItemCatForm" label-width="100px">
        <el-form-item label="分类名称:" prop="name">
          <el-input v-model="updateItemCatForm.name"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="updateItemCatDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="updateItemCat">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
  export default {
    //定义初始化函数
    created() {
      //默认获取商品分类列表数据
      this.findItemCatList()
    },
    data() {
      return {
        //定义商品分类数据
        itemCatList: [],
        //控制分类对话框的显示
        addItemCatDialogVisible: false,
        //定义商品分类新增对象
        itemCatForm: {
          name: '', //定义商品分类名称
          parentId: 0, //默认父级ID=0
          level: 1 //默认是一级菜单
        },
        //定义商品分类校验规则
        rules: {
          name: [{
            required: true,
            message: '请输入分类名称',
            trigger: 'blur'
          }]
        },

        //定义级联选择项
        props: {
          //定义子节点菜单展开方式
          expandTrigger: "hover",
          value: "id", //选中数据的value值
          label: "name", //选中数据展现名称
          children: "children", //自选项数据
          checkStrictly: true
        },
        //定义用户选中父级ID数组
        selectedKeys: [],
        //定义父级商品分类信息只查询一级和二级
        parentItemCatList: [],
        //定义修改对话框属性
        updateItemCatDialogVisible: false,
        updateItemCatForm: {}
      }
    },
    methods: {
      async findItemCatList() {
        const {
          data: result
        } = await this.$http.get("/itemCat/findItemCatList/3")
        if (result.status !== 200) return this.$message.error("获取商品分类列表失败!!")
        this.itemCatList = result.data
      },
      //根据ID修改状态信息
      async updateStatus(itemCat) {
        const {
          data: result
        } = await this.$http.put(`/itemCat/status/${itemCat.id}/${itemCat.status}`)
        if (result.status !== 200) return this.$message.error("修改状态失败")
        this.$message.success("状态修改成功")
      },
      //当展现新增商品分类时,应该渲染级联框数据
      showAddItemCatDialog() {
        this.findParentItemCatList()
        this.addItemCatDialogVisible = true
      },
      async findParentItemCatList() {
        //动态获取商品分类信息  type=2表示获取2级商品分类信息
        const {
          data: result
        } = await this.$http.get("/itemCat/findItemCatList/2")
        if (result.status !== 200) return this.$message.error("获取商品分类列表失败!!")
        this.parentItemCatList = result.data
      },
      //当选择项发生变化时,触发该函数
      parentItemCatChange() {
        console.log(this.selectedKeys)
        console.log(this.itemCatForm)
        //如果选中节点的长度>0 则表示不是一级商品分类
        if (this.selectedKeys.length > 0) {
          //[1,2] 主要获取最后一位
          this.itemCatForm.parentId = this.selectedKeys[this.selectedKeys.length - 1]
          //数组级别+1
          this.itemCatForm.level = this.selectedKeys.length + 1
        } else {
          //如果数组长度不大于0 则表示一级商品分类信息
          this.itemCatForm.parentId = 0
          this.itemCatForm.level = 1
        }
      },
      async addItemCatForm() {
        //先将整个表单进行校验
        this.$refs.itemCatFormRef.validate(async validate => {
          if (!validate) return
          const {
            data: result
          } = await this.$http.post("/itemCat/saveItemCat", this.itemCatForm)
          if (result.status !== 200) return this.$message.error("新增商品分类失败")
          this.$message.success("新增商品分类成功!!!")
          //新增成功,则刷新分类列表信息
          this.findItemCatList();
          this.addItemCatDialogVisible = false
        })
      },
      //当点击关闭按钮时,应该重置整个表单
      closeAddItemCatDialog() {
        this.initItemCatForm()
      },
      initItemCatForm() {
        this.$refs.itemCatFormRef.resetFields()
        //清空form提交其他数据
        this.itemCatForm.parentId = 0
        this.itemCatForm.level = 1
        //清空级联选择框的数组
        this.selectedKeys = []
      },
      //由于有层级关系,所有修改只能修改名称
      updateItemCatBtn(itemCat) {
        this.updateItemCatForm = itemCat
        this.updateItemCatDialogVisible = true
      },
      async updateItemCat() {
        //修改商品分类信息
        const {
          data: result
        } = await this.$http.put('/itemCat/updateItemCat', this.updateItemCatForm)
        if (result.status !== 200) return this.$message.error("更新商品分类失败")
        this.$message.success("更新商品分类成功")
        this.findItemCatList();
        this.updateItemCatDialogVisible = false;
      },
      deleteItemCatBtn(itemCat) {
        //删除商品分类信息,如果为父级节点则需要删除所有的子级信息
        this.$confirm('此操作将永久删除该数据, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(async () =>{
          //传递分类id
          const {data: result} = await this.$http.delete("/itemCat/deleteItemCat",{params:{id:itemCat.id,level:itemCat.level}})
          if(result.status !== 200) return this.$message.error("删除商品分类失败")
          this.$message.success("删除数据成功")
          //删除成功之后,刷新页面数据
          this.findItemCatList()
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      }
    }
  }
</script>

<style lang="less" scoped>
  /*定义级联选择器宽度 */
  .el-cascader {
    width: 100%;
  }
</style>
