package com.priva.tango.tomcat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class NioServerMain {
	//register poll select
	public static void main(String[] args) {
		System.out.println(Math.min(2,Runtime.getRuntime().availableProcessors()));
//		try {
//			ServerSocketChannel sc = ServerSocketChannel.open();
//			InetSocketAddress addr = new InetSocketAddress("localhost",8088);
//			sc.bind(addr, 50);
//			while (true) {
//				SocketChannel socket = sc.accept();
//				System.out.println("次连接");
//			}
//			
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}
}
