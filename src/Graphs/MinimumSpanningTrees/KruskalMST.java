package Graphs.MinimumSpanningTrees;

import Fundamentals.imp.QueueByLinkedList;
import Fundamentals.imp.UF;
import Sorting.PriorityQueues.MinPQ;

/**
 * 最小生成树的Kruskal算法(克鲁斯卡尔算法)
 * 1.找到所有的边,按权重排序
 * 2.小的边先连接,生成多个数
 * 3.多个树相连,知道只剩下最后一棵树,就得到了最小生成树
 */
public class KruskalMST {
    private QueueByLinkedList<Edge> mst;

    public KruskalMST(EdgeWeightedGraph G) {
        mst = new QueueByLinkedList<>();
        MinPQ<Edge> pq = new MinPQ<>();
        //所有的边放入优先队列
        for (Edge e : G.edges()) {
            pq.insert(e);
        }
        UF uf = new UF(G.V());
        while (!pq.isEmpty() && mst.size() < G.V() - 1) {
            Edge e = pq.delMin();
            int v = e.either();
            int w = e.other(v);
            if (uf.connected(v, w)) {
                continue;
            }

            uf.union(v, w);

            //连接最小边,放入最小生成树
            mst.enqueue(e);
        }
    }

    public Iterable<Edge> edges() {
        return mst;
    }


    public double weight() {
        double result = 0;
        for (Edge e : mst) {
            result += e.weight();
        }
        return result;
    }
}
