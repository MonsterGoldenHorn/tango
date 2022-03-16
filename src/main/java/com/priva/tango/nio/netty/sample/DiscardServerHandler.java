package com.priva.tango.nio.netty.sample;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class DiscardServerHandler extends ChannelInboundHandlerAdapter {
	
	@Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)
        // Discard the received data silently.
        ((ByteBuf) msg).release(); // (3)
        
//        ctx.writeAndFlush(msg);//写
    }
	
	/*
	 * @Override public void channelRead(ChannelHandlerContext ctx, Object msg) {
	 * try { // Do something with msg } finally { ReferenceCountUtil.release(msg); }
	 * }
	 */
	
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}
