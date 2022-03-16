package com.priva.tango.designmodle.behave;

public class TemplateMode {
    /**
     * Context refresh()是模板方法 postProcessBeanFactory是钩子方法
     * 
     * 
     * 反向的控制结构，父类调用子类
     * 定义一个操作中的算法的骨架，而将一些步骤延迟到子类中。 Te m p l a t e M e t h o d使得子类
                可以不改变一个算法的结构即可重定义该算法的某些特定步骤。

     * 
     * 1、封装不变部分，扩展可变部分。 2、提取公共代码，便于维护。 3、行为由父类控制，子类实现。
     * 缺点：每一个不同的实现都需要一个子类来实现，导致类的个数增加，使得系统更加庞大
     * 

Factory Method常被模板方法调用
S t r a t e g y：模板方法使用继承来改变算法的一部分
     */
    
    
    public static void main(String[] args) {
        Pie pie = new ApplePie();  
        pie.makePie();
        pie = new PeanutPie();
        pie.makePie();
    }
}
// A b s t r a c t C l a s s抽象类
abstract class Pie{
   final void makePie(){//为防止恶意操作，一般模板方法都加上 final 关键词。
       slice();
       System.out.println("混合搅拌");
       bake();
   }
   void hock(){
       //钩子方法，子类选择性重写，节点扩展
   }
   abstract void slice();
   abstract void bake();
}
//C o n c r e t e C l a s s
class ApplePie extends Pie{
    
    @Override
    void slice() {
        System.out.println("切苹果");
    }

    @Override
    void bake() {
        System.out.println("烤5分钟");
    }
}

class PeanutPie extends Pie{

    @Override
    void slice() {
        System.out.println("打花生");
    }

    @Override
    void bake() {
        System.out.println("烤10分钟");        
    }
    
}