package com.priva.tango.lock;

public class VolatileTest {
    private volatile Integer number = 0;
    private volatile int x = 0;

    private void add(){
        number++;
    }
    private void addx(){
        x++;
    }

    public static void main(String[] args) {
        VolatileTest test = new VolatileTest();
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 100000; j++){
                    test.addx();
                }
            }).start();
        }
        while (Thread.activeCount()>2){
            Thread.yield();
        }
        System.out.println(test.x);

    }

    /**
     * 可见性，如果变量不volatile，不会停
     */
    public void see(){
        VolatileTest test = new VolatileTest();
        new Thread(() -> {
            try {
                Thread.sleep(100*2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            test.add();
            System.out.println(test.number);
        }).start();
        while (test.number == 0 ){
//            System.out.println("test.number ==0");
        }
    }
}
