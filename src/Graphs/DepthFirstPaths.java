package Graphs;


import java.util.Stack;

/**
 * 基于深度优先的路径查找算法
 */
public class DepthFirstPaths {

    //该节点是否调用过dfs
    private boolean[] marked;

    //从起点到一个顶点已知路径上的最后一个顶点
    private int[] edgeTo;
    private final int s;

    public DepthFirstPaths(Graph graph, int s) {
        marked = new boolean[graph.V()];
        edgeTo = new int[graph.V()];
        this.s = s;
        dfs(graph, s);
    }

    private void dfs(Graph graph, int v) {
        marked[v] = true;
        for (Integer w : graph.adj(v)) {
            if (!marked[w]) {
                //v->w
                edgeTo[w] = v;
                dfs(graph, w);
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }

        Stack<Integer> path = new Stack<>();
        for (int x = v; x != s; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(s);

        return path;
    }

}
