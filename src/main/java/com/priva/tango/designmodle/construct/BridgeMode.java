package com.priva.tango.designmodle.construct;


public class BridgeMode {
/**
 * 一个类存在两个独立变化的维度，且这两个维度都需要进行扩展
 * 
 * 和适配器模式不同的地方在于，桥接模式两个对象都可以进行变化扩展，桥接只管调度，而适配器要隐藏被适配对象的方法
 * 
 * 将实现与抽象分离，抽象是行为（run），实现是对象(driver)
 * 
 * jdbc  driverManger  抽象getConnection   实现 driver
 * 
 * 解决多层次继承
 * 
 * 
Abstract Factory 模式可以用来创建和配置一个特定的 B r i d g e模式。
Adapter 模式用来帮助无关的类协同工作，它通常在系统设计完成后才会被使用。然
而，B r i d g e模式则是在系统开始时就被使用，它使得抽象接口和实现部分可以独立进行改变
 * 
 * 
角色： 
Abstraction：抽象类。
RefinedAbstraction：扩充抽象类。
Implementor：实现类接口。
ConcreteImplementor：具体实现类 。
 */
    
    public static void main(String[] args) {
        IDriver driver = new CarDriver();
        Tool tool = new Car();
        tool.setDriver(driver);
        tool.run();
    }
}

//抽象类
abstract class Tool{
    IDriver driver;

    public void setDriver(IDriver driver) {
        this.driver = driver;
    }

    abstract void run();
}
//实现类接口
interface IDriver{
    void drive();
}

//具体实现类
class CarDriver implements IDriver{
    @Override
    public void drive() {//只是一个例子，这里看起来命名像一个行为
        System.out.println("我是小车司机请上车");
    }
    
    public void oldDriver() {
        System.out.println("我是小车老司机");
    }
}

class TrainDriver implements IDriver{
    @Override
    public void drive() {
        System.out.println("我是火车司机请上车");
    }
    public void oldDriver() {
        System.out.println("我是火车老司机");
    }
}

//扩充抽象类
class Car extends Tool{
    @Override
    void run() {
        driver.drive();
    } 
}

class Train extends Tool{
    @Override
    void run() {
        driver.drive();
    } 
 }
