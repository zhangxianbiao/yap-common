package com.yap.alg.dp;

/**
 * HOT
 * LC 121 买卖股票的最佳时机
 * 给定一个数组，数组中是每天的股票价格，在某一天买入，后续的某一天卖出，计算最大的利润
 *
 */
public class BestTimeSellStock {

    public static int maxProfit(int[] prices){

        int length = prices.length;
        if (length < 2){
            return 0;
        }

        // length 行，2列
        int[][] dp = new int[length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        // 从第二天开始遍历
        for (int i =1; i<length; i++){
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i-1][1], -prices[i]);
        }
        return dp[length-1][0];
    }

    public static int maxProfitOpt(int[] prices){
        if (prices.length < 2){
            return 0;
        }
        int maxProfit = 0;
        int min = prices[0];
        for (int i = 0; i<prices.length; i++){
            if (prices[i] > min){
                maxProfit = Math.max(maxProfit, prices[i] - min);
            }else {
                min = prices[i];
            }
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        int[] prices = new int[]{7,1,5,3,6,4};
        System.out.println(maxProfit(prices));
        System.out.println(maxProfitOpt(prices));

    }
}
