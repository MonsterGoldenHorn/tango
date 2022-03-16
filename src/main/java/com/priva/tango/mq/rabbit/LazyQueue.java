package com.priva.tango.mq.rabbit;

public class LazyQueue {
    /**
     * 在持久化到磁盘时叫惰性队列
     * x-queue-mode lazy/default
     * lazy queue 消息不持久化 ， 但是这种模式还是会把消息放到硬盘里，RAM的使用率会一直很稳定，但是重启后一样会丢失消息
     *
     * lazy queue 消息持久化，这种方式无疑是最佳搭配，消息放到硬盘并且不会因为服务器重启而丢失，面对高并发也是从容不已
     */
}
