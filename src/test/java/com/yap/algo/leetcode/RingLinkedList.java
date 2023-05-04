package com.yap.algo.leetcode;

import com.yap.algo.leetcode.base.ListNode;

import java.util.HashSet;
import java.util.Set;

public class RingLinkedList {

    public static boolean hasRing(ListNode head){
        Set<ListNode> set = new HashSet<>();
        while (head != null){
            if (!set.add(head)){
                return true;
            }
            head = head.next;
        }
        return false;
    }

    public static boolean hasRing2(ListNode head){
        if (head == null || head.next == null){
            return false;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast){
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        ListNode node6 = new ListNode(6, null);
        ListNode node5 = new ListNode(5, node6);
        ListNode node4 = new ListNode(4, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1, node2);
        //node6.next = node3;

        ListNode head = node1;
        System.out.println(hasRing(head));

        ListNode head2 = node1;
        System.out.println(hasRing2(head2));
    }


}
