package Searching;

/**
 * 红黑树,又叫做有序-平衡-二叉查找树
 * 目的是为了用二叉树的查找速度,2-3树的插入速度.
 * 通过定义红链接和黑链接,把一个平衡的2-3树化为平衡二叉树.来优化在二叉树最坏情况下的插入情况.
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
     * 左旋转
     * 解决红链接是右链接的问题.
     * 原理就是:若某个节点(记作某个子树的根)的右链接是red,对于这个子树,我们认为根的指向不对,指向了小的key.
     * 调整根的指向 把key较大者作为根节点,并把黑链接正确分配.
     *
     * @param h 指向这个错误子树根的指针
     * @return 旋转后正确的root的地址
     */
    private Node rotateLeft(Node h) {
        //整理黑链接
        Node x = h.right;
        h.right = x.left;
        x.left = h;

        //重新定义红链接
        x.color = h.color;
        h.color = RED;

        //校正调整后节点的个数
        x.N = h.N;

        //比之前多一个
        h.N = 1 + size(h.left) + size(h.right);

        return x;
    }


    /**
     * 右旋转
     * 把右链接变成红链接
     *
     * @param h 需要坐旋转的子树的根节点
     * @return 旋转后的子树根节点
     */
    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;

        x.color = h.color;
        h.color = RED;

        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);

        return x;
    }


    /**
     * 转换颜色
     * <p>
     * 1.把根节点子节点的颜色染为红色
     * 2.将父节点的颜色由红变黑
     *
     * @param h 指向需要染色的子树根节点
     */
    private void flipColors(Node h) {

        //染根节点链接的颜色
        h.color = RED;

        //左边染黑
        h.left.color = BLACK;

        //右边变黑
        h.right.color = BLACK;
    }


    /**
     * 了解什么是平衡树,什么是2-3树,2-3节点插入新节点情况分类,红黑树的定义后才能去实现这个近乎完美的平衡二叉查找树.
     * 现在只需要左旋,右旋,变换颜色,就能实现这个算法,具体规则如下.
     * 1.right是red,left是black.进行左旋转.
     * 2.left是red,left.left使red,右旋转.
     * 3.left == right == red.全变黑.
     *
     * @param h     子树的根节点
     * @param key   插入的键
     * @param value 插入的值
     * @return 插入后树的根节点
     */
    private Node put(Node h, Key key, Value value) {

        //一般新节点都跟父节点用红链接进行连接
        if (h == null) {
            return new Node(key, value, 1, RED);
        }

        //老规矩,从根节点开始遍历.
        int cmp = key.compareTo(h.key);

        if (cmp < 0) {
            h.left = put(h, key, value);
        } else if (cmp > 0) {
            h.right = put(h, key, value);
        } else {
            h.val = value;
        }


        //实现连接后的颜色分类处理,如果把变色这部分提到比较大小之前,就是个自顶向下的算法.
        if (isRed(h.right) && !isRed(h.left)) {
            h = rotateLeft(h);
        }

        if (isRed(h.left) && isRed(h.left.left)) {
            h = rotateRight(h);
        }

        if (isRed(h.left) && isRed(h.right)) {
            flipColors(h);
        }


        //不要老是忘了重新计算节点个数
        h.N = size(h.left) + size(h.right) + 1;
        return h;
    }


    public void put(Key key, Value value) {
        root = put(root, key, value);

        //不要忘了换掉根节点的颜色.
        root.color = BLACK;
    }

    public static void main(String[] args) {

        System.out.println(Math.log(Math.E));

    }

}
