package com.jt.controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin //标识跨域，允许跨域的访问，没有限定
//@CrossOrigin("https://www.baidu.com") 只允许百度网址访问，localhost不能访问

/*在服务器端标记那些(网址)域名可以访问(跨域)*/

@RestController
@RequestMapping("/web")
public class WebController {

    @GetMapping("/hello")
    public String hello() {
        return "啊对对对";
    }
}
