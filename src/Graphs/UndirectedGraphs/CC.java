package Graphs.UndirectedGraphs;

import Fundamentals.api.Bag;
import Fundamentals.imp.BagByLinkedList;
import Fundamentals.utils.In;
import Fundamentals.utils.StdOut;

/**
 * 连通分量的计算
 */
public class CC {
    private boolean[] marked;
    private int[] id;
    private int count;
    
    public CC(Graph graph) {
        marked = new boolean[graph.V()];
        id = new int[graph.V()];
        for (int i = 0; i < graph.V(); i++) {
            if (!marked[i]) {
                dfs(graph, i);
                count++;
            }
        }
    }
    
    private void dfs(Graph g, int v) {
        marked[v] = true;
        id[v] = count;
        for (Integer i : g.adj(v)) {
            if (!marked[i]) {
                dfs(g, i);
            }
            
        }
        
    }
    
    public boolean connected(int v, int w) {
        return id[v] == id[w];
    }
    
    public int id(int v) {
        return id[v];
    }
    
    public int count() {
        return count;
    }
    
    public static void main(String[] args) {
        Graph g = new Graph(new In(args[0]));
        CC cc = new CC(g);
        int count = cc.count();
        StdOut.println(count + " components");
        
        Bag<Integer>[] components = new Bag[count];
        for (int i = 0; i < count; i++) {
            components[i] = new BagByLinkedList<>();
        }
        for (int v = 0; v < g.V(); v++) {
            components[cc.id(v)].add(v);
        }
        for (int i = 0; i < count; i++) {
            for (Integer v : components[i]) {
                    StdOut.print(v+" ");
            }
            StdOut.println();
        }
    }
}
