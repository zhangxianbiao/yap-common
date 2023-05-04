package com.yap.algo.leetcode.binarytree;

import com.yap.algo.leetcode.base.TreeNode;

import java.util.*;

public class TreeTraverse {

    /*******************************递归**************************************/

    /**
     *
     * @param node
     */
    public static void recursion(TreeNode node){
        if (node == null){
            return;
        }

        // 前序
        System.out.print(node.val + " ");

        recursion(node.left);

        //中序
        //System.out.print(node.val + " ");

        recursion(node.right);

        // 后序
        //System.out.print(node.val + " ");
    }

    /**
     *  /**
     *      1
     *   2    3
     * 4  5  6
     *      7
     * 层序递归遍历
     * 第一层的下标从1开始，假设父点下标是i，左子点下标是2i，右子点下标2i+1
     * @param node
     */
    public static void recursionLevel(TreeNode node, int i, List list){
        if (node == null){
            return;
        }
        // list.set可能会报数组越界
        // 需要补齐list的length-1 到 i的下标的位置补上，否则会报：Index 1 out-of-bounds for length 0
        int length = list.size();
        if (length <= i){
            // 从length开始，以为第0未是null，被占用，树的第一个元素是1开始的
            for (int j = length; j <= i; j++) {
                list.add(j, null);
            }
        }
        list.set(i, node.val);
        recursionLevel(node.left, 2*i, list);
        recursionLevel(node.right, 2*i + 1, list);
    }

    /*******************************迭代**************************************/
    /**
     * 根左右
     * 使用栈,使用Deque，stack已经废弃
     * @param root
     */
    public static void iteratorPreOrder(TreeNode root){
        if (root == null){
            return;
        }
        Deque<TreeNode> stack = new LinkedList<>();

        stack.push(root);
        while (!stack.isEmpty()){
            // 取出
            TreeNode node = stack.pop();
            if (node != null){
                System.out.print(node.val + " ");

                stack.push(node.right);

                // 后放左，先出左
                stack.push(node.left);
            }
        }
    }

    /**
     * 左根右
     * @param root
     */
    public static void iteratorMidOrder(TreeNode root){
        TreeNode node = root;

        if (node == null){
            return;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        while (!stack.isEmpty() || node != null){
            if (node != null){
                stack.push(node);
                // 一直找左节点
                node = node.left;
            }else {
                node = stack.pop();
                System.out.print(node.val + " ");
                node = node.right;
            }
        }
    }

    /**
     * 左右根
     * @param root
     */
    public static void iteratorPostOrder(TreeNode root){
        TreeNode node = root;
        if (node == null){
            return;
        }
        Deque<TreeNode> stack = new LinkedList<>();

        // 记录上次打印的
        TreeNode pre = null;
        while (!stack.isEmpty() || node != null){
            // 把左节点入栈
            while (node != null){
                stack.push(node);
                node = node.left;
            }
            // 出最左侧的节点
            node = stack.pop();

            if (node.right == null || node.right == pre){
                System.out.print(node.val + " ");
                pre = node;
                // 需要改为null,避免进去循环
                node = null;
            }else {
                stack.push(node);
                node = node.right;
            }
        }
    }

    /**
     * 使用队列
     * @param root
     */
    public static void iteratorLevelOrder(TreeNode root){
        TreeNode node = root;
        if (node == null){
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(node);
        while (!queue.isEmpty()){
            node = queue.poll();
            if (node != null){
                System.out.print(node.val + " ");
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }
    }


    /**
     * morris遍历，线索二叉树
     * 时间复杂度O(N)
     * 空间复杂度O(1) !!
     * @param root
     */
    public static void morrisPreOrder(TreeNode root){
        TreeNode cur = root;
        if (cur == null){
            return;
        }
        TreeNode mostRight = null;
        while (cur != null){
            //cur 表示当前节点，mostRight表示cur的左孩子的最右节点
            mostRight = cur.left;
            if (mostRight != null) {
                //cur 有左孩子，找到cur左子树最右节点
                while (mostRight.right != null && mostRight.right != cur){
                    mostRight = mostRight.right;
                }
                // mostRight的右孩子指向空，让其指向cur，cur向左移动
                if (mostRight.right == null){
                    mostRight.right = cur;
                    System.out.print(cur.val + " ");
                    cur = cur.left;
                    continue;
                }else {
                    //mostRight的右孩子指向cur，让其指向空，cur向右移动
                    mostRight.right = null;
                }
            }else {
                System.out.print(cur.val + " ");
            }
            cur = cur.right;
        }
    }

    /**
     *
     * @param root
     */
    public static void morrisMidOrder(TreeNode root){
        TreeNode cur = root;
        if (cur == null){
            return;
        }
        TreeNode mostRight = null;
        while (cur != null){
            //cur 表示当前节点，mostRight表示cur的左孩子的最右节点
            mostRight = cur.left;
            if (mostRight != null) {
                //cur 有左孩子，找到cur左子树最右节点
                while (mostRight.right != null && mostRight.right != cur){
                    mostRight = mostRight.right;
                }
                // mostRight的右孩子指向空，让其指向cur，cur向左移动
                if (mostRight.right == null){
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }else {
                    //mostRight的右孩子指向cur，让其指向空，cur向右移动
                    mostRight.right = null;
                }
            }
            System.out.print(cur.val + " ");
            cur = cur.right;
        }
    }

    /**
     *
     * @param root
     */
    public static void morrisPostOrder(TreeNode root){
        TreeNode cur = root;
        if (cur == null){
            return;
        }
        TreeNode head = cur;
        TreeNode mostRight = null;
        while (cur != null){
            //cur 表示当前节点，mostRight表示cur的左孩子的最右节点
            mostRight = cur.left;
            if (mostRight != null) {
                //cur 有左孩子，找到cur左子树最右节点
                while (mostRight.right != null && mostRight.right != cur){
                    mostRight = mostRight.right;
                }
                // mostRight的右孩子指向空，让其指向cur，cur向左移动
                if (mostRight.right == null){
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }else {
                    //mostRight的右孩子指向cur，让其指向空，cur向右移动
                    mostRight.right = null;
                    printEdge(cur.left);
                }
            }
            cur = cur.right;
        }
        printEdge(head);
    }

    public static void printEdge(TreeNode head){
        TreeNode tail = reverseEdge(head);
        TreeNode cur = tail;
        while (cur != null){
            System.out.print(cur.val + " ");
            cur = cur.right;
        }
        reverseEdge(tail);
    }

    public static TreeNode reverseEdge(TreeNode from){
        TreeNode pre = null;
        TreeNode next = null;
        while (from != null){
            next = from.right;
            from.right = pre;
            pre = from;
            from = next;
        }
        return pre;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        /*
         *      1
         *   2    3
         * 4  5  6
         *      7
         */
        /*
        TreeNode node7 = new TreeNode(7, null, null);
        TreeNode node6 = new TreeNode(6, node7, null);
        TreeNode node5 = new TreeNode(5, null, null);
        TreeNode node4 = new TreeNode(4, null, null);
        TreeNode node3 = new TreeNode(3,  node6, null);
        TreeNode node2 = new TreeNode(2, node4, node5);
        TreeNode node1 = new TreeNode(1, node2, node3);
         */

        /*
         *      1
         *   2    3
         * 4  5
         *   6 7
         */
        TreeNode node7 = new TreeNode(7, null, null);
        TreeNode node6 = new TreeNode(6, null, null);
        TreeNode node5 = new TreeNode(5, node6, node7);
        TreeNode node4 = new TreeNode(4, null, null);
        TreeNode node3 = new TreeNode(3,  null, null);
        TreeNode node2 = new TreeNode(2, node4, node5);
        TreeNode node1 = new TreeNode(1, node2, node3);

        // 递归
        System.out.println("==========递归===========");
        recursion(node1);
        System.out.println();
        System.out.println();


        // 层序递归
        List<Integer> list = new ArrayList<>();
        recursionLevel(node1, 1, list);
        System.out.println("=========递归-层序============");
        System.out.println(list);
        System.out.println();


        // 迭代-前序
        System.out.println("=========迭代-前序============");
        iteratorPreOrder(node1);
        System.out.println();
        System.out.println();


        // 迭代-中序
        System.out.println("=========迭代-中序============");
        iteratorMidOrder(node1);
        System.out.println();
        System.out.println();


        // 迭代-后序
        System.out.println("=========迭代-后序============");
        iteratorPostOrder(node1);
        System.out.println();
        System.out.println();


        // 迭代-层序
        System.out.println("=========迭代-层序============");
        iteratorLevelOrder(node1);
        System.out.println();
        System.out.println();


        // 迭代-morris前序
        System.out.println("=========morris前序============");
        morrisPreOrder(node1);
        System.out.println();
        System.out.println();


        // 迭代-morris中序
        System.out.println("=========morris中序============");
        morrisMidOrder(node1);
        System.out.println();
        System.out.println();


        // 迭代-morris后序
        System.out.println("=========morris后序============");
        morrisPostOrder(node1);
        System.out.println();
        System.out.println();
    }
}
