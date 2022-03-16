package com.priva.tango.mysql;

public class Lock {
    /**
     * 1.加锁机制：悲观，乐观
     * 2.兼容性：共享，排他
     * 3.粒度：表锁，行锁，页锁
     * 4.锁模式：
 *          记录锁，行锁，锁的是
     *      gap锁，一个范围但不包含自己
     *      next-key锁，一个范围包含自己，rr级别下加的锁，如果查询条件锁的是唯一索引，降级为行锁
     *      意向锁，插入意向锁
     */
    /**
     * 一致性非锁定读
     *  如果读取数据时，表上有IX锁，直接读快照
     *  mvcc在read committed时始终读的最新的版本
     *  而在read repeatable时读事务开始的版本
     * 一致性锁定读
     *  读一个session的值，需要在锁中读
     */
    /**
     * 脏读，read uncommitted时读到未提交的
     * 不可重复读，幻读，读到已提交的，数据一条变两条等，read committed ,采用next-key lock解决
     * 更新丢失，aba
     */
}
