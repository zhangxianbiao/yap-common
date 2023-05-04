package com.yap.algo.leetcode;


public class ReverseList {

    public static class ListNode {
        int val;
        ListNode next;
        public ListNode(int val, ListNode next){
            this.val = val;
            this.next = next;
        }

        public void printList(){
            ListNode node = this;
            while (node != null){
                System.out.println(node.val);
                node = node.next;
            }
        }
    }

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
        node1.printList();
        System.out.println("=========");

        //ListNode newHead1 = reverseListIteration(node1);
        ListNode newHead1 = reverseListRecursion(node1);
        newHead1.printList();
    }
}


