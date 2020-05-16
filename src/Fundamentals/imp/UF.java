package Fundamentals.imp;

import Fundamentals.utils.StdIn;
import Fundamentals.utils.StdOut;

import java.util.Arrays;

/**
 * union-find
 * 解决节点连通性检测问题
 * 使用线性结构会是的union到达O(n) 合并n个会到达O(n^2)
 * 使用简单的树结构,可能会使得树变成线性的,从而也是O(n)
 * 使用加权树 保证平衡性 O(lgN) perfect! 即:路径压缩
 */
public class UF {
    //下标是当前节点编号,值是父节点的编号
    private int[] id;
    //记录分量大小,用于压缩路径
    private int[] size;
    private int count;
    
    public UF(int N) {
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
        size = new int[N];
        Arrays.fill(size, 1);
    }
    
    public int count() {
        return count;
    }
    
    /**
     * 并查集的目的就是检验连通性的
     *
     * @param p
     * @param q
     * @return
     */
    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }
    
    public int find(int p) {
        return root(p);
    }
    
    //找到根节点的值
    private int root(int i) {
        //祖先不是自己
        while (i != id[i]) {
            //找祖先
            i = id[i];
        }
        return i;
    }
    
    //添加时保证是一颗平衡树
    public void union(int p, int q) {
        int pRoot = root(p);
        int qRoot = root(q);
        if (pRoot == qRoot) {
            return;
        }
        
        if (size[pRoot] > size[qRoot]) {
            id[qRoot] = pRoot;
            size[qRoot] += size[pRoot];
        } else {
            id[pRoot] = qRoot;
            size[pRoot] += size[qRoot];
        }
        id[pRoot] = qRoot;
        count--;
    }
    
    public static void main(String[] args) {
        int N = StdIn.readInt();
        UF uf = new UF(N);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (uf.connected(p, q)) {
                continue;
            }
            
            uf.union(p, q);
            StdOut.println(p + "" + q);
        }
        StdOut.println(uf.count() + "components");
    }
}
