package Fundamentals.imp;

import Fundamentals.api.Queue;

import java.util.Iterator;

public class QueueByLinkedList<T> implements Queue<T> {

    private Node first;
    private Node last;
    private int size;

    private class Node {
        T data;
        Node next;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<T> {
        private Node curNode = first;

        @Override
        public boolean hasNext() {
            return curNode != null;
        }

        @Override
        public T next() {
            if (curNode == null) {
                return null;
            }
            T result = curNode.data;
            curNode = curNode.next;
            return result;
        }
    }

    @Override
    public void enqueue(T t) {
        Node newNode = new Node();

        newNode.data = t;
        if (isEmpty()) {
            first = last = newNode;
        } else {
            last.next = newNode;
            last = newNode;
            last.next = null;
        }
        size++;

    }

    @Override
    public T dequeue() {
        if (isEmpty()) {
            return null;
        }
        T result = first.data;
        first = first.next;
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
        QueueByLinkedList<String> queueByLinkedList = new QueueByLinkedList<>();
        String str = "to be or not to - - be - - that - - - is";
        String[] strings = str.split("");
        for (String string : strings) {
            if (!string.equals("-")) {
                queueByLinkedList.enqueue(string);
            }

        }
        for (String o : queueByLinkedList) {
            System.out.println(o);
        }
//        System.out.println(queueByLinkedList.size() + " left on queue");
    }
}
