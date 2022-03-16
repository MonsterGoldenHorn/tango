package com.priva.tango.base;

public class Circle {
    /**
     * 循环依赖
     * spring通过三级缓存
     * 1.存储单例bean，即 Map<String, Object> singletonObjects（ bean name to bean instance. ）
     * 2.存储提前暴露的bean，真正的解决循环依赖是靠二级缓存的。
     * Map<String, Object> earlySingletonObjects （Cache of early singleton objects: bean name to bean instance.）
     * 3.存储bean和其要加强的aop处理，如果需要aop增强的bean遇到了循环依赖，则使用该缓存中的aop处理代理增强bean
     * （Cache of singleton factories: bean name to ObjectFactory.）
     * 在遍历所有的bdf时，如果是一个factoryBean，则会添加一个&，处理getBean的时候，会先创建这个factory，下一次再getBean的时候，从三级缓存中拿对应的create方法出来的bean
     *
     * 如果仅仅只有对象本身的循环依赖，2级缓存就足够了，但是对象有可能是需要从ObjectFactory中获取的，如果两个对象都有factory，则需要3级缓存
     * 单例有factory工厂可以获取，这个工厂需要放入三级缓存
     */
    public static void main(String[] args) {
//        Collections.synchronizedList()
    }
}
