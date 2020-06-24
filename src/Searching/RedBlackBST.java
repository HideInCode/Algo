package Searching;

/**
 * 红黑树,又叫做平衡-二叉查找树,可以看做是2-3树的实现
 * 目的是为了用二叉树的查找速度,2-3树的插入速度.
 * 通过定义红链接和黑链接,把一个平衡的2-3树化为红黑树.来优化在二叉树查找树最坏情况下的插入和删除情况.
 *
 * @param <Key>
 * @param <Value>
 */

public class RedBlackBST<Key extends Comparable<Key>, Value> {
    
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    
    private Node root;
    
    
    //返回表中的键值对数量,即节点数量
    private int size(Node x) {
        if (x == null) {
            return 0;
        } else {
            return x.N;
        }
        
    }
    
    public int size() {
        return size(root);
    }
    
    private class Node {
        
        //键
        Key key;
        
        //值
        Value val;
        
        //左节点
        Node left;
        //右节点
        Node right;
        
        //树的节点总数.
        int N;
        
        //父节点指向当前节点的连接颜色.
        boolean color;
        
        public Node(Key key, Value val, int n, boolean color) {
            this.key = key;
            this.val = val;
            N = n;
            this.color = color;
        }
    }
    
    private boolean isRed(Node x) {
        
        if (x == null) {
            return false;
        }
        
        return x.color == RED;
    }
    
    
    /**
     * 左旋转 画图
     * 解决红链接是右链接的问题.
     * 原理就是:若某个节点(记作某个子树的根)的右链接是red,对于这个子树,我们认为根的指向不对,指向了小的key.
     * 调整根的指向 把key较大者作为根节点,并把黑链接正确分配.
     *
     * @param root 指向这个错误子树根的指针
     * @return 旋转后正确的root的地址
     */
    private Node rotateLeft(Node root) {
        //整理黑链接
        Node x = root.right;
        root.right = x.left;
        x.left = root;
        
        //重新定义红链接
        x.color = root.color;
        root.color = RED;
        
        //校正调整后节点的个数
        x.N = root.N;
        
        root.N = 1 + size(root.left) + size(root.right);
        
        return x;
    }
    
    
    /**
     * 右旋转
     * 把右链接变成红链接
     *
     * @param root 需要坐旋转的子树的根节点
     * @return 旋转后的子树根节点
     */
    private Node rotateRight(Node root) {
        Node x = root.left;
        root.left = x.right;
        x.right = root;
        
        x.color = root.color;
        root.color = RED;
        
        x.N = root.N;
        root.N = 1 + size(root.left) + size(root.right);
        
        return x;
    }
    
    
    /**
     * 转换颜色,用于解决两个子节点都是红色
     * <p>
     * 1.把根节点子节点的颜色染为红色
     * 2.将父节点的颜色由红变黑
     *
     * @param root 指向需要染色的子树根节点
     */
    private void flipColors(Node root) {
        
        //染根节点链接的颜色
        root.color = RED;
        
        //左边染黑
        root.left.color = BLACK;
        
        //右边变黑
        root.right.color = BLACK;
    }
    
    
    /**
     * 这是一颗左倾(left-leaning,即红链接都在左边)的红黑树
     * 当然也可以右倾的,主要理解这里使用红链接替换了3-节点
     * 处理了3中情况,对于当前节点h有:
     * 1. root.right=red && root.left =black => 左旋转
     * 2. root.left =red && root.left.left =red => 右旋转
     * 3. root.right = red && root.left =red =>颜色提升
     * 树的实现一定要有图形
     *
     * @param root  子树的根节点
     * @param key   插入的键
     * @param value 插入的值
     * @return 插入后树的根节点
     */
    private Node put(Node root, Key key, Value value) {
        if (root == null) {
            return new Node(key, value, 1, RED);
        }
        int cmp = root.key.compareTo(key);
        if (cmp > 0) {
            root.left = put(root.left, key, value);
        } else if (cmp < 0) {
            root.right = put(root.right, key, value);
        } else {
            root.val = value;
        }
        
        //根据三种情况旋转
        if (isRed(root.right) && !isRed(root.left)) {
            root = rotateLeft(root);
        }
        if (isRed(root.left) && isRed((root.left))) {
            root = rotateRight(root);
        }
        if (isRed(root.left) && isRed(root.right)) {
            flipColors(root);
        }
        root.N = size(root.left) + size(root.right) + 1;
        return root;
    }
    
    
    public void put(Key key, Value value) {
        root = put(root, key, value);
        //不要忘了换掉根节点的颜色.
        root.color = BLACK;
    }
    
    public Value get(Key key) {
        return get(root, key);
    }
    
    private Value get(Node root, Key key) {
        if (root == null) {
            return null;
        }
        int cmp = root.key.compareTo(key);
        if (cmp < 0) {
            return get(root.right, key);
        } else if (cmp > 0) {
            return get(root.left, key);
        } else {
            return root.val;
        }
    }
    
    //todo
    public void delete(Key key) {
    
    }
    public static void main(String[] args) {
        
        RedBlackBST<String, String> bst = new RedBlackBST<>();
        bst.put("a", "a");
        bst.put("b", "b");
        bst.put("c", "c");
        bst.put("d", "d");
        String a = bst.get("a");
        System.out.println(a);
    }
    
}
