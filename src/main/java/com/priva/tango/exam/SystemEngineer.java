package com.priva.tango.exam;

/**
 * 系统工程
 * @description
 * @date 2023/10/24 15:23
 */
public class SystemEngineer {
    /**
     * 系统工程是为了最好地实现系统的目的，对系统的组成要素、组织结构、信息流、控制机构等进行分析研究的科学方法
     * 从系统观念出发，以最优化方法求得系统整体的最优的综合化的组织、管理、技术和方法的总称。
     * >>  方法是用于完成一个既定任务的具体技术和操作
     * >>  系统工程方法论是研究和探索 复杂)系统问题的一般规律和途径
     * >> 方法论是进行研究和探索的一般途径，是对方法如何使用的指导
     *
     * 系统工程方法论包括系统分析和组织管理
     * 系统分析涉及:问题、方法、流程
     * 组织管理涉及: 时间、资源、决策
     *
     * 系统工程是从整体出发合理开发、设计、实施和运用系统科学的工程技术。它根据总体协调的需要，综合应用自然科学和社会科学中有关的思想、理论和方法，
     *  利用计算机作为工具，对系统的结构、元素、信息和反馈等进行分析，以达到最优规划、最优设计、最优管理和最优控制的目的
     */
    /*方法*/
    /**
     * 霍尔的三维结构  适合组织管理大型工程建设项目
     *    结构:系统结构由时间维(工作进程)、逻辑维、知识维(专业科学知识)组成三维结构
     *    内涵:将系统管理过程分为紧密相连的六个阶段和七个步骤，同时考虑为完成这些阶段和步骤的工作所需的各种专业管理知识
     *    逻辑维七个步骤
     *      (1)确问题
     *      (2)建立价值体系或评价体系
     *      (3)系统分析
     *      (4)系统综合
     *      (5)系统方案的优化选择
     *      (6)决策”决策就是管理”，"决策就是决定”，人类的决策管理活动面临着被决策系统的日益庞大和日益复杂。
     *      (7)制定计划有了决策就要付诸实施，实施就要依靠严格的有效的计划。
         时间维七个阶段： 　　
         ①规划阶段。即调研、程序设计阶段，目的在于谋求活动的规划与战略； 　　
         ②拟定方案。提出具体的计划方案。 　　
         ③研制阶段。作出研制方案及生产计划。 　　
         ④生产阶段。生产出系统的零部件及整个系统，并提出安装计划。 　　
         ⑤安装阶段。将系统安装完毕，并完成系统的运行计划。 　　
         ⑥运行阶段。系统按照预期的用途开展服务。 　　
         ⑦更新阶段。即为了提高系统功能，取消旧系统而代之以新系统，或改进原有系统，使之更加有效地工作

     * 切克兰德方法，比较和探寻
     *    出发点不是为了求系统的最优解。有7个步骤：
     *    认识问题（与霍尔三维结构中的逻辑维的第一个步骤相同）、根底定义、建立概念模型、比较和探寻、选择、设计与实施、评估与反馈
     * 并行工程法，制造过程与支持过程并行
     *     三方面:在生产期间，以最快的速度按质完成；各种问题协调解决；使用信息工具
     * 综合集成法，钱学森提出的简单系统和巨系统
     *    四个原则，整体原则、互相联系原则、有序原则、动态原则
     *  WSR方法论包含的要素、物理、事理及人理
     */
    /**
     * 系统工程的生命周期阶段
     * 探索阶段--概念阶段-开发阶段-生产阶段-使用阶段-保障阶段-淘汰阶段
     * 从调研开始后形成概念，接着是设计开发阶段，根据设计来生产如编码，生产完成后就是运行使用阶段，使用过程中的维护更新就是保障阶段，若系统需要更新换代，那么就进入淘汰阶段
     * 2、系统工程的生命周期方法
     * 计划驱动方法：需求  设计 构建 测试 部署           需求很明确的开发方法
     * 渐进迭代式开发：提供连续交付以达到期望的系统       系统按部分交付
     * 精益开发：以客户为中心。是敏捷开发的一种。
     * 敏捷开发：灵活性好，其方法论很多。
     */
    /*MBSE*/
    /**
     * 基于模型的系统工程（MBSE）是一种应用建模方法的正式方式，用于支持系统需求、设计、分析、检验和验证活动，这些活动从概念设计阶段开始，贯穿整个开发过程及后续的生命周期阶段。
     * 三大支撑是  1.建模语言  2.建模方法   3.建模工具
     */
    /*性能指标*/
    /**
     * 计算机的技术性能指标主要是指字长(32/64位)、运行速度（每秒钟可执行的指令数量）、内/外存储器容量和CPU时钟频率
     * 路由器的主要性能指标，包括吞吐量（通常以每秒传输的数据包数量或每秒传输的位数来衡量）、连接数（同时连接数）、路由表大小、转发延迟、可靠性和安全性
     * 交换机性能指标
     *      1.板带宽、二/三层交换吞吐率；
     *      2.VLAN类型和数量；
     *      3.交换机端口数量及类型。
     *      4.支持网络管理的协议和方法。需要交换机提供更加方便和集中式的管理；
     *      5.Qos、802.1q优先级控制、802.1X、802.3X的支持
     * 网络性能指标    带宽、延迟、丢包率、吞吐量（网络在一定时间内传输数据的总量）
     * 操作系统性能指标 （1）系统的可靠性、可维修性和可用性； （2）系统吞吐量（系统吞吐量指的是系统在单位时间内可处理的事务的数量,是用于衡量系统性能的重要指标）； （3）系统响应是时间； （4）系统资源利用率； （5）可维护性； （6）可移植性。
     * 数据库性能指标
     *          衡量数据库管理系统的主要性能指标包括数据库本身和管理系统两部分，有：
     *              数据库的大小、数据库中表的数量、单个表的大小、
     *              表中允许的记录（行）数量、单个记录（行）的大小、表上所允许的索引数量、数据库所允许的索引数量、最大并发事务处理能力、负载均衡能力、最大连接数等等。
     *  优化时对于数据库系统，主要包括CPU/内存使用状况、查询语句性能、进程/线程使用状态、日志文件大小等。
     *  对于应用系统，主要包括应用系统的可用性、响应时间、并发用户数、特定应用资源占用等
     * >>> web服务器性能指标
     *  响应时间；响应时间一般包含网络时间 + 应用程序处理时间。用户发送一个请求到用户接收到服务器返回的响应数据，这就是响应时间。一般在3-5秒钟以内表示性能是良好的。
     *  吞吐量；指单位时间内系统处理用户的请求数，一定程度上说明系统的负载能力。
     *  并发量；一定时间内，最大的同时在线用户数。一般通过网站性能测试工具模拟虚拟用户执行。
     *  资源利用率；网站重点测试指标之一就是资源利用率，分析系统运行时CPU占用率和磁盘占用率等，这也是之后工作中改善网站性能的重要依据。
     */
    /*性能计算*/
    /**
     * 性能指标计算的主要方法有：定义法 、 公式法 、 程序检测法 、 仪器检测法。
     * 定义法主要根据其定义直接获取其理想数据，
     * 公式法则一般适用于根据基本定义所衍生出的复合性能指标的计算，
     * 而程序检测法和仪器检测法则是通过实际的测试来得到其实际值（由于测试的环境和条件不定，其结果也可能相差比较大）
     */
    /*性能设计*/
    /**
     * >> 系统调整
     * >> Amdahl 阿姆达尔解决方案
     * 系统中对某部件采用某种更快的执行方式，所获得的系统性能的改变程度，取决于这种方式被使用的频率，或所占总执行时间的比例
     * >> 负载均衡设计   参考负载均衡
     * >> 响应特性   个人理解，系统使用下班6点峰值最高，调整6点的系统性能
     */
    /*性能评估*/
    /**
     基准测试程序:把应用程序中用得最多、最频繁的那部分核心程序作为评价计算机性能的标准程序。称为基准测试程序(benchmark)
        基准测试程席有整数测试程Dhivsione、浮点测试程 序Unpack、Whetstone基准测试程序、SPEC基准测试程序和TPC基准程序
     Web 服务器的性能评估:
         在 Web 服务器的测试中，能够反映其性能的有最大并发连接数、响应延迟和吞吐量(每秒处理的请求数)等。
         现在常见的 Web 服务器性能评测方法有基准性能测试、压力测试和可靠性测试
     系统监视
         进行系统监视通常有三种方式:
             一是通过系统本身提供的命令，如UNDC/Linux 中的W、ps、last, Windows中的netsta等
             二是通过系统记录文件查阅系统在特定时 间内的运行状态:
             三是集成命令、文件记录和可视化技术，如Windows的Perfinon应用程序。
     */
}
