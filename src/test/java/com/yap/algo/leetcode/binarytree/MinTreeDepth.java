package com.yap.algo.leetcode.binarytree;

import com.yap.algo.leetcode.base.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class MinTreeDepth {
    /**
     * 递归
     * 时间 O(logN), 取决于树的高度
     * 空间 O(N)
     * @param node
     * @return
     */
    public static int minDepthDfs(TreeNode node){
        if (node == null){
            return 0;
        }

        if (node.left == null && node.right == null){
            return 1;
        }

        int min = Integer.MAX_VALUE;
        if (node.left != null){
            min = Math.min(minDepthDfs(node.left), min);
        }
        if (node.right != null){
            min = Math.min(minDepthDfs(node.right), min);
        }
        return min + 1;
    }

    public static class QueueNode{
        TreeNode node;
        int depth;

        public QueueNode(TreeNode node, int depth){
            this.node = node;
            this.depth = depth;
        }
    }

    /**
     * 引入新的类，包装一下TreeNode，记录当前节点的depth，之前怎么没有想到呢？
     * 时间 O(N)
     * 空间 O(N)
     * @param root
     * @return
     */
    public static int minDepthBfd(TreeNode root){
        if (root == null){
            return 0;
        }

        Queue<QueueNode> queue = new LinkedList<>();

        queue.offer(new QueueNode(root, 1));

        while (!queue.isEmpty()){
            QueueNode queueNode = queue.poll();
            TreeNode treeNode = queueNode.node;

            int depth = queueNode.depth;

            if (treeNode.left == null && treeNode.right == null){
                return depth;
            }

            if (treeNode.left != null){
                queue.offer(new QueueNode(treeNode.left, depth + 1));
            }
            if (treeNode.right != null){
                queue.offer(new QueueNode(treeNode.right, depth + 1));
            }
        }
        return 0;
    }


    /**
     *      1
     *   2    3
     * 4  5  6
     *      7
     * @param args
     */
    public static void main(String[] args) {
        TreeNode node7 = new TreeNode(7, null, null);
        TreeNode node6 = new TreeNode(6, node7, null);
        TreeNode node5 = new TreeNode(5, null, null);
        TreeNode node4 = new TreeNode(4, null, null);
        TreeNode node3 = new TreeNode(3,  node6, null);
        TreeNode node2 = new TreeNode(2, node4, node5);
        TreeNode node1 = new TreeNode(1, node2, node3);
        int i = minDepthDfs(node1);
        System.out.println(i);

        i = minDepthBfd(node1);
        System.out.println(i);

    }
}
