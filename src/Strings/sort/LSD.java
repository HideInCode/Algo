package Strings.sort;

/**
 * 低位优先的字符串
 * 从右往左检查字符串->优先检查数字的最低位.
 * 计数排序的多键情况
 */
public class LSD {
    //字符集是拓展的ascii,即数组中最多有256种字符
    private static final int R = 256;
    
    /**
     * @param a 要排序的数组
     * @param w 排序元素长度
     */
    public static void sort(String[] a, int w) {
        int n = a.length;
        String[] aux = new String[n];
        
        for (int i = w-1; i >= 0; i--) {
            int[] count = new int[R + 1];
            for (int j = 0; j < n; j++) {
                count[a[j].charAt(i) + 1]++;
            }
            for (int j = 0; j < R; j++) {
                count[j + 1] += count[j];
            }
            for (int j = 0; j < n; j++) {
                aux[count[a[j].charAt(i)]++] = a[j];
            }
            for (int j = 0; j < n; j++) {
                a[j] = aux[j];
            }
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
        sort(strings,7);
        for (String string : strings) {
            System.out.println(string);
        }
    }
}
