package factory;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

import java.util.Calendar;

/*
 * FactoryBean是Spring提供的接口，spring自动完成调用，
 * 获取指定的对象
 *
 * (!) @Component 用来标识类，则将该类交给Spring的容
 * 器管理。
 *
 * (!) Spring中FactoryBean：如果Spring加载的时候遇
 * 到FactoryBean的接口，则会自动执行重写的方法getObj
 * ect()和getObjectType()。不对类实例化！！！！
 *
 * (!) 最后返回的是Calendar对象而不是CalendarFactory
 * 的实例化对象。
 *
 * (!) 工厂模式：Map<key:calendarfactory,value=Calendar对象>
 *  因为它创建的是Calendar的obj，所以指定Map里面的名字是Calendar，
 *  把@Component后面的名称改了所以最后的Map里面的格式为
 *  Map<key:calendar,value=Calendar对象>
 *
 * 核心功能：
 * (!) Key:就是当前的类名(如果自己编辑了注解则以注解为准)
 *     value: 调用getObject获取的返回值对象
 *     将上述的数据交给Spring容器管理
 *
 * 什么时候用：
 *     (!) 某些对象不能实例化的时候
 *     (!) 整合其他第三方框架
 * */

@Component("calendar")
public class CalendarFactory implements FactoryBean<Calendar> {

    //只会执行这个构造方法但是实例不受Spring容器管理
    public CalendarFactory() {
        System.out.println("CalendarFactory的无参构造器");
    }

    //理解为主 设计框架结构时十分有用
    //动态执行该方法，获取返回值对象
    @Override
    public Calendar getObject() throws Exception {
        //利用Calendar的 工具API 实现对象的创建 (没有为什么，就是这样)
        System.out.println("Pass");
        return Calendar.getInstance(); //你只需要知道用这个方法会得到Calendar对象就行了
    }

    @Override
    public Class<?> getObjectType() {
        //固定写法，一般直接xxx.class即可
        return Calendar.class;
    }
}
