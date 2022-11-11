package com.priva.tango.thread.pool;


import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @description
 * @date 2022/8/19 16:51
 */
public class ThreadPoolMain {
    public static void main(String[] args) {
        ThreadPoolExecutor fixPool =
                new ThreadPoolExecutor(10, 10, 1000L, TimeUnit.MILLISECONDS,
                        new LinkedBlockingQueue<Runnable>(1),
                        new ThreadPoolExecutor.AbortPolicy());

        Runnable r =new Runnable() {
            @Override
            public void run() {
                try {

                }catch (Exception e){
                    System.out.println();
                }
            }
        };

        for (int i = 0; i < 5; i++) {
            fixPool.submit(r);
        }

    }
}
