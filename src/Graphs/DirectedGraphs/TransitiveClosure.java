package Graphs.DirectedGraphs;

/**
 * 顶点对的可达性(闭包)
 */
public class TransitiveClosure {

    private DirectedDFS[] all;

    public TransitiveClosure(Digraph G) {
        all = new DirectedDFS[G.V()];

        for (int v = 0; v < G.V(); v++) {
            all[v] = new DirectedDFS(G, v);
        }
    }


    boolean rechable(int v, int w) {
        return all[v].marked(w);
    }
}
