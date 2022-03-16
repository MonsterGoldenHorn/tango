package com.priva.tango.thread.aqs;

import java.util.concurrent.Exchanger;

/**
 * 双线合作
 *
 */
public class ExchangerMain {

    public static void main(String[] args) {
        final Exchanger<Tool> exchanger = new Exchanger<Tool>();
        
        new Thread(new Runnable() {
            
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+" start");
                Tool t = null;
                try {
                    System.out.println("wait1");
                    t = exchanger.exchange(new Tool());
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"obj:"+t);
            }
        }).start();
        
        new Thread(new Runnable() {
            
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+" start");
                Tool t = null;
                try {
                    System.out.println("wait2");
                    t = exchanger.exchange(new Tool());
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"obj:"+t);
            }
        }).start();
        
        
    }
    
    
}

class Tool{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}

