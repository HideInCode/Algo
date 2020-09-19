package Strings.sort;

import Sorting.easysort.InsertSort;

import java.util.Arrays;

/**
 * 高位优先的字符串排序
 * 还是计数排序
 * 先对首字母进行计数排序,去掉首字母,对剩余字符进行相同操作
 * 当到达末尾是,记作在字符串中下标为-1;
 * 在count中,用下标0表示到达末尾,那么总共会有R+1中情况(R为字母表大小),本来count[R+1]变成count[R+2]
 */
public class MSD {
    private static int R = 256;
    private static final int M = 1;
    private static String[] aux;

    private static int charAt(String s, int d) {
        if (d < s.length()) {
            return s.charAt(d);
        } else {
            return -1;
        }
    }

    public static void sort(String[] a) {
        int N = a.length;
        aux = new String[N];
        sort(a, 0, N - 1, 0);
    }


    private static void sort(String[] a, int low, int high, int d) {
        //小到一定程度插排比较好
        if (high <= low + M) {
            new InsertSort().sort(a);
            return;
        }
        int[] count = new int[R + 2];
        for (int i = low; i <= high; i++) {
            count[charAt(a[i], d) + 2]++;
        }
        for (int r = 0; r < R + 1; r++) {
            count[r + 1] += count[r];
        }
        for (int i = low; i <= high; i++) {
            aux[count[charAt(a[i], d) + 1]++] = a[i];
        }

        for (int i = low; i <= high; i++) {
            a[i] = aux[i - low];
        }

        //递归的以每个字符为键进行排序
        for (int r = 0; r < R; r++) {
            //todo 理解不了...
            sort(a, low + count[r], low + count[r + 1] - 1, d + 1);
        }


    }

    public static void main(String[] args) {
        String s = "4PGC938\n" +
                           "2IYE230\n" +
                           "3CIO720\n" +
                           "1ICK750\n" +
                           "1OHV845\n" +
                           "4JZY524\n" +
                           "1ICK750\n" +
                           "3CIO720\n" +
                           "1OHV845\n" +
                           "1OHV845\n" +
                           "2RLA629\n" +
                           "2RLA629\n" +
                           "3ATW723";
        String[] strings = s.split("\n");
        sort(strings);
        Arrays.asList(strings).forEach(System.out::println);
    }
}
