package com.priva.tango.nio;

import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.MessageToByteEncoder;

public class DeEnCoderMain {
	/**
	 * 一个char一个字节
	 * 16进制下一个字符要四位，两位一个字节
	 * @param args
	 */
	public static void main(String[] args) {
//		ByteToMessageDecoder
//		DelimiterBasedFrameDecoder//自定义分隔
//			先判断是否为行分隔，再找最小分隔
//		LineBasedFrameDecoder//按行解析
			//丢弃模式/非丢弃   有分隔/无分隔
//		FixedLengthFrameDecoder//定长,不足的补长
//		LengthFieldBasedFrameDecoder//长度域
			/**
			 * lengthFieldOffset//
			 * lengthFieldLength
			 */
		
//		MessageToByteEncoder<I>
		/**
		 * 匹配对象isIntance()---分配内存allocate()---编码实现encode()---释放对象---传播数据pipeline.write---释放内存release(Bytebuffer)
		 * 
		 * head向sock写数据
		 * buf转direct---添加message,entry---
		 * write0中unsafe#write向outboundBuffer addMessage(不是直接调用writeAndFlush时可以多次写数据多次调用)，将数据添加entry--unsafe.flush--NioSocketChannel.doWrite--
		 * --in.nioBuffers(1024, maxBytesPerGatheringWrite)--io.netty.channel.ChannelOutboundBuffer.nioBuffers(int, long)将entry数据写入buffer--
		 * 
		 * NioSocketChannel.doWrite的ch.write(buffer)写数据执行完，服务端就能读取到了
		 * 
		 */
		
	}
}
