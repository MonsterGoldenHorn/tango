package com.priva.tango.exam;

/**
 * @description
 * @date 2023/10/11 10:16
 */
public class Qianrushi {
    /**
     * 嵌入式操作系统特点:
     * (1) 微型化、(2)代码质量高、(3) 专业化、(4)实时性强(5) 可裁剪，可配置。
     * 针对不同的硬件平台，操作系统通常建立在一个硬件抽象层 (HAL)上，该层位于底层硬件和内核之间，为内核提供各种方便移植的宏定义接口，在不同的平台间移植时，只需要修改宏定义即可。
     * 与硬件相关，与操作系统相关。
     *  板级支撑包 BSP 介于主板硬件和操作系统之间的系统软件之一,主要目的是为了支持操作系统,使之能够更好的运行于硬件主板
     *
     *  >> 低功耗   软件设计层面的功耗控制主要可以从以下方面展开：
     *  ①　软硬件协同设计，即软件的设计要与硬件的匹配，考虑硬件因素。
     *  ②　编译优化，采用低功耗优化的编译技术。
     *  ③　减少系统的持续运行时间，可从算法角度进行优化。
     *  ④　用“中断”代替“查询”
     *  ⑤　进行电源的有效管理
     */
    /**
     * 实时系统存在多种调度算法。
     * 优先级调度算法:分配一个相对固定的优先顺序，然后调度程序根据优先级的高低排序,
     * 按时间顺序进行高优先级任务优先调度。 (非抢占式)
     * 抢占式优先级调度算法:是在优先级调度算法基础上，允许高优先级任务抢占低优先级
     * 任务而运行。
     * 时间轮转调度算法:调度程序会依次调度每个任务运行一个小的时间片，然后再调度另
     * 个任务。每个任务运行完一个时间片，无论是否结束都会释放CPU让下一个任务运行。
     * (纯粹的时间轮转调度无法满足实时系统的要求，取而代之的是基于优先级的抢占式时间
     * 轮转调度)
     * 最晚截止期调度算法:指调度程序按每个任务的最接近其截止期末端的时间进行调度。
     * 最早截止期调度算法:指调度程序按每个任务的截止期时间，选择最早到截止期头端时
     * 间的任务进行调度。
     * 在RTOS中，大多数的RTOS调度算法都是抢占式的。
     *
     * 常见的嵌入式RTOS(实时操作系统,Real-Time OperatingSystem)
     * VxWorks、RT-Linux、QNX、pSOS.
     * VxWorks
     * 操作系统与应用程序处于同一存储空间
     * 支持多任务 (线程) 操作
     * 实时系统
     * 任务间无隔离保护
     * 支持标准API
     * RT-Linux
     * 操作系统与应用程序处于不同存储空间
     * 支持多进程、多线程操作
     * 实时系统
     * 支持进程间隔离保护
     * 支持标准API
     *
     *
     */
}
