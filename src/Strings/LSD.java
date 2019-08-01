package Strings;

/**
 * 低位优先的字符串
 * 使用数字代替字符,从右往左检查字符串->优先检查数字的最低位.
 * 适用于键的长度都相同的字符串排序应用.
 */
public class LSD {
    /**
     * @param a 待排序的数组
     * @param w 通过前w个字符排序
     */
    public static void sort(String[] a, int w) {
        int N = a.length;
        int R = 256;

        String[] aux = new String[N];
        for (int d = w - 1; d >= 0; d--) {
            //关系着字符串元素频率与下标关系
            int[] count = new int[R + 1];

            //计算出现频率
            for (int i = 0; i < N; i++) {
                count[a[i].charAt(d) + 1]++;
            }
            //将频率转换为索引
            for (int r = 0; r < R; r++) {
                count[r + 1] += count[r];
            }
            //将元素分类,根据每个元素的key在count中的位置,每次自增一个是为了下一个元素能在aux中正确的索引
            for (int i = 0; i < N; i++) {
                aux[count[a[i].charAt(d)]++] = a[i];
            }

            //回写元素
            for (int i = 0; i < N; i++) {
                a[i] = aux[i];
            }

        }

    }
}
