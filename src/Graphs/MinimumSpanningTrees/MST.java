package Graphs.MinimumSpanningTrees;


import Fundamentals.utils.In;
import Fundamentals.utils.StdOut;

/**
 * 最小生成树
 * 生成树:图的所有顶点的无环连通子图
 * 最小生成树:生成树中所有边的权重值加起来最小的.
 */
public class MST {
    public MST(EdgeWeightedGraph G) {
    }

    Iterable<Edge> edges() {
        return null;
    }

    double weight() {
        return 0;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        EdgeWeightedGraph G = new EdgeWeightedGraph(in);

        MST mst = new MST(G);
        for (Edge e : mst.edges()) {
            StdOut.println(e);
        }

        StdOut.println(mst.weight());
    }

}
