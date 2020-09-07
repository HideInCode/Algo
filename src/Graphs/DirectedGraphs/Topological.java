package Graphs.DirectedGraphs;

import Fundamentals.utils.StdOut;
import Graphs.ShortestPaths.EdgeWeightedDigraph;
import Graphs.ShortestPaths.EdgeWeightedDirectedCycle;

/**
 * 拓扑排序 有向无环图的逆序就是拓扑排序
 * 拓扑是研究几何图形或空间在连续 改变形状后还能保持不变的一些性质的一个学科.
 * 只考虑物体间的位置关系,不考虑他们的形状和大小.
 * 关键:
 * 1.指定任务优先级
 * 2.检测图中的环,保证是DAG
 * 3.拓扑排序
 */
public class Topological {

    //顶点的拓扑排序
    private Iterable<Integer> order;

    public Topological(Digraph G) {
        DirectedCycle cycleFinder = new DirectedCycle(G);
        if (!cycleFinder.hasCycle()) {
            DepthFirstOrder dfs = new DepthFirstOrder(G);
            order = dfs.reversePost();
        }
    }

    public Topological(EdgeWeightedDigraph G) {
        EdgeWeightedDirectedCycle cycleFinder = new EdgeWeightedDirectedCycle(G);
        if (!cycleFinder.hasCycle()) {
            DepthFirstOrder dfs = new DepthFirstOrder(G);
            order = dfs.reversePost();
        }
    }

    //是个有向无环图吗
    public boolean isDAG() {
        return hasOrder();
    }

    public boolean hasOrder() {
        return order != null;
    }

    //拓扑有序的所有顶点
    public Iterable<Integer> order() {
        return order;
    }

    public static void main(String[] args) {
        String fileName = args[0];
        String separator = args[1];

        SymbolDigraph sg = new SymbolDigraph(fileName, separator);
        Topological top = new Topological(sg.G());


        for (Integer v : top.order()) {
            StdOut.println(sg.name(v));
        }
    }
}
