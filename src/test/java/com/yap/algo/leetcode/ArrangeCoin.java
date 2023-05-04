package com.yap.algo.leetcode;


/**
 * 给定数字n，计算可以叠几层硬币
 * o
 * o o
 * o o o
 * o o o o
 * o o o o o
 * n = 1 + 2 + 3 + 4 + ... + x层
 * n = (1+x)x/2
 */
public class ArrangeCoin {

    /**
     * 穷举
     * @param n
     * @return
     */
    public static int arrangeCoin1(int n){
        for (int x = 1; x <= n; x++){
            n = n - x;
            if (n <= x){
                return x;
            }
        }
        return -1;
    }

    /**
     * 二分
     * @param n
     * @return
     */
    public static int arrangeCoin2(int n){
        int low = 0;
        int high = n;
        while(low <= high){
            int mid = low + (high - low) / 2;
            int cost = ((mid + 1)*mid)/2;

            if (cost == n){
                return mid;
            }else if (cost < n){
                low = mid + 1;
            }else if (cost > n){
                high = mid - 1;
            }
        }
        // 因为退出条件是low <= high，退出时，high更小
        return high;
    }

    /**
     * 牛顿迭代？
     * @param n
     * @return
     */
    public static int arrangeCoin3(int n){


        return 0;
    }

    public static void main(String[] args) {
        System.out.println(arrangeCoin1(11));
        System.out.println(arrangeCoin2(11));

    }
}
