package Sorting;

public class Shell extends IntegerArraysSortTemplate {
    @Override
    public void sort(int[] arr) {
        int h = 1;
        while (h < (arr.length / 3)) {
            h = h * 3 + 1;
        }

        while (h >= 1) {
            for (int i = h; i < arr.length; i++) {
                for (int j = i; j >= h; j -= h) {
                    if (less(arr[j], arr[j - h])) {
                        exch(arr, j, j - h);
                    }
                }
            }
            h = h / 3;
        }
    }

    public static void main(String[] args) {
        IntegerArraysSortTemplate shellSort = new Shell();
        int[] ints = shellSort.randomOrderArr(15);
        shellSort.show(ints);

        shellSort.sort(ints);
        shellSort.show(ints);
    }
}
