package com.yap.algo.leetcode;

/**
 * 用字符串最为井字游戏板board， 判断改游戏板有没有可能最终形成
 * 游戏板是一个3x3的数组，由"X","O", " "组成，" "代表空位
 * 两个玩家轮流将字符放入空位，先手放X，后手放O
 * 当有3个相同（非空）的字符填充任何行、列、对角线时，游戏结束，board生成
 */
public class XJingGame {

    public static boolean validBoard(String[] board){
        int xCount = 0;
        int oCount = 0;
        for (String row : board){
            for (char c : row.toCharArray()){
                if (c == 'X'){
                    xCount ++;
                }
                if (c == 'O'){
                    oCount ++;
                }
            }
        }

        // X和O一样多，或者X比O多一个，直接返回false（X赢则X多一个，O赢则一样多）
        if (oCount != xCount && oCount != xCount-1){
            return false;
        }
        if (win(board, "XXX") && oCount != xCount -1){
            return false;
        }
        if (win(board, "OOO") && oCount != xCount){
            return false;
        }
        return true;
    }

    public static boolean win(String[] board, String flag){
        for (int i = 0; i < 3; i++){
            // 纵向3个
            if (flag.equals("" + board[i].charAt(0) + board[i].charAt(1) + board[i].charAt(2))){
                return true;
            }
            // 横向3个
            if (flag.equals(board[i])){
                return true;
            }
        }
        // \向3个
        if (flag.equals("" + board[0].charAt(0) + board[1].charAt(1) + board[2].charAt(2))){
            return true;
        }
        // /向3个
        if (flag.equals("" + board[0].charAt(2) + board[1].charAt(1) + board[2].charAt(0))){
            return true;
        }
        return false;
    }
}
