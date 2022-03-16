package com.priva.tango.designmodle.create;

import java.util.Calendar;

/**简单（静态）工厂模式
 * 
 * 对象实例化放入SimpleFactory实现
 * 
 * Calendar.
 * */
public class SimpleFactory {
    //封装实例化对象的行为///静态的为 static Pizza makePizza(String name)
    Pizza makePizza(String name){
        Pizza pizza = null;
        if(name.equals("chess")){
            pizza = new ChessPizza();
        }else if(name.equals("milk")){
            pizza = new MilkPizza();
        }
        return pizza;
    }
}

class User {////CONTROLLER
    public static void main(String[] args) {
        
        Calendar.getInstance();
        
        ///SERVICE
        PizzaStore  ps = new PizzaStore();
        ps.setFactory(new SimpleFactory());
        Pizza p = ps.order("");
        /**
         * 工厂只有一种返回的情况下，使用静态工厂的方式  make
        */
    }
}
//抽象产品角色
abstract class Pizza{
    private String pizzaName;
    
    abstract void prepare();
    
    public String getPizzaName() {
        return pizzaName;
    }

    public void setPizzaName(String pizzaName) {
        this.pizzaName = pizzaName;
    }
}

//具体产品角色
class ChessPizza extends Pizza{
    @Override
    void prepare() {
        System.out.println("准备ChessPizza");
    }
}

class MilkPizza extends Pizza{
    @Override
    void prepare() {
        System.out.println("准备MilkPizza");
    }
}

class PizzaStore{
    SimpleFactory simpleFactory = null;
    
    //or Constructor
    void setFactory(SimpleFactory simpleFactory){
        this.simpleFactory = simpleFactory;
    }
    
    Pizza order(String name){
        return simpleFactory.makePizza(name);
    }
}