package com.priva.tango.mq.rabbit;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;

import java.util.HashMap;
import java.util.Map;

/**
 * 1.ttl
 * 2.队列长度最大了，无法添加
 * 3.reject/nack && requeue = false
 */
public class DeadLetter {

    public void deadLetter(Channel channel) throws Exception{
        //声明死信交换机
        AMQP.Exchange.DeclareOk death = channel.exchangeDeclare("death", BuiltinExchangeType.DIRECT);
        //声明正常交换机
        channel.exchangeDeclare("topic1", BuiltinExchangeType.TOPIC);
        //正常交换机设置死信交换机
        Map<String, Object> params = new HashMap<>();
        params.put("x-message-ttl", 1000);
        params.put("x-max-length", 10);//队列中超过10个直接入死信
        params.put("x-dead-letter-exchange", death);//交换机
        params.put("x-dead-letter-routing-key", "deathRouting");//死信队列路由
        //正常队列机声明
        channel.queueDeclare("normal", false, false, false, params);
        //死信队列机声明
        channel.queueDeclare("dead", false, false, false, null);

        //正常队列绑定
        channel.queueBind("normal", "topic1", "r");
        //死信队列绑定
        channel.queueBind("dead", "death", "deathRouting");

        //死信队列消费者处理死信
        channel.basicConsume("dead", false, (consumerTag, message) -> {},(consumerTag, sig) -> {});
        //正常队列拒绝
        channel.basicConsume("normal", false, (consumerTag, message) -> {
            channel.basicReject(message.getEnvelope().getDeliveryTag(), false);
            channel.basicNack(message.getEnvelope().getDeliveryTag(), false,false);
        },(consumerTag, sig) -> {});
    }
}
