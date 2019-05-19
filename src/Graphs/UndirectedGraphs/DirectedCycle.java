package Graphs.UndirectedGraphs;

import Fundamentals.imp.StackByLinkedList;
import Graphs.DirectedGraphs.Digraph;

/**
 * 由于有向环存在时,任务调度不合理.
 * 因此找出有向环就成了一个重要任务.
 */
public class DirectedCycle {

    private boolean[] marked;
    private int[] edgeTo;
    private StackByLinkedList<Integer> cycle;
    private boolean[] onStack;

    public DirectedCycle(Digraph G) {
        onStack = new boolean[G.V()];
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                dfs(G, v);
            }
        }
    }


    private void dfs(Digraph G, int v) {
        onStack[v] = true;
        marked[v] = true;
        for (Integer w : G.adj(v)) {
            if (this.hasCycle()) {
                return;
            } else if (!marked[w]) {
                //v->w
                edgeTo[w] = v;
                dfs(G, w);

                //如果找到了一条v->w且w已经存在栈中,就是个环
            } else if (onStack[w]) {
                cycle = new StackByLinkedList<>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }

                cycle.push(w);
                cycle.push(v);
            }
        }
        onStack[v] = false;

    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }

}
