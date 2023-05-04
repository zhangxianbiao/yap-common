package com.yap.alg.base;


public class ListNode {

    public int val;
    public ListNode next;
    public ListNode(int val, ListNode next){
        this.val = val;
        this.next = next;
    }

    public ListNode(int val){
        this.val = val;
        this.next = null;
    }

    public ListNode setNext(ListNode next){
        this.next = next;
        return next;
    }

    public static void printList(ListNode node){
        while (node != null){
            System.out.println(node.val);
            node = node.next;
        }
    }

    public static void printNode(ListNode node){
        if (node != null){
            System.out.println(node.val);
            return;
        }
        System.out.println("empty");
    }
}
