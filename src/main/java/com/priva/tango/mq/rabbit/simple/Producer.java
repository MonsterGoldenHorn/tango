package com.priva.tango.mq.rabbit.simple;

import com.priva.tango.mq.rabbit.RabbitConfig;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 简单模式1-1
 */
public class Producer {
    public static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(RabbitConfig.host);
        factory.setUsername(RabbitConfig.name);
        factory.setPassword(RabbitConfig.password);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        /**
         * 实际开发已经提前创建好的
         * 队列名称
         * 持久化
         * 是否多个消费者消费
         * 是否自动删除，如果队列被全部消费，删除队列
         * 其他参数，如优先级priority等
         */
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String msg = "";
        channel.basicPublish(null, QUEUE_NAME, null, msg.getBytes());
    }
}
