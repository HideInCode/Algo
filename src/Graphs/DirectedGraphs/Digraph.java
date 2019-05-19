package Graphs.DirectedGraphs;

import Fundamentals.api.Bag;
import Fundamentals.imp.BagByLinkedList;
import Fundamentals.utils.In;

/**
 * 有向图
 */
public class Digraph {
    private final int V;

    private int E;
    private Bag<Integer>[] adj;


    /**
     * 创建一副含有V个顶点但没有边的有向图
     *
     * @param V
     */
    public Digraph(int V) {
        this.V = V;
        this.E = 0;

        adj = new BagByLinkedList[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new BagByLinkedList<>();
        }
    }

    /**
     * 从输入流中读取一副有向图
     * 第一行:顶点个数
     * 第二行:边的个数
     *
     * @param in
     */
    public Digraph(In in) {
        this(in.readInt());
        int E = in.readInt();
        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();

            addEdge(v, w);
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    /**
     * 向有向图中添加一条边v->w
     *
     * @param v
     * @param w
     */
    public void addEdge(int v, int w) {
        adj[v].add(w);
        E++;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    /**
     * 反转反向
     * 思路就是反向的添加一遍边
     *
     * @return
     */
    public Digraph reverse() {
        Digraph digraph = new Digraph(V);
        for (int v = 0; v < V(); v++) {
            for (Integer w : adj(v)) {
                digraph.addEdge(w, v);
            }
        }

        return digraph;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append('}');
        return sb.toString();
    }
}
