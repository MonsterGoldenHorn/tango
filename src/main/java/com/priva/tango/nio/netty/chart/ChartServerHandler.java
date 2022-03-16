package com.priva.tango.nio.netty.chart;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.HttpObject;
import io.netty.util.concurrent.GlobalEventExecutor;

public class ChartServerHandler  extends SimpleChannelInboundHandler<String>{

	private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		//不发给自己，创建一个map保存channel向指定用户发送
		
	}

	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		//channelGroup 可以广播消息
		channelGroup.writeAndFlush("xxx 加入了聊天");
		channelGroup.add(ctx.channel());
	}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		channelGroup.writeAndFlush("上线了");
	}
	
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("下线了");
	}
	
	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		System.out.println("断开连接");
	}
}
