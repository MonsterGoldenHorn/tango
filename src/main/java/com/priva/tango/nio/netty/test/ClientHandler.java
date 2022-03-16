package com.priva.tango.nio.netty.test;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Date;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

public class ClientHandler extends ChannelInboundHandlerAdapter {
	private final Charset charset = CharsetUtil.UTF_8;
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf m = (ByteBuf) msg; // (1)
        try {
//        	File file = new File("D:\\tjc\\newStudy\\netty\\test_data\\");
            System.out.println(new Date());
            byte[] tempDatas=new byte[m.readableBytes()];
			m.readBytes(tempDatas);
			System.out.println("from server:"+new String(tempDatas,"UTF-8"));
            System.out.println(msg);
            ctx.close();
        } catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            m.release();
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//    	 int initialCapacity = 1024*1024;
    	int initialCapacity = 513;
		ByteBuf buff = Unpooled.buffer(initialCapacity);
    	 for(int i = 0;i<initialCapacity;i++) {
    		 buff.writeChar('a');
    	 }
         try {
//         	File file = new File("D:\\tjc\\newStudy\\netty\\test_data\\");
             System.out.println("buff readableBytes:"+buff.readableBytes());
             ctx.writeAndFlush(buff);
//             ctx.close();
         } finally {
//        	 buff.release();
         }
    }
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
    
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    }
}