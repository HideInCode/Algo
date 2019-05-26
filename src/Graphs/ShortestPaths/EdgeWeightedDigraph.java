package Graphs.ShortestPaths;

import Fundamentals.api.Bag;
import Fundamentals.imp.BagByLinkedList;
import Fundamentals.utils.In;

/**
 * 定义加权有向图
 */
public class EdgeWeightedDigraph {
    private final int V;

    private int E;

    private Bag<DirectedEdge>[] adj;

    public EdgeWeightedDigraph(int v) {
        this.V = v;
        this.E = 0;
        adj = new BagByLinkedList[v];
        for (int i = 0; i < V; i++) {
            adj[i] = new BagByLinkedList<>();
        }
    }

    public EdgeWeightedDigraph(In in) {
        this(in.readInt());
        int E = in.readInt();
        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            double weight = in.readDouble();
            addEdge(new DirectedEdge(v, w, weight));
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(DirectedEdge e) {
        adj[e.from()].add(e);
        E++;
    }

    public Iterable<DirectedEdge> adj(int v) {
        return adj[v];
    }

    public Iterable<DirectedEdge> edges() {
        Bag<DirectedEdge> bag = new BagByLinkedList<>();
        for (int v = 0; v < V; v++) {
            for (DirectedEdge edge : adj(v)) {
                bag.add(edge);
            }
        }

        return bag;
    }
}
