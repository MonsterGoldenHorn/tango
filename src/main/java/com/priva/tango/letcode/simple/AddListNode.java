package com.priva.tango.letcode.simple;

public class AddListNode {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(9);
        l1.next = new ListNode(9);
        l1.next.next = new ListNode(9);

        ListNode l2 = new ListNode(9);
        l2.next = new ListNode(9);
        l2.next.next = new ListNode(9);
        l2.next.next.next = new ListNode(9);
        ListNode listNode = addTwoNumbers(l1, l2);
        System.out.println(listNode.val);
        while (listNode.next != null){
            listNode = listNode.next;
            System.out.println(listNode.val);
        }

    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int remain = 0;
        int start = 0;
        ListNode head = new ListNode();
        ListNode tail = null;
        while(l1 != null || l2 != null){
            int l1v = l1 == null ?0:l1.val;
            int l2v = l2 == null ?0:l2.val;
            start = (l1v+l2v+remain)%10;
            remain = (l1v+l2v+remain)/10;
            if(tail == null){
                head.val = start;
                tail = head;
            }else{
                tail.next = new ListNode(start);
                tail = tail.next;
            }
            if(l1 != null){
                l1 = l1.next;
            }
            if(l2 != null){
                l2 = l2.next;
            }
        }
        if(remain>0){
            tail.next = new ListNode(remain);
        }
        return head;
    }

    private ListNode resolve1(ListNode l1, ListNode l2){
        int begin1 = l1.val;
        int begin2 = l2.val;
        int remain = (begin1+begin2)/10;
        int start = (begin1+begin2)%10;
        ListNode ret = new ListNode(start);
        ListNode curr = ret;
        while(l1.next != null || l2.next != null){
            int l1v = 0;
            int l2v = 0;
            if(l1.next != null){
                l1v = l1.next.val;
            }
            if(l2.next != null){
                l2v = l2.next.val;
            }
            start = (l1v+l2v+remain)%10;
            remain = (l1v+l2v+remain)/10;

            ret.next = new ListNode(start);
            ret = ret.next;
            if(l1.next != null){
                l1 = l1.next;
            }
            if(l2.next != null){
                l2 = l2.next;
            }

        }
        if(remain>0){
            ret.next = new ListNode(remain);
        }
        return curr;
    }
}

class ListNode {
    int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 }