package com.priva.tango.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class SelectorMain {
	
	/**
	 * 
	 * io多路复用：单线程或单进程同时监测若干个文件描述符是否可以执行IO操作的能力
	 * Selector.open
	 * selector.select()//阻塞
	 * selector.wakeup()//唤醒阻塞
	 * selector.select(1000)
	 * selector.selectNow()//立即返回条数
	 * 
	 * 
	 * fd[size] connected(register)
	 * select
	 	使用bitmap rset：010100010001   默认1024位
	 	缺点：1024
	 		O(n)
	 		rset不可重用
	 		
		poll
			使用pollfd数组标记，超过1024bitmap限制
			重用标记
		epoll
			rset是用户态内核态共用
			标记时使用重排，触发遍历只需头部定位，O(1)
	  
	  
	  
	epoll
	pollWrapper.poll(timeout);
	updated = epollWait(pollArrayAddress, NUM_EPOLLEVENTS, timeout, epfd);
	epoll_wait(epfd, events, numfds, timeout)
	
	epoll_create/epoll_ctl/epoll_wait（创建/监听/轮询）
	
	
	 * 
	 */
	
	
	public static void main(String[] args) throws Exception {
		ServerSocketChannel ssc = ServerSocketChannel.open();
		ssc.socket().bind(new InetSocketAddress(9999));//.socket是获取真正的服务端
		ssc.configureBlocking(false);
		
		Selector selector = Selector.open();
		
		ssc.register(selector, SelectionKey.OP_ACCEPT);//监听接收,ServerSocketChannel也是channel更多关注接收请求
		while (true) {
			
			int selectNum = selector.select(1000);
			if(selectNum==0) {
				System.out.println("no connection");
				TimeUnit.SECONDS.sleep(1);
				continue;
			}else {
				Set<SelectionKey> selectionKeys = selector.selectedKeys();//
				Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
				
				while (keyIterator.hasNext()) {
					SelectionKey key = keyIterator.next();
					keyIterator.remove();
					
					if(key.isAcceptable()) {
						SocketChannel sc = ssc.accept();
						sc.configureBlocking(false);
						sc.register(selector, SelectionKey.OP_READ);
//						key.interestOps(SelectionKey.OP_ACCEPT);
					}
					
					if(key.isReadable()) {
						System.out.println("read from client");
						SocketChannel channel = (SocketChannel) key.channel();
						ByteBuffer buffer = ByteBuffer.allocate(1024);
						Charset charset = Charset.forName("utf-8");
						channel.read(buffer);
						buffer.flip();
						System.out.println(charset.decode(buffer).toString());
						
						key.interestOps(SelectionKey.OP_READ);
						
						key.cancel();
//						StringBuilder content = new StringBuilder();
//						while(channel.read(buffer) > 0)
//		                {
//		                	buffer.flip();
//		                    content.append(buffer.toString());
//		                    
//		                }
//						
//						while (buffer.hasRemaining()) {
//							System.out.println(buffer.get());
//						}
//						System.out.println(content);
//				                key.interestOps(SelectionKey.OP_READ);
//						while(buffer.hasRemaining()) {
//							System.out.println(buffer.toString());
//						}
//						channel.read(buffer);
						//获取后解析http报文处理业务请求
						
					}
				}
				
			}
			
			
		}
		
	}
}
