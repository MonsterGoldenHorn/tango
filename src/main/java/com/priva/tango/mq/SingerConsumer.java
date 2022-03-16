package com.priva.tango.mq;

import java.io.IOException;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.priva.tango.entry.ChTListInfo;
import com.priva.tango.entry.ChTSoapqueue;
import com.priva.tango.entry.ChTSongInfo;
import com.rabbitmq.client.Channel;

@Component
public class SingerConsumer {
	@Autowired
	RabbitTemplate template;
	/**
	 * 可以在注解中绑定队列
	 * @param songMsg
	 */
	@RabbitListener(queues = "rmq.meteria.song")
	public void processSong(@Payload ChTSongInfo songMsg) {
		template.receiveAndConvert("");
	}
	
	@RabbitListener(queues = "rmq.meteria.singer")
	public void processSinger(ChTSoapqueue msg, Channel channel, Message message) throws IOException {
		//参数根据类型传过来
//		channel.basicAck(deliveryTag, multiple);
		System.out.println("rmq.meteria.singer consume");
		System.out.println("get:"+message);
		System.out.println("msg:"+msg);
		//do database tx commit
//		try {
//			channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
//		} catch (IOException e) {
//			//1.在极限情况下,数据已经记录完成(spring事务也会管理rmq事务)，nack的时候断线，无法控制，但是可以通过添加版本号控制数据版本，或者用数据一致性控制
//			//最终一致性的情况下，重复处理没有影响，但是也会要求有版本号控制ABA问题
//			//2.DeliveryTag是增长的，如果生产者单线程可以控制顺序的情况下，用这个就可以，只要保证顺序都可以
//		}
		//channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
	}
	
	@RabbitListener(queues = "rmq.product.list")
	public void processSinger(ChTListInfo listMsg) {
		
	}
}
