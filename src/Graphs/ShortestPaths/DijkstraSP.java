package Graphs.ShortestPaths;

import Sorting.PriorityQueues.IndexMinPQ;

import java.util.Stack;

/**
 * 迪杰斯特拉算法
 * 求非负加权有向图的最短路径问题
 */
public class DijkstraSP {
    private DirectedEdge[] edgeTo;

    private double[] distTo;
    private IndexMinPQ<Double> pq;

    public DijkstraSP(EdgeWeightedDigraph G, int s) {
        edgeTo = new DirectedEdge[G.V()];
        distTo = new double[G.V()];
        pq = new IndexMinPQ<>(G.V());
        for (int v = 0; v < G.V(); v++) {
            //初始状态都是达不到
            distTo[v] = Double.POSITIVE_INFINITY;
        }

        distTo[s] = 0.0;
        pq.insert(s, 0.0);
        while (!pq.isEmpty()) {
            relax(G, pq.delMin());
        }
    }

    private void relax(EdgeWeightedDigraph G, int v) {
        for (DirectedEdge e : G.adj(v)) {
            int w = e.to();
            //s->w的距离和(s->v的距离+当前边e)作比较,当前e小了的话,更新数据.反之认为e失效了
            if (distTo[w] > distTo[v] + e.weight()) {
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
                if (pq.contains(w)) {
                    pq.changeKey(w, distTo[w]);
                } else {
                    pq.insert(w, distTo[w]);
                }
            }
        }
    }


    /**
     * 起点s到v的距离
     *
     * @param v 终点
     * @return
     */
    public double distTo(int v) {
        return distTo[v];
    }

    /**
     * @param v 终点
     * @return 是否有到顶点v的路径
     */
    public boolean hasPathTo(int v) {
        return distTo(v) < Double.POSITIVE_INFINITY;
    }


    //顶点s到v的路径,不存在就返回为null
    Iterable<DirectedEdge> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }
        Stack<DirectedEdge> path = new Stack();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
            path.push(e);
        }
        return path;
    }
}
