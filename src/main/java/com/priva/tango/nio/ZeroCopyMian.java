package com.priva.tango.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

import org.springframework.util.StopWatch;

public class ZeroCopyMian {
	/**
	 * 文件系统fs---DMA--->kernel缓存---cpu--->user sapace---cpu--->sockect buffer---DMA--->sock
	 * dma direct me
	 * mmap=kernel+user sapace
	 * @throws IOException 
	 * 
	 * 
	 * 
	 */
	
	public static void main(String[] args) throws Exception {
		
		SocketChannel sc = SocketChannel.open();
		sc.connect(new InetSocketAddress("localhost", 999));//不一定成功
		sc.configureBlocking(true);
		sc.finishConnect();//true阻塞
		Selector sel = Selector.open();
		sc.register(sel, SelectionKey.OP_WRITE);
		
		File file = new File("");
		FileChannel fc = new FileInputStream(file).getChannel();
		StopWatch sw = new StopWatch("client");
		sw.start();
		
		//windows 8m Linux一次  transfer
		fc.transferTo(0, file.length(), sc);
		
		sw.prettyPrint();
		
		sc.close();
		fc.close();
	}
}
