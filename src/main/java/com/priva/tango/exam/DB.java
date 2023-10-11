package com.priva.tango.exam;

/**
 * @description
 * @date 2023/10/11 16:08
 */
public class DB {
    /**
     * 数据库关系的3种类型
     * 1 基本关系 (通常又称为基本表或基表): 实际存在的表，实际存储数
     * 据的逻辑表示。
     * 2 查询表:查询结果对应的表。
     * 3 视图表:由基表或其他视图表导出的表，本身不独立存储，数据库只
     * 存放它的定义，常称为虚表。
     *
     * 数据库模式
     *
     * 数据库视图:它一个虚拟表(逻辑上的表)，其内容由查询定义 (仅保存SQL查询语句)
     * 同真实的表一样，视图包含一系列带有名称的列和行数据。但是，视图并没有真正存储这
     * 些数据，而是通过查询原始表动态生成所需要的数据。
     * 视图的优点:
     * 1、视图能简化用户操作
     * 2、视图使用户能以多种角度看待同一数据
     * 3、视图对重构数据库提供了一定程度的逻辑独立性
     * 4、视图可以对机密数据提供安全保护
     * 物化视图:它不是传统意义上虚拟视图，是实体化视图，其本身会存储数据。同时当原始
     * 表中的数据更新时，物化视图也会更新。
     *
     *  分布式数据库
     *
     *  (1) 全局外模式。全局外模式是全局应用的用户视图，是全局概念模
     * 式的子集，该层直接与用户 (或应用程序) 交互
     * (2) 全局概念模式。全局概念模式定义分布式数据库中数据的整体【逻辑结构】，数据就如同根本没有分布一样，可用传统的集中式数据库中所
     * 采用的方法进行定义
     * (3) 分片模式。将一个关系模式分解成为几个数据片
     * (4)分布模式。分布式数据库的本质特性就是数据分布在不同的物理
     * 位置。分布模式的主要职责是定义数据片段(即分片模式的处理结果)
     * 的存放节点
     * (5) 局部概念模式。局部概念模式是局部数据库的概念模式
     * (6)局部内模式。局部内模式是局部数据库的内模式。
     *
     * 1数据独立性。除了数据的逻辑独立性与物理独立性外，还有数据
     *      分布独立性(分布透明性)。
     * 2集中与自治共享结合的控制结构。各局部的DBMS可以独立地管
     *  理局部数据库，具有自治的功能。同时，系统又设有集中控制机
     *  制，协调各局部DBMS的工作，执行全局应用。
     * 3适当增加数据冗余度。在不同的场地存储同一数据的多个副本，
     *  可以提高系统的可靠性和可用性，同时也能提高系统性能。
     *  (提高系统的 可用性 ，即当系统中某个节点发生故障时，因为数据
     *  有其他副本在非故障场地上，对其他所有场地来说，数据仍然是可
     *      用的，从而保证数据的完备性。
     * 4全局的一致性、可串行性和可恢复性。
     *
     *
     * 分布透明性
     *  分片透明性  水平分片  垂直分片  混合分片
     *  位置透明性
     *  局部数据模型透明性
     *
     * 分片透明: 是指用户不必关心数据是如何分片的，它们对致据的操作在金局关系上进行，即如何分片对用户是透明的。
     * 复制透明:用户不用关心数据库在网络中各个节点的复制情况，被复制的数据的更新都由系统自动完成。
     * 位置透明:是指用户不必知道所操作的数据放在何处，即数据分配到哪个或哪些站点存储对用户是透明的
     * 局部映像透明性 (逻辑透明》 : 是最低层次的透明性，该透明性提供数据到局部数据库的映像，即用户不必关心局部DBMS支持哪种数据模型、使用哪种数据操纵语言，
     *      数据模型和操纵语言的转换是由系统完成的。因此，局部映像透明性对异构型和同构异质的分布式数据库系统是非常重要的。
     */
    /**
     * 设计过程
     * 需求分析（数据字典和数据流），概念结构设计（er图 属性实体联系），逻辑结构设计（关系模式），物理设计（硬件，OS，索引）
     *
     * 概设 -需求分析
     * 抽象数据
     * 设计局部ER模型合并局部模型
     * 消除冲突重构优化:属性冲突:包括属性域冲突和属性取值冲突。
     *              命名冲突:包括同名异义和异名同义。
     *              结构冲突:包括同一对象在不同应用中具有不同的抽象，以及同一实体在不同局部E-R图中所包含的属性个数和属性排列次序不完全相同。
     * 消除冗余
     * 逻辑设计
     *
     * 逻辑结构设计  一个实体必须转换关系模式
     *     一对一  归并 任意一端可归并另一属性，独立关系 任意一端写入另一主键
     *      一对多  归并 多端归并另一属性，独立关系 多端记录另一主键
     *      多对多   独立关系模式 中间表或者两边都写入另一主键
     *
     *  交集并集差集笛卡尔积
     *  投影  select a,b,c from t
     *  选择  select * from t where a =1
     *  自然连接  select a.x,b.y from a join b on a.x=b.x   同名的必须相同，去掉重复列后才开始编号
     *
     *          代数式意义
     *          ζ1=4（Π1235（s1*s2））   先笛卡尔再筛选
     *          ΠΠ1235（ζ1=4（s1*s2））   先筛选再笛卡尔
     *          ^  表示and
     *
     */
}
