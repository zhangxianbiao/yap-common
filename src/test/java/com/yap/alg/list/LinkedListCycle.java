package com.yap.alg.list;

import com.yap.alg.base.ListNode;

/**
 * LC 141 环形链表 判断链表是否有环
 * LC 142 环形链表2 返回链表入环的第一个节点
 * NOT UNDERSTAND
 *
 */
public class LinkedListCycle {
    public static boolean hasCycle(ListNode head){
        if (head == null){
            return false;
        }
        ListNode slow = head;
        ListNode fast = head;

        while (fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast){
                return true;
            }
        }
        return false;
    }

    public static ListNode findFistNode(ListNode head){
        if (head == null){
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;

        boolean hasLoop = false;
        while (fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast){
                hasLoop = true;
                break;
            }
        }
        if (hasLoop){
            slow = head;// 核心
            while (slow != fast){
                slow = slow.next;
                fast = fast.next;
            }
            return slow;
        }
        return null;
    }
}
