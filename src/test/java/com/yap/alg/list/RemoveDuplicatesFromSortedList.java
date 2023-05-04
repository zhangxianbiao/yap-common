package com.yap.alg.list;

import com.yap.alg.base.ListNode;

/**
 * LC 83 删除排序链表中的重复元素
 * 存在一个升序的链表，删除所有重复的元素，使每个元素只出现一次
 */
public class RemoveDuplicatesFromSortedList {

    public static ListNode deleteDuplicatesIter(ListNode head){
        if (head == null){
            return null;
        }

        ListNode node = head;
        while (node.next != null){
            if (node.next.val == node.val){
                node.next = node.next.next;
            } else{
                node = node.next;
            }
        }
        return node;
    }

    /**
     * 本质是将链表压栈后倒序处理了
     * @param head
     * @return
     */
    public static ListNode deleteDuplicatesRecursion(ListNode head){

        if (head == null || head.next == null){
            return head;
        }
        head.next = deleteDuplicatesRecursion(head.next);
        return head.val == head.next.val ? head.next : head;
    }

    public static void main(String[] args) {

    }
}
