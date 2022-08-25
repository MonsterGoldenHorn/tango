package com.priva.tango.mysql;

public class Index {
    /**
     * 1.主键（唯一非空）
     * 2.唯一索引
     * 3.普通索引
     * 4.全文索引  只能在MyISAM数据表中创建
     *  添加 ALTER TABLE article ADD FULLTEXT INDEX fulltext_article(title,content)
     *  fullText ([col])
     *  match([col]) against ('xxxx' IN [MODE] mode )
     *  MySql自带的全文索引只能用于数据库引擎为MYISAM的数据表，如果是其他数据引擎，则全文索引不会生效。
     *  此外，MySql自带的全文索引只能对英文进行全文检索，目前无法对中文进行全文检索。如果需要对包含中文在
     *  内的文本数据进行全文检索，我们需要采用Sphinx（斯芬克斯）/Coreseek技术来处理中文
     *
     *  innodb也能创建全文索引
     *
     * 5.组合索引
     * 只对唯一键建索引
     *
     * 索引和约束不等价，唯一索引=唯一约束，但是enum这种算约束不算索引
     */
    /**
     * 回表
     * 非主键数据，叶子节点存储的是id，查到后再回表查询数据
     *  索引覆盖：解释执行时 Extra：Using index
     *           select id,name from user where name='shenjian';
     *           id为主键一次查询直接拿到所有结果
     */
    /**
     * 分区
     * partition by
     * range   分区不能对函数起作用
     *  partition p1 value less then(2000)
     *  null值会被放入最左分区
     * list(1,2,3,6,101) 分区是离散的，再innodb中事务是完整的，插入分区外的数据报错，myIsam会插入之前的数据
     *  partition p1 values in(2000)
     *  null插入必须声明（a,b,c,null）
     * hash 优点是快，缺点是分布不均匀
     *  partition by hash (year(d));
     *  partitions 4;
     * key分区
     *  使用mysql的函数
     *
     *  hash,key的函数计算null会返回0
     *
     * 以上四种都是整型的
     * columns
     *  支持类型更多，range (date)会被转整型，而ranges columns(data)可以直接比较
     *
     *  子分区：range和list类型上进行hash或key分区
     *  subpartition
     *
     *  分区的数据可以与一个非分区表交换，且不会触发触发器
     */

    /**
     * show index from [tableName]
     * seq_in_index 联合索引中的位置，其他的都是1
     * collation  A ：b+树，null:其他索引
     * cardinality  索引中唯一数据的预估值，优化器会使用这个值选择索引
     */
    /**
     * FIC（fast in creation）
     * 大表对于非主键索引的添加，加上一个s锁（只读），在临时目录下建立索引
     *
     * OSC(online schema change)
     * DDL在线，先建一个相同的表，在临时表上建好索引，删除掉辅助索引，开始复制数据的同时把新表操作记录到另一个临时表。重建索引，换表名
     *
     * Online DDL
     * mysql5.0支持的在线ddl
     *
     * MRR
     * Extra：Using MRR
     * 会将二级索引结果按主键排序
     * select * from c where salary>100 and salary<500 and age=18
     * 优化后会变成age=18 and salary=101 .....age=18 and salary=499
     *
     * ICP
     * 将where条件过滤放在了存储引擎层
     * Extra：Index Condition PushDown
     */
    /**
     * redo
     * 记录修改后的数据，与事务无关，更新时先更新redo再更新数据库
     * redo log日志的大小是固定的，即记录满了以后就从头循环写，满了就写入磁盘
     *
     *
     * undo
     * 保存了事务发生之前的数据的多个版本，可以用于回滚，同时可以提供多版本并发控制下的读（MVCC），也即非锁定读
     *
     */
    /**
     * 索引失效
     * 1.复合索引最左没有
     * 2.like "%231"
     * 3.range搜索结果预估为30%以上
     */
    /**
     * 执行计划
     * type:system,const,eq_ref....
     * https://dev.mysql.com/doc/refman/5.6/en/explain-output.html#explain-join-types
     */
}
