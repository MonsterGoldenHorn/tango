//package com.priva.tango.rtpro;
//
//import java.lang.annotation.Annotation;
//import java.lang.reflect.Executable;
//import java.security.AccessControlContext;
//import java.util.Map;
//import java.util.concurrent.TimeUnit;
//
//import sun.nio.ch.Interruptible;
//import sun.reflect.annotation.AnnotationType;
//
//public class Access{
//	public static void main(String[] args) {
//		final Thread th = new Thread(new Runnable() {
//
//			@Override
//			public void run() {
//				Access a = new Access();
//				a.begin();
//				System.out.println("执行中");
//				while(true) {
//
//				}
//			}
//		});
//		th.start();
//		try {
//			TimeUnit.SECONDS.sleep(2);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Thread th2 = new Thread(new Runnable() {
//
//			@Override
//			public void run() {
//				System.out.println("2执行中");
//				th.interrupt();
//			}
//		});
//		th2.start();
//	}
//
//	private Interruptible interruptor = null;
//	protected final void begin() {
//		if (interruptor == null) {
//			interruptor = new Interruptible() {
//				public void interrupt(Thread target) {
//					System.out.println("11111111");
//				}};
//		}
//		//直接看这一行
//
//		blockedOn(interruptor);
////		Thread me = Thread.currentThread();
////		if (me.isInterrupted())
////			interruptor.interrupt(me);
//	}
//
//	static void blockedOn(Interruptible interruptor) {
//		System.out.println("blockedOn");
//		Thread me = Thread.currentThread();
//		sun.misc.SharedSecrets.getJavaLangAccess().blockedOn(Thread.currentThread(),
//				interruptor);
//	}
//}
