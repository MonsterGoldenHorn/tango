package com.priva.tango.nio.netty.http;

import java.util.concurrent.FutureTask;

import javax.swing.tree.TreeNode;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;

public class ServerMain {
	public static void main(String[] args) throws InterruptedException {
		
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap server = new ServerBootstrap();
			
			
			server.group(bossGroup, workerGroup).
				channel(NioServerSocketChannel.class).
				option(ChannelOption.SO_BACKLOG, 20).
				childOption(ChannelOption.SO_KEEPALIVE, true).
				childHandler(new HttpServerlInitializer());
			ChannelFuture f = server.bind(9950).addListener(future->{
				//监听写法
				if(future.isSuccess()) {
					System.out.println("------------succ listened on 9950-------------");
				}
			});
			f.channel().closeFuture().addListener(future->{
				//监听写法
				if(future.isSuccess()) {
					System.out.println("------------ server closed");
				}
			}).sync();
//			f.channel().closeFuture().sync();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
	
	
	/**
	 * 
	 * MessageToMessageDecoder<I> extends ChannelInboundHandlerAdapter
	 * channelRead中调用decode(ctx, cast, out);处理编码问题
	 * 
	 * HttpObjectAggregator
	 * 	Expect:100-continue 头部包含这种需要服务器确认是否接收大型数据
	 * 	Transfer-Encoding: chunked 表示支持分段
	 * new HttpObjectAggregator(1024 * 10 * 1024)超过1024 * 10 * 1024长度报413错误
	 * 只处理inbound
	 * 
	 * 
	 * HttpClientCodec
	 * 
	 * Encoder
	 * 解析httprequest,分别将请求类型，头，内容写入bytebuf然后加一些换行分隔符，调用write&flush流程写数据
	 * Decoder
	 * 
	 * 
	 * 
	 * HttpObjectAggregator
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
}
