package com.priva.tango.thread;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class ESMain {
    public static void main(String[] args) {
        
        ThreadPoolExecutor fixPool =
                new ThreadPoolExecutor(10, 10, 1000L, TimeUnit.MILLISECONDS,
                        new LinkedBlockingQueue<Runnable>(1),
                         new ThreadPoolExecutor.AbortPolicy());
        
        Runnable r =new Runnable() {
            @Override
            public void run() {
               LockSupport.park();
            }
        };
        
       fixPool.execute(r);
    }
}
