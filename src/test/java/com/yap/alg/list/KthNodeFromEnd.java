package com.yap.alg.list;

import com.yap.alg.base.ListNode;

/**
 * LC 剑指offer 获取链表中倒数第k个节点
 */
public class KthNodeFromEnd {

    public static ListNode getKthNodeFromEnd(ListNode head, int k){
        if (k <= 0 || head == null){
            return null;
        }

        ListNode pTemp = head;
        ListNode pKthNode = null;
        // 重要技巧：
        // 先跑k-1，这里是为了兼容正数第一个的情况，比如8个元素模，k=8，如果跑8次， pTemp就是null了
        // pKthNode先赋值是null，下面的while第一次循环，pKthNode不往下走，等于pTemp又跑了一次，此时pKthNode=head
        for (int count = 0; count < k; count++){
            if (pTemp != null){
                pTemp = pTemp.next;
            }
        }
        while (pTemp != null){
            if (pKthNode == null){
                pKthNode = head;
            }else {
                pKthNode = pKthNode.next;
            }
            pTemp = pTemp.next;
        }

        if (pKthNode != null){
            return pKthNode;
        }
        return null;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(6);
        head.setNext(new ListNode(0))
                .setNext(new ListNode(11))
                .setNext(new ListNode(8))
                .setNext(new ListNode(9))
                .setNext(new ListNode(5))
                .setNext(new ListNode(4))
                .setNext(new ListNode(1));

        ListNode.printNode(getKthNodeFromEnd(head, 3));
    }
}
