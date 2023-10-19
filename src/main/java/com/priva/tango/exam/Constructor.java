package com.priva.tango.exam;

/**
 * @description
 * @date 2023/10/17 13:48
 */
public class Constructor {
    /**
     * 业务需求分析之后软件设计之前进行架构，架构设计就是需求分配，即将满足需求的职责分配到组件上
     *
     * 架构的本质
     * 1、软件架构为软件系统提供了一个结构、行为和属性的高级抽象。
     * 2、软件架构风格是特定应用领域的惯用模式，架构定义一个词汇表和一组约束。
     * 架构的作用
     * 1、软件架构是项目干系人进行交流的手段
     * 2、软件架构是可传递和可复用的模型，通过研究软件架构可能预测软件的质量。
     * 3、软件架构使推理和控制的更改更加简单，有助于循序渐进的原型设计，可以作为培训的基础。
     */
    /**
     * 逻辑视图     开发视图
     *      场景视图
     * 过程视图     物理视图
     *
     *  >>>    4+1 视图模型认为，一个完整的软件设计模型，应该包括 5 部分的内容:
     * 逻辑视图:描述软件的功能逻辑，由哪些模块组成，模块中包含哪些类，其依赖关系如何。
     *      最终用户：功能需求
     * 开发视图:包括系统架构层面的层次划分，包的管理，依赖的系统与第三方的程序包。开发视图某些方面和逻辑视图有一定重复性，不同视角看到的可能是同一个东西，开发视图中一个程序包，可能正好对应逻辑视图中的一个功能模块。
     *      编程人员：软件管理
     * 过程视图: 描述程序运行期的进程、线程、对象实例，以及与此相关的并发、同步、通信等问题。
     *      系统集成人员：性能可扩充性，吞吐两
     * 物理视图:描述软件如何安装并部署到物理的服务上，以及不同的服务器之间如何关联、通信。
     *      系统工程人员：系统拓扑，安装，通信等
     * 场景视图:针对具体的用例场景，将上述 4 个视图关联起来，一方面从业务角度描述，功能流程如何完成，一方面从软件角度描述，相关组成部分如何互相依赖、调用。
     */
    /**
     * 架构风格，主考五大
     *
     * 五大架构风格	    子风格
     *
     * 数据流风格	        批处理（数据必须是完整的，以整体的方式传递，无需用户交互）、管道-过滤器（管道支持数据流或流式处理，弱用户交互）
     *      视频流，编译器等
     * 调用/返回风格	    主程序/子程序、面向对象、层次结构，CS
     *      优点：利于复用，可维护，可扩展好
     *      缺点：不好分层，耦合高的更难
     *      特点：各个层次的组件形成不同功能级别的虚拟机;多层相互协同工作，而且实现透明。
     * 独立构件风格	    进程通信、事件驱动系统(隐式调用)
     *      进程通信：构件是独立的过程，连接件是消息传递，构件通常是命名过程，消息传递的方式可以是点对点、异步或同步方式，以及远程过程（方法）调用等
     *      事件驱动系统（隐式调用）：构件不直接调用一个过程，而是触发或广播一个或多个事件
     *      优点：松耦合，高复用扩展修改
     *      缺点：失去调用控制，无法保证结果
     * 虚拟机风格	        解释器、规则系统
     *      解释器：通常包括一个完成解释工作的解释引擎，一个包含将被解释的代码存储区、一个记录解释引擎当前工作状态的数据结构，以及一个记录源代码被解释执行的进度的数据结构。
     *      基于规则的系统：基于规则的系统包括规则集、规则解释器、规则／数据选择器和工作内存，一般在人工智能领域和DSS中。根据经验规则做专家系统
     *      java虚拟机，class文件运行
     * 仓库风格（数据共享）	        数据库系统、黑板系统、超文本系统
     *      仓库风格中的构件分为两种：一种是中央数据结构，保存系统的当前状态；另一种是独立构件，对中央数据存储进行操作
     *      黑板系统：包括知识源、黑板和控制三部分。    现代编译器，语音识别系统，模式识别，图像处理，知识推理
     *      超文本系统：构件以网状链接方式相互连接
     *  闭环控制架构（过程控制）
     *      适合于嵌入式系统，涉及连续的动作与状态，用于解决简单闭环控制问题。    空调制冷，定速巡航
     *  C2风格
     *      基本原则：构件和连接件都有一个顶部和一个底部
     */
    /**
     * 信息系统架构
     * CS 三层CS或BS 多层CS MVC
     *
     * >>> 两层CS   分为表示层和数据层，业务处理分散在两处
     * 1、分布能力差
     * “成也萧何，败也萧何”，因为有客户端的安装带来的一系列个性化的方便的同时，也造成了在系统部署时，对每一个用户都要安装客户端才能进行使用，如用户数量庞大且分布广泛的话，给部署带来及大的不便。
     * 2、维护成本高昂由于客户端数量庞大，对系统的维护和升级都是很大的考验。
     * 3、跨平台使用难以实现，软件升级移植困难
     * 客户端程序设计复杂
     * C/S 系统如需跨平台使用，必须重新开发在其系统平台下的客户端才能使用。
     *
     * >>>三层CS   多一层功能层
     * 加一层应用服务器，业务逻辑在服务器，更新方便
     * >>>多层cs
     * >>bs   浏览器
     * 混合模式，对内cs，对外bs
     *>>>三层bs
     *      表现层  mvc->mpv->mvvm   mvc时期v与m耦合，mpv解耦
     *              >> mvvm
     *              View ：html，CSS，模板文件
     *              ViewModel : Js , runtime, compler
     *              Model： Java／C＃业务逻辑处理
     *      中间层  service
     *      数据访问层  orm
     *      数据架构层   DBMS
     */
    /**
     * SOA
     * 基于服务架构，主要想集成
     * 特点: 松散耦合，粗粒度，标准化接口
     * 实现方式：webservice   服务请求者，服务提供者，服务注册中心       请求者查询注册中心服务后直接与提供者通信
     *          esb         请求与提供者解耦
     *                  提供位置透明性的消息路由和寻址服务
     *                  提供服务注册和命名的管理功能
     *                  支持多种的消息传递范型
     *                  支持多种可以广泛使用的传输协议
     *                  支持多种数据格式及其相互转换
     *                  提供日志和监控功能
     *
     */
    /**
     发现服务 UDDI、DISCO
     描述服务 WSDL，XML Schema
     WSDL就是WebService接口对应的WSDL文件，该文件通过xml格式说明如何调用，可以看作WebService的接口文档（使用说明书）
     消息格式层
     SOAP：SOAP，Simple Object Access Protocol，简单对象访问协议，简单的说就是用于访问网络服务协议：它是基于XML的简易协议，可使应用程序在HTTP之上进行信息交换。
     SOAP是一种网络通信协议，用于网络上、不同平台、不同语言的应用程序间的通信。
     SOAP＝HTTP协议＋XML数据格式
     REST：HTTP＋XML进行基于Web通信的技术简单性，缺少严格的配置文件只支持几个操作（POST， GET ， PUT，DELETE）强调信息本身，称为资源。
     特点：网络上的所有事物都被抽象为资源。每个资源对应一个唯一的资源标识。通过通用的连接部接口对资源进行操作。对资源的各种操作不会改变资源标识。所有的操作都是无状态的。
     RESTFULL是REST的具体实现，REST是方法论。
     编码格式层
     XML(DOM，SAX)。
     传输协议层
     HTTP、TCP／IP，SMTP等。
     */

    /**
     * >>微服务   SOA的一种
     特点
        小，且专注做一件事情
        轻量级的通信机制
        松耦合、独立部署
     优势：

     技术异构性：每个服务都是一个独立的个体，采用符合自身的技术实现，每个服务都可以采用自己专属的技术。
     弹性：弹性主要讲的是系统中一部分出现故障会引起多大问题。在单块系统中，一个部分出现问题，可能导致整体系统的问题。而微服务架构中，每个服务可以内置可用性的解决方案与功能降级方案，所以比单块系统强。
     扩展：单块系统中，我们要做扩展，往往是整体进行扩展。而在微服务架构中，可以针对单个服务进行扩展。
     简化部署：微服务架构中，每个服务的部署都是独立的，这样就可以更快地对特定部分的代码进行部署。
     与组织结构相匹配：微服务架构可以将架构与组织结构相匹配，避免出现过大的代码库，从而获得理想的团队大小及生产力。
     可组合性：在微服务架构中，系统会开放很多接口供外部使用。当情况发生改变时，可以使用不同的方式构建应用，而整体化应用程序只能提供一个非常粗粒度的接口供外部使用。
     对可替代性的优化：在微服务架构中，我们可以在需要时轻易地重写服务，或者删除不再使用的服务。

     面临的挑战：

     分布式系统的复杂度：使用微服务实现分布式系统的复杂度要比单块系统高。这表现在多个方面，如：性能方面微服务是拆分成多个服务进行部署，服务间的通信都是通过网络，此时的性能会受影响。同时可靠性也会受影响。数据一致性也需要严格控制，其成本也比单块系统高。
     运维成本：相比传统的单块架构应用，微服务将系统分成多个独立的部分，每个部分都是可以独立部署的业务单元。这就意味着，原来适用于单块架构的集中式的部署、配置、监控或者日志收集等方式，在微服务架构下，随着服务数量的增多，每个服务都需要独立的配置、部署、监控、日志收集等，因此成本呈指数级增长。
     部署自动化：传统单块系统部署往往是以月、周为单位，部署频度很低，在这种情况下，手动部署是可以满足需求的。而对于微服务架构而言，每个服务都是一个独立可部署的业务单元，每个服务的修改都需要独立部署。这样部署的成本是比较高的，如亚马逊，每天都要执行数十次、甚至上百次的部署，此时仍用人工部署是行不通的，要使用自动化部署。如何有效地构建自动化部署流水线，降低部署成本、提高部署频率，是微服务架构下需要面临的一个挑战。
     DevOps与组织结构：传统单块架构中，团队通常是按技能划分，如开发部、测试部、运维部，并通过项目的方式协作，完成系统交付。而在微服务架构的实施过程中，除了如上所述的交付、运维上存在的挑战，在组织或者团队层面，如何传递 DevOps 文化的价值，让团队理解 DevOps 文化的价值，并构建全功能团队，也是一个不小的挑战。
     服务间依赖测试：由于微服务架构是把系统拆分为若干个可独立部署的服务，所以需要进行服务间的依赖测试。在服务数量较多的情况下，如何有效地保证服务之间能有效按照接口的约定正常工作，成为微服务实施过程中必须面临的巨大挑战。
     服务间依赖管理：传统的单块系统，功能实现比较集中，大部分功能都运行在同一个应用中，同其他系统依赖较少。而微服务架构则不同，在将系统功能拆分成相互协作的独立服务之后，随着微服务个数的增多，如何清晰有效地展示服务之间的依赖关系，成为了一个挑战

     */
    /**
        对比
     微服务	                    SOA
     能拆分的就拆分	            是整体的，服务能放一起的都放一起
     纵向业务划分	            水平分多层
     由单一组织负责	            按层级划分不同部门的组织负责
     细粒度	粗粒度
     两句话可以解释明白	        几百字只相当于SOA的目录
     独立的子公词	            类似大公司里面划分了一些业务单元（BU）
     组件小	                    存在较复杂的组件
     业务逻辑存在于每一个服务中	业务逻辑横跨多个业务领域
     使用轻量级的通信方式，如HTTP	企业服务产总线（ESB）充当了服务之间通信的角色
     */
    /**
     微服务架构实现	                        SOA实现
     团队级，自底向上开展实施	            企业级，自顶向下开展实施            级别
     一个系统被折分成多个服务，粒度细	    服务由多个子系统组成，粒度大
     无集中式总线，松散的服务架构	        企业服务总线，集中式的服务架构      有无总线区别
     集成方式简单 （HTTP／REST／JSON）	集成方式复杂（ESB／WS／SOAP）
     服务能独立部署	                    单块架构系统，相互依赖，部署复杂
     */
    /**
     * MDA（Model Driven Architecture）
     * 净室软件，形式化里出现过的
     * 使用模型完成软件的分析、设计、构建、部署、维护等各开发活动
     * 起源：一分离系统规约和平台实现的思想
     * 主要目标：Portabiity （ 可移植性）、interoperability （ 互通性）、Reusability （可重用性）
     * 核心模型：
     *      平台独立模型 （ PIM ）：具有高抽象层次、独立于任何实现技术的模型。比如UML模式
     *      平台相关模型（PSM）：为某种特定实现技术实现技术量身定做，让你用这种技术中可用的实现构造来描述系统的模型。PIM会被变换成一个或多个PSM。
     *      代码Code：用源代码对系统描述（规约）。每个PSM部将被变换成代码。
     */
    /**
     * 架构描述语言（ADL）
     * ADL是一种形式化语言，在底层语义模型的支持下，为软件系统的概念体系结构建模提供了具体的语法框架。基于底层语义工具为体系结构的表示、分析、演化、细化、设计过程等提供支持
     * ADL三个基本元素
     *      构件：计算或数据存储单元。
     *      连接件：用于构件之间交互建模的体系结构构造块及其支配这些交互的规则。
     *      架构配置：描述体系结构的构件与连接件的连接图。
     */
    /**
     * 特定领域软件架构DSSA
     * 特定领域比如金融业务，教育领域，电力领域，通信领域等等。
     * 领域分析->领域设计->领域实现（从上到下，遂渐求精，循环迭代）
     * 建立领域模型-获得DSSA-开发和组织可复用信息（上述描述中的每步的结果）
     * 垂直域，水平域
     *  >>>领域分析机制
     * 领域专家（军师）：有经验的用户、从事该领域中系统的需求分析、设计、实现以及项目管理的有经验的软件工程师等，领域专家的主要任务包括提供关于领域中系统的需求规约和实现的知识。
     * 领域分析人员：应由具有知识背景的有经验系统分析员来担任
     * 领域设计人员：应由有经验的软件设计人员来担任
     * 领域实现人员：应由有经验的程序设计人员来担任
     *
     * >>建立过程：
     * 定义领域范围、定义领域特定元素、定义领域特定的设计和实现需求约束、定义领域模型和架构、产生搜集可复用的产品单元。
     * 以上步骤是并发的、递归的、反复的、螺旋型的。
     * >>三层次模型：
     * 领域架构师：领域开发环境，是开发共性的东西，接心的
     * 应用工程师：领域特定应用的开发环境，利用共性的东西，结合客户的需求，完成应用的开发，个性的
     * 操作员：应用执行环境
     */
    /**
     *  基于架构的软件设计（ABSD）
     *  强调由商业、质量和功能需求的组合驱动软件架构设计。ABSD 方法有三个基础：
     *  第一个基础是功能分解，在功能分解中使用已有的基于模块的内聚和耦合技术。
     *  第二个基础是通过选择体系结构风格来实现质量和商业需求。
     *  第三个基础是软件模板的使用。
     *  >>>视角视图
     *  不同视角有不同视图
     *  >>用例捕获功能需求，特定场景捕获质量需求
     *  ABSD 方法是一个自顶向下，递归细化的过程，软件系统的架构通过该方法得到细化，直到能产生软件构件的类。
     *
     *  >>架构文档化过程的主要输出结果是架构需求规格说明和测试架构需求的质量设计说明书这两个文档，生成需求模型构件的精确的形式化的描述，作为用户和开发者之间的一个协约。
     *
     *  >>基于架构的软件开发模型把整个基于架构的软件过程划分为架构需求、设计、文档化、复审、实现、演化等6个子过程。
     *          复审的目的是标识潜在的风险，及早发现架构设计中的缺陷和错误，包括架构能否满足需求、质量需求是否在设计中得到体现、层次是否清晰、
     *              构件的划分是否合理、文档表达是否明确、构件的设计是否满足功能与性能的要求等等。
     *          架构文档化过程的主要输出结果是架构需求规格说明和测试架构需求的质量设计说明书这两个文档，生成需求模型构件的精确的形式化的描述，作为用户和开发者之间的一个协约。
     *              文档的完整性和质量是软件架构成功的关键因素
     *          所谓“实现”就是要用实体来显示出一个软件架构，即要符合架构所描述的结构性设计决策，分割成规定的构件，按规定方式互相交互。
     */
}