package com.priva.tango.thread;

public class WorkThread implements Runnable
{
    public static Object monitor = new Object();
    public static volatile Integer i = 0;
//    ThreadLocal
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"准备拿锁");
        synchronized (monitor) {
            while(true){
                try {
                    System.out.println("拿到锁"+Thread.currentThread().getName());
                    System.out.println(i++);
                    Thread.sleep(100);
                }catch (ThreadDeath  e) {
                    // TODO: handle exception
                    System.out.println("death");
                    throw new 
                    ThreadDeath(); 
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
    
}
