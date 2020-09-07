package Graphs.UndirectedGraphs;


import Fundamentals.imp.StackByLinkedList;
import Fundamentals.utils.In;
import Fundamentals.utils.StdOut;

/**
 * 基于深度优先的路径查找算法
 */
public class DepthFirstPaths {
    
    //是否已经到过该顶点
    private boolean[] marked;
    
    //从起点到一个顶点已知路径上的最后一个顶点: a->b->c edgeTo[c]为b
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
    
    /**
     * 把v->s中间的顶点装入栈中
     *
     * @param v
     * @return
     */
    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }
        
        StackByLinkedList<Integer> path = new StackByLinkedList<>();
        for (int x = v; x != s; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(s);
        
        return path;
    }
    
    public static void main(String[] args) {
        Graph g = new Graph(new In(args[0]));
        int start = Integer.parseInt(args[1]);
        DepthFirstPaths paths = new DepthFirstPaths(g, start);
        for (int i = 0; i < g.V(); i++) {
            StdOut.print(start + " to " + i + ": ");
            if (paths.hasPathTo(i)) {
                for (Integer v : paths.pathTo(i)) {
                    if (v == start) {
                        StdOut.print(v);
                    } else {
                        StdOut.print("-" + v);
                    }
                }
            }
            StdOut.println();
        }
    }
}
