package com.priva.tango.mq;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.impl.recovery.AutorecoveringConnection;

public class RmqWithExcutor {
	static ExecutorService es = Executors.newFixedThreadPool(20);
	public static void main(String[] args) {
		try {
			//用户提交的es不会自动关闭
			//The same executor service may be shared between multiple 
			//connections, or serially re-used on re-connection but it cannot be used after it is shutdown().
			ConnectionFactory factory = new ConnectionFactory();
			Connection conn = factory.newConnection(es);
			
			//like memcache
//			Address[] addrArr = new Address[]{ new Address(hostname1, portnumber1)
//                    , new Address(hostname2, portnumber2)};
//			ConnectionFactory factory;
//			conn = factory.newConnection(addrArr);
			//As of version 4.0.0 of the Java client, automatic recovery is enabled by default 
			//重连，建立连接，设置监听，设置队列参数，设置交换机队列绑定，设置消费者
			factory.setAutomaticRecoveryEnabled(true);
			factory.setNetworkRecoveryInterval(10000);//不要太频繁
			AutorecoveringConnection conn1 = (AutorecoveringConnection) factory.newConnection(es);
			conn1.addRecoveryListener(null);
			
			
		} catch (IOException | TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
