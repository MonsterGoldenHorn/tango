package com.priva.tango.designmodle.construct;

public class Decorator {
/**
 * 桥接两头一边创建一边行为，装饰两边实现相同的行为
 * wrapper
 * 
 * 特征，装饰者包含了一个被装饰的对象，两者都继承自一个超类(抽象)
 * 
 * 一个策略可以有自己特定的接口，而装饰的接口则必须与组件的接口一致
 * D e c o r a t o r模式不同于A d a p t e r模式，因为装饰仅改变对象的职责而不改变它的接口；而适配器将给对象一个全新的接口
 * 可以将装饰视为一个退化的、仅有一个组件的组合。然而，装饰仅 给对象添加一些额外的职责—它的目的不在于对象聚集
 * 
 * 用一个装饰你可以改变对象的外表；而 S t r a t e g y模式使得你可以改变对象的内核。这是改变对象的两种途径
 * 
 * 
 * 
 */
    
    
    
    public static void main(String[] args) {
        Drink d = new Tea();
        d = new Sugar(d);
        d = new Sugar(d);
        d = new Ice(d);
        System.out.println(d.price());
        
    }
}

abstract class Drink{//或者接口
    public String desc;
    abstract float price();
}

class Cofe extends Drink{
    private Cofe() {
        super();
    }

    @Override
    float price() {
        return 10.0f;
    }
}

class Tea extends Drink{
    @Override
    float price() {
        return 8.0f;
    }
}

//装饰类
class Plus extends Drink{//可以是abstract
    private Drink drink;
    public Plus(Drink drink) {
        this.drink = drink;
    }
    @Override
    float price() {
        // TODO Auto-generated method stub
        return drink.price();
    }
}

class Sugar extends Plus{
    public Sugar(Drink drink) {
        super(drink);
    }

    @Override
    float price() {
        return super.price()+3.0f;
    }
}

class Ice extends Plus{
    public Ice(Drink drink) {
        super(drink);
    }

    @Override
    float price() {
        return super.price()+2.0f;
    }
}