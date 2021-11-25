# Spring-IOC(Inversion of Control)
Ioc全称Inversion of Control，即“控制反转”，这是一种设计思想。对象创建的权利由Spring框架完成.由容器管理对象的生命周期.
![image](https://user-images.githubusercontent.com/39553613/143372624-7806f03d-809f-4eb9-936e-a1b14693a81c.png)


# 延伸阅读

### 小结:
* 原来的对象的创建都是由用户自己手动创建,这样的方式耦合性 肯定高. 如果类发生了变化,则代码都得修改.
* 现在所有的对象都交给Spring容器管理. 用户无需关心对象是如何实例化. 容器负责对象的注入即可. 以后几乎不用修改任何代码. 降低了代码的耦合性
