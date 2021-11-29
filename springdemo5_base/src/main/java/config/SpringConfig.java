package config;

import com.jt.User;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan("com.jt")

public class SpringConfig {
    @Bean
    //@Scope("singleton") 默认单例
    @Scope("prototype") //多例模式
    @Lazy
    public User user() {
        return new User();
    }
}
