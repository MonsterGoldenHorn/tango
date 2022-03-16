package com.priva.tango.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class ServerSockChannelMain {
	public static void main(String[] args) throws IOException {
		//server
		ServerSocketChannel ssc = ServerSocketChannel.open();
		ssc.configureBlocking(false);
		ssc.bind(new InetSocketAddress(999));//其中调用了listen
		Selector sel = Selector.open();
		ssc.register(sel , SelectionKey.OP_ACCEPT);
		
		
		//client
		SocketChannel sc = SocketChannel.open();
		sc.connect(new InetSocketAddress("localhost", 999));//不一定成功
		sc.isConnected();
		sc.configureBlocking(false);
		sc.finishConnect();//configureBlocking：false 立即返回重试结果 true 阻塞直到连接成功
		sc.register(sel, SelectionKey.OP_WRITE);
		
	}
}
