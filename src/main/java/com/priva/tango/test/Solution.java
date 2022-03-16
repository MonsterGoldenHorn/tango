package com.priva.tango.test;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        System.out.println(getMaxValue(new int[]{}, new int[]{}));
        List l = new ArrayList();
        l.stream().distinct();
    }

    public int[] tranArr (int[] arrA, int K) {
        // write code here
        return new int[]{};
    }













    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * 
     * @param nums int整型一维数组 
     * @param values int整型一维数组 
     * @return int整型
     */
    public static int getMaxValue (int[] nums, int[] values) {
        // write code here
        int left = 0;
        int right = nums.length - 1;
        return dp(nums, values, left, right, 0, 0);
//        return Math.max(dp(nums, values, 0, right - 1, 0, values[0]*nums[right]),
//                dp(nums, values, 1, right, 0, values[0]*nums[0]));
    }

    private static int dp(int[] nums, int[] values, int left, int right, int index, int sum){
        if(left>right){
            return sum;
        }
        if(left==right){
            return sum + nums[left]*values[index];
        }
        return Math.max(dp(nums, values, left + 1, right, index + 1, sum + values[index]*nums[left]),
                dp(nums, values, left, right - 1, index + 1, sum + values[index]*nums[right]));
    }
}