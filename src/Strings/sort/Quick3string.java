package Strings.sort;

/**
 * 解决相同字符开头的多个字符串的排序问题
 */
public class Quick3string {
    private static int charAt(String s, int d) {
        if (d < s.length()) {
            return s.charAt(d);
        } else {
            return -1;
        }
    }

    public static void sort(String[] a) {
        sort(a, 0, a.length - 1, 0);
    }

    /**
     * 对字符串中摸一个字符进行三向快排
     */
    private static void sort(String[] strings, int low, int high, int post) {
        if (high < low) {
            return;
        }

        int lowTemp = low;
        int highTemp = high;

        int v = charAt(strings[low], post);
        int curIndex = low + 1;

        while (curIndex < highTemp) {
            int postIndex = charAt(strings[curIndex], post);

            if (postIndex < v) {
                exch(strings, lowTemp++, curIndex++);
            } else if (post > v) {
                exch(strings, curIndex, highTemp--);
            } else {
                curIndex++;
            }
        }
        sort(strings, low, lowTemp - 1, post);
        if (v >= 0) {
            sort(strings, lowTemp, highTemp, post + 1);
        }
        sort(strings, highTemp + 1, high, post);
    }

    private static void exch(String[] strings, int i, int j) {
        String temp = strings[i];
        strings[i] = strings[j];
        strings[j] = temp;
    }
}
