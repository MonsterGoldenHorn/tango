package com.priva.tango.lock;

import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * -XX:SurvivorRatio=8
 * -XX:-UseAdaptiveSizePolicy
 * -XX:+UseConcMarkSweepGC
 * -Xms20m
 * -Xmx20m
 *
 * （1） 互斥条件：一个资源每次只能被一个进程使用。
 * （2） 请求与保持条件：一个进程因请求资源而阻塞时，对已获得的资源保持不放。
 * （3） 不剥夺条件:进程已获得的资源，在末使用完之前，不能强行剥夺。
 * （4） 循环等待条件:若干进程之间形成一种头尾相接的循环等待资源关系。
 */
public class DeadLock {
    private static String lock1 = "1";
    private static String lock2 = "2";

    public static void main(String[] args) {
        new Thread(){
            @Override
            public void run() {
                lock1();
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                lock2();
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    byte[] bytes = new byte[1024 * 1024];
                }
            }
        }.start();
    }
    private static void lock1(){
        synchronized (lock1){
            System.out.println(Thread.currentThread().getName()+":进入锁1");
            LockSupport.parkNanos(5000);
            synchronized (lock2){
                System.out.println(Thread.currentThread().getName()+":进入锁2");
            }
        }
    }
    private static void lock2(){
        synchronized (lock2){
            System.out.println(Thread.currentThread().getName()+":进入锁2");
            LockSupport.parkNanos(5000);
            synchronized (lock1){
                System.out.println(Thread.currentThread().getName()+":进入锁1");
            }
        }
    }
    private ReentrantLock lock = new ReentrantLock();
    private void deadLock2(){
        try {
            lock.lock();
            lock.lock();

            System.out.println("锁住了");

        }finally {
            lock.unlock();
        }

    }
}
