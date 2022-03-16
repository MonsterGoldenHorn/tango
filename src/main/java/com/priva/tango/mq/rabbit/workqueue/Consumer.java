package com.priva.tango.mq.rabbit.workqueue;

import com.priva.tango.mq.rabbit.RabbitConfig;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 *
 */
public class Consumer {
    public static final String QUEUE_NAME = "hello";
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(RabbitConfig.host);
        factory.setUsername(RabbitConfig.name);
        factory.setPassword(RabbitConfig.password);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

//        DeliverCallback deliverCallback = new DeliverCallback() {
//            @Override
//            public void handle(String consumerTag, Delivery message) throws IOException {
//
//            }
//        };
        //只有一个方法可以用lambda
        DeliverCallback deliverCallback = (consumerTag, message)->{
            //consumerTag是会话id，deliver是
            message.getEnvelope().getDeliveryTag();
        };
        /**
         * multiple 是否自动应答
         * true时将本channel中所有的消息应答
         */
        channel.basicAck(0l, false);

    }

}
