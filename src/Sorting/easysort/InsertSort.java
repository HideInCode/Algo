package Sorting.easysort;

import Sorting.Sort;

public class InsertSort extends Sort {
    private static int count = 0;
    @Override
    public void sort(Comparable[] comparables) {
        for (int i = 1; i < comparables.length; i++) {
            for (int j = i; j > 0; j--) {
                count++;
                if (less(comparables[j], comparables[j - 1])) {
                    exch(comparables, j, j - 1);
                }
            }
        }
    }


    public static void main(String[] args) {
        String[] test = "987654321".split("");
        new InsertSort().sort(test);
        for (String s : test) {
            System.out.println(s);
        }
        System.out.println(count);
    }


}
