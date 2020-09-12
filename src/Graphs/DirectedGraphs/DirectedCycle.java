package Graphs.DirectedGraphs;

import Fundamentals.imp.StackByLinkedList;

/**
 * 由于有向环存在时,任务调度不合理.
 * 因此找出有向环就成了一个重要任务.
 */
public class DirectedCycle {
    private boolean[] marked;
    private int[] edgeTo;
    //环上所有的顶点
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
            //如果有环
            if (cycle != null) {
                return;
            } else if (!marked[w]) {
                //v->w
                edgeTo[w] = v;
                dfs(G, w);
        
                //如果找到了一条v->w且w已经存在栈中,就是个环
            } else if (onStack[w]) {
                cycle = new StackByLinkedList<>();
                //v->..->w的路径上的顶点
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
