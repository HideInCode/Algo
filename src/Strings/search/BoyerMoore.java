package Strings.search;

/**
 * 解决问题:提高查找性能
 * 使用场景: all
 * 原理:文本指针从左往右,模式指针从右往左;利用匹配失败时文本字符与模式串的关系进行回退;
 */
public class BoyerMoore {

    private String pat;
    private int R = 256;
    private int[] right;


    //构造一个数组right,下标为字符,值为当前字符在模式串的最右下标,不存在记作-1
    public BoyerMoore(String pat) {
        this.pat = pat;
        right = new int[R];
        for (int c = 0; c < R; c++) {
            right[c] = -1;
        }
        for (int i = 0; i < pat.length(); i++) {
            right[pat.charAt(i)] = i;
        }

    }

    public int search(String txt) {
        int txtIndex;
        int patIndex;
        int skip;

        for (txtIndex = 0; txtIndex <= txt.length() - pat.length(); txtIndex += skip) {
            skip = 0;//文本和模式在指针txtIndex处是否匹配

            //模式串指针从右往左跑
            for (patIndex = pat.length() - 1; patIndex >= 0; patIndex--) {
                if (txt.charAt(txtIndex + patIndex) != pat.charAt(patIndex)) {
                    //根据文本匹配失败字符在模式字符的最右位置,调整文本指针下次开始的地方
                    skip = patIndex - right[txt.charAt(txtIndex + patIndex)];

                    //
                    if (skip < 1) {
                        skip = 1;
                    }
                    break;
                }
            }

            //skip == 0 意味着txtIndex不会再增加,即找到
            if (skip == 0) {
                return txtIndex;
            }
        }


        return -1;
    }

    public static void main(String[] args) {
        BoyerMoore search = new BoyerMoore("abcd");
        int abcd = search.search("asdfabcd");
        System.out.println(abcd);
    }
}
