package com.yap.alg.list;

import com.yap.alg.base.ListNode;

/**
 * LC 876 链表的中间节点
 * 给一个非空的链表的head节点，返回中间节点，如果有两个中间节点，返回第二个中间节点
 */
public class MiddleLinkedList {


    public static ListNode getMiddleNode(ListNode head){
        ListNode slow = head;
        ListNode fast = head;

        /**
         * 推演一下3和4节点的链表，返回的分别是2和3节点（偶数返回第二个）
         */
        while (fast != null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static void main(String[] args) {

    }
}
