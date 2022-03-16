package com.priva.tango.designmodle.behave;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StateMode {
/**
 * 允许一个对象在其内部状态改变时改变它的行为。对象看起来似乎修改了它的类
 * 1、行为随状态改变而改变的场景。 2、条件、分支语句的代替者。
 * 状态最好不能超过5个
 * 
 * 状态间有序进行传递，消除switch语句
 * 
 * 与桥接模板(选择)等不同的是，会更改方法调用对象的状态（传递）
 * 
 */
    
    
    public static void main(String[] args) {
        
        
        ExecutorService es = Executors.newCachedThreadPool();
         for(int i =0;i<10;i++){
             try {
                 Thread t1 = new Thread() {
                     public void run() {
                         Context ctx = new Context();
                         Order od = Order.startOrder;
                         ctx.setOrder(od);
                         while(true){
                             ctx.req();
                         }
                     }
                 };
                 es.execute(t1);
             }catch (Exception e) {
                 e.printStackTrace();
                 // TODO: handle exception
             }
         }
         es.shutdown();
        
//        Context ctx = new Context();
//        Order od = Order.startOrder;
//        ctx.setOrder(od);
//        while(true){
//            ctx.req();
//        }
    }
}

//state与ConcreteState简写到这里，枚举类多线程不支持
enum Order{
    startOrder {
        @Override
        void doing() {
            System.out.println("接单....");
            ctx.setOrder(waiting);
        }
    },waiting {
        @Override
        void doing() {
            System.out.println("处理...");
            //判断处理是否正常选择
            ctx.setOrder(finish);
        }
    },finish {
        @Override
        void doing() {
            System.out.println("结束...");
            System.exit(0);
        }
    },reject{
        @Override
        void doing() {
            System.out.println("拒绝...");
        }
    };

    public void setCtx(Context ctx){
        this.ctx = ctx;
    }
    public Context ctx;
    
    abstract void doing();
}

class Context{
    private Order order;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
        order.setCtx(this);
    }
    
    public void req() {
        order.doing();
    }
    
}