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
     * yourkit，jProfiler 工具可以查看
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
     * 在gc的环境种讨论并发并行
     * 并行  多个垃圾收集线程并行工作，用户线程暂停的
     * 并发  用户线程与gc线程同时，多cpu环境下，有用户和gc线程可能同时或执行（可能交替）
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
     * ***1.吞吐量：运行代码时间（总运行-gc时间）/总运行时间***
     * ***2.暂停时间：stw时间***
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
     * server模式下serial old还可以与parallel scavenge（10移除）parNew（8移除），合作，还可以作为cms后备（concurrent mode failure后会转用serial）
     * --XX:+UseSerialGC
     *
     * 最大特点：只用一个线程收集并且其他工作线程全部暂停，但是高效，针对100mb内存效果好
     * 新生代标记复制，老年代标记整理
     */
    /**
     * parNew
     * serial多线程版本
     * 新生代并行
     * 老年代配合cms,serial old
     * --XX:+UseParNewGC  只影响新生代
     * --XX:ParallelGCThreads   默认开启cpu核心数相同的线程
     *
     * 老年代设置
     * --XX:UseConcMarkSweepGC
     */
    /**
     * 1.8默认
     * parallel（吞吐量=代码运行时间/（代码运行时间+垃圾回收时间））
     * 仍然是复制算法，并行多线程处理
     * 分代和自适应分水岭
     * 自适应调节s0,s1比例，晋升老年代次数
     *  通过控制s0,s1大小，控制回收时间
     *  回收时间越低
     *  单核不开
     * parallel old
     * 标记整理
     * ++XX:+UseParallelGC
     * ++XX:+UseParallelOldGC
     * 开启一个另一个自动激活
     * --XX:ParallelGCThreads
     *
     * 控制吞吐量的设置：
     * --XX:MaxGCPauseMills  最大停顿时间，收集器自动调整堆大小或者其他参数
     *    越小，gc越频繁吞吐量越低，s0s1空间越小
     * --XX:GCTimeRatio      吞吐量的倒数，gc时间占运行时间比例，和上一个参数冲突，默认99，认为gc不超过1%
     *
     * --XX:+UseAdaptiveSizePolicy 自适应默认开启，重要特点
     *
     * 无法与cms组合的原因：
     * 架构：分代式GC框架，Serial/Serial Old/ParNew/CMS都在这个框架内，它们有公用的框架代码部分
     * ps使用的自适应，不是传统分带，独立框架
     */
    /**
     * cms（最小stw）
     * 同步标记清除（之前的老年代都是标记整理，所以若干次后需要一次整理）
     * UseConMarkSweepGC
     * 用户线程和gc线程同时交替进行，对cpu敏感，如果是cpu密集需要考虑其他
     * 追求低停顿
     * 初始标记（stw）->并发标记->重新标记(stw)->并发清理
     * 初始标记，只标记GCRoots直接管理的对象，stw
     * 并发标记，上一步继续往下遍历
     * 重新标记，修正并发过程中变动的记录,stw
     * 并发清除
     *
     *  缺点：
     * 1）cpu低于四个效率低
     * 2）有浮动垃圾，同时因为并发所以老年代不能用满，可能收集失败，失败启动serial old，可以设置内存使用比率（增长慢的可以设置大些）
     *  启动阈值低gc频繁，阈值高容易收集失败
     * 3）标记清除产生碎片，
     *      ++XX:UseCMSCompactAtFullCollection  默认开启
     *      ++XX:CMSFullGCsBeforeCompaction 可以设置一个值判定多少次之后进行一次压缩，默认0
     */
    /**
     * g1（高吞吐，尽量低stw，保证系统高概率满足低gc时间，尽量避免full gc）
     * 9以后默认，1.7可用  ++XX:UseG1GC
     * 特色：
     * 1）使用不同的region表示eden,s0,s1,老年代等,回收仍然分代
     * 2）复制算法，一个region有用的被复制到另外一处，清空当前region
     * 3）收集每个region回收价值的经验值做一个列表，根据每次回收时间选择价值最大的region回收（通过价值算法算出优先列表，也就是垃圾优先garbage first）
     * 4）字符串的去重（可以禁用）
     * 整体看是标记整理，region间是标记复制
     *  选型：小内存上cms性能高，6-8G以后g1好
     *  随着应用程序扩展和硬件提升，g1针对的：
     *      1）几十个GB或者更大内存，实时堆占用率超过50%
     *      2）分配效率随时间显著增加
     *      3）大量碎片（针对cms碎片问题）
     *      4）可控的暂停时间
     *   为什么要用g1，因为cms可能会失败，针对大内存，失败后启动单线程收集，时间会很长，导致服务不可用
     *
     * 堆自动扩容：
     * -XX:InitialHeapSize
     *  -XX:MinHeapFreeRatio
     *  -XX:MaxHeapFreeRatio
     *  在暂停时间进行扩容或者向系统释放
     *  
     *  -XX:NewSize  -XX:MaxNewSize 开启后将关闭时间控制，尽量别开
     *
     *  region 默认堆1/2000大小，-XX:G1HeapRegionSize = n  大小为2的n次幂，n范围1-32中的2次幂mb
     *  gc时间-XX:MaxGCPauseMillis 默认200 单位ms
     *  线程数：-XX:ParallelGCThread 最大8，不光是g1用到的参数，设置总共的参与gc的线程数
     *  并发线程数：-XX:ConcGCThreads 一般为线程数1/4
     *  堆占用阈值：-XX:InitiatingHeapOccupancyPercent  并发标记触发的堆空间
     *
     *  region划分
     *  eden,survivor,old,humongous
     *  大于1.5region放到h：大对象往往放到老年代，老年代回收频率低
     *
     *  回收过程(最多的情况为young-only phase和space-reclamation phase )：
     *  young gc -> young gc + concurrent mark -> mixed gc -> full gc
     *
     *  ***young-only phase:
     *  eden标记复制到survivor或old region，可能出现yong only gc，可能会
     *      1）扫描根+rs (roots对象一般最后都在老年代)
     *      2）更新rs，处理dirty card queue,也就是说rs中的引用关系不是最新的
     *          dirty card 使用写屏障，当 obj.a = b 时，引用关系会更新引用自己对应对象的card中
     *      3）处理rs，处理eden中被old引用的
     *      4）复制，复制到新的eden或者age+1,或者晋升老年代
     *      5）处理引用，比如弱引用回收
     *    ***** Young gc可能会触发concurrent mark，但不会回收，在完成yong gc后，判断是否需要准备下一次空间回收
 *        concurrent mark: heap空间默认达到45%时启动并发收集
     *      1）初始标记，roots可达对象标灰，stw且触发一次young gc
     *      2）根区域扫描：针对survivor区的对象进行标记，young gc之前
     *      3）concurrent mark 并发标记：确定old所有可达对象，没完成前可能有正常的young gc,标记种会出现两次stw，标记和清除
     *          标记清除之间，回收器会计算价值（必须在清除之前，下一次是怎么回收需要这里进行判断）
     *      4）remark 再次、最终标记,stab算法，并发期间对象变更对回收的影响
     *      5）清理：stw，计算价值排序
     *      6）回收
     *
     *  space-reclamation phase：
     *      混合回收有额外三个部分作为回收点：
     *      1）-XX:G1MixedGCCountTarget 默认八次，每次mixed gc 有1/8被 标记 的老年代
     *      2）预计回收时间有富余会回收额外的标记老年代，时间不超过剩余时间80%
     *      3）剩余其他的垃圾(Humongous猜的)
     *
     *      mixed gc: 回收时可能只回收一部分老年代,年轻代全部回收
     *      1）百分百为垃圾的region必然回收，其他的分八次回收
     *      2）可以调整老年代回收阈值，-XX:G1MixedGCLiveThresholdPercent 默认85%（版本有的65%），存活的对象占比超过此值的才被 标记 为需要回收
     *      3）八次回收可能中断，-XX:G1HeapWasterPercent 默认5%,剩余空间的垃圾占比小于此值就暂停
     *
     *  如果通过价值计算，回收老年代的效果不高（gc后空间不会增加多少）将会停止此阶段
     *
     * -XX:G1PeriodicGCInterval 定时垃圾回收，如果堆内存没有达到触发条件依然可以进行最小时间段内回收
     *  1）上一次和下一次都是young gc阶段，会触发并发标记， -XX:-G1PeriodicGCInvokesConcurrent 设置后触发full gc
     *  2）空间回收阶段，会继续进行空间回收
     *
     * remember set
     * g1跨region，其他收集器跨代引用使用
     * 通过写屏障如果发现跨region，通过cardTable将引用记录到对象所在region的rs
     * 一个region被逻辑分页成为card，每个region对应一个rs，存放card的bitmap，标记出来的就是dirty card
     * 需要回收当前region时：
     *  扫描roots后，将对应区域的rs扫描，就可以知道当前region是否有被引用的对象，避免扫描全部
     *
     * minor gc：初始分配的eden满了触发，回收eden,survivor
     *
     */
    /**
     * zgc
     * 低停顿时间，吞吐量不是优先
     * 可控制1ms不会随堆增加而增加，支持tb级别内存
     * 1）堆大小
     * 2）回收线程数：zgc自带试探算法，太大导致gc占用cpu太多，太少导致垃圾产生过快
     * 3）未使用的内存会返回给系统，可能导致内存低于xms,如果xms=xmx，隐式禁用这个特性
     *
     * 内存分页,不再分代，通过大中小页面回收管理（理解为region分成三种大小）
     * 内存多重映射，通过视图切换进行垃圾回收
     * zgc之前gc信息记录在mark word，之后记录在染色指针（对象引用地址）
     *      gc信息存放历史：
     *      1）分代式的存放在mark word
     *      2)g1，shenandoah 单独使用bitmap存放
     *      3）zgc存染色指针
     *      目前Linux下64位指针的高18位不能用来寻址，但剩余的46位指针所能支持的64TB内存仍然鞥呢够充分满足大型服务器的需要。鉴于此，
     *          ZGC将其高4位提取出来存储四个标志信息。
     *      通过这些标志虚拟机就可以直接从指针中看到器引用对象的三色标记状态（Marked0、Marked1）、是否进入了重分配集（是否被移动过
     *          ——Remapped）、是否只能通过finalize()方法才能被访问到(Finalizable)。由于这些标志位进一步压缩了原本只有46位的地址
     *          空寂，导致ZGC能够管理的内存不可以超过4TB
     * 染色指针优势：
     *   1） 复制提升为转移：
     * 转移使用转发表，旧地址可以找到新地址，清理阶段不用stw
     *   2）减少内存屏障，直接对指针进行标记
     *
     *   回收阶段：
     *   1）并发标记（Concurrent Mark）
     *   2）并发预备重分配（ Concurrent Prepare for Relocate）
     *      统计需要回收的region
     *   3）并发重分配（Concurrent Relocate）
     *      复制对象，并且维护转发表
     *   4）并发重映射（Concurrent Remap）
     *      在下一次循环时，将真正的对象地址更新
     *
     */
}
