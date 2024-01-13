package com.yap.memleak;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class MemoryLeakCollectionExample {

    public static void main(String[] args) {
        Set<Person> hashSet = new HashSet<>();
        Person p1 = new Person("a",3);
        Person p2 = new Person("b",5);
        Person p3 = new Person("c",7);
        hashSet.add(p1);
        hashSet.add(p2);
        hashSet.add(p3);//现在set里面有3个对象!

        p3.setAge(99); //修改p3的年龄,此时p3的hashcode改变

        //此时remove不掉，因为hashcode是计算年龄的，根据p3的hashcode查询不到set中的元素，造成内存泄漏
        // 因为原始的p3的hashcode是 name.hashCode() + ，即Set的key是这个hashcode，根本找不到这个对象
        //
        hashSet.remove(p3);
        System.out.println(hashSet);


        System.out.println(hashSet.size());  //3

        hashSet.add(p3);// 重新添加，成功，现在set里面有4个对象

        System.out.println(hashSet.size());  //4
        System.out.println(hashSet);


        Person p4 = new Person("c",99);
        hashSet.add(p4);
        System.out.println(hashSet);

    }

    public static class Person {

        private String name;
        private int age;
        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public int hashCode() {
            // 如果没有重写hashCode方法，集合里面的对象属性被修改后，hash值是不会改变的。remove就会成功，不会造成内存泄漏。

            // 在Java中HashSet的底层的实现是通过Map来实现，将要保存的对象的hashcode值作为Key,
            // 使用一个dummy作为Value.在对象被放入Set之后，如果有操作改变影响该对象的hashcode的变量，
            // 会造成该对象无法被remove，因为remove时是通过hashcode来查找Set内的对象,由于hashcode的变化造成该对象无法被remove，只能通过clear方法移出Set.
            return name.hashCode() + age;
            // return name.hashCode() ;//
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Person person = (Person) o;
            return age == person.age && Objects.equals(name, person.name);
        }

        public void setAge(int age) {
            this.age=age;
        }


        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", hashCode=" + hashCode() +
                    '}';
        }
    }
}
