package Sorting;

public class InsertSort extends IntegerArraysSortTemplate {

    @Override
    public void sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (less(arr[j], arr[j - 1])) {
                    exch(arr, j, j - 1);
                }
            }
        }
    }

    private static boolean less(String a, String b, int d) {
        return a.charAt(d) < b.charAt(d);
    }

    private static void exch(String[] a, int i, int j) {
        String temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void sort(String[] a, int low, int high, int d) {
        if (low < 0 || high > a.length) {
            throw new IllegalArgumentException("参数越界");
        }
        for (int i = low; i <= high; i++) {
            for (int j = i; j > 0; j--) {
                if (less(a[j], a[j - 1], d)) {
                    exch(a, j, j - 1);
                }
            }
        }

    }

    public static void main(String[] args) {
//        IntegerArraysSortTemplate insertSort = new InsertSort();
//        int[] ints = insertSort.randomOrderArr(20);
//        insertSort.show(ints);
//
//        insertSort.sort(ints);
//        insertSort.show(ints);

        String[] a = {"d", "c", "b", "a"};
        sort(a, 0, 4, 0);
        for (String s : a) {
            System.out.println(s);
        }
    }


}
