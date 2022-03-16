package com.priva.tango.mq;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.beans.factory.annotation.Autowired;

import com.priva.tango.entry.ChTSoapqueue;

public class Producer {
	@Autowired
	RabbitAdmin admin;
	@Autowired
	RabbitTemplate template;
	
	public void deliver(ChTSoapqueue queue) {
		//数据库查询循环ChTSoapqueue
		String key = this.dispatchRoutinKey(queue);
		Queue que = new Queue("topic.messages");
		BindingBuilder.bind(que).to(new DirectExchange(key)).with("topic.#");
		template.setConfirmCallback(new ConfirmCallback() {
			@Override
			public void confirm(CorrelationData correlationData, boolean ack, String cause) {
				
			}
		});
	}
	
	private String dispatchRoutinKey(ChTSoapqueue queue) {
		String key = "";
		String type = queue.getType();
		switch (type) {
		case "2":
			key = "rmq.meteria.song";
			break;
		case "3":
			key = "rmq.meteria.singer";
			break;
		default:
			key = "rmq.product.list";
			break;
		}
			
		return key;
	}
}
