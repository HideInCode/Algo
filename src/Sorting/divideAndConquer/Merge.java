package Sorting.divideAndConquer;

import Sorting.Sort;

public class Merge extends Sort {
    private static int count;
    private Comparable[] aux;

    @Override
    public void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1);
    }

    private void sort(Comparable[] a, int low, int high) {
        count++;
        //递的终点,归的起点.
        if (high <= low) {
            return;
        }

        int mid = low + (high - low) / 2;
        System.out.println(low + "=" + mid + "=" + high);
        sort(a, low, mid);
        sort(a, mid + 1, high);
        merge(a, low, mid, high);
    }


    private void merge(Comparable[] a, int low, int mid, int high) {

        System.arraycopy(a, low, aux, low, a.length - low);

        int leftIndex = low;
        int rightIndex = mid + 1;
        for (int i = low; i <= high; i++) {
            if (leftIndex > mid) {
                a[i] = aux[rightIndex++];
            } else if (rightIndex > high) {
                a[i] = aux[leftIndex++];
            } else if (less(aux[rightIndex], aux[leftIndex])) {
                a[i] = aux[rightIndex++];
            } else {
                a[i] = aux[leftIndex++];
            }
        }
    }


    public static void main(String[] args) {
        String str = "987654321";
        String[] test = str.split("");
        new Merge().sort(test);
        for (String s : test) {
            System.out.print(s);
        }
    
        System.out.println(count);
    }
}
