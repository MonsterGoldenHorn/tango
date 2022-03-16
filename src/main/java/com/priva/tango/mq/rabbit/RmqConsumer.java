package com.priva.tango.mq.rabbit;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import java.nio.channels.Channel;

public class RmqConsumer {

    @RabbitListener(queues = "direct")
    public void receive(Message msg, Channel channel){

    }
}
