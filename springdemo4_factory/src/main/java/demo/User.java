package demo;


import org.springframework.stereotype.Component;

//交给Spring容器管理 Map<类名首字母小写,实例化的对象>
@Component
public class User { //spring容器通过反射机制实例化对象
    public void say() {
        System.out.println("通过@Componet注解实例化对象");
    }
}
