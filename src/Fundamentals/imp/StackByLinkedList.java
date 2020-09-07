package Fundamentals.imp;

import Fundamentals.api.Stack;

import java.util.Iterator;

/**
 * 使用链表的重要要素
 * 1.定义Node中的next，data
 * 2.使用Node存数据要见建立node
 * 3.更新curNode
 */
public class StackByLinkedList<T> implements Stack<T> {
    private Node curNode;
    private int size;
    
    private class Node {
        T data;
        Node next;
        
        public Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
    
    
    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private Node cur = curNode;
            
            @Override
            public boolean hasNext() {
                return cur != null;
            }
            
            @Override
            public T next() {
                T item = cur.data;
                cur = cur.next;
                return item;
            }
        };
    }
    
    //新的元素应该找上个元素,不该去找第一个,所以把上个元素变成第一个
    @Override
    public void push(T t) {
        Node before = new Node(t, curNode);
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
        StackByLinkedList<String> stackByLinkedList = new StackByLinkedList<>();
        stackByLinkedList.push("hello");
        stackByLinkedList.push("world");
//        String pop = stackByLinkedList.pop();
//        System.out.println(pop);
        for (String s : stackByLinkedList) {
            System.out.println(s);
        }
    
        java.util.Stack<String> strings = new java.util.Stack<>();
        strings.push("a");
        strings.push("b");
        strings.push("c");
        for (String string : strings) {
            System.out.println(string);
        }
    }
}
