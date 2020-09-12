package Graphs.MinimumSpanningTrees;

import Fundamentals.api.Queue;
import Fundamentals.imp.QueueByLinkedList;
import Fundamentals.imp.UF;
import Fundamentals.utils.In;
import Fundamentals.utils.StdOut;
import Sorting.PriorityQueues.MinPQ;

/**
 * 最小生成树的Kruskal算法(克鲁斯卡尔算法)
 * 1.找到所有的边,按权重排序
 * 2.小的边先连接,生成多个数
 * 3.多个树相连,知道只剩下最后一棵树,就得到了最小生成树
 * 要用到两个数据结构 优先队列获取最小的边 并查集保证无环
 */
public class KruskalMST {
    Queue<Edge> mst;
    
    public KruskalMST(EdgeWeightedGraph g) {
        mst = new QueueByLinkedList<>();
        MinPQ<Edge> pq = new MinPQ<>();
        for (Edge edge : g.edges()) {
            pq.insert(edge);
        }
    
        UF uf = new UF(g.V());
        while (!pq.isEmpty() && mst.size() < g.V() - 1) {
            Edge edge = pq.delMin();
            int v = edge.either();
            int w = edge.other(v);
            if (uf.connected(v, w)) {
                continue;
            }
            mst.enqueue(edge);
            uf.union(v, w);
        }
    }
    
    public Iterable<Edge> edges() {
        return mst;
    }
    
    public double weight() {
        double res= 0;
        for (Edge edge : mst) {
            res += edge.weight();
        }
        return res;
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
