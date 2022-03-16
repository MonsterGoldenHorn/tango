package com.priva.tango.mq.rabbit.confirm;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;

import java.io.IOException;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * 发布确认模式
 * 1.队列持久化
 * 2.消息持久化
 * 3.开启发布确认
 * 模式：
 * 1.单个
 * 2.批量
 * 3.异步批量
 */
public class Producer {

    public static void main(String[] args) {

    }

    public static void confirmSingle(Channel channel) throws Exception {
        channel.confirmSelect();
        channel.basicPublish("", "queueName", null, "".getBytes());
        //确认结果
        boolean b = channel.waitForConfirms();
    }

    public static void confirmBatch(Channel channel) throws Exception {
        channel.confirmSelect();
        int total = 1000;
        for (int i = 0; i < total; i++) {
//            channel.messageCount("");
            channel.basicPublish("", "queueName", null, "".getBytes());
            if(i%100 == 0){
                //100条确认一次
                channel.waitForConfirms();
            }
        }
        //确认结果
        boolean b = channel.waitForConfirms();
    }

    //异步确认,最快
    public static void confirmAsync(Channel channel) throws Exception {
        ConcurrentSkipListMap queue = new ConcurrentSkipListMap();

        channel.confirmSelect();
        //ConfirmListener
        channel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {

            }

            @Override
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {

            }
        });
        //ConfirmCallback, ConfirmCallback
        channel.addConfirmListener((deliveryTag, multiple) -> {},(deliveryTag, multiple) -> {});

        int total = 1000;
        for (int i = 0; i < total; i++) {
            queue.put(channel.getNextPublishSeqNo(), "");
            channel.basicPublish("", "queueName", null, "".getBytes());
        }
    }

}
