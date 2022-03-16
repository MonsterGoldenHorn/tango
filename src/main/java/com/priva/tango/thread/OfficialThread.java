package com.priva.tango.thread;

public class OfficialThread{
//https://docs.oracle.com/javase/1.5.0/docs/guide/misc/threadPrimitiveDeprecation.html
    private volatile Thread blinker;

    public void shutdown() {
        blinker=null;
    }

    public OfficialThread() {
        blinker = Thread.currentThread();
    }

    public void run() {
        Thread thisThread = Thread.currentThread();
        System.out.println(thisThread);
        System.out.println(blinker);
        while (blinker == thisThread) {
            try {
                thisThread.sleep(1000);
            } catch (InterruptedException e){
            }
            dosomething();
        }
    }

    private void dosomething() {
        System.out.println("业务代码");
    }
    
    public static void main(String[] args) throws InterruptedException {
        OfficialThread ot = new OfficialThread();
        ot.run();
        Thread.sleep(1000);
        ot.shutdown();
    }
    
}
