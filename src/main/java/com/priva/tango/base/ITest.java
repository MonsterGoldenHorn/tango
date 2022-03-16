package com.priva.tango.base;

import java.util.ArrayList;
import java.util.List;

public interface ITest {
    default void test(){
        List l = new ArrayList();
        l.forEach(obj-> System.out.println(obj));
        test3();
    }

    static void test1(){

    }

    void test3();
}
