package com.priva.tango.nio.netty.sample;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.stream.ChunkedWriteHandler;

public class ServerMain {
	public static void main(String[] args) throws InterruptedException {
		
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		
		try {
			ServerBootstrap server = new ServerBootstrap();
			server.group(bossGroup, workerGroup).
				channel(NioServerSocketChannel.class).//不同协议有不同的channel类型
				option(ChannelOption.SO_BACKLOG, 20).//SO_BACKLOG 服务器连接等待队列大小
				childOption(ChannelOption.SO_KEEPALIVE, true).
				childHandler(new ChannelInitializer<SocketChannel>() {
					@Override
	                 public void initChannel(SocketChannel ch) throws Exception {
	                     ch.pipeline().addLast(new DiscardServerHandler());
	                     ch.pipeline().addLast(new ChunkedWriteHandler());//pipeLine可以有多个handler --- tomcat valve
	                 }
				});
			ChannelFuture f = server.bind(9999).addListener(future->{
				//监听写法
				if(future.isSuccess()) {
					System.out.println("succ listened on 9999");
				}
			});
			f.addListener(new ChannelFutureListener() {
				
				@Override
				public void operationComplete(ChannelFuture future) throws Exception {
					if(future.isDone()) {
						System.out.println("done");
					}
				}
			});
			f.channel().closeFuture().sync();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
}
