package Fundamentals.imp;

import Fundamentals.api.Stack;

import java.util.Iterator;

//使用数组实现一个定容的字符串stack

public class FixedCapacityStackOfStrings extends Stack {
    private String[] strArr;
    private int size;

    public FixedCapacityStackOfStrings(int capacity) {
        this.strArr = new String[capacity];
    }

    public boolean isFull() {
        return size == strArr.length;
    }

    @Override
    public Iterator iterator() {
        return super.iterator();
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
        FixedCapacityStackOfStrings fixedCapacityStackOfStrings = new FixedCapacityStackOfStrings(100);
        fixedCapacityStackOfStrings.push("hello");
        fixedCapacityStackOfStrings.push("world");
        System.out.println(fixedCapacityStackOfStrings.size());
        boolean empty = fixedCapacityStackOfStrings.isEmpty();
        System.out.println(empty);
    }
}
