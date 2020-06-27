package Searching;

/**
 * 红黑树,又叫做平衡-二叉查找树,可以看做是2-3树的实现
 * 目的是为了用二叉树的查找速度,2-3树的插入速度.
 * 通过定义红链接和黑链接,把一个平衡的2-3树化为红黑树.来优化在二叉树查找树最坏情况下的插入和删除情况.
 * 具体操作图见pdf(尤其是删除)
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
            return x.size;
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
        int size;
        
        //父节点指向当前节点的连接颜色.
        boolean color;
        
        public Node(Key key, Value val, int n, boolean color) {
            this.key = key;
            this.val = val;
            size = n;
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
     * @param root
     * @return 旋转后正确的root的地址
     */
    private Node rotateLeft(Node root) {
        //整理黑链接
        Node target = root.right;
        root.right = target.left;
        target.left = root;
        
        //重新定义红链接
        target.color = target.left.color;
        target.left.color = RED;
        
        //校正调整后节点的个数
        target.size = root.size;
        root.size = 1 + size(root.left) + size(root.right);
        
        return target;
    }
    
    
    /**
     * 右旋转
     * 把右链接变成红链接
     *
     * @param root 需要坐旋转的子树的根节点
     * @return 旋转后的子树根节点
     */
    private Node rotateRight(Node root) {
        Node target = root.left;
        root.left = target.right;
        target.right = root;
        
        target.color = target.right.color;
        target.right.color = RED;
        
        target.size = root.size;
        root.size = 1 + size(root.left) + size(root.right);
        
        return target;
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
        int cmp = key.compareTo(root.key);
        if (cmp < 0) {
            root.left = put(root.left, key, value);
        } else if (cmp > 0) {
            root.right = put(root.right, key, value);
        } else {
            root.val = value;
        }
        
        //根据三种情况旋转
        
        //右红左黑 不符合左倾原则
        if (isRed(root.right) && isBlack(root.left)) {
            root = rotateLeft(root);
        }
        
        //左边连续两个都是红的 不符合不能有两个红链接原则
        if (isRed(root.left) && isRed((root.left.left))) {
            root = rotateRight(root);
        }
        
        //同样不符合两个红链接原则
        if (isRed(root.left) && isRed(root.right)) {
            flipColors(root);
        }
        root.size = size(root.left) + size(root.right) + 1;
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
    
    private Node balance(Node root) {
        if (isRed(root.right)) {
            root = rotateLeft(root);
        }
        if (isRed(root.left) && isRed(root.left.left)) {
            root = rotateRight(root);
        }
        if (isRed(root.left) && isRed(root.right)) {
            flipColor(root);
        }
        
        return root;
    }
    
    private Node moveRedToRight(Node root) {
        flipColor(root);
        //兄弟节点是2-节点
        if (!isRed(root.left.left)) {
            root = rotateRight(root);
            flipColor(root);
        }
        return root;
    }
    
    private void flipColor(Node root) {
        root.color = !root.color;
        root.left.color = !root.left.color;
        root.right.color = !root.right.color;
    }
    
    /**
     * 删除最小键
     * 删除2-3树中的3节点很简单,删除2-节点很麻烦,所以不删除2-节点,即不删除红黑树中的黑色节点
     * 删除最小就是沿着左连接往下找,
     */
    public void deleteMin() {
        if (isBlack(root.left) && isBlack(root.right)) {
            root.color = RED;
        }
        root = deleteMin(root);
        if (root != null) {
            root.color = BLACK;
        }
    }
    
    private Node deleteMin(Node root) {
        if (root.left == null) {
            return null;
        }
        
        //当前节点是2-节点
        if (isBlack(root.left) && isBlack(root.left.left)) {
            root = moveRedToLeft(root);
        }
        root.left = deleteMin(root.left);
        return balance(root);
    }
    
    private boolean isBlack(Node root) {
        return !isRed(root);
    }
    /**
     * 左边2-节点借节点的方法
     * @param root
     * @return
     */
    private Node moveRedToLeft(Node root) {
        //合并当前节点和左右子节点
        flipColor(root);
    
        //若右边不是2-节点,可以借
        if (isRed(root.right.left)) {
            //右旋root.right,是的root.right root.right.right root.left 都变成红(其实这里已经出现了5-节点)
            root.right = rotateRight(root.right);
            //左旋root 把上一步的节点从右边借过来
            root = rotateLeft(root);
            //分解当前节点和左右子节点
            flipColor(root);
        }
        return root;
    }
    
    //删除最大节点,回复根节点颜色
    public void deleteMax() {
        root = deleteMax(root);
        root.color = BLACK;
    }
    
    /**
     * 删除最大节点并返回删除后的根节点
     * 1.向父节点借 就染色
     * 2.向兄弟节点借 就右旋
     *
     * @param root
     * @return
     */
    private Node deleteMax(Node root) {
        
        //需要左边挪一个过来,不然右边少一个后会不平衡
        if (isRed(root.left)) {
            root = rotateRight(root);
        }
        
        //用null替换right,实现删除
        if (root.right == null) {
            return null;
        }
        
        //这个是个二节点,需要接节点
        if (isBlack(root.right) && isBlack(root.right.left)) {
            root = moveRedToRight(root);
        }
        //继续往右边走
        root.right = deleteMax(root.right);
        
        //最后重新调整成平衡状态
        return balance(root);
    }
    
    /**
     * 删除操作 用右子树最大或者左子树最小来李代桃僵 保证有序性
     * @param key
     */
    public void delete(Key key) {
        if (isBlack(root.left) && isBlack(root.right)) {
            root.color = RED;
        }
        root = delete(root, key);
        if (root != null) {
            root.color = BLACK;
        }
    }
    
    private Node delete(Node root, Key key) {
        if (key.compareTo(root.key) < 0) {
            //2-节点借节点
            if (isBlack(root.left) && isBlack(root.left.left)) {
                root = moveRedToLeft(root);
            }
            root.left = delete(root.left,key);
        } else {
            //同删除最大中的平衡操作
            if (isRed(root.left)) {
                root = rotateRight(root);
            }
            //要删除的键是最底层 直接干掉
            if (key.compareTo(root.key) == 0 && root.right == null) {
                return null;
            }
    
            //排除了最底层后, 就可以李代桃僵操作法
            if (key.compareTo(root.key) == 0) {
                Node min = min(root.right);
                root.key = min.key;
                root.val = min.val;
                root.right = deleteMin(root.right);
            } else {
                root.right = delete(root.right, key);
            }
        }
        return balance(root);
    }
    
    private Node min(Node root) {
        if (root.left == null) {
            return root;
        }
        return min(root.left);
    }
    public void print() {
        print(root);
    }
    private void print(Node root) {
        if (root == null) {
            return;
        }
        print(root.left);
        System.out.println(root.val);
        print(root.right);
    }
    public static void main(String[] args) {
        
        RedBlackBST<String, String> bst = new RedBlackBST<>();
        bst.put("a", "a");
        bst.put("b", "b");
        bst.put("c", "c");
        bst.put("d", "d");
//        String a = bst.get("a");
//        System.out.println(a);
//        bst.deleteMax();
//        bst.deleteMin();
        bst.delete("b");
        bst.print();
        
        
    }
    
}
