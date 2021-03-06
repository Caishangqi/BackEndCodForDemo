package com.jt;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jt.mapper.UserMapper;
import com.jt.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootTest
class JtApplicationTests {

    @Autowired
    private UserMapper userMapper;

    /**
     * 实现数据的新增，Mybatis-plus入门案例
     */


    @Test
    void contextLoads() {
        User user = new User();
        user.setUsername("adminDemo")
                .setPassword("1234567890")
                .setPhone("13910783530")
                .setEmail("11@qq.com")
                .setStatus(true)
                .setCreated(new Date())
                .setUpdated(user.getCreated());

        int rows = userMapper.insert(user); //影响的行数
        System.out.println("(-) 影响的行数：" + rows);

    }

    /**
     * 根据ID进行查询
     */
    @Test
    public void test01() {
        int id = 1;
        User user = userMapper.selectById(id);
        System.out.println(user);
    }

    /**
     * 根据其他属性进行擦汗寻
     * 根据username="adminDemo1"进行查询
     * 组件：条件构造器 用来封装where条件的
     * Sql：select * from user where username = "adminDemo1"
     * 方式1：利用对象封装数据
     */
    @Test
    public void test02() {
        //这个User作为模板，赋予你要搜索的值进入这个User中
        User temp = new User();
        temp.setUsername("adminDemo1"); // =号操作
        //条件构造器

        /*
         * 条件构造器会根据对象中不为null的元素动态拼接SQL
         */
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>(temp);
        //如果可我保证查询的结果只有一个
        User user = userMapper.selectOne(userQueryWrapper);
        //如果不能保证查询只有一个，使用List集合接收
        //userMapper.selectList(null);
        System.out.println(user);
    }

    /**
     * 查询id>5的用户
     * 转义字符: > gt, < lt, = eq, >= ge, <= le, != ne
     * <p>
     * 方式：利用逻辑运算符 实现SQL操作
     */
    @Test
    public void test03() {


        QueryWrapper<User> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.gt("id", 5);
        List<User> users = userMapper.selectList(objectQueryWrapper);
        System.out.println(users);
    }

    /**
     * 查询username包含admin的数据，
     * 并且按照id降序排序
     * select * from user where username like %admin%
     * order by id desc
     * like %左右都有百分号%
     * likeleft %只有左面有百分号
     * likeright 只有右面有百分号%
     */
    @Test
    public void test04() {

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.like("username", "admin")
                .orderByDesc("id");

        List<User> users = userMapper.selectList(userQueryWrapper);
        System.out.println(users);

    }

    /**
     * 查询 id = 1，2，23，24
     * select * from user where id in (1，2，23，24)
     */
    @Test
    public void test05() {
        Integer[] ids = {1, 2, 23, 24};
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.in("id", ids); //需要的是Object类型，所以要用Integer包装类型
        //方式1：利用条件构造器查询
        List<User> users = userMapper.selectList(userQueryWrapper);
        System.out.println(users);
        //方式2：利用API将进行查询
        List<Integer> userList = Arrays.asList(ids); //数组转List集合
        List<User> users1 = userMapper.selectBatchIds(userList);//要的集合类型
        System.out.println(users1);
    }

    /**
     * 动态查询数据
     * 使用电话/邮箱查询
     */

    @Test
    public void test06() {

        String tel = "13910783530";
        String email = "11@qq.com";

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq(StringUtils.hasLength(tel), "phone", tel)
                .eq(StringUtils.hasLength(email), "email", email);
        List<User> users = userMapper.selectList(userQueryWrapper);
        System.out.println(users);

    }

    /**
     * 只查询主键信息
     * API：selectObjs
     * 用途：
     * 进行关联查询
     */
    @Test
    public void test07() {
        //用来获取主键的信息
        List<Object> objects = userMapper.selectObjs(null);
        System.out.println(objects);
        // [1, 2, 4, 8, 9, 10, 11, 12]
//        List<User> userList = userMapper.selectList(null);
//        System.out.println(userList);
    }

    /**
     * 用户修改操作
     * (!) 根据id修改数据
     * 讲id=22 改为admin777，phone = 11111111111
     * id当作唯一的where条件其他不为空的属性当作set的
     * 条件。
     */
    @Test
    public void test08() {

        // UPDATE User SET username=?, phone=? WHERE id=?
        User user = new User();
        user.setId(22).setUsername("admin777").setPhone("11111111111");
        userMapper.updateById(user);
        System.out.println("(!) Done");

    }

    /**
     * 用户修改操作
     * 讲password=123456的phone改为10086 email=10086@qq.com
     * 参数(!) entity：主要的目的封装set条件
     */
    @Test
    public void test09() {
        User user = new User();
        user.setPhone("10086").setEmail("10086@qq.com");
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.eq("password", "123456");
        userMapper.update(user, userUpdateWrapper);
    }

}