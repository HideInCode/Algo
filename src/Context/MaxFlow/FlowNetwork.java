package Context.MaxFlow;

import Fundamentals.api.Bag;
import Fundamentals.imp.BagByLinkedList;
import Fundamentals.utils.In;

public class FlowNetwork {
    
    private final int v;
    private int e;
    private Bag<FlowEdge>[] adjs;
    
    //加入顶点
    public FlowNetwork(int v) {
        this.v = v;
        this.e = 0;
        adjs = new BagByLinkedList[v];
        for (int i = 0; i < v; i++) {
            adjs[i] = new BagByLinkedList<>();
        }
    }
    
    //加入边
    public FlowNetwork(In in) {
        this(in.readInt());
        int e = in.readInt();
        
        for (int i = 0; i < e; i++) {
            int v = in.readInt();
            int w = in.readInt();
            double d = in.readDouble();
            
            addEdge(new FlowEdge(v, w, d));
        }
        
    }
    
    public int v() {
        return v;
    }
    
    public int e() {
        return e;
    }
    
    public void addEdge(FlowEdge edge) {
        adjs[edge.from()].add(edge);
        adjs[edge.to()].add(edge);
        e++;
    }
    
    public Iterable<FlowEdge> adj(int v) {
        return adjs[v];
    }
    
    public Iterable<FlowEdge> edges() {
        Bag<FlowEdge> bag = new BagByLinkedList<>();
        for (int i = 0; i < v; i++) {
            for (FlowEdge edge : adj(i)) {
                if (edge.to() != i) {
                    bag.add(edge);
                }
            }
        }
        return bag;
    }
    
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(v + " " + e + "\n");
        for (int v = 0; v < v; v++) {
            s.append(v + ":  ");
            for (FlowEdge e : adjs[v]) {
                if (e.to() != v) s.append(e + "  ");
            }
            s.append("\n");
        }
        return s.toString();
    }
}
