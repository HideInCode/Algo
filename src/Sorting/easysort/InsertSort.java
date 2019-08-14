package Sorting.easysort;

import Sorting.Sort;

public class InsertSort extends Sort {

    @Override
    public void sort(Comparable[] comparables) {
        for (int i = 1; i < comparables.length; i++) {
            for (int j = i; j > 0; j--) {
                if (less(comparables[j], comparables[j - 1])) {
                    exch(comparables, j, j - 1);
                }
            }
        }
    }


    public static void main(String[] args) {
        String[] a = {"d", "c", "b", "a"};
        new InsertSort().sort(a);
        for (String s : a) {
            System.out.println(s);
        }
    }


}
