package Graphs.ShortestPaths;

import Fundamentals.api.Stack;
import Fundamentals.imp.StackByLinkedList;
import Fundamentals.utils.In;
import Fundamentals.utils.StdOut;
import Sorting.PriorityQueues.IndexMinPQ;

import java.util.Arrays;

/**
 * 迪杰斯特拉算法
 * 求非负加权有向图的最短路径问题
 * 同prim算法如出一辙
 * 注意的是利用新顶点和未失效的顶点比较哪个到树的距离更短,来贪心的选取,逐步生成一个最短路径树
 */
public class DijkstraSP {
    //当前节点的父节点
    private DirectedEdge[] edgeTo;
    private double[] distTo;
    //优先队列,存索引=>key,根据key比较,返回索引;
    private IndexMinPQ<Double> pq;
    
    //只能处理非负权有向图
    public DijkstraSP(EdgeWeightedDigraph g, int start) {
        //初始没有父节点
        int vCount = g.V();
        edgeTo = new DirectedEdge[vCount];
        
        distTo = new double[vCount];
        //初始距离为无穷大,也就意味着凡是无穷大的都是没有访问过的节点
        Arrays.fill(distTo, Double.POSITIVE_INFINITY);
        pq = new IndexMinPQ<>(vCount);
        
        //起点没有父节点,故最短距离为0
        distTo[start] = 0;
        pq.insert(start, 0.0);
    
        while (!pq.isEmpty()) {
            relax(g, pq.delMin());
        }
    }
    
    /**
     * 松弛图g的顶点v
     * 即若distTo[w] > dist[v] + edge[v].weight(),使用更小的,就像撑得紧绷的皮筋松弛了一点..
     * @param g
     * @param v
     */
    private void relax(EdgeWeightedDigraph g, int v) {
        for (DirectedEdge edge : g.adj(v)) {
            int w = edge.to();
            
            //发现更短的路径才能松弛
            if (distTo[w] > distTo[v] + edge.weight()) {
                distTo[w] = distTo[v] + edge.weight();
                edgeTo[w] = edge;
                
                //加入队列,接着查找
                if (pq.contains(w)) {
                    pq.changeKey(w, distTo[w]);
                } else {
                    pq.insert(w, distTo[w]);
                }
            }
        }
    }
    
    public double distTo(int v) {
        return distTo[v];
    }
    
    public boolean hasPathTo(int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }
    
    public Iterable<DirectedEdge> pathTo(int v) {
        Stack<DirectedEdge> stack = new StackByLinkedList<>();
        //找父亲,利用栈把最老的先输出.注意:jdk的Stack输出顺序和栈的pop()顺序一致,即打印还是从顶到底;
        for (DirectedEdge edge = edgeTo[v]; edge != null; edge = edgeTo[edge.from()]) {
            stack.push(edge);
        }
        return stack;
    }
    
    public static void main(String[] args) {
        //输入数据构成图
        EdgeWeightedDigraph g = new EdgeWeightedDigraph(new In(args[0]));
        System.out.println(args[0]);
        
        //指定起点
        int start = Integer.parseInt(args[1]);
        DijkstraSP sp = new DijkstraSP(g, start);
    
        for (int i = 0; i < g.V(); i++) {
            System.out.print(start + " to " + i);
            StdOut.printf(" (%4.2f): ", sp.distTo(i));
    
            if (sp.hasPathTo(i)) {
                for (DirectedEdge edge : sp.pathTo(i)) {
                    StdOut.print(edge + " ");
                }
            }
            StdOut.println();
        }
        
    }
}
