package com.yap.alg.greed;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 有阵营R和D，给定一个R和D的顺序数组，R和D分别投票，可以禁止另一方的人失去投票权利，或者理解位杀死
 */
public class Dota2 {

    /**
     * 当轮到一个阵营选择时，其应该把距离其最近的反方阵营杀死，早早避免其参数选择，-> 贪心算法
     * @param senate
     * @return
     */
    public static String predictWinner(String senate){
        int n = senate.length();
        Queue<Integer> DQueue = new LinkedList<>();
        Queue<Integer> RQueue = new LinkedList<>();

        for (int i = 0; i<n;i++){
            if (senate.charAt(i) == 'R') {
                RQueue.offer(i);
            }else {
                DQueue.offer(i);
            }
        }
        while (!DQueue.isEmpty() && !RQueue.isEmpty()){
            int rIndex = RQueue.poll();
            int dIndex = DQueue.poll();
            if (rIndex < dIndex){
                // 上面poll均已经取出，此时R下标大，R选择，把D杀死，因此只需要把R加进去
                // 下标必须加上一个大于length的值，防止不同轮的数据混淆
                RQueue.offer(rIndex + n);
            }else {
                DQueue.offer(dIndex + n);
            }
        }

        // 哪个空了，哪个输
        return RQueue.isEmpty() ? "D" : "R";

    }

    public static void main(String[] args) {
        String senate = "RRDDRRDDR";
        final String s = predictWinner(senate);
        System.out.println(s);
    }
}
