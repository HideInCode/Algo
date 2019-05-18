package Graphs;

import Fundamentals.utils.StdIn;
import Fundamentals.utils.StdOut;

/**
 * 间隔的度数
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
