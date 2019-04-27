package Sorting;

public class Example {
    public static void sort(Comparable[] comparables) {

    }


    public static boolean less(Comparable v, Comparable w) {
        //v-w<0
        return v.compareTo(w) < 0;
    }

    public static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void show(Comparable[] comparables) {
        for (Comparable comparable : comparables) {
            System.out.println(comparable);
        }
    }

    public static boolean isSorted(Comparable[] comparables) {

        boolean isSorted = true;
        for (int i = 0; i < comparables.length; i++) {
            boolean less = less(comparables[i + 1], comparables[i]);
            if (less) {
                return false;
            }
        }
        return isSorted;
    }
}
