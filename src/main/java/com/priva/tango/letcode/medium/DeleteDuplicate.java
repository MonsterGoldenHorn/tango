package com.priva.tango.letcode.medium;


/**
 * 删除所有重复
 */
public class DeleteDuplicate {
    public ListNode deleteDuplication(ListNode pHead) {
        ListNode ret = new ListNode(-1);
        ListNode tail = ret;
        while(pHead != null){
            //只挑不相等的，如果需要进行distinct，放开if判断
            if(pHead.next == null || pHead.val != pHead.next.val){
                tail.next = pHead;
                tail = pHead;
            }
            //如果遇到相等的值，不放入结果链表，将当前链表移动到最后一个相等的值，等待外层统一循环掉
            while (pHead.next != null && pHead.val == pHead.next.val) {
                pHead = pHead.next;
            }
            //循环输入的链表
            pHead = pHead.next;
        }
        //如果最后全是重复的数据，需要剪切掉
        tail.next = null;
        return ret.next;
    }
}
class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}