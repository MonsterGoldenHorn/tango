package com.priva.tango.designmodle.construct;



public class AdapterMode {
//别名    包装器 Wr a p p e r
/**
 * 
 * <important>兼容,改变一个已有对象的接口</important>
 * 1.兼容类（方法）     协助匹配方法，根据不同请求使用不用处理类的方法处理，
 * 作为处理器，不需要知道是什么类，只是执行方法
 * A d a p t e r时常还要负责提供那些被匹配的类所没有提供的功能
 * 2.兼容接口       使用多重继承对一个接口与另一个接口进行匹配,可以实现双向适配
 * 3.兼容对象     组合
 * 
 *   
 *   
 *   
 *   PlatformTransactionManager
 *   AnnotationTransactionAttributeSource
 *   
 *   
 *   
 * 适用范围
• 你想使用一个已经存在的类，而它的接口不符合你的需求。
• 你想创建一个可以复用的类，该类可以与其他不相关的类或不可预见的类（即那些接口
可能不一定兼容的类）协同工作。
• （仅适用于对象 A d a p t e r）你想使用一些已经存在的子类，但是不可能对每一个都进行
子类化以匹配它们的接口。对象适配器可以适配它的父类接口
 * 
 * 
 * JAVA JDK 1.1 提供了 Enumeration 接口，
 * 而在 1.2 中提供了 Iterator 接口，想要使用 1.2 的 JDK，则要将以前系统的 Enumeration 接口转化为 Iterator 接口，这时就需要适配器模式
 * 
 * 
 * springmvc HandlerAdapter 属于被注入类型，对象适配 多个实例继承HandlerAdapter,使用supports方式匹配，返回不同的HandlerAdapter处理不同的Servlet
 * 
 * 新的类可以直接插入，且方法实现不需要大段if else
 * HttpRequestHandler.supports 匹配 HttpRequestHandler
 * public boolean supports(Object handler) {
        return (handler instanceof HttpRequestHandler);
    }
    
    protected HandlerAdapter getHandlerAdapter(Object handler) throws ServletException {
    for (HandlerAdapter ha : this.handlerAdapters) {
        if (logger.isTraceEnabled()) {
            logger.trace("Testing handler adapter [" + ha + "]");
        }
        if (ha.supports(handler)) {
            return ha;
        }
    }
    throw new ServletException("No adapter for handler [" + handler +
            "]: Does your handler implement a supported interface like Controller?");
    }

 * 
 * 
 * 
 */
    public static void main(String[] args) {
        new Adapter2() {//接口适配
            @Override
            public void sum() {
                // TODO Auto-generated method stub
                super.sum();
            }
            
            @Override
            public void ouput() {
                // TODO Auto-generated method stub
                super.ouput();
            }
        };
    }
}

//--------------1 object & class adapter------
class Phone{
    private void charging(Vol5V vol5V) {
        // TODO Auto-generated method stub
    }
}
interface Vol5V{
    void output5V();
}

class Vol220V{
    void output220V() {
    }
}

class Vol330V{
    void output330V() {
    }
}

class Adapter5VForClass extends Vol220V implements Vol5V{
    @Override
    public void output5V() {
        this.output220V();
        System.out.println("transfer to 5V");
    }
}

class Adapter5VForObject implements Vol5V{
    
    private Vol330V vol330V;
    
    private Adapter5VForObject(Vol330V vol330V) {
        this.vol330V = vol330V;
    }

    @Override
    public void output5V() {
        vol330V.output330V();
        System.out.println("transfer to 5V");
    }
}



//----------------------2 interface adapter---------------------------------------------

//adaptee
interface Vol{
    void ouput();
    void detect();
    void moniter();
}
//adaptee
interface Clock{
    void sum();
}

//adapter
 abstract class Adapter1 implements Vol{

    //空实现所有方法，子类选择重写
    @Override
    public void ouput() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void detect() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void moniter() {
        // TODO Auto-generated method stub
        
    }
     
 }
//子类选择实现两个接口 
abstract class Adapter2 implements Vol,Clock{

    @Override
    public void sum() {
        // TODO Auto-generated method stub
        this.ouput();
        System.out.println("输出累加成为用电量");
    }

    @Override
    public void ouput() {
        System.out.println("输出");
    }

    @Override
    public void detect() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void moniter() {
        // TODO Auto-generated method stub
        
    }
    
}
