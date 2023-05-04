package com.yap.alg.stackandqueue;

import java.util.Stack;

/**
 * LC 232 使用栈实现队列
 * 队列应当有 push pop peek empty等操作
 *
 */
public class UseStackImplementQueue {
    private static Stack<Integer> inStack;
    private static Stack<Integer> outStack;

    public UseStackImplementQueue(){
        inStack = new Stack<Integer>();
        outStack = new Stack<Integer>();
    }

    public void push(int x){
        inStack.push(x);
    }

    public int pop(){
        if (outStack.isEmpty()){
            in2out();
        }
        return outStack.pop();
    }

    public int peek(){
        if (outStack.isEmpty()){
            in2out();
        }
        return outStack.peek();
    }

    public boolean empty(){
        return outStack.isEmpty() && inStack.isEmpty();
    }

    public void in2out(){
        while (!inStack.isEmpty()){
            outStack.push(inStack.pop());
        }
    }
}
