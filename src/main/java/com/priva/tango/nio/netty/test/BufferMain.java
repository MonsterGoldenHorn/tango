package com.priva.tango.nio.netty.test;

import java.nio.charset.Charset;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

public class BufferMain {
	public static void main(String[] args) {
		
		System.out.println(-536870871 & ~536870911 );
		System.out.println( CharsetUtil.encoder(Charset.forName("utf-8")).maxBytesPerChar());//3
		System.out.println( CharsetUtil.encoder(Charset.forName("gbk")).maxBytesPerChar());//2
		
//		func1();
	}

//  pooled使用RECYCLER循环利用
//  PooledHeapByteBuf 是包内可见的
  /**
   * PooledHeapByteBuf 通过 HeapByteBufUtil 直接操作数组
   * PooledUnsafeHeapByteBuf 通过 PooledUnsafeHeapByteBuf 操作unsafe计算偏移量读取,是不是unsafe不是代码决定的
   * PooledDirectByteBuf 操作对外内存
   */
	private static void func2() {
		ByteBuf buffer = Unpooled.copiedBuffer("avass", Charset.forName("gbk"));
		System.out.println(buffer.capacity());//15   5*3
		System.out.println(buffer.readableBytes());//5
		
		System.out.println(buffer.readerIndex());//0
		System.out.println(buffer.writerIndex());//5
//		byte[] array = buffer.array();
		System.out.println(buffer.getCharSequence(1, 2, CharsetUtil.UTF_8));//va
		System.out.println(buffer.readerIndex());//0
		System.out.println(buffer.writerIndex());//5
		
	}
	
	private static void func1() {
		ByteBuf buffer = Unpooled.buffer(10);
		/**
		 * 1.init()
		 * readerIndex/writerIndex-------------------------------------maxCapacity
		 * 
		 * 2.write()
		 * readerIndex-----------------writerIndex--------------------maxCapacity
		 * 
		 * 3.read()
		 * -------------readerIndex----writerIndex--------------------maxCapacity
		 * 
		 */
		for (int i = 0; i < 9; i++) {
			buffer.writeByte(i);
		}
		
		System.out.println(buffer.capacity());//10
		System.out.println(buffer.readableBytes());//9
		
		System.out.println(buffer.readerIndex());//0
		System.out.println(buffer.writerIndex());//9
		
		buffer.readByte();//readerIndex increase
//		buffer.getByte(0);//no increase
		System.out.println(buffer.readerIndex());//1
	}
}
