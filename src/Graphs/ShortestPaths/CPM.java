package Graphs.ShortestPaths;

import Fundamentals.utils.StdIn;
import Fundamentals.utils.StdOut;

/**
 * critical path method
 * 关键路径方法
 */
public class CPM {
    public static void main(String[] args) {
        int N = StdIn.readInt();
        StdIn.readLine();
        EdgeWeightedDigraph G;

        G = new EdgeWeightedDigraph(2 * N + 2);

        int s = 2 * N;
        int t = 2 * N + 1;

        for (int i = 0; i < N; i++) {
            String[] a = StdIn.readLine().split("\\s+");
            double duration = Double.parseDouble(a[0]);
            G.addEdge(new DirectedEdge(i, i + N, duration));
            G.addEdge(new DirectedEdge(s, i, 0.0));
            G.addEdge(new DirectedEdge(i + N, t, 0.0));

            for (int j = 0; j < a.length; j++) {
                int successor = Integer.parseInt(a[j]);
                G.addEdge(new DirectedEdge(i + N, successor, 0.0));
            }

            AcyclicLP lp = new AcyclicLP(G, s);
            StdOut.println("Start times:");
            for (int j = 0; j < N; j++) {
                StdOut.printf("Finish time: %5.1f\n", lp.distTo(j));
            }
        }
    }
}
