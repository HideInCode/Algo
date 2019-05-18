package Graphs;

import Fundamentals.imp.BagByLinkedList;
import Fundamentals.utils.In;

/**
 * 图 我们为每个顶点标上序号,当做唯一标记.
 * edge 边
 * vertex 顶点
 * adjacency 邻接
 */
public class Graph {

    private final int V;
    private int E;

    //邻接表数组
    private BagByLinkedList<Integer>[] adj;

    //含有v个顶点不含有边的图
    public Graph(int V) {
        this.V = V;
        this.E = 0;

        adj = (BagByLinkedList<Integer>[]) new BagByLinkedList[V];

        for (int v = 0; v < V; v++) {
            adj[v] = new BagByLinkedList<>();
        }
    }

    //从输入流中读入图
    public Graph(In in) {
        this(in.readInt());

        int E = in.readInt();
        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();

            addEdge(v, w);
        }

    }

    //顶点
    public int V() {
        return V;
    }

    //边数
    public int E() {
        return E;
    }

    //向图中添加一条v-w
    public void addEdge(int v, int w) {

        //互为节点
        adj[v].add(w);
        adj[w].add(v);

        E++;
    }

    //和v相邻的所有顶点
    Iterable<Integer> adj(int v) {
        return adj[v];
    }


    //计算v的度数
    public static int degree(Graph G, int v) {
        int degree = 0;
        for (Integer w : G.adj(v)) {
            degree++;
        }
        return degree;

    }

    //找出所有顶点的最大度数
    public static int maxDegree(Graph graph) {
        int max = 0;
        for (int v = 0; v < graph.V(); v++) {
            if (degree(graph, v) >= max) {
                max = degree(graph, v);
            }
        }

        return max;
    }

    //计算所有顶点的平均度数
    public static double avgDegree(Graph graph) {
        return 2.0 * graph.E() / graph.V();
    }

    //计算自环个数(终点和起点都是一个顶点,中间不经过其他顶点)
    public static int numberOfSelfLoops(Graph graph) {
        int count = 0;

        for (int v = 0; v < graph.V(); v++) {
            for (int w : graph.adj(v)) {
                if (w == v) {
                    count++;
                }
            }
        }
        return count;
    }


    @Override
    public String toString() {

        String s = V + " vertices, " + E + " edge\n";
        for (int i = 0; i < V; i++) {
            s += i + ": ";
            for (Integer w : this.adj(i)) {
                s += w + " ";
            }
            s += "\n";
        }

        return s;
    }
}
