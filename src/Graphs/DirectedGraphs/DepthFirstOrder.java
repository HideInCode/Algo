package Graphs.DirectedGraphs;

import Fundamentals.api.Queue;
import Fundamentals.api.Stack;
import Fundamentals.imp.QueueByLinkedList;
import Fundamentals.imp.StackByLinkedList;
import Graphs.ShortestPaths.DirectedEdge;
import Graphs.ShortestPaths.EdgeWeightedDigraph;


/**
 * 深度优先排序
 */
public class DepthFirstOrder {
    private boolean[] marked;

    //前序: 在递归调用之前将顶点加入队列.
    private Queue<Integer> pre;

    //后序: 在递归调用之后将顶点加入队列.
    private Queue<Integer> post;

    //逆后序: 在递归调用之后将顶点压入栈.可以考虑deque
    private Stack<Integer> reversePost;

    //前序计数器
    private int preCounter;

    //后序计数器
    private int postCouter;


    public DepthFirstOrder(Digraph G) {
        pre = new QueueByLinkedList<>();
        post = new QueueByLinkedList<>();
        reversePost = new StackByLinkedList<>();

        marked = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                dfs(G, v);
            }
        }
    }

    public DepthFirstOrder(EdgeWeightedDigraph G) {
        pre = new QueueByLinkedList<>();
        post = new QueueByLinkedList<>();
        reversePost = new StackByLinkedList<>();

        marked = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                dfs(G, v);
            }
        }
    }

    private void dfs(EdgeWeightedDigraph G, int v) {
        marked[v] = true;
        pre.enqueue(v);
        for (DirectedEdge e : G.adj(v)) {
            if (!marked[e.to()]) {
                dfs(G, e.to());
            }
        }
        post.enqueue(v);
        reversePost.push(v);
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        pre.enqueue(v);

        for (Integer w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
        post.enqueue(v);
        reversePost.push(v);
    }

    public Iterable<Integer> pre() {
        return pre;
    }

    public Iterable<Integer> post() {
        return post;
    }

    public Iterable<Integer> reversePost() {
        return reversePost;
    }
}
