package Strings;

public class SimpleSearch {

    private static final int END_POSITION = -1;

    /**
     * 解决基本字符串查找问题
     *
     * @param pat 匹配模式
     * @param txt 文本
     * @return 第一次出现的位置
     */
    public static int search(String pat, String txt) {
        int M = pat.length();
        int N = txt.length();

        for (int i = 0; i < N - M; i++) {
            int patIndex;//模式串中的指针
            for (patIndex = 0; patIndex < M; patIndex++) {
                //只要遇到不匹配字符,文本指针先前挪动一位.模式指针归零.
                if (txt.charAt(i + patIndex) != pat.charAt(patIndex)) {
                    break;
                }
            }

            //模式完全匹配
            if (patIndex == M) {
                return i;
            }
        }
        return END_POSITION;
    }

    /**
     * 暴力查找的另一种表示,为kmp铺路
     *
     * @param pat 匹配模式
     * @param txt 文本
     * @return 第一次匹配到的位置
     */
    public static int reSearch(String pat, String txt) {
        int txtIndex;
        int patIndex;
        int M = pat.length();
        int N = txt.length();

        for (txtIndex = 0, patIndex = 0; txtIndex < N && patIndex < M; txtIndex++) {
            if (txt.charAt(txtIndex) == pat.charAt(patIndex)) {
                patIndex++;
            } else {//如果不匹配,文本下标回到模式串的开头,然后模式串归零
                txtIndex -= patIndex;
                patIndex = 0;
            }
        }

        //上面的循环完成后,模式串下标=模式串长度,就说明找到了,返回文本下标,否者没有找到.
        if (patIndex == M) {
            return txtIndex - M;
        } else {
            return END_POSITION;
        }
    }

    public static void main(String[] args) {
        String txt = "pjrbadmapijqwpjrwqrj";
        String pat = "adm";
        int search = search(pat, txt);
        System.out.println(search);

        int reSearch = reSearch(pat, txt);
        System.out.println(reSearch);
    }
}
