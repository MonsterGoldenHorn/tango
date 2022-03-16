package com.priva.tango.nio.netty;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.NettyRuntime;

public class NettyMain {
	public static void main(String[] args) {
		//
		//BossGroup(NioEventLoopGroup)
		/**
		 * 主reactor组---acceptors
		 * NioEventLoop
		 * 	accept事件注册到worker
		 */
		//WorkerGroup(NioEventLoopGroup)
		/**
		 * 从reactors
		 * NioEventLoop
		 * 	轮询read/write事件
		 * 
		 * 
		 * 
		 */
		//NioEventLoop---poller
		
		
		
		
		
		
		/**
		 * SimpleChannelInboundHandler<T>可以转化数据为实体类
		 */
		//ChannelHandler--ChannelOutboundHandler/ChannelInboundHandler--ChannelInboundHandlerAdapter/out--SimpleChannelInboundHandler/out
		//出入站处理 注册/注销/读取/写事件
		
		/**
		 * 处理三大组件
		 * ChannelPipeline
		 * 		↓
		 * ChannelHandler
		 * ChannelHandlerContext（保存了handler的顺序，标志，inbound顺序传播inbound,out逆序out，异常顺序且无关in/out）
		 * 	head --- myhandlers --- tail
		 * 	head对unsafe操作与java nio sock交互
		 * 	tail终结事件
		 */
		
		
	}
	//reactor 
	/**
	 * acceptor---handler
	 * 单reactor，接收处理在同一个线程
	 * 可用性低，处理时产生异常将无法接收请求
	 * 用于业务简单，并发低处理快的场景
	 * 
	 * 单reactor多线程
	 * 
	 * 
	 */
	
	
	
}
