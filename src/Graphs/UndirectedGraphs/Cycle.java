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
                dfs(graph, v, v);
            }
        }
    }
    
    
    /**
     *
     * @param g
     * @param v 当前节点
     * @param u 是当前节点父节点
     */
    private void dfs(Graph g , int v, int u) {
        marked[v] = true;
        for (Integer w : g.adj(v)) {
            //没有访问过的接着访问
            if (!marked[w]) {
                dfs(g, w, v);
            } else if (w != u) {
                //已经访问过的,且不是当前节点父节点,说明存在环
                hasCycle = true;
                break;
            }
        }
    }


    public boolean isHasCycle() {
        return hasCycle;
    }
}
