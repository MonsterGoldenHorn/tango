package com.priva.tango.designmodle.behave;

import java.util.ArrayList;
import java.util.List;

public class ObMode {
/**
 * 观察者模式:依赖(Dependents), 发布-订阅( P u b l i s h - S u b s c r i b e )

 * 
 * Observable
 * 
 * 
一个抽象模型有两个方面，其中一个方面依赖于另一个方面。将这些方面封装在独立的对象中使它们可以各自独立地改变和复用。
一个对象的改变将导致其他一个或多个对象也发生改变，而不知道具体有多少对象将发生改变，可以降低对象之间的耦合度。
一个对象必须通知其他对象，而并不知道这些对象是谁。
需要在系统中创建一个触发链，A对象的行为将影响B对象，B对象的行为将影响C对象……，可以使用观察者模式创建一种链式触发机制。
 * 
 * 
 * 
Mediator(5.5): 通过封装复杂的更新语义 , ChangeManager充当目标和观察者之间的中介者。
S i n g l e t o n (3 . 5): ChangeManager可使用S i n g l e t o n模式来保证它是唯一的并且是可全局访问的。

 * 
 */
    public static void main(String[] args) {
        SportNewsCenter snc = new SportNewsCenter();
        Observer ob1 = new PaperPart();
        Observer ob2 = new WebPart();
        snc.regisiter(ob1);
        snc.regisiter(ob2);
        snc.receive();
    }
}


interface Observer{
    void update();
}

interface Subjct{
    void receive();
    void regisiter(Observer ob);
    void remove(Observer ob);
    void notfy();
}

class SportNewsCenter implements Subjct{
    List<Observer> list = new ArrayList<Observer>();
    @Override
    public void receive() {
        System.out.println("收到最新消息");
        this.notfy();
    }

    @Override
    public void regisiter(Observer ob) {
        list.add(ob);
    }

    @Override
    public void remove(Observer ob) {
        list.remove(ob);
    }

    @Override
    public void notfy() {
        for(Observer o : list){
            o.update();
        }
    }
}

class PaperPart implements Observer{
    @Override
    public void update() {
        System.out.println("印刷最新消息");
    }
}

class WebPart implements Observer{
    @Override
    public void update() {
        System.out.println("网站发布最新消息");
    }
    
}