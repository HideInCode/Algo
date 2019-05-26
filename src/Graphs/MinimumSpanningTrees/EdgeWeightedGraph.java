package Graphs.MinimumSpanningTrees;

import Fundamentals.api.Bag;
import Fundamentals.imp.BagByLinkedList;
import Fundamentals.utils.In;

/**
 * 加权无向图
 */
public class EdgeWeightedGraph {
    private final int V;

    private int E;

    private Bag<Edge>[] adj;


    public EdgeWeightedGraph(int v) {
        this.V = v;
        this.E = 0;
        adj = new BagByLinkedList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new BagByLinkedList<>();
        }
    }

    public EdgeWeightedGraph(In in) {
        this(in.readInt());
        int E = in.readInt();
        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            double weight = in.readDouble();
            addEdge(new Edge(v, w, weight));
        }

    }

    int V() {
        return V;
    }

    int E() {
        return E;
    }

    void addEdge(Edge e) {
        int v = e.either();
        int w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
        E++;
    }

    Iterable<Edge> adj(int v) {
        return adj[v];
    }

    Iterable<Edge> edges() {
        Bag<Edge> bag = new BagByLinkedList<>();
        for (int v = 0; v < V; v++) {
            for (Edge e : adj[v]) {
                if (e.other(v) > v) {
                    bag.add(e);
                }
            }
        }
        return bag;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append('}');
        return sb.toString();
    }
}
