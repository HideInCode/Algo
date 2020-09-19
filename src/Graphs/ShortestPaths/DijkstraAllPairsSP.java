package Graphs.ShortestPaths;

/**
 * 任意顶点对之间的最短路径
 * 保存图的每个顶点为起点的查找数组,直接查找任意两个顶点的路径以及距离.
 */
public class DijkstraAllPairsSP {

    private DijkstraSP[] all;

    public DijkstraAllPairsSP(EdgeWeightedDigraph G) {
        all = new DijkstraSP[G.V()];
        for (int v = 0; v < G.V(); v++) {
            all[v] = new DijkstraSP(G, v);
        }
    }

    Iterable<DirectedEdge> path(int s, int t) {
        return all[s].pathTo(t);
    }

    double dist(int s, int t) {
        return all[s].distTo(t);
    }
    

}
