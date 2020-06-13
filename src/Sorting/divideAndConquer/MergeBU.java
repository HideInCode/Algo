package Sorting.divideAndConquer;

import Sorting.Sort;

/**
 * 自底向上归并
 */
public class MergeBU extends Sort {
    
    Comparable[] aux;
    
    //合并左右都各自有序的数组
    private void merge(Comparable[] a, int low, int mid, int high) {
        
        for (int i = 0; i < a.length; i++) {
            aux[i] = a[i];
        }
        
        
        int countLeft = low;
        int countRight = mid + 1;
        
        for (int k = low; k <= high; k++) {
            if (countLeft > mid) {
                a[k] = aux[countRight++];
            } else if (countRight > high) {
                a[k] = aux[countLeft++];
            } else if (less(aux[countLeft], aux[countRight])) {
                a[k] = aux[countLeft++];
            } else {
                a[k] = aux[countRight++];
            }
            
        }
    }
    
    @Override
    public void sort(Comparable[] comparables) {
        int len = comparables.length;
        aux = new Comparable[len];
        //i表示每次归并的数组大小
        for (int i = 1; i < len; i = i * 2) {
            //j表示每个小数组的起点下标
            for (int j = 0; j < len - i; j = j + 2 * i) {
                merge(comparables, j, j + i - 1, Math.min(len - 1, j + 2 * i - 1));
            }
        }
    }
    
    public static void main(String[] args) {
        String[] strings = new String[]{"d", "c", "b", "a"};
        MergeBU mergeBU = new MergeBU();
        mergeBU.sort(strings);
        mergeBU.show(strings);
    }
}
