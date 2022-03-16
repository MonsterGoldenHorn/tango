package com.priva.tango.jvm;

public class LockTest {
    //sync
    public synchronized static void test1(){

    }

    public synchronized void test2(){

    }

    private synchronized void test3(){

    }

    private void method(){
        synchronized(this){

        }
    }
}
