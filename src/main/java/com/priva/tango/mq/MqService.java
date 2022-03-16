package com.priva.tango.mq;

import javax.annotation.PostConstruct;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ReturnCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.transaction.config.TxNamespaceHandler;

import com.priva.tango.dao.ChTSoapqueueMapper;
import com.priva.tango.entry.ChTListInfo;
import com.priva.tango.entry.ChTSinggerInfo;
import com.priva.tango.entry.ChTSoapqueue;
import com.priva.tango.entry.ChTSongInfo;
import com.rabbitmq.client.Channel;

@Component
public class MqService {
	@Autowired
	RabbitTemplate template;
	
	@Autowired
	ChTSoapqueueMapper chTSoapqueueMapper;
	
//	@PostConstruct
//	private void setCallback() {
//		template.setConfirmCallback(new ConfirmCallback() {
//			@Override
//			public void confirm(CorrelationData correlationData, boolean ack, String cause) {
//				if(ack) {
//					System.out.println("service成功发送");
//				}else {
//					System.out.println("service发送失败");
//				}
//			}
//		});
//		
//		template.setMandatory(true);
//		template.setReturnCallback(new ReturnCallback() {
//			
//			@Override
//			public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
//				// 发送失败被退回消息
//				System.out.println("call back");
//			}
//		});
//	}
	
	
	public void send() {
		ChTSoapqueue soap = chTSoapqueueMapper.selectByUniqueKey("72562");
		System.out.println("发送消息1:"+soap);
		template.convertAndSend("direct","rmq.meteria.singer",soap);
	}
	
	
//	
}
