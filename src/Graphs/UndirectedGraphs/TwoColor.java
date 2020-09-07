package Graphs.UndirectedGraphs;

/**
 * 有两种颜色,可以用这两种颜色让所有的相邻顶点颜色不同吗?
 */
public class TwoColor {
    private boolean[] marked;

    private boolean[] color;

    private boolean isTwoColorable = true;


    public TwoColor(Graph graph) {
        marked = new boolean[graph.V()];
        color = new boolean[graph.V()];

        for (int s = 0; s < graph.V(); s++) {
            if (!marked[s]) {
                dfs(graph, s);
            }
        }
    }

    private void dfs(Graph graph, int v) {
        marked[v] = true;
        for (Integer w : graph.adj(v)) {

            if (!marked[w]) {
                //相邻的染成不一样
                color[w] = !color[v];
                dfs(graph, w);
                //标记过还一样颜色,说明无法构成二色图了.
            } else if (color[w] == color[v]) {
                isTwoColorable = false;
                break;
            }

        }
    }

    public boolean isBipartite() {
        return isTwoColorable;
    }


}
