package Sorting;

public class BubbleSort extends IntegerArraysSortTemplate {

    @Override
    protected void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (less(arr[j + 1], arr[j])) {
                    exch(arr, j + 1, j);
                }
            }
        }
    }

    public static void main(String[] args) {
        IntegerArraysSortTemplate bubbleSort = new BubbleSort();
        int[] ints = bubbleSort.randomOrderArr(10);
        bubbleSort.show(ints);
        bubbleSort.sort(ints);
        bubbleSort.show(ints);
    }
}
