package com.priva.tango.mq.rabbit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.RequestMapping;

public class RmqController {

    RabbitTemplate rabbitTemplate;
    public void func(){
        rabbitTemplate.convertAndSend("direct", "123", "");
    }

    @RequestMapping("delayMsg2")
    public void delayMsg2(String msg, Integer delayTime) {
        rabbitTemplate.convertAndSend("DELAYED_EXCHANGE_NAME", "DELAYED_ROUTING_KEY", msg, a ->{
            a.getMessageProperties().setDelay(delayTime);
            return a;
        });
    }
}
