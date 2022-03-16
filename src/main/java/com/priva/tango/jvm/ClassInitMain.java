package com.priva.tango.jvm;

/**
 * 类初始化
 * init is the (or one of the) constructor(s) for the instance, and non-static field initialization.
 * clinit are the static initialization blocks for the class, and static field initialization.
 */
public class ClassInitMain {
    private String name;
    private int age;
    //clinit
    public static String addr = "11";
    //不在clinit
    public static String email;
    //init
    {
        name="";
        addr="123";
    }
    //clinit
    static {
//        addr="2333";
    }

    public static void main(String[] args) {
        System.out.println(addr);
    }
}
