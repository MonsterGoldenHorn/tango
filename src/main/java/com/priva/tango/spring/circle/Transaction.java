package com.priva.tango.spring.circle;

public class Transaction {
    /**
     * spring.factories
     * EnableAutoConfiguration下的
     * TransactionAutoConfiguration
         * 如果是用@EnableTransactionManagement会在autoConfig的处理中会加载selector
         * 进而处理TransactionManagementConfigurationSelector
     *.....
     * 自动配置下PlatformTransactionManager具体的实现类，默认JdbcTransactionManager,自定义数据源则为DataSourceTransactionManager 将数据源传入到管理器中TransactionTemplate
     * 具体方法调用到事务由BeanPostProcessor 实现类AbstractAutoProxyCreator#postProcessAfterInitialization
     * wrapIfNecessary 创建类事务的代理类
     * 子类AbstractAdvisorAutoProxyCreator 会找所有的切面进行方法
     *
     * TransactionAnnotationParser实现类SpringTransactionAnnotationParser
     *
     * SpringTransactionAnnotationParser#parseTransactionAnnotation
     * 解析注解中的配置生成 RuleBasedTransactionAttribute rbta
     *
     * 将解析出来的放到TransactionInterceptor
     * 在cglib代理了bean后执行method时进行事务以及其他拦截
     *
     * ......
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
