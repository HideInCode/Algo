package Graphs.MinimumSpanningTrees;

import Fundamentals.api.Bag;
import Fundamentals.imp.BagByLinkedList;
import Fundamentals.utils.In;
import Sorting.PriorityQueues.IndexMinPQ;

import java.util.Arrays;

/**
 * 最小生成树的Prim算法(普利姆算法) 即时版本
 * 区别于延时版本 这里会即时删除无用的横切边
 * 利用优先队列,只保留当前顶点的每个节点中,离树最近的边.
 */
public class PrimMST {
    //到树最近的边
    private Edge[] edgeTo;
    //距离树最近的边的权重,distTo[w]=edgeTo[w].weight();
    private double[] distTo;
    //是否在树中
    private boolean[] marked;
    //用于找最小的边
    private IndexMinPQ<Double> pq;
    
    public PrimMST(EdgeWeightedGraph g) {
        edgeTo = new Edge[g.V()];
        distTo = new double[g.V()];
        //因为要比大小,所以定义为负无穷
        Arrays.fill(distTo, Double.POSITIVE_INFINITY);
        marked = new boolean[g.V()];
        pq = new IndexMinPQ<>(g.V());
        distTo[0] = 0.0;
        //初始化
        pq.insert(0, 0.0);
    
        while (!pq.isEmpty()) {
            //去除最小的值,并且返回索引,即顶点的索引
            visit(g, pq.delMin());
        }
    }
    
    private void visit(EdgeWeightedGraph g, int v) {
        marked[v] = true;
        for (Edge edge : g.adj(v)) {
            int w = edge.other(v);
            if (marked[w]) {
                continue;
            }
            
            //v的所有邻接边,如果比当前最小边小,进行替换
            if (edge.weight() < distTo[w]) {
                distTo[w] = edge.weight();
                edgeTo[w] = edge;
                
                //更新队列
                if (pq.contains(w)) {
                    pq.changeKey(w, edge.weight());
                } else {
                    pq.insert(w, edge.weight());
                }
            }
        }
    }
    
    public Iterable<Edge> edges() {
        Bag<Edge> bag = new BagByLinkedList<>();
        //只有v-1条边
        for (int i = 1; i < edgeTo.length; i++) {
            bag.add(edgeTo[i]);
        }
        return bag;
    }
    
    public double weight() {
        double res = 0;
        for (double dist : distTo) {
            res += dist;
        }
        return res;
    }
    
    
    public static void main(String[] args) {
        In in = new In(args[0]);
        EdgeWeightedGraph graph = new EdgeWeightedGraph(in);
        PrimMST mst = new PrimMST(graph);
        for (Edge edge : mst.edges()) {
            System.out.println(edge);
        }
        System.out.println(mst.weight());
        
    }
    
}
