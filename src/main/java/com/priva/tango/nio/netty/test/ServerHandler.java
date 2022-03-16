package com.priva.tango.nio.netty.test;

import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import org.redisson.codec.MsgPackJacksonCodec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.util.CharsetUtil;

public class ServerHandler extends ChannelInboundHandlerAdapter {
	//static int packLength = 0;
	private final Charset charset = CharsetUtil.UTF_8;
//	ProtobufDecoder/MsgPackDecoder
//	SimpleChannelInboundHandler<I>  channelRead封装释放bytebuf,调用 channelRead0
	@Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
		int packLength = 0;
//		ctx.fireChannelRead(msg);//多个handler链，从当前pipeline传播，寻找ctx.next
//		ctx.channel().pipeline().fireChannelRead("");//从head开始向下传播
		// task queue
		ByteBuf b = (ByteBuf) msg;
		packLength+=b.readableBytes();
		System.out.println("msg size:"+b.readableBytes());
//		System.out.println("packLength:"+packLength);
		System.out.println("---start-----");
		while(b.isReadable()) {
			System.out.print(b.readChar());
		}
		System.out.println();
		System.out.println("----end----");
		ctx.channel().eventLoop().execute(()->{
			try {
//				ctx.writeAndFlush("");
				ctx.writeAndFlush(Unpooled.copiedBuffer("yes",CharsetUtil.UTF_8));//write从tail开始传播到head,然后unsafe完成对java nio的write
//				ctx.fireChannelRead(msg);
			} catch (Exception e) {
//				ctx.fireExceptionCaught(cause);//找next fire,在tail触发onUnhandledInboundException日志释放
				ctx.close();
			}
		});
		
		//schedule queue
//		ctx.channel().eventLoop().schedule(()->{
//			try {
////				ctx.writeAndFlush("");
//				ctx.writeAndFlush(Unpooled.copiedBuffer("yes",CharsetUtil.UTF_8));
//			} catch (Exception e) {
//				ctx.close();
//			}
//		},5,TimeUnit.SECONDS);
		
		//cross channel
		//ChannelInitializer做标记，集中存储
    }
	
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
    
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
    	System.out.println("read complete");
    }
}
