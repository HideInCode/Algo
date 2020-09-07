package Graphs.DirectedGraphs;

/**
 * 线性复杂度计算有向图的强连通分量
 */
public class KosarajuSCC {
    private boolean[] marked;

    //强连通分量的标识,第几个连通分量
    private int[] id;

    //强连通分量的数量
    private int count;

    public KosarajuSCC(Digraph digraph) {
        
        marked = new boolean[digraph.V()];
        id = new int[digraph.V()];


        //拿到这个有向图的反向图
        DepthFirstOrder dfo = new DepthFirstOrder(digraph);
        for (Integer s : dfo.reversePost()) {
            if (!marked[s]) {
                dfs(digraph, s);
            }
        }
    }

    private void dfs(Digraph digraph, int v) {
        marked[v] = true;
        id[v] = count;

        for (Integer w : digraph.adj(v)) {
            if (!marked[w]) {
                dfs(digraph, w);
            }
        }
    }

    public boolean stronglyConnected(int v, int w) {
        return id[v] == id[w];
    }

    public int count() {
        return count;
    }
    
    public int id(int v) {
        return id[v];
    }


}
