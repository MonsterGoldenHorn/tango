package com.priva.tango.thread.consumer;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class PCClient2 {
    public static ReentrantLock lock = new ReentrantLock();
    public static Condition proLock = lock.newCondition();
    public static Condition conLock = lock.newCondition();
    public static int resourceNum=10;
    public static void main(String[] args) {
        PCClient2 p = new PCClient2();
        Consumer consumer = p.new Consumer("消费者1");
        Consumer consumer2 = p.new Consumer("消费者2");
        Consumer consumer3 = p.new Consumer("消费者3");
        SingelProducer producer = p.new SingelProducer("生产者1");
        SingelProducer producer2 = p.new SingelProducer("生产者2");
        producer.start();
        producer2.start();
        
        consumer.start();
        consumer2.start();
        consumer3.start();
    }
    private static volatile LinkedList<Resource> queue = new LinkedList<Resource>();;
    class Consumer extends Thread{
        private String name;
        
        public Consumer(String name) {
            this.name = name;
        }
        
        @Override
        public void run() {
            while(true){
                lock.lock();
                while(queue.size()==0){
                    //System.out.println(name+"进来了");
                    proLock.signalAll();
                    try {
                        conLock.await();
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
                Resource r = queue.pollFirst();
//                System.out.println(name);
                r.work(); 
                lock.unlock();
            }   
        }
    }
    
    class Resource{
        private int x = 0;
        public Resource() {
            x ++;
        }
        
        public void work() {
            // System.out.println("消费第"+x+"条");
        }
    }
    
    class SingelProducer extends Thread {
        private String name;
        
        public SingelProducer(String name){
            this.name = name;
        }
        @Override
        public void run() {
            while(true){
                lock.lock();
                while(queue.size()>=resourceNum){
                    System.out.println(name+"进来了");
                    try {
                        proLock.await();  
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                for(int i=0;i<resourceNum;i++){
                    queue.offer(new Resource());
                }
                System.out.println(name+"生产"+resourceNum+"条，队列剩余"+queue.size());
                conLock.signalAll();
                lock.unlock(); 
            }
            }
        }
    }  
