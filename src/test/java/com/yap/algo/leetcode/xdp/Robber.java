package com.yap.algo.leetcode.xdp;


import com.yap.algo.leetcode.base.TreeNode;

/**
 *
 * 给定一个非负的整数数组表示连续房间的金额，不能偷连续的两个房间，如何最大化金额
 */
public class Robber {

    public static int maxMoneyRecursion(int[] nums, int index){
        if(nums == null || index < 0){
            return 0;
        }
        if (index == 0){
            return nums[0];
        }

        // 倒着计算
        return Math.max(
                // 偷当前的房间
                maxMoneyRecursion(nums, index - 2) + nums[index],
                // 不偷当前的房间
                maxMoneyRecursion(nums, index - 1));
    }

    public static int maxMoneyDp(int[] nums){
        if(nums == null || nums.length == 0){
            return 0;
        }

        int length = nums.length;
        if (length == 1){
            return nums[0];
        }

        int[] dp = new int[length];
        dp[0] = nums[0];
        // 两个房间只能选一个
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i =2; i<length; i++){
            dp[i] = Math.max(dp[i-2] + nums[i], dp[i-1]);
        }
        return dp[length-1];
    }

    public static int maxMoneyDp2(int[] nums){
        if(nums == null || nums.length == 0){
            return 0;
        }

        int length = nums.length;
        if (length == 1){
            return nums[0];
        }

        int first =  nums[0];
        // 两个房间只能选一个
        int second = Math.max(nums[0], nums[1]);

        for (int i =2; i<length; i++){
            int temp = second;
            second = Math.max(first + nums[i], second);
            first = temp;
        }
        return second;
    }

    /**
     * 房子收尾相连的情况
     * @param nums
     * @return
     */
    public static int robWithConnect(int[] nums){
        int length = nums.length;
        if (length == 1){
            return nums[0];
        }else if (length == 2){
            return Math.max(nums[0], nums[1]);
        }

        return Math.max(
                //如果抢第0个，那么最后一个不可以抢，范围为0，length - 2
                robRangeWithConnect(nums, 0, length - 2),
                //如果不抢第0个，那么最后一个可以抢，范围为1，length - 1
                robRangeWithConnect(nums, 1, length - 1));
    }

    public static int robRangeWithConnect(int[] nums, int start, int end){
        int first = nums[start];
        int second = Math.max(nums[start], nums[start + 1]);
        for (int i = start + 2; i<=end; i++){
            int temp = second;
            second = Math.max(first + nums[i], second);
            first = temp;
        }
        return second;
    }

    /**
     * 房屋的链接方式是二叉树的形式
     * @param root
     * @return
     */
    public static int robWithTreeNode(TreeNode root){
        int[] rootStatus = robDfs(root);
        return Math.max(rootStatus[0], rootStatus[1]);
    }

    /**
     * 定义返回为一个两个元素的数组，第0位置表示抢当前节点的金额， 第1位表示不抢当前节点的金额
     * @param node
     * @return
     */
    public static int[] robDfs(TreeNode node){
        if (node == null){
            return new int[]{0,0};
        }
        int[] l = robDfs(node.left);
        int[] r = robDfs(node.right);
        // 当前节点抢的化，左右节点都不能抢
        int selected = node.val + l[1] + r[1];
        // 不抢当前节点，将抢与不抢左右节点的最大值 金额加起来
        int notSelect = Math.max(l[0], l[1]) + Math.max(r[0], r[1]);
        return new int[]{selected, notSelect};
    }


    public static void main(String[] args) {

        int[] arr1 = new int[]{1,2,3,1};//4
        int[] arr2 = new int[]{2,7,9,3,1};//12

        System.out.println(maxMoneyRecursion(arr1, arr1.length - 1));
        System.out.println(maxMoneyRecursion(arr2, arr2.length - 1));

        System.out.println(maxMoneyDp2(arr1));
        System.out.println(maxMoneyDp2(arr2));

    }
}
