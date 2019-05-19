package Graphs.UndirectedGraphs;

/**
 * 联通分量
 */
public class CC {

    private boolean[] marked;

    //以顶点当做数组下标
    private int[] id;


    //连通分量个数
    private int count;

    public CC(Graph graph) {

        marked = new boolean[graph.V()];
        for (int s = 0; s < graph.V(); s++) {
            if (!marked[s]) {
                dfs(graph, s);
                count++;
            }
        }
    }

    private void dfs(Graph graph, int v) {
        marked[v] = true;
        id[v] = count;

        for (Integer w : graph.adj(v)) {
            if (!marked[w]) {
                dfs(graph, w);
            }
        }
    }


    //v,w这两个顶点联通吗
    public boolean connected(int v, int w) {
        return id[v] == id[w];
    }

    //连通分量
    int count() {
        return count;
    }

    //v所在的连通分量的标识符(0~count()-1)
    public int id(int v) {
        return id[v];
    }
}
