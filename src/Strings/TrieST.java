package Strings;

import java.util.LinkedList;
import java.util.Queue;

public class TrieST<Value> {
    private static int R = 256;
    private Node root;

    private static class Node {
        private Object val;
        private Node[] next = new Node[R];
    }

    private Node get(Node x, String key, int d) {
        if (x == null) {
            return null;
        }
        if (d == key.length()) {
            return x;
        }

        char c = key.charAt(d);
        return get(x.next[c], key, d + 1);
    }

    public Value get(String key) {
        Node node = get(root, key, 0);
        if (node == null) {
            return null;
        }
        return (Value) node.val;
    }

    private Node put(Node node, String key, Value value, int d) {
        if (node == null) {
            node = new Node();
        }

        if (d == key.length()) {
            node.val = value;
            return node;
        }
        char c = key.charAt(d);

        //有点动态规划的味道
        node.next[c] = put(node.next[c], key, value, d + 1);
        return node;
    }

    public void put(String key, Value value) {
        root = put(root, key, value, 0);
    }

    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) {
            return 0;
        }

        int count = 0;
        if (node.val != null) {
            count++;
        }
        for (int i = 0; i < R; i++) {
            count += size(node.next[i]);
        }
        return count;
    }

    private void collect(Node node, String pre, Queue<String> queue) {
        if (node == null) {
            return;
        }
        if (node.val != null) {
            queue.add(pre);
        }

        for (int c = 0; c < R; c++) {
            collect(node.next[c], pre + c, queue);
        }
    }

    public Iterable<String> keysWithPrefix(String pre) {
        Queue<String> queue = new LinkedList<>();
        collect(get(root, pre, 0), pre, queue);
        return queue;
    }

    public Iterable<String> keys() {
        return keysWithPrefix("");
    }

    private void collect(Node node, String pre, String pat, Queue<String> queue) {
        int d = pre.length();
        if (node == null) {
            return;
        }

        if (d == pat.length() && node.val != null) {
            queue.add(pre);
        }
        if (d == pat.length()) {
            return;
        }

        char next = pat.charAt(d);
        for (int c = 0; c < R; c++) {
            if (next == '.' || next == c) {
                collect(node.next[c], pre + c, pat, queue);
            }
        }
    }

    public Iterable<String> keysThatMatch(String pat) {
        Queue<String> queue = new LinkedList<>();
        collect(root, "", pat, queue);
        return queue;
    }

    private int search(Node node, String str, int d, int length) {
        if (node == null) {
            return length;
        }
        if (node.val != null) {
            length = d;
        }

        if (d == str.length()) {
            return length;
        }
        char c = str.charAt(d);
        return search(node.next[c], str, d + 1, length);
    }

    public String longestPrefixOf(String s) {
        int length = search(root, s, 0, 0);
        return s.substring(0, length);
    }

    private Node delete(Node node, String key, int d) {
        if (node == null) {
            return null;
        }

        if (key.length() == d) {
            node.val = null;
        } else {
            char c = key.charAt(d);
            node.next[c] = delete(node.next[c], key, d + 1);
        }
        if (node.val != null) {
            return node;
        }
        for (int c = 0; c < R; c++) {
            if (node.next[c] != null) {
                return node;
            }
        }
        return null;
    }

    public void delete(String key) {
        root = delete(root, key, 0);
    }

    public static void main(String[] args) {
        TrieST trieST = new TrieST();
        trieST.put("key", "value");
        trieST.put("key2", "value");
        trieST.put("key3", "value");
        trieST.delete("key");
        System.out.println(trieST.size());
    }
}
