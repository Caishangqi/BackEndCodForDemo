package com.jt.service;

import com.jt.mapper.UserMapper;
import com.jt.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

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

}
