package Graphs.UndirectedGraphs;

/**
 * 给定图是无环图吗
 * 这里的处理不包括自环,平行边
 */
public class Cycle {

    private boolean[] marked;

    private boolean hasCycle;

    public Cycle(Graph graph) {
        marked = new boolean[graph.V()];
        for (int v = 0; v < graph.V(); v++) {
            if (!marked[v]) {

            }
        }
    }


    private void dfs(Graph graph, int v, int u) {
        marked[v] = true;
        for (Integer w : graph.adj(v)) {
            if (!marked[w]) {
                dfs(graph, w, v);
            } else if (w != u) {
                hasCycle = true;
            }
        }
    }


    public boolean isHasCycle() {
        return hasCycle;
    }
}
