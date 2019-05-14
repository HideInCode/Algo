package Searching;

public class SequentialSearchST<K, V> {
    private Node first;

    private class Node {
        K key;
        V value;

        Node next;

        public Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }


    public void put(K key, V value) {
        for (Node node = first; node != null; node = node.next) {
            if (key.equals(node.key)) {
                node.value = value;
                return;
            }
        }
        first = new Node(key, value, first);

    }

    public V get(K key) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                return x.value;
            }
        }
        return null;
    }

}
