package com.priva.tango.rmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Recoverable;
import com.rabbitmq.client.RecoveryListener;
import com.rabbitmq.client.impl.DefaultExceptionHandler;
import com.rabbitmq.client.impl.StandardMetricsCollector;
import com.rabbitmq.client.impl.nio.NioParams;
import com.rabbitmq.client.impl.recovery.AutorecoveringConnection;

public class HighTechRmq {
	private static String userName = "guest";
	private static String password = "guest";
	private static String virtualHost = "/guest";
	private static String hostName = "localhost";
	private static int portNumber = 5672;
	static ConnectionFactory factory = null;
	static {
		Connection conn = null;
		Channel channel = null;
		try {
			factory = new ConnectionFactory();
			// "guest"/"guest" by default, limited to localhost connections
			factory.setUsername(userName);
			factory.setPassword(password);
			factory.setVirtualHost(virtualHost);
			factory.setHost(hostName);
			factory.setPort(portNumber);
			
			conn = factory.newConnection();
			channel = conn.createChannel();
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	/**
	 * ha 集群镜像队列，policies 设置参数
	 * ha-mode
	 * ha-params  备份数
	 * ha-sync-mode
	 */
	/**
	 * As of version 4.0.0 of the Java client, automatic recovery is enabled by default 
	 * 重连，建立连接，设置监听，设置队列参数，设置交换机队列绑定，设置消费者
		1.触发时机
		An I/O exception is thrown in connection's I/O loop
		A socket read operation times out
		Missed server heartbeats are detected
		Any other unexpected exception is thrown in connection's I/O loop
		2.不会触发的情况
		 初始化连接时出现的需要手动进行重试
		 Connection.Close手动调用
		 Channel-level exceptions will not trigger
		3.结果
		重连自动将unack的消息入队列`
	 * @throws Exception
	 */
	public void autoRecovery() throws Exception{
		//4.0不需要了
		factory.setAutomaticRecoveryEnabled(true);
		factory.setNetworkRecoveryInterval(10000);//不要太频繁
		AutorecoveringConnection conn1 = (AutorecoveringConnection) factory.newConnection();
		conn1.addRecoveryListener(new RecoveryListener() {
			
			@Override
			public void handleRecoveryStarted(Recoverable recoverable) {
				System.out.println("start to recovery");
			}
			
			@Override
			public void handleRecovery(Recoverable recoverable) {
				System.out.println("complect to recovery");
			}
		});
	}
	
	//heartbeat 两次失败后重连
	public void setHeartbeates() {
		// TODO Auto-generated method stub
		factory.setRequestedHeartbeat(60);//0关闭  5 to 20 一般
	}
	
	/**
	 * NIO
	 */
	public void enableNIO() {
		// TODO Auto-generated method stub
		factory.useNio();
		factory.setNioParams(new NioParams().setNbIoThreads(4));
	}
	
	/**
	 * 统一日志
	 */
	public void setLogHandler() {
		// 继承DefaultExceptionHandler自定义处理
		factory.setExceptionHandler(new DefaultExceptionHandler());
	}
	
	/**
	 * 统计
	 */
	public void enableMetric () {
//		JmxMeterRegistry registry = new JmxMeterRegistry();
//		MicrometerMetricsCollector metrics = new MicrometerMetricsCollector(registry);
//		JmxReporter reporter = JmxReporter
//				.forRegistry(registry)
//				.inDomain("com.rabbitmq.client.jmx")
//				.build();
//		reporter.start();
		StandardMetricsCollector metrics = new StandardMetricsCollector();
		factory.setMetricsCollector(metrics);
	}
	
	public void enableTLS() throws Exception{
		factory.useSslProtocol();
	}
}
