package Strings.search;

/**
 * 三向单词查找树
 * 比之前用数组搞的要省不少空间
 * 不用数组实现,自己申请空间.
 */
public class TST<Value> {
    
    private Node<Value> root;
    
    private class Node<Value> {
        char c;
        Node<Value> left, mid, right;
        Value val;
    }
    
    private Node<Value> get(Node<Value> node, String key, int d) {
        if (node == null) {
            return null;
        }
        
        char c = key.charAt(d);
        if (c < node.c) {
            return get(node.left, key, d);
        } else if (c > node.c) {
            return get(node.right, key, d);
        } else if (d < key.length() - 1) {
            return get(node.mid, key, d + 1);
        } else {
            return node;
        }
    }
    
    public Value get(String key) {
        Node<Value> node = get(root, key, 0);
        return node.val;
    }
    
    private Node<Value> put(Node node, String key, Value val, int d) {
        char c = key.charAt(d);
        if (node == null) {
            node = new Node();
            node.c = c;
        }
        
        if (c < node.c) {
            node.left = put(node.left, key, val, d);
        } else if (c > node.c) {
            node.right = put(node.right, key, val, d);
        } else if (d < key.length() - 1) {
            node.mid = put(node.mid, key, val, d + 1);
        } else {
            node.val = val;
        }
        return node;
    }
    
    public void put(String key, Value value) {
        root = put(root, key, value, 0);
    }
    
    
    public String longestPrefixOf(String query) {
        if (query == null) {
            throw new IllegalArgumentException("ensure query not null plz");
        }
        
        if (query.length() == 0) {
            return null;
        }
        
        int length = 0;
        
        Node<Value> x = root;
        int i = 0;
        while (x != null && i < query.length()) {
            char c = query.charAt(i);
            if (c < x.c) {
                x = x.left;
            } else if (c > x.c) {
                x = x.right;
            } else {
                i++;
                if (x.val != null) {
                    length = i;
                    x = x.mid;
                }
            }
        }
        return query.substring(0, length);
    }
    
    
    public static void main(String[] args) {
        TST<String> test = new TST<>();
        test.put("hello", "qwer");
        String hello = test.get("hello");
        System.out.println(hello);
    }
}
