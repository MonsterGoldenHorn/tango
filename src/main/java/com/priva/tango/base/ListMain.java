package com.priva.tango.base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListMain {
    public static void main(String[] args) {
        List list = new ArrayList();
        List list1 = new Vector();
        List list2 = Collections.synchronizedList(list);
        List list3 = new CopyOnWriteArrayList();//rw lock
//  rb树      ConcurrentHashMap
//  跳表      ConcurrentSkipListMap
    }
}
