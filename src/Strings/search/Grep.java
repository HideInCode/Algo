package Strings.search;

import Fundamentals.api.Bag;
import Fundamentals.api.Stack;
import Fundamentals.imp.BagByLinkedList;
import Fundamentals.imp.StackByLinkedList;
import Graphs.DirectedGraphs.Digraph;
import Graphs.DirectedGraphs.DirectedDFS;

/**
 * 正则表达式的匹配
 * 1.数据结构:其实是个有向图,顶点意味着邻接数组的索引.
 * 2.算法步骤:构建有向图:要根据所匹配的模式进行分析,列举出{},(),*,|,等字符时,怎么去连接顶点,构造一个有向图出来.
 * 搜索:1.把构造好的有向图的所有可达路径拿到2.遍历文本,匹配当前字符,放入容器3.再次利用有向图,获取到所有匹配的可达路径
 * 4.遍历匹配的可达路径,有顶点达到模式的终止状态就匹配成功,并停止匹配.
 * <p>
 * (.*AB((C|D*E)F)*G)以这个正则表达式当做示例
 */
public class Grep {
    
    private int patLen;
    private Digraph digraph;
    private char[] patStatus;
    
    public Grep(char[] pat) {
        patLen = pat.length;
        patStatus = pat;
        digraph = new Digraph(patLen + 1);
        Stack<Integer> ops = new StackByLinkedList<>();
        
        for (int v = 0; v < patLen; v++) {
            
            //对于最简单的{}情况的连接
            if (pat[v] == '(' || pat[v] == '|' || pat[v] == '*') {
                digraph.addEdge(v, v + 1);
            }
            
            //parentheses圆括号
            int leftPar = v;
            
            if (pat[v] == '(' || pat[v] == '|') {
                ops.push(v);
            }
            if (pat[v] == ')') {
                //弹出来的可能是( |
                Integer or = ops.pop();
                if (pat[or] == '|') {
                    //不会连着两个|,下个一定是左括号
                    leftPar = ops.pop();
                    digraph.addEdge(leftPar, or + 1);
                    digraph.addEdge(or, v);
                } else if (pat[or] == '(') {
                    //不是 | 就是(,对于(不做操作
                    leftPar = or;
                }
                
                
            }
            
            //下一个字符是闭包操作符*,进行连接,并且创建回到左括号的路
            if (v < patLen - 1 && pat[v + 1] == '*') {
                digraph.addEdge(leftPar, v + 1);
                digraph.addEdge(v + 1, leftPar);
            }
            
            
        }
        
    }
    
    
    public boolean search(String txt) {
        
        DirectedDFS directedDFS = new DirectedDFS(digraph, 0);
        Bag<Integer> reachableStatus = new BagByLinkedList<>();
        for (int v = 0; v < digraph.V(); v++) {
            if (directedDFS.marked(v)) {
                reachableStatus.add(v);
            }
        }
        
        for (int i = 0; i < txt.length(); i++) {
            Bag<Integer> match = new BagByLinkedList<>();
            for (Integer status : reachableStatus) {
                if (status < patLen) {
                    if (txt.charAt(i) == patStatus[status] || patStatus[status] == '.') {
                        match.add(status + 1);
                    }
                }
            }
            
            //找到匹配的顶点在图中所有可以到达的顶点
            reachableStatus = new BagByLinkedList<>();
            directedDFS = new DirectedDFS(digraph, match);
            
            for (int v = 0; v < digraph.V(); v++) {
                if (directedDFS.marked(v)) {
                    reachableStatus.add(v);
                }
            }
        }
        
        
        for (Integer status : reachableStatus) {
            //到达了最终状态
            if (status == patLen) {
                return true;
            }
        }
        
        return false;
    }
    
    public static void main(String[] args) {
        Grep grep = new Grep(".*".toCharArray());
        boolean result = grep.search("asfhisajfajfjasdf");
        System.out.println(result);
    }
    
}
