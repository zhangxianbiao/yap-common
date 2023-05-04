package com.yap.alg;

import org.testng.annotations.Test;

import java.util.Arrays;

/**
 *
 * 斐波那契数列，又称黄金分割数列，指的是这样一个数列：0、1、1、2、3、5、8、13、21、34、55
 * 在数学上，斐波纳契数列以如下被以递归的方法定义：F（0）=0，F（1）=1，F（n）=F(n-1)+F(n-2)（n≥2，n∈N*）
 */
public class Fibonacci {

    @Test
    public void test() {
        int n = 10;
        final int fiboWithRecursion = getFiboWithRecursion(n);
        System.out.println("recursion: " + fiboWithRecursion);

        final int fiboWithVar = getFiboWithVar(n);
        System.out.println("var: "+ fiboWithVar);

        final int[] fiboArr = getFiboArr(n);
        System.out.println("arr: " + Arrays.toString(fiboArr));
    }

    /**
     * 从 0 开始
     * 复杂度高，涉及重复计算
     * @param n 参数
     * @return int
     */
    public int getFiboWithRecursion(int n) {
        if (n < 0) {
            return -1;
        }
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }

        return getFiboWithRecursion(n-1) + getFiboWithRecursion(n - 2);
    }

    /**
     * 定义了3个变量a、b、c其中 c=a+b，然后逐步进行计算从而得到下标为n的值
     * @param n
     * @return
     */
    public int getFiboWithVar(int n) {
        if (n < 0) {
            return -1;
        }
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }

        int a = 0, b = 1, c = 0;
        for (int i = 2; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }

    /**
     * 将每一步的结果都存起来
     * @param n
     * @return
     */
    public int[] getFiboArr(int n) {
        int[] fibo;
        if (n < 0){
            return null;
        }else {
            fibo = new int[n + 1];
        }

        if (n == 0){
            fibo[0] = 0;
        } else if(n == 1) {
            fibo[0] = 0;
            fibo[1] = 1;
        } else {
            fibo[0] = 0;
            fibo[1] = 1;
            for (int i = 2; i <= n; i++) {
                fibo[i] = fibo[i-1] + fibo[i-2];
            }
        }
        return fibo;
    }
}
