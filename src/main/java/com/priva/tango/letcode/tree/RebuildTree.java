package com.priva.tango.letcode.tree;

/**
 * 根据前序遍历和中序遍历推断出原树
 */
public class RebuildTree {

    public static void main(String[] args) {
        int[] pre = new int[]{1,2,4,7,3,5,6,8};
        int[] inv = new int[]{4,7,2,1,5,3,8,6};
        TreeNode treeNode = reConstructBinaryTree(pre, inv);
        pre(treeNode);

    }

    public static void pre(TreeNode root){
        if(root == null){
            System.out.println("#");
            return;
        }
        System.out.println(root.val);
        pre(root.left);
        pre(root.right);
    }
    public static TreeNode reConstructBinaryTree(int [] pre,int [] vin) {
        return reConstructBinaryTree(pre, vin, 0, 0, vin.length -1);
    }

    public static TreeNode reConstructBinaryTree(int [] pre,int [] vin,int rootIdex, int start, int end) {
        if(rootIdex > pre.length-1 || start > end){
            return null;
        }
        TreeNode rootNode = new TreeNode(pre[rootIdex]);
        for(int i = start;i <= end; i++){
            int x = vin[i];
            if(x == pre[rootIdex]){
                //找到子树的根节点
                rootNode.left = reConstructBinaryTree(pre, vin, rootIdex + 1, start, i-1);
                rootNode.right = reConstructBinaryTree(pre, vin, rootIdex + (i - start + 1) , i+1, end);
            }
        }
        return rootNode;
    }
}
 class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
 }
