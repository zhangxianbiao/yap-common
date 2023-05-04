package com.yap.alg.list;

import com.yap.alg.base.ListNode;

/**
 * LC 234 回文链表
 */
public class PalindromeLinkedList {

    public static boolean isPalindrome(ListNode head){
        ListNode fast = head;
        ListNode slow = head;

        // 快慢指针找到中心节点
        // 注意看下奇偶
        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }

        if (fast != null){
            //说明链表有奇数个节点，把正中心的划到左边,统一进行计算,
            //这里的slow是右边界，再移动一位，下面的whle循环判断slow即可，slow比较短
            slow = slow.next;
        }

        slow = reverseNode(slow);
        fast = head;
        while (slow != null){
            if (fast.val != slow.val){
                return false;
            }
            fast = fast.next;
            slow = slow.next;
        }
        return true;
    }

    /**
     * 翻转链表
     * @param head
     * @return
     */
    public static ListNode reverseNode(ListNode head){
        ListNode prev = null;
        while (head != null){
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    public static void main(String[] args) {


    }

}
