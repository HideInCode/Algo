package Fundamentals.practice.bag_queue_stack;

//反转链表,链表一定要画图
public class No_1_3_30<T> {
    private Node first;
    private Node last;
    private int size;

    public boolean isEmpty() {
        return size == 0;
    }

    private class Node {
        Node next;
        T item;
    }

    //这次从尾巴上增加
    public void add(T item) {

        //先断开,再连接
        Node oldLast = last;
        last = new Node();
        last.item = item;
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
        size++;


    }

    public Node reverse(Node first) {

        //搞个中间变量reversedFirst,反过来指向
        Node reversedFirst = null;
        while (first != null) {
            Node second = first.next;
            first.next = reversedFirst;

            //复位
            reversedFirst = first;
            first = second;
        }
        return reversedFirst;
    }

    public Node getFirst() {
        return first;
    }

    public int size() {
        return size;
    }

    public Node reverseByRecur(Node first) {
        if (first == null) {
            return null;
        }
        if (first.next == null) {
            return first;
        }
        Node second = first.next;
        Node result = reverseByRecur(second);
        second.next = first;
        first.next = null;
        return result;
    }

    public String toString() {
        String str = "";
        for (Node curNode = first; curNode != null; curNode = curNode.next) {
            str += curNode.item;
        }
        return str;
    }

    public static void main(String[] args) {
        No_1_3_30<String> linkedList = new No_1_3_30<>();
        linkedList.add("a");
        linkedList.add("b");
        linkedList.add("c");
        linkedList.add("d");
        linkedList.add("e");
        System.out.println(linkedList);

        No_1_3_30.Node reverse = linkedList.reverseByRecur(linkedList.getFirst());
        for (No_1_3_30.Node curNode = reverse; curNode != null; curNode = curNode.next) {
            System.out.println(curNode.item);
        }
    }
}
