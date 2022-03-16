package com.priva.tango.nio.netty.test;


import io.netty.util.concurrent.FastThreadLocal;
import io.netty.util.concurrent.FastThreadLocalThread;

public class FastThreadLocalMain {
//	private static FastThreadLocal<Object> f1 = new FastThreadLocal<Object>();
//	private static FastThreadLocal<Object> f2 = new FastThreadLocal<Object>();
	
//	FastThreadLocalThread 配合 FastThreadLocal 使用一套自己的index处理缓存
	
	//TODO 比较两个ThreadLocal变量时的情况
	private static ThreadLocal<Object> t1 = new ThreadLocal<Object>();
//	private static ThreadLocal<Object> t2 = new ThreadLocal<Object>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		t1.set(new Object());
//		t2.set(new Object());
		Thread currentThread = Thread.currentThread();
		t1.get();
		new java.lang.Thread(()->{
			Thread currentThread2 = Thread.currentThread();
			t1.set(new Object());
//			t2.set(new Object());
			t1.get();
		}).start();
	}
}
