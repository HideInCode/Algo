package Graphs.MinimumSpanningTrees;

import Sorting.PriorityQueues.IndexMinPQ;

/**
 * 最小生成树的Prim算法(普利姆算法) 即时版本
 * 区别于延时版本 这里会即时删除无用的横切边
 * 这次利用索引优先队列,只保留当前顶点的每个节点中,离树最近的边.
 */
public class PrimMST {
    //距离树最近的边
    private Edge[] edgeTo;

    //distTo[w]=edgeTo[w].weight()
    private double[] distTo;

    private boolean[] marked;

    //有效的横切边
    private IndexMinPQ<Double> pq;

    public PrimMST(EdgeWeightedGraph G) {
        edgeTo = new Edge[G.V()];
        distTo = new double[G.V()];
        marked = new boolean[G.V()];

        for (int v = 0; v < G.V(); v++) {
            //无穷大
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        pq = new IndexMinPQ<>(G.V());
        distTo[0] = 0.0;
        pq.insert(0, 0.0);
        while (!pq.isEmpty()) {
            //最近的顶点加入树中
            visit(G, pq.delMin());
        }
    }


    private void visit(EdgeWeightedGraph G, int v) {
        marked[v] = true;
        for (Edge e : G.adj(v)) {
            int w = e.other(v);
            if (marked[w]) {
                continue;
            }
            if (e.weight() < distTo[w]) {
                //连接w和树的最佳边Edge变为e
                edgeTo[w] = e;
                distTo[w] = e.weight();

                //找到更小的横切边后,放入优先队列中,并且设置为最优先
                if (pq.contains(w)) {
                    pq.change(w, distTo[w]);
                } else {
                    pq.insert(w, distTo[w]);
                }
            }
        }
    }

    public Iterable<Edge> edges() {
        return null;
    }


    public double weight() {
        return 0;
    }

}
