package com.priva.tango.jvm;

public class JIT {
    /**
     * C1 客户端的Jit，优化更快，低编译耗时
     *  方法内联
     *  去虚拟化：唯一的实现类进行内联
     *  冗余消除：运行时折叠不会执行的代码
     *
     * C2 （c++写的）服务端的jit,优化更深，低执行速度,基于逃逸分析
     *  标量替换：标量代替聚合对象的属性
     *  栈上分配：未逃逸的对象分配到栈
     *  同步消除：清除sync（未逃逸的对象）
     *
     *  JDK9 jaotc
     *  AOT（Ahead of time Compiler）
     *  程序执行之前转换机器码生成so文件（在class后），jit要运行过程中替换
     *  破坏了一次编译到处运行，还在优化中
     *
     *  JDK10以后：Graal  (c2一级)
     *  需要特殊开启
     */
    /**
     * -Xint:完全采用解释器 interpreted mode
     * -Xcomp:完全采用即时编译器，编译出问题才有解释器介入 compiled mode
     * -Xmixed:共同，默认mixed
     *
     * //java -Xint
     */
    /**
     * 方法调用计数器
     * -XX:CompileThreshold  默认客户端1500，服务端10000
     *
     * 方法区调用->不是热点->方法计数器+1->没达到阈值->解释执行
     *      丨                   |
     *      是热点->热点执行     达到阈值->解释热点加入cache->热点执行
     *
     *  半衰期，如果一定时间内未达到热点，半衰
     *  //开启半衰期,关闭后绝大部分代码都会成为本地代码
     *  --XX:UseCounterDecay
     *  //半衰周期
     *  --XX:CounterHalfLifeTime 单位为秒
     *
     *  回边型计数器：判断循环体代码
     */
}
