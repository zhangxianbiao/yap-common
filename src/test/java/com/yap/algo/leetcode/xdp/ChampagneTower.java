package com.yap.algo.leetcode.xdp;

/**
 * 香槟塔，把玻璃杯摆成金字塔的形状，第一层1个，第二层2个，第三层3个。。。。最多100层
 * 每个玻璃杯250ml，每次倒入一杯250ml的香槟
 * 从顶层第一个杯子倒香槟，满了之后，会等流量的溢出倒其左右的两个杯子，以此类推，直到底层的杯子满了，留到地上（底层的杯子可能不会同时满）
 * 举例，倒完第四杯后，一二层都满了，三层中间的杯子有一半，左右两边各有1/4
 * 输入非负数的倒入的香槟的杯数，返回第i行，第j个玻璃杯容积的比例，i和j从0开始
 *
 * 当索引从1开始时，能看出上下的关系
 *        1
 *      1   2
 *    1   2   3
 */
public class ChampagneTower {
    public static double champagneTower(int poured, int row, int glassIndex){
        double[][] dp = new double[102][102];
        dp[0][0] = (double)poured;
        for (int r = 0; r <= row; r ++){
            for (int c = 0; c <= r; c++){
                double q = (dp[r][c]-1.0)/2.0;
                if (q > 0){
                    dp[r+1][c] += q;
                    dp[r+1][c+1] += q;
                }
            }
        }
        return Math.min(1, dp[row][glassIndex]);
    }

    public static void main(String[] args) {
        System.out.println(champagneTower(4, 2, 2));
    }


}
