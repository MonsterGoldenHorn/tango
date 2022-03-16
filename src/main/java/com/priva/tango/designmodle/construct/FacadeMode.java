package com.priva.tango.designmodle.construct;

public class FacadeMode {
/**
 * 比如说子系统垂直领域用户注册，审批有一个流程
 * 现在有需求要直接让用户通过审批，建立一个外观类，直接走完所有流程 
 * 
 * 还可以统一管理访问层次，重新组合定义流程
 * <important>
 * 1、为复杂的模块或子系统提供外界访问的模块。 2、子系统相对独立。 3、预防低水平人员带来的风险
 * 也叫过程模式
 * 
 * 
 * mybatis Configuration
 * org.springframework.jdbc.support.JdbcUtils
 * 
 * 
 * Abstract Factory (3.1)模式可以与F a c a d e模式一起使用以提供一个接口，这一接口可用来
以一种子系统独立的方式创建子系统对象。 Abstract Factory也可以代替F a c a d e模式隐藏那些
与平台相关的类。


Mediator中介模式与F a c a d e模式的相似之处是，它抽象了一些已有的类的功能。然而，
M e d i a t o r的目的是对   <important>  同事之间的任意通讯进行抽象  </important>  ，通常集中不属于任何单个对象的功能。
M e d i a t o r的同事对象知道中介者并与它通信，而不是直接与其他同类对象通信。相对而言，
F a c a d e模式仅对子系统对象的接口进行抽象，从而使它们更容易使用；它并不定义新功能，
子系统也不知道f a c a d e的存在。
通常来讲，仅需要一个F a c a d e对象，因此F a c a d e对象通常属于Singleton (3.5)模式
 * 
 *  
 * 
 */
    
    
}

class AutoMachine{
    Shape shape;
    Handle handle;
    Fire fire;
    public void makeup(){
        shape.shape();
        handle.handle();
        fire.fire();
    }
}

class Shape{
    public void shape(){
       System.out.println("切菜");
   }
}

class Handle{
    public void handle(){
        System.out.println("多菜");
    }
}

class Fire{
    public void fire(){
        System.out.println("炒菜");
    }
}