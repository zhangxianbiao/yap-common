package com.yap.algo.leetcode;

import java.util.Arrays;

/**
 * 获取n以内的素数，0、1除外
 */
public class NPrimeNum {
    public static boolean isPrime(int num) {
        // 遍历到sqrt(num)即可
        for (int i = 2; i * i <= num; i++){
            if (num % i == 0){
                return false;
            }
        }
        // 2 不进去循环，直接return了
        return true;
    }

    public static int countPrime(int n) {
        int count = 0;
        for (int i = 2; i <= n; i++){
            if (isPrime(i)){
                count ++;
            }
        }
        return count;
    }

    /**
     * 埃式筛选法
     * 素数 * n 必然是合数
     * @return
     */
    public static int countPrimeEratosthenes(int n) {
        boolean[] isPrimeArr = new boolean[n];
        // 初始全部为true，合数标记为false
        // 2就是素数
        // 输入10
        // i = 2 -> j = 4, 6, 8
        // i = 3 -> j = 9
        // i = 4 -> 不进循环
        // i = 5 ->
        // i = 6 -> 不进循环
        // i = 8 -> 不进循环
        // i = 9 -> 不进循环

        Arrays.fill(isPrimeArr, true);
        int count = 0;
        for (int i = 2; i < n; i++){
            if(isPrimeArr[i]){
                count ++;
                for (int j = i * i; j < n; j+=i){
                    // j = 2*i, 优化为i*i
                    // 非素数
                    isPrimeArr[j] = false;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int n = 100;
        int i = countPrime(n);
        int j = countPrimeEratosthenes(n);
        System.out.println("普通方法 " + i);
        System.out.println("埃式筛选法: " + j);
    }
}
