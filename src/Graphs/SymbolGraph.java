package Graphs;

import Fundamentals.utils.In;
import Searching.BST;


/**
 * 符号图
 * 1.顶点名字为字符串
 * 2.用指定分隔符隔开定点名
 * 3.每一行都是一组边的 集合,每一条边都连接着这一行的第一个名称表示的顶点和其他所表示的顶点
 * 4.顶点总数V和边的总数E都是隐式定义.
 */
public class SymbolGraph {

    //符号名->索引
    private BST<String, Integer> st;

    //索引->符号名
    private String[] keys;

    private Graph graph;

    /**
     * 定义了输入格式
     *
     * @param stream 文件名
     * @param sp     间隔符
     */
    public SymbolGraph(String stream, String sp) {
        st = new BST<>();
        In in = new In(stream);

        while (in.hasNextLine()) {
            String[] a = in.readLine().split(sp);

            for (int i = 0; i < a.length; i++) {
                if (!st.contains(a[i])) {
                    st.put(a[i], st.size());
                }
            }

        }

        keys = new String[st.size()];

        for (String name : st.keys()) {
            keys[st.get(name)] = name;
        }

        graph = new Graph(st.size());

        //重新读取输入的字符信息
        in = new In(stream);

        while (in.hasNextLine()) {
            String[] a = in.readLine().split(sp);

            //将每一行的第一个顶点和改行的其他顶点相连.
            int v = st.get(a[0]);
            for (int i = 0; i < a.length; i++) {
                graph.addEdge(v, st.get(a[i]));
            }
        }
    }

    /**
     * @param key 键
     * @return 当前符号图是否包含key
     */
    public boolean contains(String key) {
        return st.contains(key);
    }

    /**
     * @param key 键
     * @return key的下标
     */
    public int index(String key) {
        return st.get(key);
    }

    public String name(int v) {
        return keys[v];
    }

    public Graph G() {
        return graph;
    }

}
