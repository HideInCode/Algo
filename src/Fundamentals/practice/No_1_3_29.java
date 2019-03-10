package Fundamentals.practice;

class CycleQueue<T> {
    private Node first;
    private Node last;
    private int size;

    public int size() {
        return size;
    }

    private class Node {
        T item;
        Node next;


    }

    public boolean isEmpty() {
        return first == null && last == null;
    }

    //这次从尾巴上增加
    public void add(T item) {
        Node newNode = new Node();
        newNode.item = item;
        if (isEmpty()) {
            first = last = newNode;
            last.next = first;
            size++;
        } else {
            //insert
            newNode.next = last.next;
            last.next = newNode;
            newNode.next = first;
            size++;
        }
    }


}

public class No_1_3_29 {

    public static void main(String[] args) {
        CycleQueue<String> cycleQueue = new CycleQueue<>();
        cycleQueue.add("a");
        cycleQueue.add("b");
        cycleQueue.add("c");
        cycleQueue.add("d");
        System.out.println(cycleQueue.size());
//        System.out.println(cycleQueue);
    }
}
