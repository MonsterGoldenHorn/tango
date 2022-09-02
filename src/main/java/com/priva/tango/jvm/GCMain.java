package com.priva.tango.jvm;

public class GCMain {
    /**
     *  system.gc() runtime.getRuntime.gc()，显示触发full gc,提示系统执行，系统不一定执行，如果下一步要执行耗内存的，可以提示系统一次
     * 新生代频繁gc，老年代很少，几乎不在永久区、元空间收集
     *
     * YongGC/MinorGC
     *  新生代的eden区满了触发，同时会回收survivor from
     *  eden存活对象会复制到 survivor to ,survivor from存活对象 age+1 一起放入survivor to，如果发现存放不下或者age阈值超过了直接放老年代
     *
     *  MajorGC(老年代)/FullGC（包括方法区）
     *  老年代空间不足
     */
    /**
     * 内存溢出
     * 如果FullGC后对象不能存放，oom 或者超大对象进入不了老年代直接oom
     *
     * 内存泄露
     * 对象不被使用，但是gc无法回收，可能导致内存溢出
     * Io连接
     */
    /**
     * 可作为gc root的对象，在堆中但是引用在堆外的对象
     * 1.栈中引用的对象，参数局部变量等
     * 2.jni栈中引用的
     * 3.方法区静态变量引用
     * 4.方法区常量引用
     * 5.sync锁住
     * 6.虚拟机内部引用
     *
     * yourkit 工具可以查看
     */
    /**
     * 标记清除
     * 1.通过gcRoots标记可达对象，在对象header存放可达标记
     * 2.清除堆中未标记的（假删除，内存还在但是下次可以覆盖）
     * 缺点：效率不高（遍历），stw时间长，碎片化需要空闲列表管理内存
     *
     * 复制算法
     * 1.高效，遍历一遍gcRoots复制可达的对象
     * 2.保证空间连续性
     * 一般在新生代用，新生代存活的少，复制的也少
     * 缺点：耗内存，对象地址变了，堆栈关联的数据要更新
     *
     * 标记压缩
     * 不需要复制算法的两倍空间，等同于标记清除后再碎片整理
     *
     */
    /**
     * 并行  同一时间
     * 并发  同一时间段
     */
    /**
     * 安全点
     * 选择一些执行时间长的代码
     * 方法调用，循环跳转，异常跳转
     *
     * 中断方式
     * 抢先试中断，中断所有，如果不在安全点，将不在的运行到中断点
     *
     * 主动式中断，设置中断标志，线程运行到安全点主动轮询（因为睡眠中的线程无法中断，需要用安全区域）
     *
     * 安全区域：在一段代码区域中，对象引用关系不会发生变化的，在区域中任何地方都可以GC
     * 如果线程执行到安全区域，标志已经进入安全区，这个时间内发生gc时会忽略这个线程，当执行完成后，如果当前gc没有完成，需要等待完成后再继续执行
     *
     */
    /**
     * 引用类型
     * 强
     *  常见的new方式创建
     * 软
     *  内存不够或者溢出前进行第二次回收，再报oom，有用但是不必要，比如缓存类型
     * 弱
     *  使用list软引用做缓存
     *  下一次gc必然回收
     * 虚
     *  无法通过虚引用获得对象，只能收到gc的通知
     */
    private void phantomRef(){
        //PhantomReference(T referent, ReferenceQueue<? super T> q)
        //虚引用队列可以在回收时方入队列进行通知操作
        //终结器引用也是一样的构造方式
    }
    /**
     * 垃圾回收器
     *
     * 性能指标：
     * 1.吞吐量：运行代码时间（总运行-gc时间）/总运行时间
     * 2.暂停时间：stw时间
     * 3.收集频率：gc频率
     * 4.内存占用：堆区所占大小
     * 5.速率：生成对象到被回收时间
     *
     * 吞吐，暂停时间，内存占用 三选二，主要关注前二个
     *
     * 1.6之后parallel/parallel old默认
     * 1.7u4 G1可用
     * 9 默认g1
     *
     * 串行：serial serial old
     * 并行(多核)：parNew,parallel scavenge,parallel old
     * 并发（单核）：g1,cms
     *
     */
    /**
     * 串行垃圾回收(开销最小)
     * serial,serial old
     * 1.3之前唯一选择
     * client模式下默认的收集器
     * serial :mark-copy
     * serial old: mark-compact
     * 都是单线程的
     * server模式下serial old还可以与parallel scavenge（10移除）parNew（8移除），合作，还可以作为cms后备
     * --XX:+UseSerialGC
     */
    /**
     * parNew
     * serial多线程版本
     * 新生代并行
     * 老年代配合cms,serial old
     * --XX:+UseParNewGC  只影响新生代
     * --XX:ParallelGCThreads   默认开启cpu核心数的线程
     *
     * 老年代设置
     * --XX:UseConcMarkSweepGC
     */
    /**
     * 1.8默认
     * parallel（吞吐量）
     * 仍然是复制算法，并行多线程处理
     * 自适应调节s0,s1比例，晋升老年代次数
     *
     * parallel old
     * 标记整理
     * ++XX:+UseParallelGC
     * ++XX:+UseParallelOldGC
     * 开启一个另一个自动激活
     * --XX:ParallelGCThreads
     * --XX:MaxGCPauseMills  最大停顿时间，收集器自动调整堆大小或者其他参数
     * --XX:GCTimeRatio      gc时间占运行时间比例，和上一个参数冲突，默认99，认为gc不超过1%
     * --XX:+UseAdaptiveSizePolicy 自适应默认开启
     */
    /**
     * cms（最小stw）
     * UseConMarkSweepGC
     * 用户线程和gc线程同时交替进行，对cpu敏感，如果是cpu密集需要考虑其他
     * 追求低停顿
     * 初始标记（stw）->并发标记->重新标记(stw)->并发清理
     * 初始标记，只标记GCRoots直接管理的对象
     * 并发标记，上一步继续往下遍历
     * 重新标记，修正并发过程中变动的记录
     *
     * 因为并发所以老年代不能用满，可能收集失败，失败启动serial old，可以设置内存使用比率（增长慢的可以设置大些）
     * 标记清除产生碎片，
     *      ++XX:UseCMSCompactAtFullCollection  默认开启
     *      ++XX:CMSFullGCsBeforeCompaction 可以设置一个值判定多少次之后进行一次压缩，默认0
     */
    /**
     * g1（高吞吐，尽量低stw）
     * 9以后默认，1.7可用  ++XX:UseG1GC
     * 使用不同的region表示eden,s0,s1,老年代等,回收仍然分代
     * 复制算法，一个region有用的被复制到另外一处，清空当前region
     * 收集每个region回收价值的经验值做一个列表，根据每次回收时间选择价值最大的region回收
     *
     * 小内存上cms性能高，6-8G以后g1好
     */
}
