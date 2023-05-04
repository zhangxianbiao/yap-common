package com.yap.algo.leetcode.base;


public class ListNode {

    public int val;
    public ListNode next;
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
