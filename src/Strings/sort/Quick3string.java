package Strings.sort;

/**
 * 前缀相同的字符排序问题
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
     * 对字符串数组的字符串,按照首字母进行三向快排
     */
    private static void sort(String[] strings, int low, int high, int post) {
        if (high <= low) {
            return;
        }
    
        int lowTemp = low;
        int highTemp = high;
    
        int lowVal = charAt(strings[low], post);
        int curIndex = low + 1;
    
        while (curIndex <= highTemp) {
            int postVal = charAt(strings[curIndex], post);
        
            if (postVal < lowVal) {
                exch(strings, lowTemp++, curIndex++);
            } else if (postVal > lowVal) {
                exch(strings, curIndex, highTemp--);
            } else {
                curIndex++;
            }
        }
        sort(strings, low, lowTemp - 1, post);
        if (lowVal >= 0) {
            sort(strings, lowTemp, highTemp, post + 1);
        }
        sort(strings, highTemp + 1, high, post);
    }
    
    private static void exch(String[] strings, int i, int j) {
        String temp = strings[i];
        strings[i] = strings[j];
        strings[j] = temp;
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
        for (String string : strings) {
            System.out.println(string);
        }
    }
}
