package Strings.search;

/**
 * Knuth-Morris-Pratt算法
 * 作用:提高暴力搜索时出现自我重复性的模式字符串的效率 如用aaaaab匹配aaaaaaaaaaaaaab
 * 使用场景:处理二进制流
 * 复杂度:时间O(txt.length+pat.length) 空间:O(R*pat.length)
 */
public class KMP {
    // 当前字符集字符个数
    private int R = 128;
    //确定有限状态机,若dfa[i][c]=x 表示:第i个状态遇见字符c时,应该进入状态x
    private int[][] dfa;
    private String pat;
    
    /**
     * 根据输入的pat个数,确定有几种状态;
     * 然后对于当前状态遇到字符集的每一个字符该如何选择:进入下一状态还是回退?
     * 如果回退,回退到哪里合适???
     * 用x表示上一次该字符出现时要转移到哪个状态
     *
     * @param pat
     */
    public KMP(String pat) {
        this.pat = pat;
        int m = pat.length();
        dfa = new int[m][R];
        //遇见第一个模式串开始前进,否则保持在状态0
        dfa[0][pat.charAt(0)] = 1;
        //x保留上次出现该字符时的状态
        int x = 0;
        for (int i = 1; i < m; i++) {
            //当前状态遇到字符集任意字符要进入到哪个状态
            for (int j = 0; j < R; j++) {
                //把上个状态的情况保留下来
                dfa[i][j] = dfa[x][j];
            }
            //上个状态保留后,找出推进状态,即遇到哪个字符状态会前进
            dfa[i][pat.charAt(i)] = i + 1;
            
            //有新的x的话即时更新
            x = dfa[x][pat.charAt(i)];
        }
    }
    
    
    public int search(String txt) {
        int n = txt.length();
        int m = this.pat.length();
        int initStatus = 0;
        for (int i = 0; i < n; i++) {
            initStatus = dfa[initStatus][txt.charAt(i)];
            if (initStatus == m) {
                return i - m + 1;
            }
        }
        return -1;
    }
    
    public static void main(String[] args) {
        KMP kmp = new KMP("dfa");
        String txt = "adfaghjm,jhgfdsasdfgh";
        
        int search = kmp.search(txt);
        System.out.println(search);
    }
}
