package com.priva.tango.exam;

/**
 * 软件架构评估
 * @author tango
 * @description
 * @date 2023/10/18 16:29
 */
public class SoftwareQuality {
    /**
     * >> 质量属性
     1、性能：指 ~系统的响应能力~，如 响应时间，吞吐率，准确率，有效性，资源利用率，可用性和可靠性。
     设计策略：优先级队列（资源仲裁）、增加计算资源、减少计算开销、引入并发机制、采用  ~资源调度~。
     图像音频采样~分辨率和音质
     2、可靠性：在意外或错误使用情况下维持软件系统功能特征。  包含 容错性，健壮性
     MTTF：平均无故障时间。
     MTBF：平均故障间隔时间。
     MTTR：平均故障修复时间。
     设计策略：心跳、PING/Echo、冗余、选举。
     3、可用性：系统正常运行的时刻。如 故障间隔时间。
     高可用必须包含：错误监测，错误恢复，错误预防
     设计策略：心跳、PING/Echo 冗余、选举、备件  进程监视
     4、安全性：指 阻止非授权用户使用企业或拒绝服务的能力。 如 保密性、完整性、不可抵赖性、可控性。
     抵御攻击，检测攻击，从攻击恢复
     设计策略：入侵检测、用户认证、用户授权、~追踪审计~。   冗余机制同时属于安全性，可用性
     5、可修改性：以较高性能价格比对系统进行更改。
     局部化修改，防止连锁反应，推迟绑定时间
     设计策略：接口-实现分离、抽象、~信息隐藏~（getter）。
     6、功能性：系统所能完成所期望的工作和能力。
     7、可变性：指 体系结构经扩充或变更而成为新系统结构的能力。（可扩展）
     8、互操作性：作为系统 组成部分的软件不是独立存在的，经常与其他系统或自身环境相互作用。
     9.易用性：界面友好，培训用户时间短
     10.可测试性：通过测试揭示缺陷的容易程度

     常见属性   可用性、可修改性、性能、安全性、可测试性、易用性
     */
    /**
     * >>>>敏感点 风险点 权衡点
     1. 架构风险：系统架构风险是指架构设计中潜在的、存在问题的架构决策所带来的的隐患
     2. 风险点：可能引起风险的因素，称为风险点。如果某个做法有隐患，有可能导致一些~问题~，则称为风险点
     3. 非风险点：如果某件事情是可行的， xxx是可以接受（实现）的，则为非风险点
     4. 敏感点：敏感点是指为了实现某种特定的质量属性，一个或多个构件所具有的特性  和风险的区别在于是否是构建
     5. 权衡点：权衡点是影响多个质量属性的特性，是多个质量属性的敏感点，影响的一定要是质量属性
     */
    /**
     * >>>架构评估方法
     1、问卷调查、检查表       通过问卷粗略了解   通用方式，特定领域
     2、度量     通过特定领域精确了解      通用或特定领域   较客官
     3、基于场景    特定系统 较主观
        比如通过jmeter测试并发

     *  >>基于场景的评估方法
     *  软件架构分析法 SAAM 非功能质量属性的架构分析方法
     *       最初用于分析架构的可修改性，后扩展到其它质量属性。通过问题描述、需求说明、架构描述，进行分析后，主打一个总体或者单体评估
     *  架构权衡分析法ATAM
     *       四个基本阶段
     *             演示，调查和分析，测试、报告
     *       SAAM基础上发展，主要针对性能、实用性、安全性和可修改性，在系统开发之前，对这些质量属性进行评价和折中
     *    >>四个主要活动领域
     *      第一阶段 场景和需求收集       收集场景,收集需求、约束、环境,描述架构视度
     *      第二阶段 架构视图和场景实现      描述架构视图,实现场景
     *      第三阶段 属性模型的构造分析         特定属性分析（优秀的单一理论）
     *      第四阶段 折中                 标志折中,标志敏感度
     *  成本效益分析法 CBAM
     *      基于ATAM，对收益和成本进行建模考虑后选择
     *  其他方法，SAEM,SAABNet,SACMM
     */
}
