package com.priva.tango.thread.aqs;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
/***
 * 
 * countDown到0后await解锁
 * 与CyclicBarrier类似，简易版，需要主动countDown
 *
 * 比如通知任务全部完成了主任务才结束
 *
 */
public class CountLacth {
    public static void main(String[] args) {
        CountDownLatch  cdl = new CountDownLatch(5);
        new CountThread(cdl).start();
        new CountThread(cdl).start();
        new CountThread(cdl).start();
        new CountThread(cdl).start();
        new CountThread2(cdl).start();
        try {
            cdl.await();
            System.out.println("sss");
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}

class CountThread extends Thread{
    private static CountDownLatch cd;
    
    public CountThread(CountDownLatch cd) {
        this.cd = cd;
    }

    @Override
    public void run() {
        cd.countDown();
    }
}
class CountThread2 extends Thread{
    private static CountDownLatch cd;
    
    public CountThread2(CountDownLatch cd) {
        this.cd = cd;
    }

    @Override
    public void run() {
        LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(5L));
        cd.countDown();
    }
}