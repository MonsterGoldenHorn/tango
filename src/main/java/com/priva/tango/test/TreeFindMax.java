package com.priva.tango.test;

import java.util.List;

public class TreeFindMax {
    static int max = Integer.MIN_VALUE;
    static List<Integer> list  = null;
    public static void main(String[] args) {
        Node node = new Node(1);
        find(node);
        for (int i = 1; i < list.size(); i++) {
            if(list.get(i) < list.get(i-1)){
//                return false;
            }
        }
    }

    public static void find(Node node){
        find(node.left);
        list.add(node.val);
        find(node.right);
    }

    public static boolean judge(Node node){
        if(null == node){
            return true;
        }

        return judge(node.left)&&judge(node.right);


    }

    public static void findMax(Node node){
        if(null == node){
            return;
        }
        if(max < node.val){
            max = node.val;
        }
        findMax(node.left);
        findMax(node.right);
    }
}

class Node{
    int val;
    Node left;
    Node right;
    public Node(int val) {
        this.val = val;
    }
}