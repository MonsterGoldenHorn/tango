package com.priva.tango.letcode.simple;

/**
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 *
 * 你可以按任意顺序返回答案。
 *输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 */
public class TwoNumberSum {
    public static void main(String[] args) {
        int[] nums = new int[]{2,7,11,15};
        int target = 22;
        int[] ints = twoSum(nums, target);
        System.out.println(ints[0]+"---"+ints[1]);

    }

    public static int[] twoSum(int[] nums, int target){
        int[] ret = new int[2];
        for (int i = 0; i < nums.length; i++) {
            int num1 = nums[i];
            for (int j = i+1; j < nums.length; j++) {
                int num2 = nums[j];
                if(num1+num2 == target){
                    ret[0] = i;
                    ret[1] = j;
                    return ret;
                }
            }
        }
        return null;
    }
}
