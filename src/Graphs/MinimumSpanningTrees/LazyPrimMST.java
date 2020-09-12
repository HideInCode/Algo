package Graphs.MinimumSpanningTrees;

import Fundamentals.api.Queue;
import Fundamentals.imp.QueueByLinkedList;
import Fundamentals.utils.In;
import Fundamentals.utils.StdOut;
import Sorting.PriorityQueues.MinPQ;

/**
 * 最小生成树 Prim算法 延时实现 贪心算法 bfs
 * 1.最小生成树的特点:加一条边则成环,少一条边则成分成两个树
 * 2.横切边:对于图中,能有把一个图分割成两个图的所有边.即连接加入树和未加入树的两个部分顶点的边.
 * 3.要维护三个数据结构分为顶点,最小生成树的边,横切边
 * 4.顶点(是否已经在树中)->横切边(所有邻接边)->权重最小的边(已在树中的边不参与比较)->下一个顶点.
 * 广度优先的找,从起点开始,找到起点邻接边的所有边,利用优先队列找出最小的边,把另一个顶点做起点,再次遍历...
 */
public class LazyPrimMST {

    //标记当前顶点是否已经属于树
    private boolean[] marked;

    //保存最小生成树的边
    private Queue<Edge> mst;

    //横切边(包括失效的边)
    private MinPQ<Edge> pq;

    public LazyPrimMST(EdgeWeightedGraph G) {
        pq = new MinPQ<>();
        marked = new boolean[G.V()];
        mst = new QueueByLinkedList<>();

        //从序号为0的顶点开始,把所有邻接边加入pq
        visit(G, 0);
        while (!pq.isEmpty()) {
            //弹出最小的边
            Edge e = pq.delMin();

            //一个顶点
            int v = e.either();

            //另一个顶点
            int w = e.other(v);

            //如果两个顶点都被标记,说明这条边已经在树中,放弃这个边.
            if (marked[v] && marked[w]) {
                continue;
            }

            //只要有一个没有被标记,边加入树.
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
