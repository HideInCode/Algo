package Graphs.DirectedGraphs;

import Fundamentals.api.Bag;
import Fundamentals.imp.BagByLinkedList;
import Fundamentals.utils.In;
import Fundamentals.utils.StdOut;

/**
 * 可达性
 * 顺藤摸瓜
 */
public class DirectedDFS {
    private boolean[] marked;

    /**
     * 在digraph中找到s所有能去的点
     *
     * @param digraph 在这个图中找
     * @param s       一个顶点
     */
    public DirectedDFS(Digraph digraph, int s) {
        marked = new boolean[digraph.V()];
        dfs(digraph, s);
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
