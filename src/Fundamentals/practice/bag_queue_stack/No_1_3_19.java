package Fundamentals.practice.bag_queue_stack;

/**
 * 线性表之链表练习
 * 增删改查
 */

class LinkedList<T> {
    private Node first;
    private int size;

    class Node {
        T item;
        Node next;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    //从表头添加
    public void add(T data) {
        Node oldFirst = first;
        first = new Node();
        first.item = data;
        first.next = oldFirst;
        size++;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        //便利链表的for
        for (Node curNode = first; curNode != null; curNode = curNode.next) {
            sb.append(curNode.item);
        }
        return sb.toString();
    }

    public boolean delete(int k) {
        if (isEmpty()) {
            return false;
        }
        if (k == 1) {
            first = null;
            size--;
            return true;
        }
        if (k > size) {
            System.out.println("too large");
            return false;
        }
        boolean isOk = false;
        int curIndex = 1;
        for (Node curNode = first; curNode != null; curNode = curNode.next) {
            if (curIndex == k - 1) {
                //删除节点,没有pre指针时,要拿到前一个节点来做删除
                curNode.next = curNode.next.next;
                size--;
                isOk = true;
                break;
            }
            curIndex++;
        }
        return isOk;
    }

    public Node search(T item) {
        if (isEmpty()) {
            return null;
        }
        for (Node curNode = first; curNode != null; curNode = curNode.next) {
            if (curNode.item.equals(item)) {
                return curNode;
            }
        }
        return null;
    }

    public boolean find(LinkedList<?> linkedList, String key) {
        if (isEmpty()) {
            return false;
        }
        for (Node curNode = first; curNode != null; curNode = curNode.next) {
            if (curNode.item.equals(key)) {
                return true;
            }
        }
        return false;
    }

    public boolean removeTailNode() {
        if (first == null) {
            return false;
        }
        if (first.next == null) {

            return true;
        }
        boolean isRemoved = false;
        //利用链表的next查找,找到尾节点
        Node curNode = first;
        while (curNode.next != null) {
            if (curNode.next.next == null) {
                curNode.next = null;
                isRemoved = true;
                size--;
                break;
            }
            curNode = curNode.next;
        }
        return isRemoved;

    }

    //接受两个节点,position插入到pre后面插入链表,pre是链表本来就有的
    public void insertAfter(Node pre, Node position) {
        if (pre == null || position == null) {
            return;
        }
        position.next = pre.next.next;
        pre.next = position;
    }

    public void removeAfter(Node position) {
        if (position == null) {
            return;
        }
        if (isEmpty()) {
            return;
        }
        if (position.next != null) {
            position.next = null;
        }
        //要不要断开放弃的链表
    }

    public Node getFirstNode() {
        return first;
    }

    public int max(Node firstNode) {
        int max = 0;
        if (firstNode == null) {
            return 0;
        }
        if (isEmpty()) {
            return max;
        }
        max = (int) firstNode.item;
        for (Node curNode = firstNode; curNode != null; curNode = curNode.next) {
            int curNum = (int) curNode.item;
            if (curNum > max) {
                max = curNum;
            }
        }
        return max;
    }

    public int getMaxByRecur(Node first) {
        if (first == null) {
            return 0;
        }
        if (first.next == null) {
            return (int) first.item;
        }
        int secondNum = getMaxByRecur(first.next);
        int firstNum = (int) first.item;

        return firstNum > secondNum ? firstNum : secondNum;

    }

    public void remove(String key) {
        if (isEmpty()) {
            return;
        }
        Node curNode = first;
        while (curNode.next != null) {

            if (curNode.next != null && curNode.next.item.equals(key)) {
                curNode.next = curNode.next.next;
            }
            if (curNode.next != null && !curNode.next.item.equals(key)) {
                curNode = curNode.next;
            }
        }
    }

}

public class No_1_3_19 {
    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>();


        list.add("1");
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
//        System.out.println(list.toString());
//        list.delete(2);
//        System.out.println(list);
//        System.out.println();
//        LinkedList.Node node = list.search("c");
//        list.removeAfter(node);
//        System.out.println(list);
//        LinkedList.Node node = list.search("c");
//        LinkedList.Node newNode = list.new Node();
//        newNode.item = "新节点";
//        list.insertAfter(node, newNode);
//        System.out.println(list);
//        list.remove("d");
//        System.out.println(list);

        LinkedList<Integer> integerLinkedList = new LinkedList<>();
        integerLinkedList.add(1);
        integerLinkedList.add(3);
        integerLinkedList.add(4);
        integerLinkedList.add(5);
        integerLinkedList.add(90);

        int max = integerLinkedList.getMaxByRecur(integerLinkedList.getFirstNode());
        System.out.println(max);
    }
}
