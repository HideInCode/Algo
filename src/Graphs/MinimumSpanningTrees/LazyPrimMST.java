package Graphs.MinimumSpanningTrees;

import Fundamentals.imp.QueueByLinkedList;
import Fundamentals.utils.In;
import Fundamentals.utils.StdOut;
import Sorting.PriorityQueues.MinPQ;

/**
 * 最小生成树 Prim算法 延时实现 贪心算法
 * 1.最小生成树的特点:加一条边则成环,少一条边则成分成两个树
 * 2.横切边:对于图中,能有把一个图分割成两个图的所有边.
 * 3.要分为顶点,最小生成树的边,横切边三个数据结构
 * 4.顶点(是否已经在树中)->横切边(所有邻接边)->权重最小的边(已在树中的边不参与比较)->下一个顶点.
 */
public class LazyPrimMST {

    //最小生成树的顶点
    private boolean[] marked;

    //最小生成树的边
    private QueueByLinkedList<Edge> mst;

    //横切边(包括失效的边) ps:这个数据结构的api不是我实现的,有时间自己实现一遍
    private MinPQ<Edge> pq;

    public LazyPrimMST(EdgeWeightedGraph G) {
        pq = new MinPQ<>();
        marked = new boolean[G.V()];
        mst = new QueueByLinkedList<>();

        //从序号为0的顶点开始
        visit(G, 0);
        while (!pq.isEmpty()) {
            //弹出最小的边
            Edge e = pq.delMin();

            //一个顶点
            int v = e.either();

            //另一个顶点
            int w = e.other(v);

            //如果两个顶点都被标记,说明已经在树中,跳过这次查找最小权重.
            if (marked[v] && marked[w]) {
                continue;
            }

            //只要有一个没有被标记,就成了最小生成树的一个顶点.
            mst.enqueue(e);

            //要是v没有被标记,那么标记v,并把v周围的边加入横切边
            if (!marked[v]) {
                visit(G, v);
            }

            //同理
            if (!marked[w]) {
                visit(G, w);
            }
        }
    }

    /**
     * 每次到达一个顶点,标记,并且把这个顶点周围的边都算横切边
     *
     * @param G 加权无向图
     * @param v 顶点序号
     */
    private void visit(EdgeWeightedGraph G, int v) {
        marked[v] = true;
        for (Edge e : G.adj(v)) {
            if (!marked[e.other(v)]) {
                pq.insert(e);
            }
        }
    }

    public Iterable<Edge> edges() {
        return mst;
    }

    //最小生成树的总和
    public double weight() {
        double result = 0;
        for (Edge e : mst) {
            result += e.weight();
        }
        return result;
    }

    //找个mediumEWG.txt,当做程序启动参数
    public static void main(String[] args) {
        In in = new In(args[0]);
        EdgeWeightedGraph G = new EdgeWeightedGraph(in);

        LazyPrimMST mst = new LazyPrimMST(G);
        for (Edge e : mst.edges()) {
            StdOut.println(e);
        }

        StdOut.println(mst.weight());
    }
}
