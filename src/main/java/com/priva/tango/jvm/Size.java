package com.priva.tango.jvm;

public class Size {
    public static void main(String[] args) throws InterruptedException {
        Integer i =127;
        Integer j =127;
        System.out.println(i==j);
//        CalculateObject calculateObject = new CalculateObject();
//        CalculateObjectD calculateObjectA = new CalculateObjectD();
//        Thread.sleep(1000*100);
    }
}
class CalculateObject{
    /**
     * 对象头8字节
     * 自身引用4字节
     * i int 4字节
     *
     * 如果不满8字节需要补齐
     */
    int i;//4
}
class CalculateObjectF{
    float i;//4
}
class CalculateObjectD{
    double i;//8
    CalculateObject a;//4
}
class CalculateObjectB{
    boolean i;//1
}
class CalculateObjectL{
    long i;//8
}
class CalculateObjectS{
    short i;//2
}
class CalculateObjectC{
    char i;//2
}
class CalculateObjectb{
    byte i;//1
}

/**
 * 8 头
 * 4 自身引用
 * 4 byte[]引用
 * 1
 *
 */
class CalculateObjectA{
    byte[] i;//4
    byte x;//1
}