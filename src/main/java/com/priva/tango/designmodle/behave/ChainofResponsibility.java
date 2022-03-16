package com.priva.tango.designmodle.behave;

public class ChainofResponsibility {
/**
 * 1、有多个对象可以处理同一个请求，具体哪个对象处理该请求由运行时刻自动确定。
 * 2、在不明确指定接收者的情况下，向多个对象中的一个提交一个请求。
 * 3、可动态指定一组对象处理请求。
 * 
 * 
 * 
 * 缺点
 *  1、不能保证请求一定被接收。
 *  2、系统性能将受到一定影响，而且在进行代码调试时不太方便，可能会造成循环调用。 
 *  3、可能不容易观察运行时的特征，有碍于除错
 *  
 *  在链式执行过程中，常常使用适配器来选择下一个执行器
 *  
 *  filterChain
 *  
 *  dispatchServlet
 *  
 *  tomcat:Valve
 *  
 */
}
