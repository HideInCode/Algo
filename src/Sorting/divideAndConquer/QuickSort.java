package Sorting.divideAndConquer;

import Sorting.Sort;

import java.util.Arrays;

public class QuickSort extends Sort {
    @Override
    public void sort(Comparable[] a) {
        sort(a, 0, a.length - 1);
    }
    
    private void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int pivot = partition(a, lo, hi);
        sort(a, lo, pivot - 1);
        sort(a, pivot + 1, hi);
    }
    
    private int partition(Comparable[] a, int lo, int hi) {
        int left = lo+1;
        int right = hi;
        Comparable pivot = a[lo];
        while (true) {
            while (less(a[left], pivot)) {
                left++;
                if (left == hi) {
                    break;
                }
            }
    
            while (less(pivot, a[right])) {
                right--;
                if (right == lo) {
                    break;
                }
            }
            if (left >= right) {
                break;
            }
            exch(a, left, right);
        }
        exch(a, lo, right);
        return right;
    }
    
    public Comparable select(Comparable[] a, int k) {
        k = k + 1;
        int left = 0;
        int right = a.length - 1;
        while (left < right) {
            int i = partition(a, left, right);
            if (i > k) {
                right = i - 1;
            } else if (i < k) {
                left = i + 1;
            } else {
                return a[k];
            }
        }
        return a[k];
    }
    public static void main(String[] args) {
    
        String str = "987654321";
        String[] strings = str.split("");
    
        QuickSort quickSort = new QuickSort();
        quickSort.sort(strings);
        System.out.println(quickSort.select(strings, 0));
        Arrays.stream(strings).forEach(System.out::print);
        
    }

}
