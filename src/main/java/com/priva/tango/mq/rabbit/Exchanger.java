package com.priva.tango.mq.rabbit;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;

/**
 * direct
 * topic
 * headers
 * fanout
 */
public class Exchanger {
    //广播
    public void fanout(Channel channel) throws Exception{
        //声明交换机
        channel.exchangeDeclare("fanout1", BuiltinExchangeType.FANOUT);
        //声明队列
        channel.queueDeclare().getQueue();
        //绑定队列,routingKey随便写都能收到
        channel.queueBind("q1", "fanout1", "");
        channel.queueBind("q2", "fanout1", "");
    }

    //直接
    public void direct(Channel channel) throws Exception{
        //声明交换机
        channel.exchangeDeclare("direct1", BuiltinExchangeType.DIRECT);
        //声明队列
        channel.queueDeclare().getQueue();
        //绑定队列,如果相同routingKey就和FANOUT一样
        channel.queueBind("q1", "direct1", "");
        channel.queueBind("q2", "direct1", "");
    }

    //主题
    public void topic(Channel channel) throws Exception{
        //声明交换机
        channel.exchangeDeclare("topic1", BuiltinExchangeType.TOPIC);
        //声明队列
        channel.queueDeclare().getQueue();
        //绑定队列,*表示一个单词，#表示若干个，
        channel.queueBind("q1", "topic1", "*.right");
        channel.queueBind("q2", "topic1", "#.right");
        //如果绑定#，接收所有，和fanout一样
        channel.queueBind("q2", "topic1", "#");
        //如果绑定没有通配符，和direct一样
        channel.queueBind("q2", "topic1", "abc");
        //会被q1q2接收
        channel.basicPublish("topic1", "abc.right", null, "".getBytes());
    }
}
