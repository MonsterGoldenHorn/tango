package com.priva.tango.thread.consumer;

import java.util.LinkedList;
import java.util.concurrent.locks.LockSupport;

public class SingelProducer extends Thread {
    private static volatile LinkedList<Resource> queue;
    
    public SingelProducer(LinkedList<Resource> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while(true){
            synchronized (queue) {
                if(queue.size()>=100){
                    
                    try {
                        queue.wait();
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                for(int i=0;i<100;i++){
                    queue.offer(new Resource());
                }
                System.out.println("生产100条，队列剩余"+queue.size());
                queue.notifyAll();
            }    
        }
    }
}

