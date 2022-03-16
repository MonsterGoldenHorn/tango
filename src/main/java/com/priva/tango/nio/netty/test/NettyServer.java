package com.priva.tango.nio.netty.test;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;

public class NettyServer {
	
	
	/**
	 * ulimit -n 进程最大文件句柄，包含class，tcp
	 * 
	 * 
	 * 
	 * 
	 * @param args
	 * @throws InterruptedException
	 */
	
	
	public static void main(String[] args) throws InterruptedException {
		
		EventLoopGroup bossGroup = new NioEventLoopGroup(1);
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		EventLoopGroup bizGroup = new NioEventLoopGroup();
		
		try {
			ServerBootstrap server = new ServerBootstrap();
			server.group(bossGroup, workerGroup).
				channel(NioServerSocketChannel.class).
				option(ChannelOption.SO_BACKLOG, 20).
				childOption(ChannelOption.SO_KEEPALIVE, true).
				childHandler(new ChannelInitializer<SocketChannel>() {
					@Override
	                 public void initChannel(SocketChannel ch) throws Exception {
						 ch.pipeline().addLast(bizGroup, new FixedLengthFrameDecoder(1026));
//						 ch.pipeline().addLast(bizGroup, new StringDecoder());
						//观察者模式
	                     ch.pipeline().addLast(bizGroup, new ServerHandler());//add的handler都包装为context
	                     //一个channel只绑定一个NioEventLoop，对应一个selector一个pipeline
	                     
	                     // ch.pipeline().addLast(new ServerHandler());
	                 }
				});
			ChannelFuture f = server.bind(9999).sync();
			f.channel().closeFuture().addListener((future)->{
				System.out.println(future);
				System.out.println("closed----------");
			}).sync();
//			f.channel().close();
		} finally {
			System.out.println("--shut--");
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
}
