package com.priva.tango.mysql;

public class MysqlFile {
    /**
     * 配置文件 cnf或者ini
     * linux cnf文件，多个路径存在最后一个生效
     * mysql--help|grep my.cnf
     * windows
     * mysql--help
     */
    /**
     * sock文件
     * pid文件
     */
    /**
     * 日志文件
     * 错误日志  show variables like 'log_error'
     *      mysql启动和运行异常时的日志
     * 慢查询日志
     *   查之前需要先开启 set global slow_query_log=‘ON’; 开启后才有后续日志提供搜索
     *      show variables like 'long_query_time'  默认10秒为慢查询
     *      show variables like 'log_slow_queries'  旧版本的开关
     *      select * from mysql.slow_log  查询慢日志
     *      show variables like 'long_query_not_using_indexes' 允许未使用索引的查询入日志
     *
     * binary log
     *  1)恢复数据库
     *  2）主从复制
     *  3）审计判断是否有攻击
     *
     */
    /**
     * mysql表文件
     * 元表信息
     */
    /**
     * 存储引擎文件
     *
     * 真正存放数据索引的文件
     */
}
