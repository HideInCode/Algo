package Fundamentals.imp;

import Fundamentals.api.Stack;

import java.util.Iterator;

/**
 * 使用链表的重要要素
 * 1.定义Node中的next，data
 * 2.使用Node存数据要见建立node
 * 3.更新curNode
 */
public class StackByLinkedList<T> extends Stack<T> {
    private Node curNode;
    private int size;

    private class Node {
        T data;

        //递归才是链表的根本
        Node next;
    }

    public StackByLinkedList() {
        curNode = new Node();
    }

    @Override
    public Iterator<T> iterator() {
        return super.iterator();
    }

    //新的元素应该找上个元素,不该去找第一个,所以把上个元素变成第一个
    @Override
    public void push(T t) {
        Node before = new Node();
        before.data = t;
        before.next = curNode;
        curNode = before;
        size++;
    }

    @Override
    public T pop() {
        T result = null;
        if (size != 0) {
            result = curNode.data;
            curNode = curNode.next;
        }
        size--;
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

    public static void main(String[] args) {
        StackByLinkedList stackByLinkedList = new StackByLinkedList();
        stackByLinkedList.push("hello");
        stackByLinkedList.push("world");
        Object pop = stackByLinkedList.pop();
        System.out.println(pop);
    }
}
