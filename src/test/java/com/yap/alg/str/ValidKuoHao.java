package com.yap.alg.str;

import java.util.Deque;
import java.util.LinkedList;

public class ValidKuoHao {

    public static boolean validKuoHao(char[] kuohaoArr){
        Deque<Character> stack = new LinkedList<>();
        for (char kuohao: kuohaoArr){
            // 把对应的push进去，方便比较
            if (kuohao == '{'){
                stack.push('}');
            }else if(kuohao == '['){
                stack.push(']');
            }else if(kuohao == '('){
                stack.push(')');
            }else {
                if (kuohao != stack.pop()){
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {

        char[] kuoHao1 = new char[]{'{', '[', ']', '(', ')', '}'};
        char[] kuoHao2 = new char[]{'{', '[', ']', '(', ')', ')'};


        System.out.println(validKuoHao(kuoHao1));
        System.out.println(validKuoHao(kuoHao2));

    }
}
