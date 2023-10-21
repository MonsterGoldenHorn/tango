package com.priva.tango.exam;

/**
 * 信息安全保障
 */
public class NetSafe {
    /**
     *>> 安全保护能力五个等级
     ①　用户自主保护级；
     ②　系统审计保护级；
     ③　安全标记保护级；
     ④　结构化保护级；
     ⑤　访问验证保护级。
     */
    /*安全保障有关制度*/
    /**
     *  保护信息的保密性、完整性、可用性、另外也包括其他属性，如真实性、可核查性、不可抵赖性和可靠性
     */
    /*安全保密管理及协议*/
    /**
     * 数据泄露防护（DLP）：通过技术手段，防止企业指定数据或信息资产以违反安全策略规定的形式流出企业的一种策略。
     * 数字水印：通过数字信号处理方法，在数字化的媒体文件中嵌入特定标记。可感知、不易感知两种。
     *
     * PGP  应用层，邮件文件混合加密，广泛应用于电子邮件安全。
     * SSL  传输层到应用层
     * TLS   传输层
     * SET     传输层电商身份认证,解决用户、商家、银行之间通过信用卡支付的交易问题
     * IPSEC   工作在网络层的安全协议，优点是透明性，提供安全服务不需要更改应用程序。
     */
    /*安全风险管理*/
    /**
     * 风险评估基本要素：脆弱性、资产、威胁、风险和安全措施
     *  安全威胁：对机构及其资产构成潜在破坏的可能性因素和事件
     *  脆弱性：安全风险评估中的重要内容，包括各种资产本身、没有正确实施的安全保护措施本身
     *  风险计算模型：信息资产、弱点/脆弱性、威胁。
     */
    /*信息安全抗攻击*/
    /**
     * >>>秘钥的选择：
     * 数据加密秘钥（DK）
     * 秘钥加密秘钥（KK）：保护秘钥
     *
     * >>秘钥生成需要考虑：增大秘钥空间、选择强钥、秘钥随机性
     *
     *
     * 网络攻击分为
     * 被动攻击     收集信息，破坏保密性
     *      窃听  非法或合法窃取 信息资源和敏感信息
     *      业务流分析  通过系统监听分析
     * 主动攻击    中断（可用），篡改（完整），伪造（真实）
     *
     * >> DOS      对信息资源的合法访问被无条件阻止
     * 拒绝服务攻击的分类：资源消耗，系统或应用程序缺陷、配置修改、物理破坏或改变网络部件
     * DDoS三级结构：客户端、主控端、代理端
     * 入侵检测的最基本手段是采用模式匹配的方法来发现入侵攻击行为
        防御包括：特征识别、防火墙、通信数据量的统计、修正问题和漏洞

     * >>欺骗攻击与防御
     *  ARP：解析IP地址为MAC地址
     *      欺骗该机制可阻断正常的网络访问
     *      防范方法：固化ARP表、使用ARP服务器、双向绑定、安装防护软件
     *  DNS：解析域名为IP地址
     *       欺骗该机制可以使用户访问错误的服务器地址
     *       防范方法：被动监听检测、虚假报文探测、交叉检查查询
     *  IP欺骗
     *        修改IP数据报的报头，把自身的IP地址修改为另一个IP，以获取信任。
     *        防范方法：防火墙
     *  >>端口扫描
     *      端口扫描是入侵者搜集信息的常用手法之一。
     *      原理分类：  全TCP连接、打开式扫描（SYN扫描）、 FIN扫描、第三方代理扫描
     *  >> 针对TCP/IP堆栈的攻击方式
     *      同步包风暴（SYN Flooding）
     *          应用最广泛的DoS攻击，攻击TCP协议建立连接的三次握手，让目标主机等待连接完成而耗尽资源。
     *           防范：减少等待超时时间
     *      ICMP攻击
     *          攻击操作系统的网络层缓冲区，旧版操作系统会崩溃死机
     *          防范：打补丁、升级到新版操作系统
     *      SNMP攻击
     *          SNMP协议用户管理网络设备，早期V1版本缺少认证，可能被攻击者入侵
     *          防范：升级SNMP协议到V2以上并设置访问密码
     *  >>  系统漏洞扫描
     *      基于网络的漏洞扫描。通过网络扫描目标主机的漏洞，常被主机边界的防护封堵，因此获得的信息有限
     *      基于主机的漏洞扫描。目标系统上安装代理（Agent）或者服务（Service），因而扫描到更多的漏洞。
     *      优点：扫描的漏洞数量多、集中化管理、网络流量负载小
     */
}