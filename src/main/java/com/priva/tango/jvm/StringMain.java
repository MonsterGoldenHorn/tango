package com.priva.tango.jvm;

/**
 * --XX:+PrintStringTableStatistics
 * 字符串常量池统计信息
 */
public class StringMain {
    /**
     * final char[]  1char = 2byte = 64bit
     * 1.8
     * 9
     * final byte[]
     */
    /**
     * String pool  1.6之前在永久代 1.7之后在堆 方便回收，大小较大
     * hashtable 默认大小1009 --XX:StringTableSize=
     * 1.7默认60013  最小可设置1009
     * 如果太短导致hash冲突太简单，链表变长
     */
    /**
     * G1会对堆中的字符串对象去重
     * @param args
     */
    public static void main(String[] args) {
        test2();
    }

    private void test1(){
        //常量优化
        String s1 = "a" + "b" + "c";
        String s2 = "abc";

        System.out.println(s1 == s2);

        String s3 = "abc";
        String s4 = "abcd";
        String s5 = s3 + "d";//StringBuilder
        System.out.println(s5 == s4);//false
        final String s6 = "a";
        final String s7 = "b";
        String s9 = "ab";
        String s8 = s6 + s7;//true  常量直接优化

        s5 = s4.intern();//先在pool找equals，没有就添加到Pool
        s4.toString();//不会添加pool
        System.out.println(s5 == s4);//true
    }

    private static void test2(){
        String s1 = "1";//s1直接指向pool，常量池有1
        String s2 = new String("1");//s2在堆
        s2 = s2.intern();//s2指向Pool
        System.out.println(s2 == s1);//true
    }

    private void test3(){
        String s1 = new String("1") + new String("1");//s1直接指向pool，常量池没有有11
        s1.intern();//1.6中创建新对象11 1.7以后常量池在堆，直接将常量池指向堆
        String s2 = "11";//s2指向堆
        System.out.println(s2 == s1);//true
    }

    private void test4(){
        String s1 = new String("1") + new String("1");//s1直接指向pool，常量池没有有11
        String s2 = s1.intern();

        //1.6 false/true   1.7 true/true
        System.out.println(s1 == "11");
        System.out.println(s2 == "11");
    }
}
