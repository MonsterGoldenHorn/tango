package com.priva.tango.nio.netty.http;

import java.nio.charset.Charset;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutorGroup;
public class HttpServerHandler  extends SimpleChannelInboundHandler<FullHttpRequest>{
	//handler线程池数量有限，业务量大需要线程池处理业务
	EventExecutorGroup group = new DefaultEventExecutorGroup(10);
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest  msg) throws Exception {
		System.out.println("HttpRequest arrive");
		if(msg instanceof HttpRequest) {
			String content = ((HttpContent) msg).content().toString(Charset.forName("UTF-8"));
			System.out.println(content);
			
//			String uri = ((HttpRequest) msg).getUri();
//			if("".equals(uri)) {
//				
//			} 
			ByteBufAllocator alloc0 = ctx.alloc();
			ByteBufAllocator alloc1 = ctx.channel().alloc();
			alloc0.buffer();
			ByteBuf bb = Unpooled.copiedBuffer("<?xmlversion=\"1.0\"encoding=\"utf-8\"?><portal><Response><RspCode>000000</RspCode><RspDesc>成功</RspDesc></Response></portal>",CharsetUtil.UTF_8);
			
			DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, bb);
			response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
			response.headers().set(HttpHeaderNames.CONTENT_LENGTH, bb.readableBytes());
//			ctx.channel().write(msg);
//			ctx.channel().flush();
			
//			ctx.channel().writeAndFlush(response);
			
//			ctx.channel().eventLoop().execute(()->{System.out.println("11");});
//			ctx.executor().execute(command);
			
		}else {
//			System.out.println(msg.getClass());
		}
		ctx.channel().close();
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("客户端断开-----------");
		super.channelInactive(ctx);
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}
