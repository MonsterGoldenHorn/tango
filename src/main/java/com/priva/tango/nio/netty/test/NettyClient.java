package com.priva.tango.nio.netty.test;

import com.priva.tango.nio.netty.sample.TimeClientHandler;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient {
	public static void main(String[] args) throws InterruptedException {
		EventLoopGroup workerGroup = new NioEventLoopGroup();
	        
	    try {
	        Bootstrap b = new Bootstrap(); // (1)
	        b.group(workerGroup); // (2)
	        b.channel(NioSocketChannel.class); // (3)
	        b.option(ChannelOption.SO_KEEPALIVE, true); // (4)
	        b.handler(new ChannelInitializer<SocketChannel>() {
	            @Override
	            public void initChannel(SocketChannel ch) throws Exception {
	                ch.pipeline().addLast(new ClientHandler());
	            }
	        });
	        
	        // Start the client.
	        ChannelFuture f = b.connect("localhost", 9999).sync(); // (5)
	        // Wait until the connection is closed.
	        f.channel().closeFuture().sync();
	    } finally {
	        workerGroup.shutdownGracefully();
	    }
	}
}
