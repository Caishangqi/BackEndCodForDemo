package com.jt.controller;

import com.jt.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;

//     ___                _                 _  _
//    / __\  ___   _ __  | |_  _ __   ___  | || |  ___  _ __
//   / /    / _ \ | '_ \ | __|| '__| / _ \ | || | / _ \| '__|
//  / /___ | (_) || | | || |_ | |   | (_) || || ||  __/| |
//  \____/  \___/ |_| |_| \__||_|    \___/ |_||_| \___||_|
//

@Controller //将类交给SpringMVC管理，SpringMVC交给spring容器管理
@ResponseBody //将数据转化为 "特殊字符串" 返回
@CrossOrigin
public class UserController {

    /*URL地址 http://localhost:8080/hello get类型的请求
     * 参数：无
     * 返回值：您好，SpringMVC小甲虫の字符串*/

    @RequestMapping("/hello")
    public String hello() {
        String result = "您好，SpringMVC小甲虫の字符串";
        return result;

    }

    /*需求：查询后端数据 参数两个数据 name=tomcat，age=18
     * URL地址 http://localhost:8080/findUserByNA?name=tomcat&age=18
     *
     * 通过URL里面的数据动态获取key
     *
     * 需求：接收参数 name=xxx age=xxx
     * 返回值：数据正确
     * (!) 通过URL中的key获取数据。
     * (!) 如果参数众多，则可以使用对象的方式接收，要求必须有set方法
     */
    @RequestMapping("/findUserByNA")
    public String findUserByNA(String name, int age) {

        return "数据正确 " + name + ":" + age;

    }


    /*URL地址 http://localhost:8080/findUserByNA2?name=tomcat&age=18&id=100&sex=男
     * 返回值：User.toString*/
    @RequestMapping("/findUserByNA2")
    public String findUserByNA2(User user) {

        return user.toString();

    }

    @RequestMapping("/")
    public String noRes() {
        String mes = "你没访问任何东西";
        return mes;
    }

    /*
     * URL地址： http://localhost:8080/hobby?hobby=A,B,C,D
     * 参数：hobby = A,B,C,D
     * 返回值：获取参数返回即可
     * 如果遇到同名提交，则SpringMVC可以采用数组接收
     * 底层实现：截取+数组转换
     * */
    @RequestMapping("/hobby")
    public String hobby(String[] hobby) {

        //截取
        //String[] hobbyStrings = hobby.split(",");
        //System.out.println(hobbyStrings[0]);
        //数组转换成字符串

        /*上面这个方法已被SpringMVC实现↑*/

        return Arrays.toString(hobby);
    }


//                    _   ______  _   _  _
//                   | |  |  ___|| | | || |
//   _ __   ___  ___ | |_ | |_   | | | || |
//  | '__| / _ \/ __|| __||  _|  | | | || |
//  | |   |  __/\__ \| |_ | |    | |_| || |____
//  |_|    \___||___/ \__|\_|     \___/ \_____/
//
//

/*案例: url3: http://localhost:8080/findUser/tomcat/18/男 (发送)
要求:
1. restFul的风格数据的位置一旦确定,不能修改.
2. 参数与参数之间使用"/"的方式分割.
3. restFul的风格适用于 get/post/put/delete 请求类型

请求类型种类: get/post/put/delete
*/

    /**
     * URL地址:
     * http://localhost:8080/findUser/tomcat/18/男  get类型
     * 参数: name/age/sex
     * 返回值: 返回值获取的数据
     * restFul语法:
     * (!) 参数与参数之间使用/分割
     * (!) 需要接收的参数使用{}包裹
     * (!) 参数接收时采用@PathVariable取值
     */

//    @RequestMapping("/findUser/{name}/{age}/{sex}")
//    public String findUser(@PathVariable String name,@PathVariable int age,@PathVariable String sex){
//
//
//        return name+":"+age+":"+sex;
//    }

    //也可以直接使用对象接收参数
    @RequestMapping("/findUser/{name}/{age}/{sex}")
    public String findUser(User user) {

        return user.toString();
    }


//     _
//    (_)
//     _  ___   ___   _ __
//    | |/ __| / _ \ | '_ \
//    | |\__ \| (_) || | | |
//    | ||___/ \___/ |_| |_|
//   _/ |
//  |__/


    /*JSON(JavaScript Object Notation) 是一种轻量级的数据交换格式。它使得人们很容易的进行阅读和编写。
    同时也方便了机器进行解析和生成。它是基于 JavaScript Programming Language , Standard ECMA-262
     3rd Edition - December 1999 的一个子集。 JSON采用完全独立于程序语言的文本格式，但是也使用了类C
     语言的习惯（包括C, C++, C#, Java, JavaScript, Perl, Python等）。这些特性使JSON成为理想的数据
     交换语言。
    */

    /*JSON格式-对象格式*/
    /*对象（object） 是一个无序的“‘名称/值’对”集合。一个对象以“{”（左括号）开始，“}”（右括号）结束。每个
    “名称”后跟一个“:”（冒号）；“‘名称/值’ 对”之间使用“,”（逗号）分隔。`*/

    /* {"id": "100","name": "tomcat", "age": "18"} */


    /*JSON格式-数组格式*/
    /*数组（array） 是值（value）的有序集合。一个数组以“[”（左中括号）开始，“]”（右中括号）结束。值之间使
    用“,”（逗号）分隔。*/

    /* [100,"张三",true] */




    /* JSON格式-嵌套格式*/
    /*值（value） 可以是双引号括起来的字符串（string）、数值(number)、true、false、 null、对象（object
    ）或者数组（array）。这些结构可以嵌套。*/


//  ______                                            ______             _
//  | ___ \                                           | ___ \           | |
//  | |_/ /  ___  ___  _ __    ___   _ __   ___   ___ | |_/ /  ___    __| | _   _
//  |    /  / _ \/ __|| '_ \  / _ \ | '_ \ / __| / _ \| ___ \ / _ \  / _` || | | |
//  | |\ \ |  __/\__ \| |_) || (_) || | | |\__ \|  __/| |_/ /| (_) || (_| || |_| |
//  \_| \_| \___||___/| .__/  \___/ |_| |_||___/ \___|\____/  \___/  \__,_| \__, |
//                    | |                                                    __/ |
//                    |_|                                                   |___/


    /*SpringMVC 前后端交互-@ResponseBody*/

    /* (!) 前端访问后端服务器,一般采用Ajax方式进行数据
        传递. 后端服务器返回给前端页面 通常采用JSON格式
        数据

    *  (!) 后端服务器怎么接收前端的参数的. servlet机制*/

    /*需求: 根据name/age 查询用户,要求返回User对象的JSON串
    URL: http://localhost:8080/findJSON?name=tomcat&age=18*/

    /**
     * URL: http://localhost:8080/findJSON?name=tomcat&age=18  get
     * 参数: name=tomcat&age=18
     * 返回值:  User对象的JSON
     * 知识点:
     * 1.@ResponseBody //将返回值转化为JSON串  使用最多!!!
     * 2.如果返回String类型,则@ResponseBody将字符串本身返回给前端.
     */

    @RequestMapping("/findJSON")
    @ResponseBody //将返回值转化为JSON串
    public User findJSON(User user) {
        //在业务层拓展也业务
        user.setId(101);
        user.setSex("男");

        return user;
        //@ResponseBody 把返回的user对象转换成json
    }


}
