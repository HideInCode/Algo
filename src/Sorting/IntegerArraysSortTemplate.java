package Sorting;

import java.util.Random;


public abstract class IntegerArraysSortTemplate {


    protected abstract void sort(int[] arr);

    protected boolean less(int a, int b) {
        return a < b;
    }

    protected void exch(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;

    }

    protected void show(int[] arr) {
        for (int i : arr) {
            System.out.print(i + "\t");
        }
        System.out.println();
    }


    //只做升序(小->大)排序,不符合就认为没有排序
    protected boolean isSorted(int[] arr) {

        for (int i = 1; i < arr.length; i++) {
            if (less(arr[i], arr[i - 1])) {
                return false;
            }
        }
        return true;
    }

    protected int[] randomOrderArr(int length) {
        int[] result = new int[length];
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            result[i] = random.nextInt(length);
        }
        return result;
    }

}
