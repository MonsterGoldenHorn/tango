/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.priva.tango.nio.netty.heartbeate;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;


/**
 * Connects to a server periodically to measure and print the uptime of the
 * server.  This example demonstrates how to implement reliable reconnection
 * mechanism in Netty.
 */
public final class UptimeClient {

	/**
	 * IdleStateHandler
	 * 定时器
	 * 触发下一个handler的 userEventTriggered方法
	 * 
	 * 心跳就是检测读写事件
	 * 
	 */
    static final String HOST = System.getProperty("host", "127.0.0.1");
    static final int PORT = Integer.parseInt(System.getProperty("port", "8080"));
    // Sleep 5 seconds before a reconnection attempt.
    static final int RECONNECT_DELAY = Integer.parseInt(System.getProperty("reconnectDelay", "5"));
    // Reconnect when the server sends nothing for 10 seconds.
    private static final int READ_TIMEOUT = Integer.parseInt(System.getProperty("readTimeout", "10"));

    private static final UptimeClientHandler handler = new UptimeClientHandler();
    private static final Bootstrap bs = new Bootstrap();

    public static void main(String[] args) throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        bs.group(group)
                .channel(NioSocketChannel.class)
                .remoteAddress(HOST, PORT)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new IdleStateHandler(READ_TIMEOUT, 1000, 2000), handler);
                        /**
                         * 创建定时事件，mainloop循环判断时间满足就trigger不满足就放入队列
                         */
                        
                        
                        
                        //ReadTimeoutHandler/W  读写超时返回异常
                    }
                });
        bs.connect();
    }

    static void connect() {
        bs.connect().addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.cause() != null) {
                    handler.startTime = -1;
                    handler.println("Failed to connect: " + future.cause());
                }
            }
        });
    }
}
