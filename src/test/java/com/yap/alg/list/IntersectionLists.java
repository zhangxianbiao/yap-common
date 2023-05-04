package com.yap.alg.list;

import com.yap.alg.base.ListNode;

/**
 * LC 160 相交链表
 */
public class IntersectionLists {

    public static ListNode getIntersectionNode(ListNode head1, ListNode head2){
        int len1 = 0;
        int len2 = 0;
        int diff = 0;
        ListNode p1 = head1;
        ListNode p2 = head2;
        while (p1 != null){
            p1 = p1.next;
            len1++;
        }
        while (p2 != null){
            p2 = p2.next;
            len2++;
        }

        // 设置p1 长
        if (len1 < len2){
            p1 = head2;
            p2 = head1;
            diff = len2 - len1;
        }else {
            p1 = head1;
            p2 = head2;
            diff = len1 - len2;
        }
        // p1 先跑diff的距离
        for (int i = 0; i< diff; i++){
            p1 = p1.next;
        }

        // 同时开始遍历
        while (p1 != null && p2 != null){
            if (p1 == p2){
                return p1;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        return null;
    }

    public static ListNode getIntersectionNodeOptim(ListNode head1, ListNode head2){
        if (head1 == null || head2 == null){
            return null;
        }

        ListNode p1 = head1;
        ListNode p2 = head2;
        while (p1 != p2){
            p1 = p1 == null ? p2 : p1.next;
            p2 = p2 == null ? p1 : p2.next;
        }
        return p1;
    }

    public static void main(String[] args) {

    }
}
