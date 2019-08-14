package Fundamentals.imp;

import Fundamentals.api.Stack;

import java.util.Iterator;

//使用数组实现一个定容的字符串stack

public class FixedCapacityStackOfStrings implements Stack {
    private String[] strArr;
    private int size;

    public FixedCapacityStackOfStrings(int capacity) {
        this.strArr = new String[capacity];
    }

    public boolean isFull() {
        return size == strArr.length;
    }

    private class MyIterator implements Iterator {
        int temp = size;

        @Override
        public boolean hasNext() {
            return temp > 0;
        }

        @Override
        public Object next() {
            return strArr[temp--];
        }
    }

    @Override
    public Iterator iterator() {
        return new MyIterator();
    }

    @Override
    public void push(Object o) {
        strArr[size++] = (String) o;
    }

    @Override
    public Object pop() {
        return strArr[--size];
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    public static void main(String[] args) {
        FixedCapacityStackOfStrings stack = new FixedCapacityStackOfStrings(100);
        stack.push("hello");
        stack.push("world");


        System.out.println(stack.size());
        stack.pop();
        System.out.println(stack.size());

        boolean empty = stack.isEmpty();
        System.out.println(empty);

        for (Object string : stack) {
            System.out.println(string);
        }

    }
}
