package com.priva.tango.base;

import net.sf.cglib.beans.BeanGenerator;
import net.sf.cglib.beans.BeanMap;

public class Test {
    public static void main(String[] args) {
        new Thread(() -> {
            if(1!=0){

            }
            for (int i = 0; i < 10000; i++) {
                //cglib创建对象会放到方法区
                BeanGenerator generator = new BeanGenerator();
                generator.addProperty("id", Object.class);
                Object obj = generator.create();
                BeanMap beanMap = BeanMap.create(obj);
                beanMap.put("id", new byte[1024]);
                beanMap.setBean(obj);
                if(i%1000 == 0){
                    System.out.println("*****************************");
                    System.out.println(i);
                    System.out.println("*****************************");
                }
            }
        }).start();

        try {
                Thread.sleep(1000*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
}
