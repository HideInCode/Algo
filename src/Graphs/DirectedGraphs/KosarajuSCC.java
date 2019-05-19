package Graphs.DirectedGraphs;

/**
 * 计算联通分量的实现
 */
public class KosarajuSCC extends SCC {
    private boolean[] marked;

    //强连通分量的标识
    private int[] id;

    //强连通分量的数量
    private int count;

    public KosarajuSCC(Digraph digraph) {

        //虽然啥也不干,但是格式上这么要求的.
        super(digraph);

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

    @Override
    public boolean stronglyConnected(int v, int w) {
        return id[v] == id[w];
    }

    @Override
    public int count() {
        return count;
    }

    @Override
    public int id(int v) {
        return id[v];
    }


}
