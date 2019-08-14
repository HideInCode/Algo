package Sorting.divideAndConquer;

import Sorting.Sort;

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
        aux = new Comparable[comparables.length];

        //sz表示每次归并的数组大小
        for (int sz = 1; sz < comparables.length; sz = sz + sz) {

            //每次重新归并翻倍的数组
            for (int low = 0; low < comparables.length - sz; low = low + sz + sz) {
                merge(comparables, low, low + sz - 1, Math.min(low + sz + sz - 1, comparables.length - 1));
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
