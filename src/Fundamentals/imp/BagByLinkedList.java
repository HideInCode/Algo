package Fundamentals.imp;

import Fundamentals.api.Bag;

import java.util.Iterator;

/**
 * 用链表实现bag,bag只能装东西,不能吐东西
 *
 * @param <T>
 */
public class BagByLinkedList<T> extends Bag<T> {
    private Node first;
    private int size;

    private class Node {
        T item;
        Node next;
    }

    @Override
    public Iterator<T> iterator() {
        return new BagIterator<T>();
    }

    private class BagIterator<E> implements Iterator<T> {
        //把first指向对象的地址复制一份给curNode
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
            T result = curNode.item;
            curNode = curNode.next;
            return result;
        }
    }

    //压栈
    @Override
    public void add(T t) {
        Node oldFirst = first;
        first = new Node();
        first.item = t;
        first.next = oldFirst;
        size++;
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
        BagByLinkedList<String> bag = new BagByLinkedList<>();
        bag.add("a");
        bag.add("b");
        bag.add("c");
        bag.add("d");
        System.out.println(bag.size());
        Iterator<String> iterator = bag.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
