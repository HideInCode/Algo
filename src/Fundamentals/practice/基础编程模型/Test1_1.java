package Fundamentals.practice.基础编程模型;

import Fundamentals.api.Stack;
import Fundamentals.imp.StackByLinkedList;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test1_1 {
    
    
    public static int[] diStringMatch(String S) {
        char[] chars = S.toCharArray();
        int left = 0;
        int right = chars.length;
        int[] result = new int[chars.length + 1];
        
        int i;
        for (i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c == 'I') {
                result[i] = left++;
            }
            if (c == 'D') {
                result[i] = right--;
            }
        }
        result[i] = left;
        return result;
    }
    
    
    private static int getMinAndRemove(List<Integer> list) {
        Integer integer = list.get(0);
        list.remove(0);
        return integer;
    }
    
    private static int getMaxAndRemove(List<Integer> list) {
        Integer integer = list.get(list.size() - 1);
        list.remove(list.size() - 1);
        return integer;
    }
    
    //循环中递增变量超出int会进入死循环,所以使用int做递增进行for时考虑会不会超出边界
    private void IntIsCircle() {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            if (i == Integer.MAX_VALUE - 1) {
                i += 2;
            }
        }
    }
    
    private static void printIntArr(int[][] a) {
        System.out.println("-\t".repeat(a.length));
        
        for (int[] ints : a) {
            for (int anInt : ints) {
                System.out.print(anInt + "\t");
            }
            System.out.println();
        }
        System.out.println("-\t".repeat(a.length));
        
        
    }
    
    private static void printBoolArr(boolean[][] a) {
        System.out.println("-\t".repeat(a.length));
        
        for (boolean[] ints : a) {
            for (boolean anInt : ints) {
                System.out.print(anInt + "\t");
            }
            System.out.println();
        }
        System.out.println("-\t".repeat(a.length));
        
        
    }
    
    
    /**
     * 整数的二进制表示
     *
     * @param N
     * @return
     */
    private static String toBinaryString(int N) {
        if (N == 0) {
            return "0";
        }
        if (N == 1) {
            return "1";
        }
        
        return toBinaryString(N / 2) + N % 2;
    }
    
    /**
     * 行列互换
     *
     * @param a
     */
    private static int[][] upDown(int[][] a) {
        int row = a.length;
        int[][] result = new int[a[0].length][a.length];
        for (int i = 0; i < row; i++) {
            int[] rowData = a[i];
            for (int j = 0; j < rowData.length; j++) {
                result[j][i] = rowData[j];
            }
        }
        return result;
    }
    
    
    private static void foo1() {
        int f = 0;
        int g = 1;
        
        for (int i = 0; i <= 15; i++) {
            System.out.println(f);
            f += g;
            //g= f没有增加之前
            g = f - g;
        }
    }
    
    /**
     * @param N 一个整数,没有正负
     * @return <= log(2)N的最大值
     */
    private static int lg(int N) {
        int count = 0;
        long product = 1;
        for (; ; ) {
            if (product >= N) {
                return count - 1;
            } else {
                product *= 2;
                count++;
            }
        }
    }
    
    private static int[] histogram(int[] a, int M) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : a) {
            Integer count = map.get(i);
            if (count == null) {
                map.put(i, 1);
            } else {
                map.put(i, count + 1);
            }
        }
        
        int[] result = new int[M];
        for (int i = 0; i < result.length; i++) {
            Integer count = map.get(i);
            if (count == null) {
                result[i] = 0;
            } else {
                result[i] = count;
            }
            
            
        }
        return result;
    }
    
    
    private static String exR1(int n) {
        if (n <= 0) {
            return "";
        }
        return exR1(n - 3) + n + exR1(n - 2) + n;
    }
    
    private static long f(int N) {
        return f(0, N, new long[N]);
    }
    
    
    private static long f(int i, int n, long[] a) {
        
        if (i > n) {
            return 0;
        }
        
        if (i == n) {
            return 1;
        }
        
        if (a[i] > 0) {
            return a[i];
        }
        
        a[i] = f(i + 1, n, a) + f(i + 2, n, a);
        return a[i];
    }
    
    //求ln(n!)
    private static double lnFactorial(int n) {
        if (n == 1) {
            return 0;
        }
        
        return Math.log(n) + lnFactorial(n - 1);
    }
    
    private static double factorial(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }
    
    private static long euclid(int p, int q) {
        System.out.println(p + "->" + q);
        if (q == 0) {
            return p;
        }
        
        int r = p % q;
        return euclid(q, r);
    }
    
    private static long count = 0;
    
    private static double binomial(int N, int k, double p) {
        System.out.println(count++);
        
        if (N == 0 && k == 0) {
            return 1;
        }
        
        if (N < 0 || k < 0) {
            return 0;
        }
        
        return (1 - p) * binomial(N - 1, k, p) + p * binomial(N - 1, k - 1, p);
    }
    
    private static int rank(int key, int[] a) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            
            int mid = (lo + hi) / 2;
            if (key < a[mid]) {
                hi = mid - 1;
            } else if (key > a[mid]) {
                lo = mid;
            } else {
                return mid;
            }
        }
        return -1;
    }
    
    private static int[] removeRpt(int[] a) {
        return Arrays.stream(a).distinct().toArray();
    }
    
    private static int count(int key, int[] a) {
        Map<Integer, Integer> valMapCounter = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            Integer count = valMapCounter.get(a[i]);
            if (count == null) {
                valMapCounter.put(a[i], 1);
            } else {
                valMapCounter.put(a[i], count + 1);
            }
        }
        if (valMapCounter.get(key) == null) {
            return -1;
        } else {
            return valMapCounter.get(key);
        }
    }
    
    private static boolean isPrimEach(int a, int b) {
        return euclid(a, b) > 1;
    }
    
    private static boolean[][] createArr(int N) {
        boolean[][] result = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (isPrimEach(i, j)) {
                    result[i][j] = true;
                }
            }
        }
        return result;
    }
    //hello llohe 是个回环变位
    private static boolean isCircle(String s, String t) {
        return (s + s).indexOf(t) > 0 && s.length() == t.length();
    }
    
   
    public static void main(String[] args) {
        String s1 = "hello";
        String s2 = "llohe";
        System.out.println(isCircle(s1, s2));
    
        System.out.println(Character.isWhitespace('\n'));
    }
}
