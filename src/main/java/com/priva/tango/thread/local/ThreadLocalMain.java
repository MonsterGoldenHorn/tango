package com.priva.tango.thread.local;

import java.lang.ref.WeakReference;
import java.util.concurrent.locks.LockSupport;

public class ThreadLocalMain {
    private static final int MAXIMUM_CAPACITY = 1 << 30;
    public static void main(String[] args) {
        System.out.println(Integer.numberOfLeadingZeros(16) | (1 << (16 - 1)));

//        ExecutorService executorService = Executors.newFixedThreadPool(1);
//        Runna runna = new Runna();
//        for (int i = 0; i < 10; i++) {
//            executorService.submit(runna);
//        }
    }
}
 class Runna implements Runnable{
    ThreadLocal<StringBuffer> s = new ThreadLocal(){
        @Override
        protected Object initialValue() {
            return new StringBuffer();
        }
    };
     @Override
     public void run() {
         s.set(s.get().append("1"));
         s = null;
         System.gc();
         LockSupport.parkNanos(1000);
         System.out.println(Thread.currentThread().getName() + s.get());
     }
 }
class TestValue extends WeakReference {
    /** The value associated with this ThreadLocal. */
    Object value;

    public TestValue(Object value) {
        super(value);
        this.value = value;
    }
    //RuntimeException 继承自Exception，方法体上可以不throws
    private void test(int i){
        if(i== 0 ){
            throw new RuntimeException("");
//            throw new Exception("");
        }
    }
}