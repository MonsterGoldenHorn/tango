package com.priva.tango.thread.consumer;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class RetrainProducer {
//ReentrantLock实现读写锁，这里不适用
//    public static ReentrantLock lock = new ReentrantLock();
//    public static Condition proLock = lock.newCondition();
//    public static Condition conLock = lock.newCondition();
    private static volatile LinkedList<Resource> queue;
    public static void main(String[] args) {
        LinkedList<Resource> queue = new LinkedList<Resource>();
        RetrainProducer retrainProducer = new RetrainProducer();
        retrainProducer.new Producer("生产者1").start();
        //new Producer(queue, "生产者2").start();
        retrainProducer.new Consumer2("消费者1").start();
    //    new Consumer2(queue, "消费者2").start();
    }
    class Consumer2 extends Thread{
        private String name;
        public Consumer2(String name) {
            this.name = name;
        }
        
        @Override
        public void run() {
            while(true){
                if(queue.size()==0){
                    LockSupport.park(queue);
                }
                Resource r = queue.pollFirst();
                System.out.println(name);
                r.work();
            }
        }
    }
    
    class Producer extends Thread{
        private String name;
        
        public Producer(String name) {
            this.name = name;
        }
        
        @Override
        public void run() {
            while(true){
                if(queue.size()>=20){
                    LockSupport.park(queue);
                }
                for(int i=0;i<20;i++){
                    queue.offer(new Resource());
                }
                System.out.println(name+"生产100条，队列剩余"+queue.size());
            }
        }
    }
}
