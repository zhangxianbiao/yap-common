package com.yap.alg;

import org.testng.annotations.Test;

/**
 * 小青蛙跳台阶
 * 青蛙每一次可以跳1阶或2阶，假设有n阶台阶，青蛙要跳完所有台阶，有多少种跳法？
 * 可以假设 f(0)=1 。
 * ————————————————
 * 参考斐波那契数列，唯一的区别就是起始值不一样。
 */
public class FrogStep {

    @Test
    public void test() {
        int n = 10;
        final int frogStep = getFrogStep(n);
        System.out.println(frogStep);

        final int frogStepWays = getFrogStepWays(n);
        System.out.println(frogStepWays);
    }

    /**
     * f(0)=0
     * @param n
     * @return
     */
    public int getFrogStep(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }

        int a = 1, b = 2, c = 0;
        for (int i = 3; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }

    /**
     * f(0)=1 的情况下，这种感觉不合理，不过leetcode的答案就是这么定的
     * @param n
     * @return
     */
    public int getFrogStepWith0(int n) {
        if (n <= 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }

        // 第0个a=1, 第一个b=1
        int a = 1, b = 1, c = 0;
        for (int i = 2; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }


    /**
     * 一、大数相乘，大数的排列组合等为什么要取模
     * 1000000007是一个质数（素数），对质数取余能最大程度避免结果冲突/重复
     * int32位的最大值为2147483647，所以对于int32位来说1000000007足够大。
     * int64位的最大值为2^63-1，用最大值模1000000007的结果求平方，不会在int64中溢出。
     * 所以在大数相乘问题中，因为(a∗b)%c=((a%c)∗(b%c))%c，所以相乘时两边都对1000000007取模，再保存在int64里面不会溢出。
     *
     * 二、这道题为什么要取模，取模前后的值不就变了吗？
     *   取模前 f(43) = 701408733, f(44) = 1134903170, f(45) = 1836311903, 但是 f(46) > 2147483647结果就溢出了。
     *   取模后 f(43) = 701408733, f(44) = 134903163 , f(45) = 836311896, f(46) = 971215059没有溢出。
     * 取模之后能够计算更多的情况，如 f(46)
     * 这道题的测试答案与取模后的结果一致。
     *
     * 三、总结一下，这道题要模1000000007的根本原因是标准答案模了1000000007。
     *   不过大数情况下为了防止溢出，模1000000007是通用做法，原因见第一点。
     * @param n
     * @return
     */
    public int getFrogStepWays(int n) {
        if (n <= 0) {
            return 0;
        }

        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }

        int a = 1;// 一个台阶 n=1
        int b = 2;// 两个台阶 n=2
        int sum = 0;

        // return a 可以使得循环从0开始？？，多计算了一次啊！！！！
        /*
        for (int i = 0; i < n - 1; i++) {
            sum = (a + b) % 1000000007;
            a = b;
            b = sum;
        }

        return a;
         */
        for (int i = 3; i <= n; i++) {
            sum = (a + b) % 1000000007;
            a = b;
            b = sum;
        }
        return sum;
    }

}
