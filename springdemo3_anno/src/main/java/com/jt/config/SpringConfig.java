package com.jt.config;

import com.jt.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //将当前类标记为配置类
public class SpringConfig { //xml

    /*
     *
     * (1) xml形式
     *   <bean id="user" class="com.jt.User"></bean>
     * (2) 注解形式
     *   Map集合的结构 Map<方法名，方法的返回值>
     *   @Bean 将方法的返回值，交给Spring容器管理
     * */

    @Bean("user") //也可重新命名基于新的id，不过一让方法名代表id
    public User user() {
        return new User(); //反射机制
    }
}
