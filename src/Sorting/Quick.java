package Sorting;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static Sorting.Example.exch;
import static Sorting.Example.less;

public class Quick {

    private static boolean shuffleArray(Comparable[] comparables) {
        List<Comparable> comparableList = Arrays.asList(comparables);

        Collections.shuffle(comparableList);

        for (int i = 0; i < comparables.length; i++) {
            comparables[i] = comparableList.get(i);
        }

        return comparables.length == comparableList.size();
    }

    public static void sort(Comparable[] comparables) {

        shuffleArray(comparables);
        sort(comparables, 0, comparables.length - 1);

    }

    private static void sort(Comparable[] a, int low, int high) {

        if (low >= high) {
            return;
        }
        int pivotIndex = getPivotIndex(a, low, high);

        sort(a, low, pivotIndex - 1);
        sort(a, pivotIndex + 1, high);
    }


    /**
     * 找切点
     * 0.找一个确定的初始值当做切点,一般用a[0]
     * 1.准备两个计数器,分两个方向->和<-在数组中找.
     * 2.两个计数器都不能越界
     * 3.左计数器永远不能大于右计数器
     * 4.左边找到比轴小的,和右边对换.右边计数器同理.
     * 5.把找出来的轴和初始值对换.
     *
     * @param a    数组
     * @param low  最小下标
     * @param high 最大下标
     * @return 轴的下标
     */
    private static int getPivotIndex(Comparable[] a, int low, int high) {

        Comparable partitionVal = a[low];

        int leftIndex = low;
        int rightIndex = high + 1;

        while (true) {

            while (less(a[++leftIndex], partitionVal)) {
                if (leftIndex == high) {
                    break;
                }

            }

            while (less(partitionVal, a[--rightIndex])) {
                if (rightIndex == low) {
                    break;
                }
            }

            //左右相遇,找到切点
            if (leftIndex >= rightIndex) {
                break;
            }


            //内循环跳出时说明找到了可以交换的值.
            exch(a, leftIndex, rightIndex);
        }

        //一定要返回右边的计数器啊,因为
        exch(a, low, rightIndex);
        return rightIndex;
    }


    public static void main(String[] args) {

        String[] strings = new String[]{"d", "c", "b", "a"};


        sort(strings);

        Arrays.stream(strings).forEach(System.out::println);


    }

}
