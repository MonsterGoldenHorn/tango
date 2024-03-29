package com.priva.tango.nio.netty.rpc.provider;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class NettyServer {
	public static void main(String[] args) throws InterruptedException {
		
		EventLoopGroup bossGroup = new NioEventLoopGroup(1);
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		
		try {
			ServerBootstrap server = new ServerBootstrap();
			server.group(bossGroup, workerGroup).
				channel(NioServerSocketChannel.class).
				option(ChannelOption.SO_BACKLOG, 20).
				childOption(ChannelOption.SO_KEEPALIVE, true).
				childHandler(new ChannelInitializer<SocketChannel>() {
					@Override
	                 public void initChannel(SocketChannel ch) throws Exception {
	                     ChannelPipeline pipeline = ch.pipeline();
	                     pipeline.addLast(new StringDecoder());
	                     pipeline.addLast(new StringEncoder());
	                     pipeline.addLast(new ServerHandler());
	                 }
				});
			ChannelFuture f = server.bind(9999).sync();
			f.channel().closeFuture().sync();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
}
