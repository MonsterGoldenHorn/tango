package com.priva.tango.thread;

import java.util.concurrent.locks.LockSupport;


public class ShutdowMain {
 public static void main(String[] args) throws InterruptedException {
     
     
     new Thread(new Runnable() {
        @Override
        public void run() {
            LockSupport.park();
        }
    }).start();
     System.out.println("exit");
     System.exit(0);
     
     
     
     
     
     
     
     
     
     
     Thread t1 = new Thread(new WorkThread());
     Thread t2 = new Thread(new WorkThread());
//     Thread t3 = new Thread(new WorkThread());
     t1.start();
     Thread.sleep(1000);
     t2.start();
     System.exit(0);
     t1.stop();///老子觉得没问题，再看看
     Thread.sleep(1000);
     t2.stop();
     
     
     
     
//     Thread.sleep(15000);
//     new Thread(new Runnable() {
//         
//         @Override
//         public void run() {
//             Thread th = new Thread(new WorkThread());
//             try {
//                 Thread.sleep(100);
//                 th.start();
//                 Thread.sleep(1000);
//                 th.stop();
//                 Thread.sleep(1000);
//                 System.out.println(th.isAlive());
//                 System.out.println("wait to null");
//            } catch (InterruptedException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
////             th.interrupt();
//         }
//     }).start();
////     System.out.println("join");
////     th.join();
////     System.out.println("done");
//     
//     System.out.println("main sleep"+Thread.currentThread().getName());
//     
}
}
