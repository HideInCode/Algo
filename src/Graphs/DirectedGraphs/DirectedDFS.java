package Graphs.DirectedGraphs;

import Fundamentals.api.Bag;
import Fundamentals.imp.BagByLinkedList;
import Fundamentals.utils.In;
import Fundamentals.utils.StdOut;

/**
 * 利用深度优先遍历,提供一个检测有向图-------可达性接口marked(int v);
 * 数据结构:有向图
 * 算法:深度优先遍历
 */
public class DirectedDFS {
    private boolean[] marked;

    /**
     * 在digraph中找到s所有能去的点
     *
     * @param digraph 在这个图中找
     * @param v       起始顶点
     */
    public DirectedDFS(Digraph digraph, int v) {
        marked = new boolean[digraph.V()];
        dfs(digraph, v);
    }


    /**
     * 在G中找到从sources中的所有顶点可达的所有顶点
     *
     * @param G
     * @param sources
     */
    public DirectedDFS(Digraph G, Iterable<Integer> sources) {
        marked = new boolean[G.V()];
        for (Integer source : sources) {
            if (!marked[source]) {
                dfs(G, source);
            }
        }

    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;

        for (Integer w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }


    public boolean marked(int v) {
        return marked[v];
    }

    public static void main(String[] args) {
        Digraph G = new Digraph(new In(args[0]));
        Bag<Integer> sources = new BagByLinkedList<>();
        for (int i = 1; i < args.length; i++) {
            sources.add(Integer.parseInt(args[i]));
        }


        DirectedDFS reachable = new DirectedDFS(G, sources);

        for (int v = 0; v < G.V(); v++) {
            if (reachable.marked(v)) {
                StdOut.print(v + " ");
            }
        }
        StdOut.println();
    }

}
