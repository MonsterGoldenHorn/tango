package com.priva.tango.designmodle.create;
/**抽象工厂
 * 工厂类型有点多，特征工厂为一个接口
 * 
 * 多种产品下由工厂方法实现
 * 单个产品下由原型模式实现
 * 
• 一个系统要独立于它的产品的创建、组合和表示时。
• 一个系统要由多个产品系列中的一个来配置时。
• 当你要强调一系列相关的产品对象的设计以便进行联合使用时。
• 当你提供一个产品类库，而只想显示它们的接口而不是实现时。


1) 将工厂作为单件 一个应用中一般每个产品系列只需一个 C o n c r e t e F a c t o r y的实例。因此
工厂通常最好实现为一个S i n g l e t o n
2)如果有多个可能的产品系列，具体工厂也可以使用 P r o t o t y p e（3 . 4）模式来实现。具体工
厂使用产品系列中每一个产品的原型实例来初始化
 * */
public class AbstractFactoryMode {///CONTROLLER
    public static void main(String[] args) {
        //SERVICE
        PizzaStore3  ps = new PizzaStore3();
        ps.setFactory(new WalishPizza1());
        Pizza p = ps.order("");
    }
}

//方法产生成品产品
interface AbstractFactory{
    ChessPizza makeChessFactory1();
    MilkPizza makeMilkFactory2();
}

//工厂完成对应成品 C o n c r e t e F a c t o r y
class WalishPizza1 implements AbstractFactory{
//它有利于产品的一致性 当一个系列中的产品对象被设计成一起工作时，一个应用一 次只能使用同一个系列中的对象
//此处只能生产walish    
    @Override
    public ChessPizza makeChessFactory1() {
     // WalishChessPizza   需要扩展
        return null;
    }

    @Override
    public MilkPizza makeMilkFactory2() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
 class DecosPizza1  implements AbstractFactory{

    @Override
    public ChessPizza makeChessFactory1() {
     // DecosChessPizza   需要扩展
        return null;
    }

    @Override
    public MilkPizza makeMilkFactory2() {
        // TODO Auto-generated method stub
        return null;
    }
     
 }

 
 //----------------------------------------------------

class PizzaStore3{
    AbstractFactory factory = null;
    
    //or Constructor
    // 它使得易于交换产品系列 一个具体工厂类在一个应用中仅出现一次—即在它初始化 的时候
    void setFactory(AbstractFactory simpleFactory){
        this.factory = factory;
    }
    
    Pizza order(String name){
        //if name==chess
        return factory.makeChessFactory1();
    }
}