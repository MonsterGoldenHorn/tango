package com.priva.tango.thread.aqs;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockMain {
    /**
     * 公平锁线程tryLock时入队列，发现队列为空或者头为自己成功，否则入队列尾部，吞吐量小
     * 非公平不入队列，直接看成功失败，吞吐量大，sync
     *
     * clh队列
     * 可重入（递归锁）
     *
     * 因为有公平锁，所以记录了线程，所以需要自旋，不然加进去没意义
     */
    public static void main(String[] args) {
        MyLock lock = new MyLock();
        new Thread(()->{
            lock.sync1();
        }).start();
        new Thread(()->{
            lock.sync1();
        }).start();
    }

}

class MyLock{
    private ReentrantLock lock = new ReentrantLock();
    public synchronized void sync1(){
        System.out.println(Thread.currentThread().getName() + "sync1");
        sync2();
    }

    public synchronized void sync2(){
        System.out.println(Thread.currentThread().getName() + "sync2");
    }

    public void ree1(){
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "ree1");
            ree2();
        }finally {
            lock.unlock();
        }
    }

    public void ree2(){
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "ree2");
        }finally {
            lock.unlock();
        }
    }

}