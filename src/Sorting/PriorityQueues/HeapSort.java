package Sorting.PriorityQueues;

import java.util.Arrays;

public class HeapSort {
    private void sink(Comparable[] a, int k, int len) {
        while (2 * k < len) {
            int left = 2 * k;
            int right = 2 * k + 1;
            if (less(a, left, right)) {
                left = right;
            }
            if (!less(a, k, left)) {
                break;
            }
            exch(a, k, left);
            k = left;
        }
    }
    
    private void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i - 1];
        a[i - 1] = a[j - 1];
        a[j - 1] = temp;
    }
    
    private boolean less(Comparable[] a, int i, int j) {
        return a[i - 1].compareTo(a[j - 1]) < 0;
    }
    
    public void sort(Comparable[] a) {
        int len = a.length;
        //heapify
        for (int i = len / 2; i >= 1; i--) {
            sink(a, i, len);
        }
        
        //选择最大
        while (len > 1) {
            exch(a, 1, len);
            len--;
            sink(a, 1, len);
        }
    }
    
    public static void main(String[] args) {
        HeapSort test = new HeapSort();
        Integer[] a = {1};
        test.sort(a);
        System.out.println(Arrays.toString(a));
    }
}
