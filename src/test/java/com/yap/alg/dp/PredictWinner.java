package com.yap.alg.dp;


/**
 * NOT UNDERSTAND
 * 给一个非负的正数数组，玩家1从数组的任意一端取一个数，随后玩家2从剩余的数组的任意一端取一个数
 * 顺序取数，直至取完，获得的数字的和最大获胜，预测玩家1（先手）能否获胜，假定两个玩家都足够聪明
 */
public class PredictWinner {

    /**
     * 方法一，递归，只关注当前的情况，剩下的交给递归
     * @param nums
     * @param left
     * @param right
     * @return
     */
    public static int maxScore(int[] nums, int left, int right){
        if (left == right){
            // 剩下一个，只能取这个数
            return left;
        }
        int selectLeftScore = 0;
        int selectRightScore = nums.length - 1;
        
        if (right - left >= 2){
            // 剩下大于两个数字，先手选择使自己得分最高的一边，后手则选择使对手得分最低的一边

            // 先手选择left的得分 = 先手选择left + 后手做选择后的最低分
            // 先手选择left后，后手可以选择right，此时
            selectLeftScore = nums[left] +
                    Math.min(maxScore(nums,left + 2, right),
                            maxScore(nums, left + 1, right -1));

            // 先手选择right
            selectRightScore = nums[right] +
                    Math.min(maxScore(nums, left + 1, right - 1),
                            maxScore(nums, left, right - 2));
        }
        if ((right - left) == 1){
            selectLeftScore = nums[left];
            selectRightScore = nums[right];
        }
        return Math.max(selectLeftScore,selectRightScore);
    }

    /**
     * 提取重复运算，节省一次递归
     * @param nums
     * @param left
     * @param right
     * @return
     */
    public static int maxScore2(int[] nums, int left, int right){
        if (left == right){
            // 剩下一个，只能取这个数
            return left;
        }
        int selectLeftScore = 0;
        int selectRightScore = nums.length - 1;

        if (right - left >= 2){
            // 剩下大于两个数字，先手选择使自己得分最高的一边，后手则选择使对手得分最低的一边

            int score = maxScore(nums,left + 1, right - 1);
            // 先手选择left的得分
            selectLeftScore = nums[left] +
                    Math.min(maxScore(nums,left + 2, right),
                            score);

            // 先手选择right
            selectRightScore = nums[right] +
                    Math.min(score,
                            maxScore(nums, left, right - 2));
        }
        if ((right - left) == 1){
            selectLeftScore = nums[left];
            selectRightScore = nums[right];
        }
        return Math.max(selectLeftScore, selectRightScore);
    }

    /**
     * 方法二，只计算一个的（差值计算）
     * @param nums
     * @return
     */
    public boolean predictWinner(int[] nums){
        int sum = 0;
        // 计算总分
        for (int num : nums){
            sum += num;
        }
        // 只计算玩家1的分数
        int player1 = getScore(nums, 0, nums.length - 1);
        int player2 = sum - player1;
        // 两个玩家分数相等，玩家1仍旧是赢家？
        return player1 >= player2;
    }

    public static int getScore(int[] nums, int start, int end){
        if (start == end){
            return nums[start];
        }
        // 选择左边后，
        int selectLeft = nums[start] - getScore(nums, start + 1, end);
        int selectRight = nums[end] - getScore(nums, start, end - 1);
        return Math.max(selectLeft, selectRight);
    }

    public static boolean predictWinnerDp(int[] nums){
        int length= nums.length;
        int[][] dp = new int[length][length];

        for (int i = 0; i < length; i++){
            dp[i][i] = nums[i];
        }

        // 简化为一维时
        int[] dpNew = new int[length];
        for (int i = 0; i < length; i++){
            dpNew[i] = nums[i];
        }

        for (int i = length - 2; i >= 0;i--){
            for (int j = i+1; j<length; j++){

                dp[i][j] = Math.max(nums[i] - dp[i+1][j], nums[j] - dp[i][j-1]);
                // j = j + 1,可以优化为一维数组，下标相同才有值，据此推导其他的值
                // Math.max(nums[i] - dp.txt[j][j], nums[j] - dp.txt[j-1][j-1]);
                dpNew[j] = Math.max(nums[i] -dpNew[j], nums[j] - dpNew[j-1]);
            }
        }
        return dp[0][length-1] >= 0;
        //return dpNew[length-1] >= 0;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{5, 200, 2, 3};// 必赢
        System.out.println(maxScore(arr, 0, arr.length-1));// 得203分
        System.out.println(predictWinnerDp(arr));// true

        arr = new int[]{5, 200, 2, 3, 5};// 必输
        System.out.println(maxScore(arr, 0, arr.length-1));// 最多得12分
        System.out.println(predictWinnerDp(arr));// false

    }

}












