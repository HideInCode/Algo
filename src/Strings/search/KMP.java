package Strings.search;

/**
 * Knuth-Morris-Pratt算法
 * 作用:提高暴力搜索时出现自我重复性的模式字符串的效率 如用aaaaab匹配aaaaaaaaaaaaaab
 * 使用场景:处理二进制流
 * 复杂度:时间O(txt.length+pat.length) 空间:O(R*pat.length)
 */
public class KMP {
    private int R = 256;// 当前字符集字符个数
    private int[][] dfa; //确定有限自动状态机
    private String pat;//匹配模式


    /**
     * 构造dfa,储存的是状态,也是模式字符串指针下一个移动的位置
     * 1.匹配失败:此时模式串指到patIndex时,考虑正常回退的情况.
     * 如果模式指针都回退,文本指针回退后右移再匹配下一位.获取dfa状态.这时的pat[1]到pat[patIndex-1]是已知的.但是我们还要重新扫描一遍.
     * 因此,我们可以找到pat:1->patIndex-1的dfa的状态,来替换掉当前状态.即initIndex = dfa[patIndex][initIndex],把上一次的转态转移到下一次来
     * 2.匹配成功:就是开始匹配pat串的下一位即dfa[c][patIndex] = patIndex+1
     *
     * @param pat 匹配模式串
     */
    public KMP(String pat) {
        this.pat = pat;

        //开始构造一个dfa 这个算法的核心
        dfa = new int[R][pat.length()];
        dfa[pat.charAt(0)][0] = 1;//第一位匹配时,下一状态一定是1

        //两个指针同时开始移动
        for (int initIndex = 0, patIndex = 1; patIndex < pat.length(); patIndex++) {

            //匹配失败,已经匹配过的状态保留
            for (int c = 0; c < R; c++) {
                dfa[c][patIndex] = dfa[c][initIndex];
            }

            //匹配成功,下个要匹配的模式字符肯定是patIndex+1
            dfa[pat.charAt(patIndex)][patIndex] = patIndex + 1;

            //更新重启位置,状态转移
            initIndex = dfa[pat.charAt(patIndex)][initIndex];

        }
    }

    /**
     * 有了dfa提供下个模式指针的位置,一切都简单了
     *
     * @param txt 匹配文本
     * @return 第一匹配到的位置
     */
    public int search(String txt) {
        int txtIndex;
        int patIndex;

        for (txtIndex = 0, patIndex = 0; txtIndex < txt.length() && patIndex < pat.length(); txtIndex++) {
            //利用自动机来找到下个模式串指针的位置
            patIndex = dfa[txt.charAt(txtIndex)][patIndex];
            System.out.println(patIndex);
        }

        //模式字符串匹配到结尾,说明找到了
        if (patIndex == pat.length()) {
            return txtIndex - patIndex;
        }

        return -1;
    }

    public static void main(String[] args) {
        KMP kmp = new KMP("dfa");
        String txt = "qwerftghjkpoiuytrdasdfaghjm,jhgfdsasdfgh";

        int search = kmp.search(txt);
        System.out.println(search);
    }
}
