package Sorting;

public class InsertSort extends IntegerArraysSortTemplate {

    @Override
    protected void sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (less(arr[j], arr[j - 1])) {
                    exch(arr, j, j - 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        IntegerArraysSortTemplate insertSort = new InsertSort();
        int[] ints = insertSort.randomOrderArr(20);
        insertSort.show(ints);

        insertSort.sort(ints);
        insertSort.show(ints);

    }

}
