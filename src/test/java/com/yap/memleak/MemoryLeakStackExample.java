package com.yap.memleak;

import java.util.Arrays;
import java.util.EmptyStackException;

public class MemoryLeakStackExample {

    public static class Stack{
        private Object[] elements;

        private int size = 0;
        private static final int DEFAULT_INITIAL_CAPACITY = 16;

        public Stack() {
            elements = new Object[DEFAULT_INITIAL_CAPACITY];
        }

        public void push(Object obj){
            ensureCapacity();
            elements[size++] = obj;
        }

        public Object pop(){
            if (size == 0){
                throw new EmptyStackException();
            }
            // memory leak
            // 引用未进行置空，gc是不会释放的
            return elements[--size];
        }

        public Object pop2(){
            if (size == 0){
                throw new EmptyStackException();
            }

            Object obj = elements[--size];
            elements[size] = null;
            return obj;
        }

        public void ensureCapacity(){
            if (elements.length == size){
                elements = Arrays.copyOf(elements,  2 * size + 1);
            }
        }
    }

    public static class Person {
        private int[] aa = new int[1024*1024];//1M
    }


    public static void main(String[] args) throws Exception{
        Stack stack = new Stack();
        stack.push(new Person());

        int i = 100;
        while (i>1){
            stack.push(new Person());
            System.out.println("push i=" + i);
            i--;
            Thread.sleep(100);
        }
        System.out.println("push finish=====");

        i = 100;
        while (i>1){
            stack.pop();
            System.out.println("pop i=" + i);
            i--;
            System.gc();
            Thread.sleep(1000);
        }
    }
    // pop2
    //       S0     S1     E      O      M     CCS      YGC     YGCT    FGC    FGCT     CGC    CGCT       GCT
    // pop  0.00   0.00   0.00  83.44  71.33  16.72     31     0.032    99     0.462    52     0.012     0.506
    // pop2 0.00   0.00   0.00  52.73  71.90  16.72     31     0.036    99     0.398    52     0.012     0.446

}
