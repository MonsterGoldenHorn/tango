package com.priva.tango.nio.netty.http;

import javax.net.ssl.SSLEngine;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class HttpServerlInitializer extends ChannelInitializer<SocketChannel>{
	private SslContext context;
	private boolean client;
	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		
		SSLEngine engine = context.newEngine(ch.alloc());
		pipeline.addLast("sslHandler", new SslHandler(engine));
		pipeline.addLast("http decoder",new HttpRequestDecoder());
		pipeline.addLast("http enc",new HttpResponseEncoder());
		pipeline.addLast("http agg",new HttpObjectAggregator(65535));
		pipeline.addLast("http chunk",new ChunkedWriteHandler());
		pipeline.addLast("http handler",new HttpServerHandler());
		//入站从头向尾 出站从尾到头，都包在了ChannelHandlerContext中且保存了双向链表
		//pipeline中就是handler的集合组成的责任链
	}

}
