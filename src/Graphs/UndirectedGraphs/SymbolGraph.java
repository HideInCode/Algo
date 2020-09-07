package Graphs.UndirectedGraphs;

import Fundamentals.utils.In;
import Fundamentals.utils.StdIn;
import Searching.BST;


/**
 * 符号图 对图结构的一个封装
 * 参考movies.txt的数据:
 * 1.顶点名字为字符串
 * 2.用指定分隔符隔开定点名
 * 3.每一行都是一组边,每一条边都连接着这一行的第一个名称表示的顶点和其他所表示的顶点
 * 4.顶点总数V和边的总数E都是隐式定义.
 */
public class SymbolGraph {

    //key->int类型的索引
    private BST<String, Integer> st;

    //索引->符号名,与上面的相反
    private String[] keys;

    private Graph graph;

    /**
     * 定义了输入格式
     *
     * @param stream 文件名
     * @param splitter     间隔符
     */
    public SymbolGraph(String stream, String splitter) {
        st = new BST<>();
        In in = new In(stream);

        while (in.hasNextLine()) {
            String[] data = in.readLine().split(splitter);
            //所有数据读入到符号表
            for (String s : data) {
                if (!st.contains(s)) {
                    //直接存入当前符号表的size当索引,不用再搞个变量表示索引
                    st.put(s, st.size());
                }
            }

        }

        //用keys存下索引对应的名字
        keys = new String[st.size()];
        for (String name : st.keys()) {
            keys[st.get(name)] = name;
        }
    
        //再读取一次,把数据构造成图
        graph = new Graph(st.size());
        in = new In(stream);

        while (in.hasNextLine()) {
            String[] a = in.readLine().split(splitter);

            //a[0]为电影名字,a[1..len]为演员名字
            int v = st.get(a[0]);
            for (int i = 1; i < a.length; i++) {
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
    
    public static void main(String[] args) {
        String filename = args[0];
        String delim = args[1];
    
        SymbolGraph graph = new SymbolGraph(filename, delim);
        Graph g = graph.G();
        //输入顶点,查找该顶点邻接的顶点
        while (StdIn.hasNextLine()) {
            String line = StdIn.readLine();
            for (Integer v : g.adj(graph.index(line))) {
                System.out.println(" " + graph.name(v));
            }
        }
    }
    

}
