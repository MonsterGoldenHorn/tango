package com.priva.tango.lock;

/**
 * synchronized
 * 原子,和Mysql类似，要么同时失败要么同时成功
 * 可见
 * 有序（执行上保证有序）
 *
 * volatile
 * 可见
 * 有序（禁止指令重排）
 *
 * 内存屏障：
 * 1.禁止指令前后重排序
 * 2.强制刷新缓存数据
 *
 */
public class SyncTest {
    public static volatile Integer i = 0;
    public static Object lock = 0;
    private int  x = 0;
    public static void main(String[] args) {
        SyncTest syncTest = new SyncTest();
        for (int j = 0; j < 10; j++) {
            new Thread(() -> {
                for (int k = 0; k < 1000000; k++) {
                    syncTest.add();
                }
            }).start();
        }

        while (Thread.activeCount()>2){
            Thread.yield();
        }
//        System.out.println(i);synchronized
        System.out.println(syncTest.getX());
    }

    private void add(){
//        x=x;
        int k = x + 1;
        synchronized (lock){
            x++;
        }
    }

    public static Integer getI() {
        return i;
    }

    public static void setI(Integer i) {
        SyncTest.i = i;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
}
