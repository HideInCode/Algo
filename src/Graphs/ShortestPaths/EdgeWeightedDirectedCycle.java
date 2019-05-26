package Graphs.ShortestPaths;

import java.util.Stack;

/**
 * 有向加权图的环判断
 */
public class EdgeWeightedDirectedCycle {
    private boolean[] marked;
    private DirectedEdge[] edgeTo;
    private boolean[] onStack;
    private Stack<DirectedEdge> cycle;

    public EdgeWeightedDirectedCycle(EdgeWeightedDigraph G) {
        marked = new boolean[G.V()];
        onStack = new boolean[G.V()];
        edgeTo = new DirectedEdge[G.V()];

        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                dfs(G, v);
            }
        }
        assert check();
    }

    private boolean check() {
        if (hasCycle()) {
            DirectedEdge first = null;
            DirectedEdge last = null;
            for (DirectedEdge e : cycle()) {
                if (first == null) {
                    first = e;
                }
                if (last != null) {
                    if (last.to() != e.from()) {
                        System.err.printf(" cycle edges %s and %s not incident\n", last, e);
                        return false;
                    }
                }
                last = e;
            }

            if (last.to() != first.from()) {
                System.err.printf("cycle edges %s and %s not incident\n", last, first);
                return false;
            }
        }
        return true;
    }

    private void dfs(EdgeWeightedDigraph g, int v) {
        onStack[v] = true;
        marked[v] = true;

        for (DirectedEdge e : g.adj(v)) {
            int w = e.to();
            if (cycle != null) {
                return;
            } else if (!marked[w]) {
                edgeTo[w] = e;
                dfs(g, w);
            } else if (onStack[w]) {
                cycle = new Stack<>();

                DirectedEdge f = e;
                while (f.from() != w) {
                    cycle.push(f);
                    f = edgeTo[f.from()];
                }
                cycle.push(f);
                return;
            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<DirectedEdge> cycle() {
        return cycle;
    }


}


