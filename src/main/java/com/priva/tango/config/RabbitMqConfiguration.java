package com.priva.tango.config;

import com.priva.tango.service.MsgService;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class RabbitMqConfiguration {
	
	@Autowired
	MsgService service;
	/**
	 * 无需创建queueDeclare，实际运营中在控制台创建，这里负责创建绑定就行了
	 * @return
	 */
	@Bean(name="song")
    public Queue song() {
        return new Queue("rmq.meteria.song");
    }

    @Bean(name="singer")
    public Queue singer() {
        return new Queue("rmq.meteria.singer");
    }
    
    @Bean(name="list")
    public Queue list() {
        return new Queue("rmq.product.list");
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange("exchange");
    }
    
    @Bean
    public DirectExchange direct() {
        return new DirectExchange("direct");
    }

    //下面的bean不需要用到，但是可以进行注册操作
    @Bean
    Binding bindingExchangeMessage(@Qualifier("singer") Queue queueMessage, DirectExchange exchange) {
    	//可以在此处绑定所有，试试看
    	
    	//rmq.meteria.singer路由发送到singer
    	//BindingBuilder.bind(queueMessage).to(exchange).with("rmq.meteria.singer");
    	//rmq.meteria.singer rmq.meteria.song发送的数据都会到queueMessage的queue中
    	//BindingBuilder.bind(queueMessage).to(exchange).with("rmq.meteria.*");//*表示一个词,#表示零个或多个词
    	//rmq.meteria.singer rmq.meteria.song rmq.product.list 发送的数据都会到queueMessage的queue中
    	//BindingBuilder.bind(queueMessage).to(exchange).with("rmq.#");//*表示一个词,#表示零个或多个词
        return BindingBuilder.bind(queueMessage).to(exchange).with("rmq.meteria.singer");
    }

//    @Bean
//    Binding bindingExchangeMessages(@Qualifier("song") Queue queueMessages, TopicExchange exchange) {
//        return BindingBuilder.bind(queueMessages).to(exchange).with("topic.#");//*表示一个词,#表示零个或多个词
//    }
    
    //单独为某个队列设置回调
    @Bean
    public SimpleMessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory) {
	    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
	    container.setConnectionFactory(connectionFactory);
	    container.setQueueNames("rmq.meteria.singer");
	    container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
	    container.setMessageListener((ChannelAwareMessageListener) (message, channel) -> {
	    if(message.getMessageProperties().getHeaders().get("error") == null){
	    	System.out.println("config ack");
		    // 消息手动ack
		   // channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
	    }else {
	    	System.out.println("config nack");
		    // 消息重新回到队列
		    //channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,false);
		    // 拒绝消息（删除）
		    channel.basicReject(message.getMessageProperties().getDeliveryTag(),false);
	    }
	
	    });
	    return container;
    }
    
//    @ConditionalOnBean(org.springframework.amqp.rabbit.core.RabbitTemplate.class)
    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
    	RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
    	//投递回调,发布确认,主要是用来判断消息是否有正确到达交换机, 如果有, 那么就 ack 就返回 true
        /**
         * 配置
         * spring.rabbitmq.publisher-confirm-type=correlated
         */
    	rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
			@Override
			public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                String id = correlationData.getId();
                if(ack) {
					System.out.println("成功发送");
				} else {
					System.out.println("失败发送");
				}
			}
		});
    	// 触发setReturnCallback回调必须设置mandatory=true, 否则Exchange没有找到Queue就会丢弃掉消息, 而不会触发回调
        /**
         * spring.rabbitmq.publisher-return=true
         */
        rabbitTemplate.setMandatory(true);
        //投递回调,接口用于实现消息发送到 RabbitMQ 交换器, 但无相应队列与交换器绑定时的回调
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
        	System.out.println("call 投递失败才会调用的方法");
        	service.dealFailure();
        });
		return rabbitTemplate;
    }
}
