package com.priva.tango.jvm;

public class MemoryMain {

    /**
     * 内存参数：建议最大和初始大小相同
     * -Xms startMemory
     * -Xmx maxMemory
     *
     * 运行时打印GC参数：-XX:PrintGCDetails
     * 简化gc日志：-XX:PrintGC
     * 已运行查看：jstat -gc <进程id>
     * 运行参数查看：jinfo -flag <param> <进程id>
     *
     *  可用内存：最大内存-survivor0(或者1)
     *
     *  新生老年代比例配置
     *  -XX:NewRatio=2   //1:2 新生代1，老年代2
     *  长时间存在的对象多，将比例调大
     *  新生代区比例：
     *  -XX:-UseAdaptiveSizePolicy  //关闭自适应内存分配
     *  -XX:SurvivorRation=8
     *  young区：8：1：1
     *  新生代大小：
     *  -Xmn:100    //如果设置大小，比例计算将失效
     *
     *  新生代对象晋升阈值，默认15次存活后晋升老年代
     *  -XX:MaxTenuringThreshold=<N>
     *
     * 堆空间溢出：OutOfMemoryError:Java heap space
     * 永久代溢出：OutOfMemoryError:PermGen space
     * 元空间溢出：OutOfMemoryError:Meta space
     *
     * 方法区
     *   永久代、元空间(不同实现)
     *   1.7之前永久代：
     *     最大  -XX:MaxPermSize 默认82M
     *     初始  -XX:PermSize 默认20.75M
     *   1.8元空间：不占用jvm内存
     *      最大  -XX:MaxMetaspaceSize 默认21
     *      初始  -XX:MetaspaceSize 默认-1无限制
     *
     * StringTable,静态成员变量存放在堆中，字符串常量池因为需要回收不能放方法区
     *
     */

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(100000);
    }
}