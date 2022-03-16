package com.priva.tango.designmodle.create;

/**
 * 单例
 * 恶汉与678能用
 * 1.频繁创建销毁的类
 * 2.创建耗时较长且使用频繁，工具类，sessionFactory,dataResource等
 * 
 * 
 */
/**
 * jdk  RunTime
 * Hibernate sessionFactory
 *
 */
/**恶汉模式 （静态常量）*/
public class Singleton {

    //java.lang.Runtime
    //classLoader加载一次到方法区，类初始化时创建对象，线程安全
    
    //内存浪费，非懒加载
    //确定程序会使用
    private static Singleton singleton = new Singleton();
    
    //私有构造器
    private Singleton() {
    }
    
    public static Singleton getSingleton(){
        return singleton;
    }
}

/**恶汉模式 （静态代码块）*/
class Singleton2 {

    //与静态常量方式一样
    private static Singleton2 singleton;
    
    //私有构造器
    private Singleton2() {
    }
    
    static{
        singleton = new Singleton2();
    }
    
    public static Singleton2 getSingleton(){
        return singleton;
    }
}

/**懒汉模式 (无锁)*/
class Singleton3 {
    //线程不安全

    //优点：懒加载  缺点：线程不安全
    private static Singleton3 singleton;
    
    //私有构造器
    private Singleton3() {
    }
    
    public static Singleton3 getSingleton(){
        if(null == singleton){
            singleton = new Singleton3();
        }
        return singleton;
    }
}

/**懒汉模式 (方法单锁)*/
class Singleton4 {
    //线程安全

    //优点：懒加载  缺点：类加锁效率太低
    private static Singleton4 singleton;
    
    //私有构造器
    private Singleton4() {
    }
    
    public synchronized static Singleton4 getSingleton(){
        if(null == singleton){
            singleton = new Singleton4();
        }
        return singleton;
    }
}

/**懒汉模式 (代码块单锁)  不能用*/
class Singleton5 {
    //线程不安全

    //优点：懒加载  缺点：线程依然不安全
    private static Singleton5 singleton;
    
    //私有构造器
    private Singleton5() {
    }
    
    public static Singleton5 getSingleton(){
        if(null == singleton){
            synchronized (singleton) {
                singleton = new Singleton5();
            }
        }
        return singleton;
    }
}

/**懒汉模式 (DCL double check lock代码块双重检查)*/
class Singleton6 {
    //线程安全

    //优点：懒加载  
    private static volatile Singleton6 singleton;
    
    //私有构造器
    private Singleton6() {
    }
    
    public static Singleton6 getSingleton(){
        if(null == singleton){
            synchronized (singleton) {
                if(null == singleton)
                singleton = new Singleton6();
            }
        }
        return singleton;
    }
}


/**懒汉模式 (静态内部类)*/
class Singleton7 {
    //线程安全，外部类加载不会加载内部类

    //私有构造器
    private Singleton7() {
    }
    
    private static class SingletonInner{
        private static final Singleton7 singleton = new Singleton7();
    }
    
    //调用时才会加载内部类
    public static Singleton7 getSingleton(){
        return SingletonInner.singleton;
    }
}

/**
 * 枚举类
 *防止反序列化
 */
enum Singleton8{
    //线程安全
    instance1, instance2;
    public void method(){
        System.out.println("do something....");
    }
}