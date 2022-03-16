package com.priva.tango.letcode;

import java.util.ArrayList;
import java.util.List;

//遍历树
public class TreeScan {
    /**
     * 前序遍历
     * 根节点开始，一直向左
     *
     * 中序遍历
     * 找到最左-》父-》兄弟左
     *
     * 后序遍历
     *
     * 《》前中后指的是父节点的遍历时机，子节点都是从左到右
     *
     * 层序
     */
    public static void main(String[] args) {
        List<TreeNode> list = new ArrayList<>();

    }
    List<TreeNode> ret = new ArrayList<>();
    //1.前序
    private void preOrder(TreeNode root){
        if(root != null){
            ret.add(root);
            //左
            preOrder(root.left);
            //右
            preOrder(root.right);
        }
    }
    //中序
    private void midOrder(TreeNode root){
        if(root != null){
            //左
            midOrder(root.left);
            ret.add(root);
            //右
            preOrder(root.right);
        }
    }
    //后序
    private void lastOrder(TreeNode root){
        if(root != null){
            //左
            midOrder(root.left);
            //右
            preOrder(root.right);
            ret.add(root);
        }
    }

}
class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }
}
