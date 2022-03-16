package com.priva.tango.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * 非虚方法（）
 * invokestatic
 * invokespecial
 *  init,private,super.
 *
 * 虚方法
 * invokevirtual
 *  编译期无法确定的方法，final修饰的方法除外
 * invokeinterface
 * invokedynamic
 *
 * 直接引用方法，编译期确定
 * 动态连接引用，虚方法，无法确定，final除外
 */
public class InvokeFunction extends SuperClass implements InterfaceClass{
    public static void main(String[] args) {
        InvokeFunction i = new InvokeFunction();//invokespecial
//        i.dynamic();
        i.special();

        InterfaceClass ix = new InvokeFunction();
        ix.api();//invokeinterface
    }

    //invokedynamic
    /**
     *  0 new #5 <java/util/ArrayList>
     *  3 dup
     *  4 invokespecial #6 <java/util/ArrayList.<init>>
     *  7 astore_1
     *  8 aload_1
     *  9 invokedynamic #7 <accept, BootstrapMethods #0>
     * 14 invokeinterface #8 <java/util/List.forEach> count 2
     * 19 return
     */
    public void dynamic(){
        List l = new ArrayList();
        l.forEach(item -> System.out.println(item));
    }
    //invokespecial
    public void special(){
        this.privateFunction();
        this.doSometh();//invokevirtual
        this.doSometh1();//invokevirtual
        super.doSometh();//invokespecial
        super.doSometh1();//invokespecial
        this.api();//invokevirtual
    }

    private void invokeInterface(){

    }
    private void privateFunction(){
    }

    @Override
    public void api() {

    }
}
interface InterfaceClass{
    void api();
}
class SuperClass{
    public void doSometh(){

    }

    public final void doSometh1(){

    }
}