package com.priva.tango.nio.netty.test;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.util.Recycler;

public class RecycleMain {
	private static Recycler<User> RECYCLE = new Recycler<User>(){

		@Override
		protected User newObject(Handle<User> handle) {
			return null;
		}
		
	};
	/**
	 * 创建RECYCLE，维护FastThreadLocal，包含一个stack线程变量，每个线程有自己的handle在stack中，对应了一个value,通过handle回收和获取
	 * @param args
	 */
	public static void main(String[] args) {
		ByteBuf bf = PooledByteBufAllocator.DEFAULT.directBuffer();
	}
}

class User{
	private String name;
	private int id;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}