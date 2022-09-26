package com.priva.tango.designmodle.construct;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyMode {
/**
 * 
 * 方法增强，控制
 * 
 * 静态与动态代理基于接口
 * 静态直接持有目标对象（创建代理时传入）
 * cglib使用字节码属于动态代理
 * 
 * 与其他模式区别：
 *  1、和适配器模式的区别：适配器模式主要改变所考虑对象的接口，而代理模式不能改变所代理类的接口。
 *  2、和装饰器模式的区别：装饰器模式为了增强功能，而代理模式是为了加以控制
 *      代理的特点之一：无法直接访问
 *      装饰器特点：为了添加功能，可以理解为用了代理来加功能，多个代理的方式来完成不同的装饰
 *
 * 静态
 * soap的consumerService，代理所有类型的soapSerivce处理，统一添加日志和处理大表版权
 * 
 *  1、远程代理。
 *  2、虚拟代理。
 *  3、Copy-on-Write 代理。
 *  4、保护（Protect or Access）代理。
 *  5、Cache代理。 
 *  6、防火墙（Firewall）代理。 
 *  7、同步化（Synchronization）代理。 
 *  8、智能引用（Smart Reference）代理
 * 
 */
    
    public static void main(String[] args) {
//        StaticProxy sp = new StaticProxy(new Teacher());
//        sp.teach();
        
        DynamicProxy dp = new DynamicProxy(new Teacher());
        ITeacher teacher = (ITeacher) dp.getInstance();
        try {
            teacher.teach();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}

interface ITeacher{
    void teach() throws Exception;
    void listen() throws Exception;
}

class Teacher implements ITeacher{
    @Override
    public void teach() throws Exception {
        System.out.println("上课");
        throw new Exception("wrong");
    }
    @Override
    public void listen() throws Exception{
        System.out.println("听课");
        throw new Exception("wrong2");
    }
}
//静态代理类
class StaticProxy implements ITeacher{
    ITeacher teacher;

    public StaticProxy(ITeacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public void teach() {
        System.out.println("静态代理开始");
//        teacher.teach();
        System.out.println("静态代理结束");
    }

    @Override
    public void listen() {
        System.out.println("静态代理开始");
//        teacher.listen();
        System.out.println("静态代理结束");
    }
}
//动态代理类JDK代理
class DynamicProxy{
    private Object target;
    public DynamicProxy(Object target){
        this.target = target;
    }
    public Object getInstance(){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),//使用动态代理的对象必须实现一个或多个接口
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("动态代理开始");
                        Object o = null;
                        try {
                            o = method.invoke(target, args);
                        } catch (Exception e) {
                            if(e.getLocalizedMessage().contains("1")){
                                return  o;
                            }else{
                                throw e;
                            }
                        }
                        return o;
                    }
                }) ;
    }
}

//cglib动态代理
class CglibProxy implements MethodInterceptor{
    private Object target;
    public CglibProxy(Object target){
        this.target = target;
    }
    
    // 给目标对象创建代理对象
    public Object getProxyInstance(){
        //1. 工具类
        Enhancer en = new Enhancer();
        //2. 设置父类
        en.setSuperclass(target.getClass());
        //3. 设置回调函数
        en.setCallback(this);
        //4. 创建子类(代理对象)
        return en.create();
    }
    
    @Override
    public Object intercept(Object arg0, Method method, Object[] arg2, MethodProxy arg3)
            throws Throwable {
        System.out.println("开始事务.....");
        // 执行目标对象的方法
        Object returnValue = method.invoke(target, arg2);
        System.out.println("提交事务.....");
        return returnValue;
    }
}



//interface EnhancerKey {
//
//    public Object newInstance(String type,
//            String[] interfaces,
//            WeakCacheKey<CallbackFilter> filter,
//            Type[] callbackTypes,
//            boolean useFactory,
//            boolean interceptDuringConstruction,
//            Long serialVersionUID);
//}






