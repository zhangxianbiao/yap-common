package com.yap.alg;

/**
 * LC 470 使用rand7 实现rand10
 * 思路： rand7 -> rand49 -> rand40 -> % -> rand10
 *
 * 特别的 randN -> randM
 * randN -> randX X是N的整数倍
 * randX -> randY Y是M的整数倍
 * randY mod M+1  -> randM
 */
public class Rand10 {

    public static int rand10(){
        int temp = 40;
        while (temp >= 40){
            temp = (rand7()-1) * 7 + rand7() - 1;
        }
        return temp%10 + 1;
    }

    /**
     * 已知方法
     * @return
     */
    public static int rand7(){
        // todo
        return 1;
    }


}
