package com.priva.tango.rmq;
import com.rabbitmq.client.*;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.AMQP.Queue;
import org.apache.tomcat.util.http.fileupload.IOUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class RmqMain {
	/**
	 * default param
	 */
	private static String userName = "guest";
	private static String password = "guest";
	private static String virtualHost = "/guest";
	private static String hostName = "localhost";
	private static int portNumber = 5672;
	
	public static void main(String[] args) {
		Connection conn = null;
		Channel channel = null;
		try {
			ConnectionFactory factory = new ConnectionFactory();
			// "guest"/"guest" by default, limited to localhost connections
			factory.setUsername(userName);
			factory.setPassword(password);
			factory.setVirtualHost(virtualHost);
			factory.setHost(hostName);
			factory.setPort(portNumber);
			
			conn = factory.newConnection();
			channel = conn.createChannel();
			/**
			 * 持久化队列时如果是非持久化转持久化会报错
			 * 消息保证不丢失还需要消息持久化到本地磁盘（消费者还要ack）
			 * 开启发布确认
			 */
			channel.exchangeDeclare("", "direct", true);
			//系统默认生成的随机不持久队列名称 Ad-exd
			String queueName = channel.queueDeclare().getQueue();
			//路由绑定队列
			channel.queueBind(queueName, "exchangeName", "routingKey");
			//下面三个方法都是精简版，可以单独创建更多参数更复杂的控制
//			channel.exchangeDeclare(exchangeName, "direct", true);
//			channel.queueDeclare(queueName, true, false, false, null);
//			channel.queueBind(queueName, exchangeName, routingKey);
			/**
			 * declare queue
			 */
			//被动模式，一旦查询失败需要重新连接
			Queue.DeclareOk response = channel.queueDeclarePassive("queue-name");
			// returns the number of messages in Ready state in the queue
			response.getMessageCount();
			// returns the number of consumers the queue has
			response.getConsumerCount();
			//做命令使用，不等待结果
			channel.queueDeclareNoWait(queueName, true, false, false, null);
			//delete
			channel.queueDelete("queue-name");//A queue or exchange can be explicitly deleted:
			channel.queueDelete("queue-name", false, true);//It is possible to delete a queue only if it is empty:
			channel.queueDelete("queue-name", true, false);//or if it is not used (does not have any consumers):
			channel.queuePurge("queue-name");//A queue can be purged (all of its messages deleted):
			//扩展参数x-max-priority 1-255,越大越优先
			Map<String, Object> args1 = new HashMap<String, Object>();
			args1.put("x-max-priority", 10);//最大允许10
			channel.queueDeclare("my-priority-queue", true, false, false, args1);
			/**
			 * publish
			 */
			byte[] messageBodyBytes = "Hello, world!".getBytes();
			channel.basicPublish("exchangeName", "routingKey", null, messageBodyBytes);
			//mandatory->true 找不到routingKey，入队列失败返回消息
			//false 丢弃
			channel.basicPublish("exchangeName", "routingKey", true,
					/**
					 * 消息持久化保存 MessageProperties.PERSISTENT_TEXT_PLAIN
					 */
					MessageProperties.PERSISTENT_TEXT_PLAIN,
                    messageBodyBytes);
			//发布确认
			channel.confirmSelect();
			channel.addReturnListener(new ReturnListener() {
	                @Override
	                public void handleReturn(int arg0, String arg1, String arg2, String arg3, BasicProperties arg4, byte[] arg5)
	                        throws IOException {
	                    //此处便是执行Basic.Return之后回调的地方
	                    String message = new String(arg5);
	                    System.out.println("Basic.Return返回的结果:  "+message);
	                }
	            });
			 //向定制参数的queue插入定制消息
			 channel.basicPublish("exchangeName", "routingKey",
		             new AMQP.BasicProperties.Builder()
		               .contentType("text/plain")
		               .deliveryMode(2)//2:persistent TODO
		               .priority(1)//queueDeclare中最大的范围内
		               .userId("bob")
						.expiration("1000")//过期时间,超过后未消费成为死信
		               .build(),
		               messageBodyBytes);
			 //
			 Map<String, Object> headers = new HashMap<String, Object>();
			 headers.put("latitude",  51.5252949);
			 headers.put("longitude", -0.0905493);
			 channel.basicPublish("exchangeName", "routingKey",
			              new AMQP.BasicProperties.Builder()
			                .headers(headers)
			                .build(),
			                messageBodyBytes);
			 String exchangeName = "exchangeName";
			 String routingKey = "routingKey";
			//expiration 有效期
			 channel.basicPublish(exchangeName, routingKey,
		             new AMQP.BasicProperties.Builder()
		               .expiration("60000")
		               .build(),
		               messageBodyBytes);
			/**
			 * 消费者
			 */
			//***通道上允许的未确认消息的最大数量，实现不公平分发，如果1条未确认，该消费者就不会有消息
			channel.basicQos(1);
			/**
             * 多线程
             * 1.每个线程一个Channel， using a channel per thread.(一个消费一个生产是可以的)
             * 2.spring中channel最好使用ampq连接池
             * 3.一个channel最好只有一个消费者
             */
			 //push
			 //consumerTag Distinct Consumer instances must have distinct consumer tags,多标签有连接重置的问题
			 boolean autoAck = false;
			 final Channel channel1 = conn.createChannel();//conn中有一个线程连接池，在这里start conn
			 channel1.basicConsume(queueName, autoAck, "myConsumerTag",
			      new DefaultConsumer(channel1) {
			          @Override
			          public void handleDelivery(String consumerTag,
			                                     Envelope envelope,
			                                     AMQP.BasicProperties properties,
			                                     byte[] body)
			              throws IOException
			          {
			              String routingKey = envelope.getRoutingKey();
			              String contentType = properties.getContentType();
			              long deliveryTag = envelope.getDeliveryTag();
			              // (process the message components here ...)
			              channel1.basicQos(5);//当前超过5个unack就不再被发送
			              channel1.basicQos(5, false);//当前超过5个就不再被发送   false：consumer独用 true：channel用
			              //这里可以做到按处理能力分发，多个消费者公用一个值
			 //deliveryTag Channel每一个都有渠道可见的增长值，必须与接收到的值一样，感觉可以防止重复消费
			              channel1.basicAck(deliveryTag, false);
			          }
			          //handleShutdownSignal when channels and connections close
			          //handleConsumeOk is passed the consumer tag before any other callbacks to that Consumer are called.
			          //handleCancel channel1.basicCancel(consumerTag); 
			      });
			 //pull highly discouraged
			 autoAck = false;
			 GetResponse response1 = channel.basicGet(queueName, autoAck);
			 if (response1 == null) {
			     // No message retrieved.
			 } else {
			     AMQP.BasicProperties props = response1.getProps();
			     byte[] body = response1.getBody();
			     long deliveryTag = response1.getEnvelope().getDeliveryTag();
			     /**
				  * publisher confirms
				  * consumer Ack
				  */
			    
			     //ack multiple  true:一对全对，小于当前tag的（多线程有跑的快的） false：当前对
			     channel1.basicAck(deliveryTag, false); // acknowledge receipt of the message
			     //nack multiple  true:拒绝所有 false：拒绝当前deliveryTag
			     //requeue false:入死信队列 true:入队列顶端
			     channel1.basicNack(deliveryTag, true, true);
			     channel1.basicReject(deliveryTag, false);
			 }
			 //
			 //addShutdownListener(ShutdownListener listener) and
			 //removeShutdownListener(ShutdownListener listener), to manage any listeners, which will be fired when the object transitions to closed state. Note that, adding a ShutdownListener to an object that is already closed will fire the listener immediately
			 //getCloseReason(), to allow the investigation of what was the reason of the object's shutdown
			 //isOpen(), useful for testing whether the object is in an open state
			 //close(int closeCode, String closeMessage), to explicitly notify the object to shut down
			 conn.addShutdownListener(new ShutdownListener() {
				    public void shutdownCompleted(ShutdownSignalException cause)
				    {
				        //
				    	cause.getCause();//network communication failure etc(AMQP.Channel.Close,AMQP.Connection.Close)
				    	cause.getReason();//returns information about the cause
				    	cause.isHardError();//get information whether it was a connection or a channel error
				    }
				});
			 conn.getCloseReason();
			 
			 
		} catch (IOException | TimeoutException e) {
			System.out.println("connect failed");
		} finally {
			if(channel!=null) {
				// it will be done automatically anyway when the underlying connection is closed.
				try {
					channel.close();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (TimeoutException e) {
					e.printStackTrace();
				}
			}
			IOUtils.closeQuietly(conn);
//			channel.close();
//			conn.close();
		}
		//2rd way
//		ConnectionFactory factory = new ConnectionFactory();
//		factory.setUri("amqp://userName:password@hostName:portNumber/virtualHost");
//		Connection conn = factory.newConnection();
		
	}
}
/**
 * 
 * 	private String contentType;
    private String contentEncoding;
    private Map<String,Object> headers;
    private Integer deliveryMode;
    private Integer priority;
    private String correlationId;
    private String replyTo;
    private String expiration;
    private String messageId;
    private Date timestamp;
    private String type;
    private String userId;
    private String appId;
    private String clusterId;
*/