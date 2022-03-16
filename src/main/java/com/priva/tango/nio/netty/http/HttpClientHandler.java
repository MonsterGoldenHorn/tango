package com.priva.tango.nio.netty.http;

import java.nio.charset.Charset;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.cookie.ClientCookieEncoder;
import io.netty.handler.codec.http.cookie.DefaultCookie;
import io.netty.util.CharsetUtil;

public class HttpClientHandler  extends SimpleChannelInboundHandler<FullHttpRequest>{

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest msg) throws Exception {
		System.out.println("response arrive");
		if(msg instanceof HttpRequest) {
			String content = ((HttpContent) msg).content().toString(Charset.forName("UTF-8"));
			System.out.println(content);
		}else {
			System.out.println(msg.getClass());
		}
		ctx.close();
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ByteBuf bb = Unpooled.copiedBuffer("<?xmlversion=\"1.0\"encoding=\"utf-8\"?><portal><Response><RspCode>000000</RspCode><RspDesc>succ</RspDesc></Response></portal>",CharsetUtil.UTF_8);
		int initialCapacity = 513;
    	 for(int i = 0;i<initialCapacity;i++) {
    		 bb.writeChar('a');
    	 }
    	 bb.writeChar('b');
    	 System.out.println("total sending:"+bb.readableBytes());
		try {
//			DefaultFullHttpRequest req = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.POST, "/", bb);
			
			 // Prepare the HTTP request.
            HttpRequest request = new DefaultFullHttpRequest(
                    HttpVersion.HTTP_1_1, HttpMethod.POST, "/", bb);
            request.headers().set(HttpHeaderNames.HOST, "localhost");
            request.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.CLOSE);
            request.headers().set(HttpHeaderNames.ACCEPT_ENCODING, HttpHeaderValues.GZIP);
            request.headers().set(HttpHeaderNames.EXPECT,HttpHeaderValues.CONTINUE);
            request.headers().set(HttpHeaderNames.TRANSFER_ENCODING,HttpHeaderValues.CHUNKED);

            // Set some example cookies.
            request.headers().set(
                    HttpHeaderNames.COOKIE,
                    ClientCookieEncoder.STRICT.encode(
                            new DefaultCookie("my-cookie", "foo"),
                            new DefaultCookie("another-cookie", "bar")));
            ctx.channel().writeAndFlush(request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//bb.release();
		}
	}
}
