package com.yap.algo.leetcode.bit;

import java.util.Arrays;

public class BitAlg {

    public static void checkJiOu(){
        //&1=0是偶数
        System.out.println((4&1) == 0);
        System.out.println((5&1) == 0);
    }

    public static void swap(int a, int b){

        int temp = a;
        a = b;
        b = temp;
        System.out.println("a=" + a + ", b=" + b);


        a = a + b;
        b = a - b;
        a = a - b;
        System.out.println("a=" + a + ", b=" + b);

        a = a^b;
        b = a^b;
        a = a^b;
        System.out.println("a=" + a + ", b=" + b);
    }

    /**
     * L136
     * 一个整数数组，除了一个数字外，剩下的都是成对出现的，找到这个一个的数字
     * 一个数异或自己等于0
     * 一个数异或0等于其自己
     * 0^0 = 0
     * 1^1 = 0
     * 1^0 = 1 0^1 = 1
     * @param nums
     * @return
     */
    public static int getOnlyOneShouNum(int[] nums){
        int result = 0;
        for (int num : nums){
            result = result^num;
        }
        return result;
    }

    /**
     * L338
     * 比特位计算:给定一个整数n, 计算 0-n的所有的数的bit位中1的个数，返回一个n+1的数组
     *
     * 1  0001
     * 2  0010
     * 3  0011
     * 4  0100
     * 5  0101
     * 6  0110
     *
     *
     * x&(x-1)的作用时清除x最低位的1
     * 21&20 =
     * 0001 0101 &
     * 0001 0100
     * 0001 0100=20
     *
     * 20&19 =
     * 0001 0100
     * 0001 0011
     * 0001 0000 = 16
     *
     * 16&15
     * 0001 0000
     * 0000 1111 = 0
     *
     * 21的清除顺序为 0001 0101 -> 0001 0100 -> 0001 0000 -> 0000 0000
     *
     * @return
     */
    public static int[] bitCount1(int num){
        int[] bits = new int[num + 1];
        for (int i = 1; i<=num; i++){
            bits[i] = bits[i&(i-1)] + 1;
        }
        return bits;
    }

    /**
     *
     * @param num
     * @return
     */
    public static int[] bitCount2(int num){
        int[] bits = new int[num + 1];
        for (int i = 1; i<=num; i++){
            // 奇数时：等于其对用的偶数的1的bit数+1，因为偶数最后一位时0，加一的奇数，就是最后一位变为1
            // 偶数时：因为最后一位时0， 左移动后1的个数不变，比如
            bits[i] = (i&1) == 1 ? bits[i-1]+1 : bits[i>>1];
        }
        return bits;
    }
    /**
     * L461
     * 计算两数的汉明距离: 两个数字的二进制对用位置不同的数量
     * 1  0001
     * 4  0100
     * 输出 两个地方不同，输出2
     * @return
     */
    public static int hanMingDistance(int x, int y){
        int distance = 0;
        for (int xor = x^y; xor!=0; xor &= (xor-1)){
            distance ++;
        }
        return distance;
    }

    public static void main(String[] args) {
//        swap(2,4);
//        System.out.println(getOnlyOneShouNum(new int[]{2,2,1,4,1,4,5}));
//        System.out.println(hanMingDistance(4,1));

        final int[] bitCount1 = bitCount1(4);
        final int[] bitCount2 = bitCount2(4);
        Arrays.stream(bitCount1).forEach(x -> System.out.print(x + " "));
        System.out.println();
        Arrays.stream(bitCount2).forEach(x -> System.out.print(x + " "));
        System.out.println();

    }
}
