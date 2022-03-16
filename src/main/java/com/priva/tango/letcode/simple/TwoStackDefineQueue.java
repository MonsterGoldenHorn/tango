package com.priva.tango.letcode.simple;

import java.util.Stack;

/**
 * 用两个栈来实现一个队列，使用n个元素来完成 n 次在队列尾部插入整数(push)和n次在队列头部删除整数(pop)的功能。 队列中的元素为int类型。保证操作合法，即保证pop操作时队列内已有元素。
 * 保证出栈顺序，如果出栈空了才往入栈里面取，就不需要在入栈时将出栈数据复制回来了
 */
public class TwoStackDefineQueue {

    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() throws Exception {
        if (stack2.isEmpty())
            while (!stack1.isEmpty())
                stack2.push(stack1.pop());

        if (stack2.isEmpty())
            throw new Exception("queue is empty");

        return stack2.pop();
    }
}

/**
 * 费时间
 */
class Solution {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        if(stack2.size() > 0){
            for(int i = stack2.size(); i>0; i--){
                stack1.push(stack2.pop());
            }
        }
        stack1.push(node);
    }

    public int pop() {
        if(stack1.size() > 0){
            for(int i = stack1.size(); i>0; i--){
                stack2.push(stack1.pop());
            }
        }
        int node = stack2.pop();
        return node;
    }
}
