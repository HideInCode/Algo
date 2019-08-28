package Context;

import Strings.sort.Quick3string;

/**
 * 后缀数组
 */
public class SuffixArray {
    private final String[] suffixes;
    private final int N;
    
    //在构造函数里预处理后缀数组
    public SuffixArray(String text) {
        N = text.length();
        suffixes = new String[N];
        for (int n = N - 1; n >= 0; n--) {
            suffixes[n] = text.substring(n);
        }
        Quick3string.sort(suffixes);
    }
    
    //文本的长度
    public int length() {
        return N;
    }
    
    //后缀数组中第i个
    public String select(int i) {
        return suffixes[i];
    }
    
    //select(i)的索引
    public int index(int index) {
        return index;
    }
    
    //select(i)和select(i-1)的最长公共前缀长度
    public int lcp(int i) {
        return lcp(suffixes[i], suffixes[i - 1]);
    }
    
    private int lcp(String s, String t) {
        int N = Math.min(s.length(), t.length());
        for (int i = 0; i < N; i++) {
            if (s.charAt(i) != t.charAt(i)) {
                return i;
            }
        }
        
        return N;
    }
    
    //小于key的后缀数量
    public int rank(String key) {
        return rank(key, 0, N);
    }
    
    
    //由于后缀数组已经有序,可以使用二分查找
    private int rank(String key, int low, int high) {
        if (low >= high) {
            return -1;
        }
        int mid = (low + high) / 2;
        if (suffixes[mid].equals(key)) {
            return mid;
        } else if (suffixes[mid].compareTo(key) > 0) {
            return rank(key, low, mid);
        } else {
            return rank(key, mid, high);
        }
        
    }
    
    public static void main(String[] args) {
        SuffixArray suffixArray = new SuffixArray("qwertyuiop");
        int er = suffixArray.rank("uiop");
        System.out.println(er);
    }
}
