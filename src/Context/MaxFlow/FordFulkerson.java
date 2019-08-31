package Context.MaxFlow;

import Fundamentals.api.Queue;
import Fundamentals.imp.QueueByLinkedList;
import Fundamentals.utils.In;

/**
 * 最短增广路径的最大流算法
 */
public class FordFulkerson {
    private boolean[] marked;
    
    private FlowEdge[] edgeTo;
    
    private double value;
    
    public FordFulkerson(FlowNetwork g, int s, int t) {
        while (hasAugmentingPath(g, s, t)) {
            double bottle = Double.POSITIVE_INFINITY;
            for (int v = t; v != s; v = edgeTo[v].other(v)) {
                bottle = Math.min(bottle, edgeTo[v].residualCapacityTo(v));
            }
            
            for (int v = t; v != s; v = edgeTo[v].other(v)) {
                edgeTo[v].addResidualFlowTo(v, bottle);
            }
            
            value += bottle;
        }
    }
    
    public double value() {
        return value;
    }
    
    public boolean intCut(int value) {
        return marked[value];
    }
    
    /**
     * 判断还有没有增广路径
     *
     * @param g 图
     * @param s 起点
     * @param t 终点
     * @return 是否有增广路径
     */
    private boolean hasAugmentingPath(FlowNetwork g, int s, int t) {
        marked = new boolean[g.v()];
        edgeTo = new FlowEdge[g.v()];
        
        Queue<Integer> queue = new QueueByLinkedList<>();
        marked[s] = true;
        queue.enqueue(s);
        
        while (!queue.isEmpty()) {
            Integer v = queue.dequeue();
            for (FlowEdge edge : g.adj(v)) {
                int w = edge.other(v);
                //再剩余的网络中,对于任意一条连接到一个未标记的顶点的边.
                if (edge.residualCapacityTo(w) > 0 && !marked[w]) {
                    edgeTo[w] = edge;
                    marked[w] = true;
                    queue.enqueue(w);
                }
            }
        }
        return marked[t];
    }
    
    public static void main(String[] args) {
        FlowNetwork g = new FlowNetwork(new In(args[0]));
        int s = 0;
        int t = g.v() - 1;
        
        FordFulkerson maxflow = new FordFulkerson(g, s, t);
        System.out.println("max flow s:" + s + "->" + t);
        for (int i = 0; i < g.v(); i++) {
            for (FlowEdge e : g.adj(i)) {
                if ((i == e.from()) && e.flow() > 0) {
                    System.out.println("\t" + e);
                }
            }
        }
        
        System.out.println("max flow value:" + maxflow.value());
    }
}
