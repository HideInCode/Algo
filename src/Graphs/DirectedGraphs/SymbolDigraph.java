package Graphs.DirectedGraphs;

import Fundamentals.utils.In;
import Searching.BST;

/**
 * 参考无向图的符号图
 */
public class SymbolDigraph {
    private BST<String, Integer> st;
    private String[] keys;
    private Digraph digraph;

    public SymbolDigraph(String stream, String sp) {
        st = new BST<>();
        In in = new In(stream);

        while (in.hasNextLine()) {
            String[] strings = in.readLine().split(sp);

            for (String s : strings) {
                if (!st.contains(s)) {
                    st.put(s, st.size());
                }
            }

        }
        keys = new String[st.size()];
        for (String key : st.keys()) {
            keys[st.get(key)] = key;
        }

        digraph = new Digraph(st.size());

        in = new In(stream);

        while (in.hasNextLine()) {
            String[] strings = in.readLine().split(sp);
            int v = st.get(strings[0]);

            for (int i = 1; i < strings.length; i++) {
                digraph.addEdge(v, st.get(strings[i]));
            }
        }

    }

    public boolean contains(String key) {
        return st.contains(key);
    }

    public int index(String key) {
        return st.get(key);
    }

    public String name(int v) {
        return keys[v];
    }

    public Digraph G() {
        return digraph;
    }

}
