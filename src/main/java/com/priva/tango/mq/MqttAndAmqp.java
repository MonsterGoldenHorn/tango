package com.priva.tango.mq;

/**
 * @author tango
 * @description
 * @date 2022/8/30 9:43
 */
public class MqttAndAmqp {
    /**
     * rabbitmq两种协议都可以支持
     */

    /**
     * MQTT（Message Queuing Telemetry Transport，消息队列遥测传输协议）由IBM在1999年发布，是一种基于发布/订阅（publish/subscribe）模式的"轻量级"通讯协议
     *
     * 发布/订阅：使用发布/订阅消息模式，提供一对多的消息发布，解除应用程序耦合
     *
     * 消息传输：对负载内容屏蔽的消息传输机制。
     *
     * 服务质量（QoS）：提供三种等级的服务质量，来提高数据传输质量
     *
     * 低功耗：小型传输，开销很小（固定长度的头部是2字节），协议交换最小化，以降低网络流量
     *
     * 通知机制：使用Last Will和Testament特性通知有关各方客户端异常中断的机制
     *
     * 连接顺序，参考http
     * publisher:
     *  connect and ack to broker
     * broker:
     *  connect and ack subscribe
     *
     */
}
