package com.priva.tango.exam;

public class ExamMain {

    /**
     * 软件开发方法
     * 结构化   注重数据流DFD进行功能建模，状态转换图表示行为模型
     * 面向对象  UML
     *          类图:一组类、接口、协作和它们之间的关系
     *          对象图:一组对象及它们之间的关系
     *          构件图:一个封装的类和它的接口
     *          部署图:软硬件之间映射
     *          制品图:系统的物理结构
     *          包图:由模型本身分解而成的组织单元，以及它们之间的依赖关系
     *          用例图: 系统与外部参与者的交互
     *          顺序图: 强调按时间顺序
     *          通信图 (协作图)
     *          状态图:状态转换变迁
     *          活动图:类似程序流程图，并行行为
     *          定时图: 强调实际时间
     *          交互概览图
     *          组合结构图
     *     4+1视图。 逻辑视图  分析设计人员
     *              实现视图    程序员
     *              进程视图    系统集成人员
     *              部署视图    运维，网络工
     *              用例视图    最终用户
     *
     * 面向服务
     */
    /**
     * 建模方法
     * 1、结构化建模方法
     * 结构化建模方法是以过程为中心的技术，可用于分析一个现有的系统以及定义新系统的业务需求。结
     * 构化建模方法所绘制的模型称为数据流图 (DFD)。对于流程较为稳定的系统可考虑结构化建模方法
     * 2、信息工程建模方法 (或数据库建模方法)
     * 信息工程建模方法是一种以数据为中心，但过程敏感的技术，它强调在分析和研究过程需求之前，首
     * 先研究和分析数据需求。信息工程建模方法所创建的模型被称为实体联系图 (ERD)。主要用于数据
     * 建模。
     * 3、面向对象建模方法
     * 面向对象建模方法将“数据”和“过程”集成到被称为“对象”的结构中，消除了数据和过程的人为
     * 分离现象。面向对象建模方法所创建的模型被称为对象模型。随着面向对象技术的不断发展和应用，
     * 形成了面向对象的建模标准，即UML (统一建模语言)。UML定义了几种不同类型的模型图，这些
     * 模型图以对象的形式共建一个信息系统或应用系统，是目前比较常用的建模方法。
     */
    /**
     * 过程模型
     *  瀑布  sdlc  从需求，软件设计，开发设计，编码实现，单元测试，集成测试，系统测试到运行维护
     *  V模型
     *  原型模型
     *  螺旋模型  原型+瀑布  每个阶段目标分析，  风险分析   ，开发验证，评审
     *  构建组装模型  cbse
     *  快速应用开发  rad 瀑布+构建
     *  统一过程/开发方法  用例驱动，架构中心，迭代增量 （迭代：新增或优化  增量：新增）
     *          四大过程/阶段：初始，细化，构造，移交
     *         核心工作流： 业务建模，需求，分析设计，实现，测试，部署，配置与变更管理，项目管理，环境
     *  敏捷开发    适应性，以人为本，增量迭代，小型项目
     *         价值观 极限编程 XP  接近螺旋开发：
     *          加强面对面沟通，减少文档
     *          简单不过度设计，替换代价小
     *          及时反馈
     *          勇气，对变更说yes
     *         水晶方法：机动性的方法，不同项目不同敏捷方式
     *         SCRUM：  敏捷过程，偏项目管理
     *         特征驱动开发 FDD：开发三要素 （人，过程，技术）
     *                  关键角色：PM，CTO，开发经理，主程序，程序，领域专家
     */
    /**
     *  净室软件
     *  受控污染级别的环境
     *  使用盒结构规约，或形式化方法进行分析和设计建模，  强调将正确性验证而不是测试   个人理解正确性验证的覆盖度
     *  总体太大要使用统计学原理抽样方式
     */
    /**
     * 把应用程序中用得最多、最频繁的那部分核心程序作为评估计算机系统性能的标准
     *      程序，称为基准测试程序(benchmark)。基准程序法是目前一致承认的测试系统性能的较好方法。
     *
     * >>性能测试
     *  [测试精确度排名] 真实的程序>核心程序>小型基准程序>合成基准程序
     *  >>> TPC
     *  TPC基准程序: TPC (Transaction Processing Councl，事务处理委员) 基准程序用以评测计算机在事务处理
     *      数据库处理、企业管理与决策支持系统等方面的性能。该基准程序的评测结果用每秒完成的事务处理数TPC来表示。
     *  TPC-A基准程序规范用于评价在OLTP环境下的数据库和硬件的性能;
     *  TPC-B测试的是不包括网络的纯事务处理量，用于模拟企业计算环境;
     *  TPC·C测试的是联机订货系统;
     *  TPC-D、TPC-H和TPC-R测试的都是决策支持系统，其中TPC-R允许有附加的优化选项;
     *  TPC-E测试的是大型企业信息服务系统。
     *  TPC-W是基于Web 应用的基准程序，用来测试一些通过Internet进行市场服务和销售的商业行为，所以TPC-W可以看作是一个服多器的测试标准。
     */
    /**
     * >>>人机界面黄金三法则
     * 置于用户控制之下
     *      。以不强迫用户进入不必要的或不希望的动作的方式来定义交互方式
     *      ·提供灵活的交互
     *      允许用户交百可以被中断和撒销
     *      当技能级别增加时可以使交互流水化并允许定制交互
     *      ·使用户隔离内部技术细节
     *      ·设计应允许用户和出现在屏幕上的对象直接交互
     * 减少用户的记忆负担
     *      。减少对短期记忆的要求
     *      ，建立有意义的缺省
     *      定义直觉性的捷径
     *      ·界面的视觉布局应该基于真实世界的隐喻
     *      以不断进展的方式揭示信息
     * 保持界面的一致性
     *      ·允许用户将当前任务放入有意义的语境
     *      。在应用系列内保持一致性
     *      。如过去的交互模型已建立起了用户期望，除非有迫不得已的理由，不要改变它
     */
    /**
     * >>>软件设计包括体系结构设计、接口设计、数据设计和过程设计
     *      结构设计:定义软件系统各主要部件之间的关系。
     *      数据设计:将模型转换成数据结构的定义。好的数据设计将改善程序结构和模块划分，降低过程复杂性。
     *      接口设计 (人机界面设计): 软件内部，软件和操作系统间以及软件和人之间如何通信。
     *      过程设计:系统结构部件转换成软件的过程描述。
     *  >>结构化设计
     *      概要设计[外部设计]:功能需求分配给软件模块，确定每个模块的功能和调用关系，形成模块结构图
     *      详细设计[内部设计]:为每个具体任务选择适当的技术手段和处理方法
     *      >设计原则
     *          模块独立（高内聚低耦合）
     *          保持模块的大小适中
     *          多扇入少扇出
     *          深度宽度不宜过高
     *      >模块的四个要素
     *          (1)输入和输出。模块的输入来源和输出去向都是同一个调用者，即一个楼块从调用者那儿取得输入，进行加工后再把输出返回调用者。
     *          (2)处理功能。指模块把输入转换成输出所做的工作。
     *          (3)内部数据。指仅供该模块本身引用的数据。
     *          (4)程序代码。指用来实现模块功能的程序。
     *
     *
     *     内聚
     *      功能内聚（内部协同）>顺序内聚（内部必须有顺序）>通信内聚>过程内聚>瞬时内(时间内聚)>逻辑内聚>偶然内聚(巧合内聚)
     *      1.偶然内聚或巧合内聚:指一个模块内的各处理元素之间没有任何联系，或者有联系，这种联系也很松散，是内聚度最低的模块。
     *      2.逻辑内聚(多态):指模块内执行若个逻辑上相似的功能，通过传送给模块的参数确定该模块完成哪一个功能。
     *      3.时间内聚:把需要同时执行的动作组合在一起形成的模块为时间内聚模块。所有的动作需在同一个时间段内执行。
     *      4.过程内聚:指一个模块完成多人任务，这些任务必须按指定的过程执行。构建或者操作的组合方式是，允许在调用前面的构件或操作之后，马上调用后面的构件或操作，即使两者之间没有数据进行传递
     *      5.通信内聚:指博块内的所有处理元素都在同一数据结构上操作，或者各处理使用相同的输入教据或产生相同的输出数据
     *      6.顺序内聚:指一个模块中的各人处理元素都密切相关于同一各功能且必须顺序执行，前一个功能元素的输出就是下一个功能的输入。即一个模块完成多个功能，这些模块又必须顺序执行。
     *      7.功能内聚: 指模块内的所有元素共同作用完成一个功能，缺一不可。是最强的内聚类型.
     *
     *     耦合
     *      1)非直接耦合(无直接耦合): 两个模块之间没有直接关系，它们之间的联系完全是通过主模块的控制和调用来实现的，它们之间不传递任何信息，因此模块间的耦合性最弱，模块独立性最高。
     *      2)数据耦合:指两个模块之间有调用关系，传递的是简单的数据值(不是控制参数，公共数据结构或外部变量)，相当于高级语言中的值传递
     *      3)标记耦合: 指两个模块之间通过参数表(数据结构) 传递记录信息
     *      4)控制耦合:指一个模块调用另一个模块时，传递的是控制变量，被调用模块通过该控制变量的值有选择地执行模块内的某一功能。因此，被调用模块内应具有多个功能，那个功能起作用受调用模块控制。也
     *      就是说一个模块通过传送开关、标志、名字等控制信息，明显地控制选择另一模块的功能。
     *      5)外部耦合:模块间通过软件之外的环境联结( 如I/0将模块合到特定的设备、格式、通信协议上)时称为外部耦合
     *      6)公共耦合: 指通过一个公共数据环境相互作用的那些模块间的耦合
     *      7)内部耦合:一个模块直接访问另一个模块的内部数据，或者通过非正常入口转入另一个模块内部，或者两个模块有一部分程序代码重叠，又或者一个模块有多种入口。这种模块之间的糟合称之为内容糟合。
     */
}
