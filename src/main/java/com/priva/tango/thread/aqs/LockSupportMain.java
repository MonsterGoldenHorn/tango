package com.priva.tango.thread.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class LockSupportMain {
    public static void main(String[] args) {
        Thread  thread = new Thread(new Runnable() {
            
            @Override
            public void run() {
                LockSupport.parkUntil(TimeUnit.SECONDS.toNanos(5L));
                LockSupport.park();
                System.out.println(TimeUnit.SECONDS.toNanos(5L));
//                LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(5L));
//                LockSupport.park();
                System.out.println("1");
            }
        });
        thread.start();
        //LockSupport.unpark(thread);
    }
}
