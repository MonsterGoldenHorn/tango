package com.priva.tango.thread.consumer;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

public class PCClient {
    public static void main(String[] args) {
        LinkedList<Resource> queue = new LinkedList<Resource>();
        Consumer consumer = new Consumer(queue,"消费者1");
        Consumer consumer2 = new Consumer(queue,"消费者2");
        Consumer consumer3 = new Consumer(queue,"消费者3");
        SingelProducer producer = new SingelProducer(queue);
        SingelProducer producer2 = new SingelProducer(queue);
        consumer.start();
        producer.start();
        consumer2.start();
        consumer3.start();
    }
}

class Consumer extends Thread{
    private static volatile LinkedList<Resource> queue;
    private String name;
    
    public Consumer(LinkedList<Resource> queue, String name) {
       this.queue = queue;
       this.name = name;
    }

    @Override
    public void run() {
        while(true){
            synchronized (queue) {
                if(queue.size()==0){
                    try {
                        queue.wait();
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                Resource r = queue.pollFirst();
                System.out.println(name);
                r.work();
                queue.notifyAll();
            }
        }   
    }
}

class Resource{
    private static volatile int i = 0;
    private int x = 0;
    public Resource() {
        i++;
        x = i;
    }
    
    public void work() {
       // System.out.println("消费第"+x+"条");
    }
}


class Resource2{
    private static volatile AtomicInteger i = new AtomicInteger(0);
    private int x = 0;
    public Resource2() {
        i.getAndIncrement();
        x = i.get();
        System.out.println("生产第"+x+"条");
    }
    
    public void work() {
        System.out.println("消费第"+x+"条");
    }
}