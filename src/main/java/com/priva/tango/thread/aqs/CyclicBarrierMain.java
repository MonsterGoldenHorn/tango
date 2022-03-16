package com.priva.tango.thread.aqs;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * 阶段性的多次同步
 * 拼团
 *
 * <p>Title: CyclicBarrierMain.java</p>
 * <p>Description: CyclicBarrierMain.java</p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company: SI-TECH </p>
 * @author tangjc
 * @version 1.0
 * @creattime  2019-10-26 下午10:02:34
 */
public class CyclicBarrierMain {
     public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
         final CyclicBarrierMain barrierMain = new CyclicBarrierMain();
//runnable 做回调
         CyclicBarrier cyclicBarrier = new CyclicBarrier(2, new Runnable() {
             @Override
             public void run() {
                 System.out.println("all have being done");
             }
         });
         //CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
         new Thread(barrierMain.new Task(cyclicBarrier)).start();
//         TimeUnit.SECONDS.sleep(1);
         new Thread(barrierMain.new Task(cyclicBarrier)).start();
         System.out.println(cyclicBarrier.getNumberWaiting());
         cyclicBarrier.await();
         System.out.println("main done1");
         System.out.println("reset");
         cyclicBarrier.reset();
         cyclicBarrier.await();
         System.out.println("main done2");
         System.out.println(cyclicBarrier.isBroken());
         System.out.println(cyclicBarrier.getNumberWaiting());
    }
    class resetController{
        private CyclicBarrier cyclicBarrier;
        public resetController(CyclicBarrier cyclicBarrier){
            this.cyclicBarrier=cyclicBarrier;
        }
    }
    class Task implements Runnable{
         private CyclicBarrier cyclicBarrier;
         public Task(CyclicBarrier cyclicBarrier){
             this.cyclicBarrier=cyclicBarrier;
         }
         @Override
         public void run() {
             try {
                System.out.println(Thread.currentThread().getName()+"waiting1");
                System.out.println(cyclicBarrier.getNumberWaiting());
                TimeUnit.SECONDS.sleep(2);
                this.cyclicBarrier.await();//wait点1
                System.out.println(Thread.currentThread().getName()+"keep going1");
                System.out.println(Thread.currentThread().getName()+"waiting2");
                TimeUnit.SECONDS.sleep(2);
                this.cyclicBarrier.await();//wait点2
                //cyclicBarrier.reset();
                System.out.println(Thread.currentThread().getName()+"keep going2");
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
         }
         
     }
}

