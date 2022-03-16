package com.priva.tango.mq.rabbit;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

/**
 * #spring.rabbitmq.host=localhost
 * #spring.rabbitmq.port=5672
 * #spring.rabbitmq.username=java
 * #spring.rabbitmq.password=java
 */
//@Configuration
public class RabbitConfig {
    public static String host = "localhost";
    public static String name = "java";
    public static String password = "java";
    //交换机
    @Bean("direct")
    public DirectExchange exchange(){
        return new DirectExchange("direct");
    }

    //队列
    @Bean("prod")
    public Queue queue(){
        Map<String, Object> prams = new HashMap<>();
        //额外参数，死信队列这里设置
        /**
         * 死信队列如果在生产者设置超时时间，只会检查队列第一个是否过期，如果第一个是10S第二个是5S，会导致第二个慢了
         * 解决方案为安装rmq插件重启
         * rabbitmq-plugins enable rabbitmq_delayed_message_exchange
         */
        return QueueBuilder.durable("prod").ttl(1000).withArguments(prams).build();
    }

    //绑定
    @Bean
    public Binding bind(@Qualifier("prod") Queue queue, @Qualifier("direct")DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("123");
    }


    /**
     * 插件方式实现延时队列
     */
    public static final String DELAYED_QUEUE_NAME = "delay.queue.demo.delay.queue";
    public static final String DELAYED_EXCHANGE_NAME = "delay.queue.demo.delay.exchange";
    public static final String DELAYED_ROUTING_KEY = "delay.queue.demo.delay.routingkey";
    @Bean
    public Queue immediateQueue() {
        return new Queue(DELAYED_QUEUE_NAME);
    }

    @Bean
    public CustomExchange customExchange() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-delayed-type", "direct");
        return new CustomExchange(DELAYED_EXCHANGE_NAME, "x-delayed-message", true, false, args);
    }

    @Bean
    public Binding bindingNotify(@Qualifier("immediateQueue") Queue queue,
                                 @Qualifier("customExchange") CustomExchange customExchange) {
        return BindingBuilder.bind(queue).to(customExchange).with(DELAYED_ROUTING_KEY).noargs();
    }

    /**
     * 备份交换机,和普通一样创建
     */
    @Bean("backupDirect")
    public DirectExchange backupDirect(){
        return ExchangeBuilder.directExchange("backup").build();
    }

    /**
     * 如果未发现路由，直接转发到备份路由，不会回退到生产者
     * @return
     */
    @Bean("backupDirectBind")
    public DirectExchange backupDirectBind(){
        return ExchangeBuilder.directExchange("aaa").withArgument("alternate-exchange","backup").build();
    }
}
