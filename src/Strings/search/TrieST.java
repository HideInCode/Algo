package Strings.search;

import java.util.LinkedList;
import java.util.Queue;

public class TrieST<Value> {
    private static int R = 256;
    private Node root;
    
    //定义多叉树,字母表的个数
    private static class Node {
        private Object val;
        private Node[] next = new Node[R];
    }
    
    /**
     * 根据key获取在树中的value
     *
     * @param key
     * @return
     */
    public Value get(String key) {
        if (key == null) {
            return null;
        }
        Node node = get(root, key, 0);
        return (Value) node.val;
    }
    
    /**
     * @param root 根节点
     * @param key  关键字
     * @param d    关键字的字符下标
     * @return 与关键字匹配的末尾节点
     */
    private Node get(Node root, String key, int d) {
        if (root == null) {
            return null;
        }
        
        //类似于回溯中到达决策树的底部了
        if (d == key.length()) {
            return root;
        }
        char c = key.charAt(d);
        return get(root.next[c], key, d + 1);
    }
    
    public void put(String key, Value value) {
//        if (key == null) {
//            return;
//        }
       root = put(root, key, value, 0);
    }
    
    /**
     * 把当前键对应的值放入trie,先检查有没有,没有就新建节点,有的话就更新
     *
     * @param root  根节点
     * @param key   关键字
     * @param value 值
     * @param d     关键的字符下标
     * @return
     */
    private Node put(Node root, String key, Value value, int d) {
        if (root == null) {
            root = new Node();
        }
        //触底反弹
        if (d == key.length()) {
            root.val = value;
            return root;
        }
        char c = key.charAt(d);
        root.next[c] = put(root.next[c], key, value, d + 1);
        return root;
    }
    
    public int size() {
        return size(root);
    }
    
    /**
     * 延时实现,性能不好
     *
     * @param node
     * @return
     */
    private int size(Node node) {
        if (node == null) {
            return 0;
        }
        
        int count = 0;
        if (node.val != null) {
            count++;
        }
        for (char i = 0; i < R; i++) {
            count += size(node.next[i]);
        }
        return count;
    }
    
    private void collect(Node root, String pre, Queue<String> queue) {
        if (null == root) {
            return;
        }
        //找到一个字符串,就放入队列
        if (root.val != null) {
            queue.offer(pre);
        }
        
        //对当前节点的所有选择遍历
        for (char c = 0; c < R; c++) {
            collect(root.next[c], pre + c, queue);
        }
    }
    
    public Iterable<String> keysWithPrefix(String pre) {
        //pre的最后一个字符的节点
        Node root = get(this.root, pre, 0);
        LinkedList<String> strings = new LinkedList<>();
        collect(root, pre, strings);
        return strings;
    }
    
    //以空字符串为前缀的字符串就是所有key
    public Iterable<String> keys() {
        return keysWithPrefix("");
    }
    
    /**
     * 含有通配符.的匹配,遍历找出结果
     *
     * @param pat
     * @return
     */
    public Iterable<String> keysThatMatch(String pat) {
        Queue<String> queue = new LinkedList<>();
        collect(root, "", pat, queue);
        return queue;
    }
    
    private void collect(Node root, String pre, String pat, Queue<String> queue) {
        if (root == null) {
            return;
        }
        int d = pre.length();
        if (d == pat.length() && root.val != null) {
            queue.offer(pre);
        }
        if (d == pat.length()) {
            return;
        }
        
        char next = pat.charAt(d);
        for (char c = 0; c < R; c++) {
            if (c == next || next == '.') {
                collect(root.next[c], pre + c, pat, queue);
            }
        }
    }
    
    public String longestPrefixOf(String s) {
        int length = search(root, s, 0, 0);
        return s.substring(0, length);
    }
    
    /**
     * 查找以root为根节点
     *
     * @param root
     * @param str
     * @param d
     * @param length
     * @return
     */
    private int search(Node root, String str, int d, int length) {
        if (root == null) {
            return length;
        }
    
        if (root.val != null) {
            length = d;
        }
    
        if (d == str.length()) {
            return length;
        }
        char c = str.charAt(d);
    
        return search(root.next[c], str, d + 1, length);
    }
    
    /**
     * todo 好好理解下
     * 找到key的值,干掉
     * 删除后要是没有子节点了,那么这个节点就没用了,直接节点干掉
     * 如果还有子节点,把当前值置空
     * 如果删除后父节点为空,那么递归的向上删除
     * @param root
     * @param key
     * @param d
     * @return
     */
    private Node delete(Node root, String key, int d) {
        if (root == null) {
            return null;
        }
        //找到key的最后一个字符
        if (d == key.length()) {
            root.val = null;
        } else {
            char c = key.charAt(d);
            //持续往后删除
            root.next[c] = delete(root.next[c], key, d + 1);
        }
        //没有子节点了,不再操作,结束
        if (root.val != null) {
            return root;
        }
    
        for (int c = 0; c < R; c++) {
            //只要还有子节点,不再操作,结束
            if (root.next[c] != null) {
                return root;
            }
        }
        
        return null;
    }
    
    public void delete(String key) {
        root = delete(root, key, 0);
    }
    
    public static void main(String[] args) {
        TrieST<String> trie = new TrieST<>();
        trie.put("key", "value");
        System.out.println(trie.get("key"));
        
        trie.put("key1", "value");
        trie.delete("key");
        System.out.println(trie.size());
        
        trie.put("s", "a");
        trie.put("se", "b");
        trie.put("sea", "c");
        trie.put("sh", "d");
        trie.put("she", "d");
        trie.put("shell", "d");
        trie.put("shells", "d");
        
        //已知前缀找到所有键
        System.out.println(trie.keys());
        
        //含有通配符.的匹配
        System.out.println(trie.keysThatMatch("sh."));
    
        //在字典树中的最长前缀
        System.out.println(trie.longestPrefixOf("shellsasdfpu"));
    }
}
