package com.priva.tango.designmodle.create;

/**
 * 主要是工厂方法的抽象,工厂实现方法不同
 * 符合OCP
 * 工厂方法通常在Template Methods中被调用
 *
 *<important>对象实例化推迟到子类SimpleFactoryImpl1实现</important>
 */
public class FactoryMethodMode {///CONTROLLER
    public static void main(String[] args) {
        //SERVICE
        PizzaStore2  ps = new PizzaStore2();
        ps.setFactory(new DecosPizza());
        ps.order("");
    }
}
//抽象工厂角色   abstract or interface
abstract class PizzaFactory {
    //封装实例化对象的行为///静态的为 static Pizza makePizza(String name)
    Pizza makePizza(String name){
        return this.make(name);
        }
    
    abstract Pizza make(String name);
}
//具体工厂角色 
class DecosPizza extends PizzaFactory {
    @Override
    Pizza make(String name) {
        Pizza pizza = null;
        if(name.equals("chess")){
            pizza = new ChessPizza();
        }else if(name.equals("milk")){
            pizza = new MilkPizza();
        }
        return pizza;
    }
}

class WalishPizza extends PizzaFactory {
    @Override
    Pizza make(String name) {
        Pizza pizza = null;
        if(name.equals("chess")){
            pizza = new ChessPizza();
        }else if(name.equals("milk")){
            pizza = new MilkPizza();
        }
        return pizza;
    }
}


class PizzaStore2{
    PizzaFactory simpleFactory = null;
    
    //or Constructor
    void setFactory(PizzaFactory simpleFactory){
        this.simpleFactory = simpleFactory;
    }
    
    Pizza order(String name){
        return simpleFactory.makePizza(name);
    }
}