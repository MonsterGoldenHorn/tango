package com.priva.tango.nio.netty.rpc.provider;

import com.priva.tango.nio.netty.rpc.MyService;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ServerHandler extends ChannelInboundHandlerAdapter {
	MyService service = new MyServiceImpl();
	@Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
		service.sayHello(msg.toString());
		ctx.writeAndFlush("done");
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
