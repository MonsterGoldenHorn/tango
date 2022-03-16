package com.priva.tango.test;

import java.util.concurrent.ArrayBlockingQueue;

import org.apache.tomcat.util.threads.TaskThread;

public class BlockingQueueMain {
	public static void main(String[] args) {
		
		TaskThread taskThread = new TaskThread(new ThreadGroup("ss"), ()->{}, "");
		
		final ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);
		new Thread(new Runnable() {
			int i = 0;
			@Override
			public void run() {
				while (true) {
					try {
						queue.put(i++);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		}).start(); 
	}
}
