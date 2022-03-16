package com.priva.tango.thread.aqs;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;
/**
 * 歌曲模板部分，update与使用缓存的读写分离可以用这个
 *  读写分离，等读完了写，写的时候不能读
 */
public class ReadWriteMain {
    private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    private ReadLock rl = rwl.readLock();
    private WriteLock wl = rwl.writeLock();

    List<Resource> list = new ArrayList<>();
    public static void main(String[] args) {

//        rl.tryLock();
//        rl.newCondition();
//        rl.notifyAll();
//
//        rwl.writeLock();

    }

    /**
     * 已有的读写锁
     */
    public void reentrant(){
        ReadWriteMain lock = new ReadWriteMain();
        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                lock.read();
            }
        }, "x").start();
        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                if(i%10 == 0){
                    lock.write(new Resource(i,""));
                } else {
                    lock.read();
                }
            }
        },"y").start();
    }
    private void read(){
        try {
            rl.lock();
            System.out.println(Thread.currentThread().getName() + "线程读取");
            for (Resource resource : list) {
                System.out.println(resource.getId());
                System.out.println(resource.getName());
            }
        }finally {
            rl.unlock();
        }

    }

    private void write(Resource res){
        try {
            wl.lock();
            System.out.println(Thread.currentThread().getName() + "线程写数据");
            list.add(res);
        }finally {
            wl.unlock();
        }

    }

}
class Resource{
    private int id;
    private String name;

    public Resource(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}