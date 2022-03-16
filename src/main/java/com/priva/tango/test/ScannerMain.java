package com.priva.tango.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ScannerMain {
    public static int max = Integer.MIN_VALUE;
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] nums = new int[n][m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                nums[i][j] = sc.nextInt();
            }
        }
        //找最大包含关系
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(nums[i][j] == 1){
                    int x = df(nums, i, j);
                    if(x>max){
                        max = x;
                    }
                }
            }
        }
        System.out.println(max);



//        Scanner in = new Scanner(System.in);
//        while (in.hasNext()){
//            String next = in.next();
//            List<List<Integer>> nums = null;
//            try {
//                nums = handle(next);
//            } catch (Exception e) {
//                System.out.println("(0,0)");
//                return;
//            }
//            int max = Integer.MIN_VALUE;
//            List<Integer> ret = null;
//            for (List<Integer> num : nums) {
//                int x = num.get(0);
//                int y = num.get(1);
//                if(max<x*x + y*y){
//                    ret = num;
//                }
//            }
//            String s = "("+ret.get(0)+","+ret.get(1)+")";
//            System.out.println(s);
//        }
    }

    public static int df(int[][] nums, int start1, int start2){
        int max_x = nums[0].length;
        int max_y = nums.length;
        if(start1 > max_x-1 || start2 > max_y-1){
            return 0;
        }


        int i = nums[start1][start2];
        if(i == 0){
            return 0;
        }else {
            //向右和向下
            return 1 + df(nums, start1+1, start2) + df(nums, start1, start2+1);
        }
    }

    public static List<List<Integer>> handle(String s) throws Exception {
        char[] chars = s.toCharArray();
        List<List<Integer>> l = new ArrayList<>();
        for (int i = 0; i < chars.length; i++) {
            if(chars[i] == '('){
                List<Integer> m = new ArrayList<>();
                StringBuilder sbd = new StringBuilder();
                for (int j = i+1;j < chars.length; j++){
                    sbd.append(chars[j]);
                    if(chars[j] == ','){
                        addChar(m, sbd);
                        sbd = new StringBuilder("");
                    }else if(chars[j] == ')'){
                        addChar(m, sbd);
                        l.add(m);
                        i=j;
                        break;
                    }
                }
            }
        }
        return l;
    }

    private static void addChar(List<Integer> m, StringBuilder sbd) throws Exception {
        sbd.deleteCharAt(sbd.length() - 1);
        if (sbd.charAt(0) == '0') {
            throw new Exception("输入非法");
        } else {
            String s1 = sbd.toString();
            Integer x = Integer.valueOf(s1);
            m.add(x);
        }
    }
}
