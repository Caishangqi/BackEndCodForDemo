package com.jt.service;

import com.jt.mapper.UserMapper;
import com.jt.pojo.User;
import com.jt.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    /**
     * (!)
     * User数据 User(id=null, username=abc, password=abc, phone=null, email=null, status=null)
     * (!) 把密码明文转化为密文 sha1/md5
     * (!) 根据用户名和密码查询用户信息
     * (!) 有结果返回token (UUID) 没有结果返回空 null
     *
     * @param user 需要传入用户对象
     * @return String
     */
    @Override
    @Transactional
    public String login(User user) {
        String password = user.getPassword();
        byte[] bytes = password.getBytes();
        //将密码加密
        String md5Password = DigestUtils.md5DigestAsHex(bytes);
        user.setPassword(md5Password);
        //根据用户名密码查询数据库
        User userDb = userMapper.findUserByUP(user);
        //判断userDb是否有值
        if (userDb == null) {
            //用户名密码查询错误
            //不用返回密钥
            return null;
        }
        //生成UUID
        String token = UUID.randomUUID().toString();
        return token;
    }

    /**
     * 分页查询Sql：
     * SELECT * FROM USER LIMIT 起始位置，查询条数
     * 第一页：每页10条
     * SELECT * FROM USER LIMIT 0, 10 (0-9)
     * 第二页：每页10条
     * SELECT * FROM USER LIMIT 10, 10 (10 - 19)
     * 第N页：
     * SELECT * FROM USER LIMIT (n-1)*10 , 10
     *
     * @param pageResult
     * @return
     */
    @Override
    public PageResult findUserList(PageResult pageResult) {
        //总记录数 (从数据库找)
        long total = userMapper.findTotal();
        //获取分页查询的结果
        int start = (pageResult.getPageNum() - 1) * pageResult.getPageSize();
        int size = pageResult.getPageSize();
        String query = pageResult.getQuery();
        List<User> rows = userMapper.findUserList(start, size, query);
        return pageResult.setTotal(total).setRows(rows);
    }

    @Override
    public void updateStatus(User user) {

        user.setUpdated(new Date()); //设定当前时间
        userMapper.updateStatus(user);
    }

    @Override
    @Transactional
    public void deleteUserById(Integer id) {
        userMapper.deleteUserById(id);
    }

    /**
     * <h2>添加用户信息</h2>
     * 添加默认值以及创建时间
     *
     * @param user 用户
     * @throws NullPointerException 空
     * @Description:
     * @return: {@link Void}
     * @author: Caizii
     * @Date: 2021/12/9
     * @deprecated 1997年
     */
    @Override
    @Transactional
    public void addUser(User user) {
        String password = user.getPassword();
        String md5Pass = DigestUtils.md5DigestAsHex(password.getBytes());
        user.setPassword(md5Pass).setStatus(true).setUpdated(user.getCreated());
        userMapper.addUser(user);
    }


    @Override
    public User findUserById(Integer id) {
        return userMapper.findUserById(id);
    }

    /**
     * @param user 用户
     * @throws NullPointerException
     * @Description: <h2></h2>
     * @return: {@link Void}
     * @author: Caizii
     * @Date: 2021/12/9
     */
    @Override
    @Transactional
    public void updateUser(User user) {
        user.setUpdated(new Date());
        userMapper.updateUser(user);
    }

}
