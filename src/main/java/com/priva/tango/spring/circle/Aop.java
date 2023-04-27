package com.priva.tango.spring.circle;

/**
 * @description
 * @date 2023/4/26 16:09
 */
public class Aop {
    /**
     *  AbstractAdvisorAutoProxyCreator 实现了 SmartInstantiationAwareBeanPostProcessor
     *  所有类初始化后都会判断wrapIfNecessary，进行动态代理
     *  findCandidateAdvisors 将代码中实现advisor的类集中初始化
     *  findAdvisorsThatCanApply 通过切面类ClassFilter判断当前类返回当前类
     *  动态代理时将advisor封装到proxy中
     *
     */
}
