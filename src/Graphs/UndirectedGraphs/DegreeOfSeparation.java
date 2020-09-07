package Graphs.UndirectedGraphs;

import Fundamentals.utils.StdIn;
import Fundamentals.utils.StdOut;

/**
 * 间隔的度数 可以认为是两个顶点间的最小距离问题
 * 可以用来研究演员与演员之间的度数
 */
public class DegreeOfSeparation {
    public static void main(String[] args) {

        SymbolGraph sg = new SymbolGraph(args[0], args[1]);

        Graph graph = sg.G();

        String source = args[2];

        if (!sg.contains(source)) {
            StdOut.println(source + "not in database.");
            return;
        }

        int s = sg.index(source);
        BreadthFirstPaths bfp = new BreadthFirstPaths(graph, s);

        while (!StdIn.isEmpty()) {
            String sink = StdIn.readLine();

            if (sg.contains(sink)) {

                int t = sg.index(sink);
                if (bfp.hasPathTo(t)) {
                    for (Integer v : bfp.pathTo(t)) {
                        StdOut.println("  " + sg.name(v));
                    }
                } else {
                    StdOut.println("not connected");
                }

            } else {
                StdOut.println("not in database");
            }
        }
    }
}
