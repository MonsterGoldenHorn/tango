package com.priva.tango.letcode.medium;

import java.util.ArrayList;

/**
 * 和为 S 的连续正数序列
 * 1-100的数组
 */
public class ArraySumProblem {
    public ArrayList<ArrayList<Integer>> findContinuousSequence(int sum) {
        int start = 1;
        int end = 2;
        int curSum = 3;
        //从1-sum遍历，因为是序列,如果累计值太小，增加end，如果累计值太大，增加start
        while(end < sum){
            if(curSum > sum){
                curSum -= start;
                start++;
            }else if(curSum < sum){
                curSum += end;
                end++;
            }else{
                //累加
                curSum -= start;
                curSum += end;
                start++;end++;
            }
        }

        return null;
    }

    public static void main(String[] args) {
        test();
    }
    private static void test(){
        int start = 1;
        int end = 2;
        int curSum = 3;
        curSum -= start;
        end++;
        System.out.println(end);
        System.out.println(curSum);
        curSum += end;
        System.out.println(curSum);
    }
}
