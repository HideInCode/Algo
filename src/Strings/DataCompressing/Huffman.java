package Strings.DataCompressing;

import Sorting.PriorityQueues.MinPQ;
import Strings.DataCompressing.BinaryUtils.BinaryStdIn;
import Strings.DataCompressing.BinaryUtils.BinaryStdOut;

/**
 * 霍夫曼树
 * 出现频率高的用更少的bit
 * 关键是要找到前缀码
 * 前缀码自然使用trie,只用叶子结点存值的话,每个值就不存在前缀.
 */
public class Huffman {
    //所用字符集的字符个数
    private static int R = 256;
    
    //霍夫曼查找树的节点
    private static class Node implements Comparable<Node> {
        
        //表示字符
        private char ch;
        
        //表示频率
        private int freq;
        private final Node left;
        private final Node right;
        
        public Node(char ch, int freq, Node left, Node right) {
            this.ch = ch;
            this.freq = freq;
            this.left = left;
            this.right = right;
        }
        
        
        //没有子节点的节点叫做叶子结点
        public boolean isLeaf() {
            return left == null && right == null;
        }
        
        //按照频率比较
        @Override
        public int compareTo(Node o) {
            return this.freq - o.freq;
        }
    }
    
    
    /**
     * 使用前缀码展开
     * 对于输入的二进制,遇到0往左,遇到1往右,找叶子结点;
     * 匹配到叶子结点就把叶子结点的值写出
     */
    public static void expand() {
        Node root = readTrie();
        int N = BinaryStdIn.readInt();
        
        for (int i = 0; i < N; i++) {
            
            //展开第i个编码对应的字母
            Node x = root;
            while (!x.isLeaf()) {
                if (BinaryStdIn.readBoolean()) {
                    x = x.right;
                } else {
                    x = x.left;
                }
            }
            BinaryStdOut.write(x.ch);
        }
        BinaryStdOut.close();
        
    }
    
    /**
     * 前序遍历查找
     *
     * @param x 开始节点
     */
    private static void writeTrie(Node x) {
        if (x.isLeaf()) {
            BinaryStdOut.write(true);
            BinaryStdOut.write(x.ch);
            return;
        }
        
        BinaryStdOut.write(false);
        writeTrie(x.left);
        writeTrie(x.right);
    }
    
    /**
     * 认真理解:
     * 读到1就读取字符的编码并创造一个叶子结点
     * 读到0就创建一个内部节点,并构造内部节点的左右子树
     */
    private static Node readTrie() {
        if (BinaryStdIn.readBoolean()) {
            return new Node(BinaryStdIn.readChar(), 0, null, null);
        }
        return new Node('\0', 0, readTrie(), readTrie());
    }
    
    
    private static String[] buildCode(Node root) {
        String[] st = new String[R];
        buildCode(st, root, "");
        return st;
    }
    
    /**
     * 构造一个编译表;即每个字符和他的比特字符相关联的表
     *
     * @param st 编译表
     * @param x  结点
     * @param s  字符
     */
    private static void buildCode(String[] st, Node x, String s) {
        if (x.isLeaf()) {
            st[x.ch] = s;
            return;
        }
        
        buildCode(st, x.left, s + '0');
        buildCode(st, x.right, s + '1');
    }
    
    /**
     * 利用字符出现的频率自底向上的构造一颗单词查找树
     * 即用频率最小的两个结点合成一个结点.
     *
     * @param freq
     * @return
     */
    private static Node buildTrie(int[] freq) {
        
        MinPQ<Node> pq = new MinPQ<>();
        //形成一个由频率结点构成的队列
        for (char c = 0; c < R; c++) {
            if (freq[c] > 0) {
                pq.insert(new Node(c, freq[c], null, null));
            }
        }
        
        //化森林为树的技巧
        while (pq.size() > 1) {
            Node x = pq.delMin();
            Node y = pq.delMin();
            //拿出x,y合成一个结点
            Node parent = new Node('\0', x.freq + y.freq, x, y);
            pq.insert(parent);
        }
        return pq.delMin();
    }
    
    /**
     * huffman压缩
     * 1.读取输入
     * 2.根据输入字符制频率成表格
     * 3.根据频率构造相应的huffman编码树
     * 4.构造编译表
     * 5.将单词查找树编码为bit字符串并写入输出流
     * 6.将单词总数编码为bit字符串并写入输出流
     * 7.使用编译表翻译每一个输入字符
     */
    public static void compress() {
        //读取输入
        String s = BinaryStdIn.readString();
        char[] input = s.toCharArray();
        
        //统计频率
        int[] freq = new int[R];
        for (char value : input) {
            freq[value]++;
        }
        
        //构造huffman树
        Node root = buildTrie(freq);
        
        //构造编译表,字符为数组索引,值为对应的bit字符
        String[] st = new String[R];
        buildCode(st, root, "");
        
        //打印解码用的单词查找树
        writeTrie(root);
        
        //打印字符总数
        BinaryStdOut.write(input.length);
        
        //使用霍夫曼编码处理
        for (char c : input) {
            String code = st[c];
            for (int j = 0; j < code.length(); j++) {
                if (code.charAt(j) == '1') {
                    BinaryStdOut.write(true);
                } else {
                    BinaryStdOut.write(false);
                }
            }
        }
        
        BinaryStdOut.close();
    }
    public static void main(String[] args){
        if (args[0].equals("-")) {
            compress();
        }
    
        if (args[0].equals("+")) {
            expand();
        }
    }
}
