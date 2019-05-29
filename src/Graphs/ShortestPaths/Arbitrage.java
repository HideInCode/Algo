package Graphs.ShortestPaths;

import Fundamentals.utils.StdIn;
import Fundamentals.utils.StdOut;

/**
 * 套汇 加权有向图的福权重检测问题
 * 看来我能想到的赚钱方法路都快走绝了
 */
public class Arbitrage {

    private Arbitrage() {

    }

    public static void main(String[] args) {
        int V = StdIn.readInt();
        String[] name = new String[V];

        //读数据
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(V);
        for (int v = 0; v < V; v++) {
            name[v] = StdIn.readString();
            for (int w = 0; w < V; w++) {
                double rate = StdIn.readDouble();
                DirectedEdge e = new DirectedEdge(v, w, -Math.log(rate));
                G.addEdge(e);
            }
        }

        //找出负权重环
        BellmanFordSP spt = new BellmanFordSP(G, 0);
        if (spt.hasNegativeCycle()) {
            double stake = 1000.0;
            for (DirectedEdge e : spt.negativeCycle()) {
                StdOut.printf("%10.5f %s", stake, name[e.from()]);
                stake *= Math.exp(-e.weight());
                StdOut.printf("= %10.5f %s\n", stake, name[e.to()]);
            }
        } else {
            StdOut.println("No arbitrage opportunity");
        }
    }
}
