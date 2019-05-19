package Graphs.DirectedGraphs;

/**
 * 强连通分量(Strong connected component)
 * 两个顶点v和w是互相可达的,则称其为强连通
 * v和w处于一个普通的有向环中
 */
public abstract class SCC {
    public SCC(Digraph digraph) {

    }

    //顶点vw是不是强连接
    public abstract boolean stronglyConnected(int v, int w);

    //当前图中联通分量的个数
    public abstract int count();


    //    v所在的强连通分量的标识符(0~count()-1)
    public abstract int id(int v);

}
