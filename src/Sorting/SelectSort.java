package Sorting;

/**
 * 选择排序法
 * 不断找寻最小,把最小的选择出来放到最前面
 */
public class SelectSort extends IntegerArraysSortTemplate {


    @Override
    public void sort(int[] arr) {
        int minCursor;
        for (int i = 0; i < arr.length - 1; i++) {
            minCursor = i;
            for (int j = minCursor + 1; j < arr.length; j++) {
                if (less(arr[j], arr[minCursor])) {
                    exch(arr, minCursor, j);
                    j = minCursor;
                }
            }
        }
    }


    public static void main(String[] args) {
        IntegerArraysSortTemplate integerArraysSortExample = new SelectSort();
        int[] ints = integerArraysSortExample.randomOrderArr(20);
        integerArraysSortExample.show(ints);

        integerArraysSortExample.sort(ints);
        integerArraysSortExample.show(ints);

        System.out.println(integerArraysSortExample.isSorted(ints));
    }
}
