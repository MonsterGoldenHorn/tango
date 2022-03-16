package com.priva.tango.letcode.medium;

/**
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 */
public class MaxChildStringMain {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
    }
    public static int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        int max = 0;
        //算法题不要用高级语言的集合
        for (int i = 0; i < s.length(); i++) {
            StringBuilder sb = new StringBuilder();
            int n = 0;
            for (int k = i; k < s.length(); k++) {
                char si = s.charAt(k);
                String s1 = String.valueOf(si);
                if(sb.toString().contains(s1)){

                    break;
                }else{
                    sb.append(si);
                }
                n++;
                if(n > max){
                    max = n;
                }
            }
         }
        return max;
    }
}
