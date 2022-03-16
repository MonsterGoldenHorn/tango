package com.priva.tango.tomcat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class ClientMain {
	public static void main(String[] args) {
		
		try {
			SocketChannel socketChannel = SocketChannel.open();
			socketChannel.configureBlocking(false);
			
			InetSocketAddress address = new InetSocketAddress("127.0.0.1", 9999);
//			socketChannel.connect(address);
			
			if(!socketChannel.connect(address)) {
				while (!socketChannel.finishConnect()) {
					//一直循环
					System.out.println("connection fail");
				}
			}
			String str = "yes connected";
			ByteBuffer buffer = ByteBuffer.wrap(str.getBytes("utf-8"));
			
			socketChannel.write(buffer);
			buffer.flip();
//			socketChannel.read(buffer);
			TimeUnit.SECONDS.sleep(5);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		CyclicBarrier cb = new CyclicBarrier(10);
//		for (int i = 0; i < 9; i++) {
//			new Thread(new MaxConnectTest(cb)).start(); 
//		}
	}
}

class MaxConnectTest implements Runnable{
	private CyclicBarrier cb;
	public MaxConnectTest(CyclicBarrier cb) {
		this.cb = cb;
	} 
	@Override
	public void run() {
		
		try {
			SocketChannel socketChannel = SocketChannel.open();
			socketChannel.configureBlocking(false);
			
			InetSocketAddress address = new InetSocketAddress("127.0.0.1", 9999);
			socketChannel.connect(address);
			
			if(!socketChannel.connect(address)) {
				while (!socketChannel.finishConnect()) {
					//一直循环
				}
			}
			String str = "yes connected";
			ByteBuffer buffer = ByteBuffer.wrap(str.getBytes());
			
			socketChannel.write(buffer);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		

//		try {
//			try {
//				cb.await();
//			} catch (BrokenBarrierException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			Socket socket = new Socket("localhost", 8080);
//			System.out.println("sock addr:"+socket.getInetAddress().toString());
//			System.out.println("local port:"+socket.getLocalPort());
//			System.out.println("localsock addr:"+socket.getLocalSocketAddress().toString());
//			System.out.println("---------------------------------");
//			
//			System.out.println("Waiting ");
//			TimeUnit.SECONDS.sleep(110);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}
	
	
}
