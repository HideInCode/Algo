package Graphs.UndirectedGraphs;

import Fundamentals.api.Queue;
import Fundamentals.api.Stack;
import Fundamentals.imp.QueueByLinkedList;
import Fundamentals.imp.StackByLinkedList;
import Fundamentals.utils.In;
import Fundamentals.utils.StdOut;


/**
 * 广度优先搜索
 */
public class BreadthFirstPaths {

    //到达该顶点的最短路径是已知的吗?
    private boolean[] marked;
    private int[] edgeTo;
    private final int start;

    public BreadthFirstPaths(Graph graph, int start) {
        marked = new boolean[graph.V()];
        edgeTo = new int[graph.V()];
        this.start = start;
        bfs(graph, start);
    }

    /**
     * 广度优先搜索(所以说搜索是各类的算法的基本)
     * 1.有记号集合,一般用boolean[]
     * 2.每到一个顶点,把未标记的相邻顶点全部加入队列.
     * 3.和深度有限不同的是,每次都要把所有没有标记的节点全都遍历.
     * 4.所以要把队列中的全都放出来,再把队列中的元素的相邻未标记顶点放入队列.
     * 5.重复上述操作.
     *
     * @param graph
     * @param s
     */
    private void bfs(Graph graph, int s) {
        Queue<Integer> queue = new QueueByLinkedList<>();
        marked[s] = true;
        queue.enqueue(s);

        while (!queue.isEmpty()) {
            int v = queue.dequeue();
            for (Integer w : graph.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    marked[w] = true;
                    queue.enqueue(w);
                }
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

        Stack<Integer> path = new StackByLinkedList<>();
        for (int x = v; x != start; x = edgeTo[x]) {
            path.push(x);
        }

        path.push(start);

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
