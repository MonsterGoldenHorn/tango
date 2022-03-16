package com.priva.tango.jvm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExceptionMain {

    public static void main(String[] args) {
        /**
         * 异常分
         * runtime,exception
         * runtime继承自exception，可以不用显示抛出
         *
         * 异常表记录开始代码行和结束行以及跳转行
         * 如果有finally,跳转到finally中执行
         */
        System.out.println(test2().toString());
    }

    private void test() throws FileNotFoundException,IOException{
        File f ;
        try {
            f = new File("");
            FileInputStream ff = new FileInputStream(f);
        }catch (IOException e){
            e.printStackTrace();
        }catch (RuntimeException e2){
            e2.printStackTrace();
        }finally {
            int x =0;
            int i = x++;
        }
    }

    public static StringBuffer test2(){
        StringBuffer s = new StringBuffer("111");
        try {
            return s;
        }finally {
            s.append("2");
        }
    }
}
