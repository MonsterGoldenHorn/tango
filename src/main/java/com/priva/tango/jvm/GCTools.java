package com.priva.tango.jvm;

/**
 * 调优诊断
 */
public class GCTools {
    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(1000000);
    }
    /**
     * 场景：卡顿
     * 1.--XX:+PrintGCDetails -Xloggc:./log/gc.log
     * PrintGCDetails   gc详细日志
     * PrintGC          gc日志，等于 -verbose:gc
     * PrintGCTimeStamps gc时间戳
     * PrintGCDateStamps gc日期，日志前面每一行有日期
     * PrintHeapAtGC     gc前后打印堆
     *
     * 日志内容：
     * [GC(Allocation Failure)] 内存分配失败导致的gc，类似的格式为gc类型+gc原因
     * PSYoungGen    parallel scavenge的年轻代，DefNew是Serial,ParNew是parallel
     * ParOldGen     parallel old老年代大小
     * xxx secs      gc花费时间
     * Times: user(所有的cpu时间,可能是多线程) sys(等待系统调用时间) real(实际时间)
     *
     */
    /**
     * 优化
     * Tomcat则直接加在JAVA_OPTS变量里
     *
     * 如果是频繁gc基本上通过内存扩容，如果内存比较大，可以考虑换g1
     */
    /**
     * 日志分析工具（-Xloggc:./log/gc.log 针对这个日志文件）
     *
     * gcview 开源的java分析工具
     * gceasy 在线的网站
     */
}
