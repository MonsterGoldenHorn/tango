package com.priva.tango.nio.netty.rpc;

/**
 * 
 * 两个项目中共有的公共接口，消费者调用，生产者实现
 * @author 86139
 *
 */
public interface MyService {
	void sayHello(String msg);
}
