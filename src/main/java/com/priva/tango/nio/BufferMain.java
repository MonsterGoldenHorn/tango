package com.priva.tango.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.IntBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.atomic.AtomicInteger;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufProcessor;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.buffer.UnpooledByteBufAllocator;

public class BufferMain {
	private final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
	private static final int COUNT_BITS = Integer.SIZE - 3;
	private static final int CAPACITY   = (1 << COUNT_BITS) - 1;
	private static final int RUNNING    = -1 << COUNT_BITS;
    private static final int SHUTDOWN   =  0 << COUNT_BITS;
    private static final int STOP       =  1 << COUNT_BITS;
    private static final int TIDYING    =  2 << COUNT_BITS;
    private static final int TERMINATED =  3 << COUNT_BITS;
    private static int runStateOf(int c)     { return c & ~CAPACITY; }
    private static int workerCountOf(int c)  { return c & CAPACITY; }
    private static int ctlOf(int rs, int wc) { return rs | wc; }
    
	public static void main(String[] args) throws IOException {
		
		
		 int c = new AtomicInteger(-536870907).get();
         int rs = runStateOf(c);
         int wc = workerCountOf(c);
         System.out.println(wc);
		
		
		/*
		 * 0000 0010 //2
		 * 1111 1101 //revert
		 * 1111 1110 //add 1
		 * 
		 * 0000 0010 //&
		 */
        /* ByteBuf
         * <pre>
		 *  BEFORE clear()
		 *
		 *      +-------------------+------------------+------------------+
		 *      | discardable bytes |  readable bytes  |  writable bytes  |
		 *      +-------------------+------------------+------------------+
		 *      |                   |                  |                  |
		 *      0      <=      readerIndex   <=   writerIndex    <=    capacity
		 *
		 *
		 *  AFTER clear()
		 *
		 *      +---------------------------------------------------------+
		 *      |             writable bytes (got more space)             |
		 *      +---------------------------------------------------------+
		 *      |                                                         |
		 *      0 = readerIndex = writerIndex            <=            capacity
		 * </pre>
		 * 
		 * readable = writerIndex - readerIndex
		 * writable = maxWrite - writerIndex
		 * 
		 * clear只进行指针移动，不会回收缓存
		 * 
		 * mark reset
		 */
         ByteBuf byteBuf = Unpooled.buffer();
         byteBuf.discardReadBytes();//重新覆盖回收缓存
         
//         pooled使用RECYCLER循环利用
//         PooledHeapByteBuf 是包内可见的
         /**
          * PooledHeapByteBuf 通过 HeapByteBufUtil 直接操作数组
          * PooledUnsafeHeapByteBuf 通过 PooledUnsafeHeapByteBuf 操作unsafe计算偏移量读取,是不是unsafe不是代码决定的
          * PooledDirectByteBuf 操作对外内存
          */
//         unpooled
         
         UnpooledByteBufAllocator u = new UnpooledByteBufAllocator(false);
         u.directBuffer();//newDirectBuffer
         u.heapBuffer();
         
         //pooled会使用PoolThreadCache
         PooledByteBufAllocator p = new PooledByteBufAllocator(true);
         p.heapBuffer();
         p.buffer();//还是heapBuffer
         
		
         /**
          * cache
          * MemoryRegionCache	32k以上不缓存
          * 
          * 
          */
         
         /**
          * 内存
          * 	tiny		small		normal			huge
          * 0			512b		8k			16M
          * 
          * 
          * 		subpage			page		chunk
          * 
          * 
          * tiny 16b 32b …… 496b
          * 	分配时除以16找tiny[16]的index
          * small
          * normal
          * 
          * 1 Chunk = 16M = 2048 Page
          * 1 Page = 8K
          * 
          */
         
//         PoolThreadCache 
         
		//初始化，定义大小
		IntBuffer intBuffer = IntBuffer.allocate(5);
		
		//write
		for (int i = 0; i < 5; i++) {
			intBuffer.put(i);
		}
		intBuffer.flip();
		//read old
//		while (intBuffer.hasRemaining()) {
//			System.out.println(intBuffer.get());
//		}
		
		IntBuffer if2 = intBuffer.asReadOnlyBuffer();
		
		while (if2.hasRemaining()) {
			System.out.println(intBuffer.get());
		}
		if2.put(0);//exception
		
//		int[] dst = new int[5];
//		intBuffer.get(dst, 0, intBuffer.position());
		
		
		
		/**
		 * MappedByteBuffer 堆外内存操作
		 */
		RandomAccessFile raf = new RandomAccessFile("d:\\1.txt", "rw"); 
		FileChannel fc = raf.getChannel();
		MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_WRITE, 0, 5);
		mbb.putChar('x');//当前位置0放置？？
		mbb.putChar(1,'x');//放到第1个字符
		raf.close();
		
		//addComponent是零拷贝,CompositeByteBuf是组合性的一个类似适配器
		CompositeByteBuf cb = ByteBufAllocator.DEFAULT.compositeBuffer(3);
		cb.addComponent(Unpooled.wrappedBuffer(new byte[] {1,2,3}));
		cb.addComponent(Unpooled.wrappedBuffer(new byte[] {4,2,3}));
	}
}
