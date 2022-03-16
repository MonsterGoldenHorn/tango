package com.priva.tango.jvm;

public class Iplusplus {
    public static void main(String[] args) {
        Iplusplus s = new Iplusplus();
        s.testi();
    }
    //i++问题
    public void testi(){
        int i = 10;
        i=i++;//i还是=10
        int j= i++;//j还是10
        //如果

//        int k = 20;
//        ++k;
//        int j= ++k;//22

        System.out.println(j);
    }
}
