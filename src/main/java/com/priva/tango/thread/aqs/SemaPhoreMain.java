package com.priva.tango.thread.aqs;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;


/**
 * sync只能锁一个，SemaPhore可以锁多个，控制处理线程数量
 *
 */
public class SemaPhoreMain {
    public static void main(String[] args) {
        SemaPhoreMain phoreMain = new SemaPhoreMain();
        final Semaphore semaphore = new Semaphore(1);
        new Thread(phoreMain.new Task(semaphore)).start();
        new Thread(phoreMain.new Task(semaphore)).start();
        new Thread(phoreMain.new Task(semaphore)).start();
        System.out.println(semaphore.getQueueLength());
        semaphore.availablePermits();
        semaphore.hasQueuedThreads();//
        semaphore.drainPermits();//返回可用的许可个数，并将可用许可数清零。
        semaphore.getQueueLength();
    }
    
    class Task implements Runnable{
        private Semaphore semaphore;
        public Task(Semaphore semaphore){
            this.semaphore=semaphore;
        }
        
        @Override
        public void run() {
            try {
                semaphore.acquire();
                TimeUnit.SECONDS.sleep(5);
                System.out.println(semaphore.getQueueLength());
//                semaphore.acquire(2);//获取多个，应用注册时从服务端获取连接数这样
           } catch (InterruptedException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
           } finally{
               semaphore.release();
           }
        }
        
    }
}
