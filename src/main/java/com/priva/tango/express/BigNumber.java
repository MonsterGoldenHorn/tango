package com.priva.tango.express;

public class BigNumber {
    public static void main(String[] args) {
        String a = "1192222222222222222222222222222222222222222222222222222222222111312667";
        String b = "122219999911111111111111111111";
        String multiply = multiply("123", "456");
        System.out.println(multiply);

    }

    public static String multiply(String num1, String num2) {
        char[] chars1 = revers(num1.toCharArray());
        char[] chars2 = revers(num2.toCharArray());
        String f = "0";
        for (int i = 0; i < chars1.length; i++) {
            StringBuilder x = cals(chars2, Integer.valueOf(chars1[i]+""));
            for (int j = 0; j < i; j++) {
                x.insert(0, "0");
            }
            f = add(f.toString(), new String(revers(x.toString().toCharArray())));
        }

        return new String(f.toString());
    }

    /**
     * 乘
     * @param nums1
     * @param num2
     * @return
     */
    public static StringBuilder cals(char[] nums1, int num2) {
        if(num2 == 0){
            return new StringBuilder("0");
        }
        StringBuilder sb = new StringBuilder();
        int remain = 0;
        for (int i = 0; i < nums1.length; i++) {
            int x = Integer.valueOf(nums1[i]+"")*Integer.valueOf(num2+"")+remain;
            sb.append(""+x%10);
            remain = x/10;
        }
        if(remain>0){
            sb.append(""+remain);
        }
        return sb;
    }

    private static String add(String a, String b) {
        char[] arr1 = revers(a.toCharArray());
        char[] arr2 = revers(b.toCharArray());
        int leng = arr1.length;
        int max = arr1.length;
        if(leng > arr2.length){
            leng =  arr2.length;
        }else{
            max = arr2.length;
        }
        int rest = 0;
        StringBuffer sbf = new StringBuffer("");
        for (int i = 0; i < max; i++) {
            char c = i<arr1.length?arr1[i]:'0';
            char d = i<arr2.length?arr2[i]:'0';
            int i1 = Integer.valueOf(c+"") + Integer.valueOf(d+"") + rest;
            //复位
            rest = 0;
            if(i1 >= 10){
                rest = 1;
                sbf.insert(0,i1%10);
            }else{
                sbf.insert(0,i1);
            }
        }
        if(rest>0){
            sbf.insert(0,rest);
        }
        return sbf.toString();
    }

    public static char[] revers(char[] arr){
        char arrr[]  = new char[arr.length];
        for (int i = arr.length; i > 0; i--) {
            arrr[arr.length - i] = arr[i-1];
        }
        return arrr;
    }
}
