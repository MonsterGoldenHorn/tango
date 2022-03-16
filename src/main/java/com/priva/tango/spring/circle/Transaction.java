package com.priva.tango.spring.circle;

public class Transaction {
    /**
     * spring.factories
     * EnableAutoConfiguration下的
     * TransactionAutoConfiguration
     * 在autoConfig的处理中会加载selector
     * 进而处理TransactionManagementConfigurationSelector
     *.....
     * 在getTransaction方法中  ->PlatformTransactionManager#getTransaction(org.springframework.transaction.TransactionDefinition)
     * 判断当前是否有事务
     */
    /**
     *                  已有                  没有
     * required         join                  create
     * supports         join                  非事务执行
     * mandatory        join                  throw new IllegalTransactionStateException
     *  以上支持当前事务
     * required_new     挂起，创建新的         创建新的
     * not_supports     挂起                  非事务
     * never            报错
     * nested           挂起，创建新的     按照required执行
     *  内层失败不影响外层 ，外层失败内层回滚
     *
     */
}
