package Graphs.UndirectedGraphs;

/**
 * 深度优先搜索
 */
public class DepthFirstSearch {

    private boolean[] marked;
    private int count;

    public DepthFirstSearch(Graph graph, int s) {
        marked = new boolean[graph.V()];
        dfs(graph, s);
    }

    /**
     * 深度优先步骤
     * 1.有个标记容器来给每个顶点做记号,一般用boolean[]
     * 2.每次前进一个顶点,标记,对邻接顶点判断是否来过
     * 3.找到第一个没标记过的,就往前走
     * 4.标记过就跳过
     *
     * @param graph
     * @param v
     */
    private void dfs(Graph graph, int v) {
        marked[v] = true;
        count++;
        for (Integer w : graph.adj(v)) {

            if (!marked[w]) {
                dfs(graph, w);
            }
        }
    }

    private int count() {
        return count;
    }

}
