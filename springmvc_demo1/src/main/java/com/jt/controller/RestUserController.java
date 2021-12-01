package com.jt.controller;


import com.jt.pojo.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//  ______             _    _____                _                 _  _
//  | ___ \           | |  /  __ \              | |               | || |
//  | |_/ /  ___  ___ | |_ | /  \/  ___   _ __  | |_  _ __   ___  | || |  ___  _ __
//  |    /  / _ \/ __|| __|| |     / _ \ | '_ \ | __|| '__| / _ \ | || | / _ \| '__|
//  | |\ \ |  __/\__ \| |_ | \__/\| (_) || | | || |_ | |   | (_) || || ||  __/| |
//  \_| \_| \___||___/ \__| \____/ \___/ |_| |_| \__||_|    \___/ |_||_| \___||_|
//
//


//@Controller //将类交给mvc管理
//@ResponseBody /*要把当前类所有方法都返回成json串*/
@RestController // = @Controller + @ResponseBody (整合)
@RequestMapping("/rest") //可以前缀，让mvc先到指定的类去找mapping，实现重名
public class RestUserController {

    /**
     * 注意事项:
     * (!) springMVC中的请求路径不能重复!!!!
     * (!) 可以@RequestMapping("/业务名称")自定义前缀
     *
     * @return
     */
    @RequestMapping("/findJSON")
    public User findUser(User user) {
        user.setId(105);
        user.setSex("男");
        return user;
    }


}
