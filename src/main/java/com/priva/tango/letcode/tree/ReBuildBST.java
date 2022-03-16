package com.priva.tango.letcode.tree;

/**
 * 二叉搜索树的后序遍历序列
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。如果是则返回 true ,否则返回 false 。假设输入的数组的任意两个数字都互不相同。
 *
 * 数据范围： 节点数量 0 ≤ n ≤ 1000 ，节点上的值满足  1 ≤ val ≤ 10^5
 *   ，保证节点上的值各不相同
 * 要求：空间复杂度 O(n) ，时间时间复杂度 O(n^2)
 * 提示：
 * 1.二叉搜索树是指父亲节点大于左孩子节点，但是小于右孩子节点。
 * 2.该题我们约定空树不是二叉搜索树
 * 3.后序遍历是指按照 “左子树-右子树-根节点” 的顺序遍历
 * 4.参考下面的二叉搜索树
 * [1,3,2]
 * true
 *
 * [3,1,2]
 * false
 */
public class ReBuildBST {
    public static void main(String[] args) {
        int[] pre = new int[]{5,4,3,2,1};
//        int[] pre = new int[]{5,7,6,9,11,10,8};
        System.out.println(VerifySquenceOfBST(pre));
    }
    //BST 树是数据库存储的一种方式，左小于根，根小于右，左小于右
    //如果是后续遍历，先右
    public static boolean VerifySquenceOfBST(int [] sequence) {
        int length = sequence.length;
        if(length == 0){
            return true;
        }
        return isBST(sequence, 0, length - 1);
    }

    public static boolean isBST(int [] sequence, int begin, int end) {
        if(begin > end){
            return true;
        }
        for(int i = begin; i < end; i++){
            //限定不相同，遇到大于的就分组
            if(sequence[i] > sequence[end]){
                int temp = i;
                while(i < end - 1){
                    i++;
                    if(sequence[i] < sequence[end]){
                        return false;
                    }
                }
                return isBST(sequence, begin, temp-1) && isBST(sequence, temp, end-1);
            }
        }
        return true;
    }

}
