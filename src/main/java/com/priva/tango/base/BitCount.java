package com.priva.tango.base;

/**
 * 位运算
 * 二进制转正整数(二进制位左边首位为0为正数（6 --->00000110），1为负数(-6---->11111010))
 * 二进制转负整数，取反+1得到一个正数再加符号
 */
public class BitCount {
    public static void main(String[] args) {
        int x = 23513133;
        long start = System.currentTimeMillis();
        for (int i =0; i<1000000000;i++){
            int i1 = x >> 18;
        }
        long start2 = System.currentTimeMillis();
        System.out.println(start2-start);
//        for (int i =0; i<100000000;i++){
//            int i1 = x >>> 18;
//        }
//        System.out.println(System.currentTimeMillis()-start2);
    }
    //按位与&
    private static void and(){
        //按位与的运算规则是将两边的数转换为二进制位，然后运算最终值，运算规则即(两个为真才为真)1&1=1
    }
    //按位或|
    private static void or(){
        //按位与的运算规则是将两边的数转换为二进制位，然后运算最终值，一真即真
    }
    //异或^
    private static void dif(){
        //相同为0，不同为1
    }
    //左移运算符<<
    private static void l(){
        //二进制位往左挪,右边补0,正数左边第一位补0，负数补1,等于乘于2的n次方
    }
    //右移运算符>>
    private static void r(){
        //二进制位往右挪,右边补0,正数左边第一位补0，负数补1,等于除以2的n次方
    }
    //无符号右移运算符>>>
    private static void rr(){
        //不管正负标志位为0还是1，将该数的二进制码整体右移，左边部分总是以0填充，右边部分舍弃
    }
    //取反运算~
    private static void revers(){
    }
    //逻辑与
    private static void logicAnd(){
        //&逻辑与也称为短路逻辑与，先运算&&左边的表达式，一旦为假，后续不管多少表达式，均不再计算，一个为真，再计算右边的表达式，两个为真才为真
    }
}
