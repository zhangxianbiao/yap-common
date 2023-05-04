package com.yap.alg.list;

import com.yap.alg.base.ListNode;

/**
 * LC 21 合并两个有序的链表
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 */
public class MergeTwoSortedList {

    public static ListNode mergeTwoListsIter(ListNode node1, ListNode node2){
        if (node1 == null){
            return node2;
        }
        if (node2 == null){
            return node1;
        }

        ListNode resultNode = new ListNode(0, null);
        ListNode p = resultNode;
        while (node1 != null && node2 != null){
            if (node1.val < node2.val){
                p.next = node1;
                node1 = node1.next;
            }else {
                p.next = node2;
                node2 = node2.next;
            }
            p = p.next;
        }

        if (node1 != null){
            p.next = node1;
        }
        if (node2 != null){
            p.next = node2;
        }
        return resultNode.next;
    }

    public static ListNode mergeTwoListsRecursion(ListNode node1, ListNode node2){
        if (node1 == null){
            return node2;
        }
        if (node2 == null){
            return node1;
        }

        if (node1.val < node2.val){
            node1.next = mergeTwoListsRecursion(node1.next, node2);
            return node1;
        }
        node2.next = mergeTwoListsRecursion(node1, node2.next);
        return node2;
    }
    public static void main(String[] args) {

        ListNode node1 = new ListNode(1,
                new ListNode(3,
                        new ListNode(5, null)));

        ListNode node2 = new ListNode(2,
                new ListNode(4,
                        new ListNode(6, new ListNode(9, null))));


        //ListNode node = mergeTwoListsIter(node1, node2);
        ListNode node = mergeTwoListsRecursion(node1, node2);

        ListNode.printList(node1);
        System.out.println("=========");
    }
}
