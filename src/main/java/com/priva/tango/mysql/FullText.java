package com.priva.tango.mysql;

/**
 * @description
 * @date 2022/11/7 16:53
 */
public class FullText {
    /**
     *  全文索引
     *  添加
     *  ALTER TABLE article ADD FULLTEXT INDEX fulltext_article(title,content)
     *  使用
     *  select * from table where match([col]) against ('xxxx' IN [MODE] mode )
     *  上古版本
     *  MySql自带的全文索引只能用于数据库引擎为MYISAM的数据表，如果是其他数据引擎，则全文索引不会生效。
     *  此外，MySql自带的全文索引只能对英文进行全文检索，目前无法对中文进行全文检索。如果需要对包含中文在
     *  内的文本数据进行全文检索，我们需要采用Sphinx（斯芬克斯）/Coreseek技术来处理中文
     *
     *  5.6 开始innodb也能创建全文索引
     *  5.7 ngram支持cjk 中韩日
     */

    /**
     * 系统参数表 SHOW VARIABLES LIKE '%ft%';
     * ft_stopword_file
     * 默认(built-in)，为系统编译时自带
     * 可以修改为文件绝对路径
     *
     * innodb_ft_aux_table      索引统计信息表，设置后可以观察该表的索引分词情况
     *
     * innodb_ft_server_stopword_table  停用词表，设置后分词时不存索引
     *
     * ngram可以实现中文英文混合的分词，以最大和最小词构造滑动窗口
     */
}
