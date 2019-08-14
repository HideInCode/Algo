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

    /**
     * 找到一个有序数组中的第k小的元素
     *
     * @param a 数组
     * @param k k
     * @return 目标元素
     */
    public Comparable select(Comparable[] a, int k) {


        int low = 0;
        int high = a.length - 1;

        while (high > low) {

            int j = partition(a, low, high);
            if (j == k) {
                return a[k];
            } else if (j > k) {
                high = j - k;
            } else {
                low = j + 1;
            }
        }
        return a[k];
    }


    private int partition(Comparable[] a, int low, int high) {
        Comparable pivot = a[low];

        int leftIndex = low;
        int rightIndex = high + 1;

        while (true) {
            while (less(leftIndex, pivot)) {
                if (leftIndex > high) {
                    break;
                }
            }

            while (less(pivot, --rightIndex)) {
                if (rightIndex < low) {
                    break;
                }
            }

            if (leftIndex >= rightIndex) {
                break;

            }

            exch(a, leftIndex, rightIndex);

        }

        //轴归位
        exch(a, low, rightIndex);

        return rightIndex;
    }

    protected String[] getRandStrings() {

        String str = "asfjsaopf";
        return str.split("");
    }
}
