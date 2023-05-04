package com.yap.alg.list;


import com.yap.alg.base.ListNode;

public class ReverseList {

    public static ListNode reverseListIteration(ListNode head) {
        ListNode cur = head;
        ListNode pre = null;
        ListNode next = null;
        
        while (cur != null) {
            next = cur.next;

            cur.next = pre;

            pre = cur;
            cur = next;
        }
        return pre;
    }

    public static ListNode reverseListRecursion(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        ListNode newHead = reverseListRecursion(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }


    public static void main(String[] args) {
        ListNode node5 = new ListNode(5, null);
        ListNode node4 = new ListNode(4, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1, node2);
        ListNode.printList(node1);
        System.out.println("=========");

        //ListNode newHead1 = reverseListIteration(node1);
        ListNode newHead1 = reverseListRecursion(node1);
        ListNode.printList(newHead1);
    }
}


