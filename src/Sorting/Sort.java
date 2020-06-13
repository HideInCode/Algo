package Sorting;

public abstract class Sort {


    public abstract void sort(Comparable[] comparables);


    public boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public void show(Comparable[] comparables) {
        for (Comparable comparable : comparables) {
            System.out.print(comparable + "\t");
        }
        System.out.println();
    }

    public boolean isSorted(Comparable[] comparables) {

        boolean isSorted = true;
        for (int i = 0; i < comparables.length - 1; i++) {
            boolean less = less(comparables[i + 1], comparables[i]);
            if (less) {
                return false;
            }
        }
        return isSorted;
    }


    public int countUnique(Comparable[] comparables) {
        int result = 1;

        for (int i = 1; i < comparables.length; i++) {
            if (comparables[i].compareTo(comparables[i - 1]) != 0) {
                result++;
            }
        }

        return result;
    }

    protected String[] getRandStrings() {

        String str = "asfjsaopf";
        return str.split("");
    }
}
