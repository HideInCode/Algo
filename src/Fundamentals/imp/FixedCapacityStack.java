package Fundamentals.imp;

import Fundamentals.api.Stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

//使用泛型实现定容栈,使用迭代器模式
public class FixedCapacityStack<T> implements Stack<T> {
    private T[] tArr;
    private int size;
    
    public FixedCapacityStack(int capacity) {
        //也参考jdk把数组每个元素都转型
        tArr = (T[]) new Object[capacity];
    }
    
    @Override
    public Iterator<T> iterator() {
        return new ReverseArrayIterator();
    }
    
    private class ReverseArrayIterator<T> implements Iterator<T> {
        int curSize = size;
        
        @Override
        public boolean hasNext() {
            return curSize > 0;
        }
        
        @Override
        public T next() {
            if (curSize == 0) {
                throw new NoSuchElementException();
            }
            //这里用size会和pop冲突,故增加自己的变量
            return (T) tArr[--curSize];
        }
    }
    
    @Override
    public void push(T t) {
        if (size == tArr.length) {
            reSize(tArr.length * 2);
        }
        tArr[size++] = t;
    }
    
    @Override
    public T pop() {
        T result = tArr[--size];
        //担心引用存在,对象游离,垃圾回收不了
        tArr[size] = null;
        
        //如果删除后空间只剩最大空间的1/4,那么我们就减小数组
        if (size > 0 || size < (tArr.length / 4)) {
            reSize(tArr.length / 2);
        }
        return result;
    }
    
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    
    @Override
    public int size() {
        return size;
    }
    
    //考虑到底层是数组,数组会溢出,
    // 所以要在增加时扩容,空间不足,应该扩容
    // 删除数据后,空间浪费,应该缩减数组
    private void reSize(int limit) {
        T[] temp = (T[]) new Object[limit];
        for (int i = 0; i < size; i++) {
            temp[i] = tArr[i];
        }
        tArr = temp;
    }
    
    
    public static void main(String[] args) {
        FixedCapacityStack<Integer> fixedCapacityStack = new FixedCapacityStack<>(100);
        int N = 50;
        
        while (N > 0) {
            fixedCapacityStack.push(N % 2);
            N = N / 2;
        }
        System.out.println(fixedCapacityStack.size());
        Iterator<Integer> iterator = fixedCapacityStack.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next());
        }
        
    }
}
