package Sorting.easysort;

import Sorting.Sort;

/**
 * 选择排序法
 * 不断找寻最小,把最小的选择出来放到最前面
 */
public class SelectSort extends Sort {
    private static int count;
    @Override
    public void sort(Comparable[] comparables) {
        int minIndex;
        for (int i = 0; i < comparables.length; i++) {
            minIndex = i;
            for (int j = i + 1; j < comparables.length; j++) {
                count++;
                if (less(comparables[j], comparables[minIndex])) {
                    minIndex = j;
                }
            }
            //放在外循环,内循环用于找出最小的
            exch(comparables, minIndex, i);
        }

    }

    public static void main(String[] args) {
        SelectSort sort = new SelectSort();
        String str = "987654321";
        String[] strings = str.split("");

        sort.show(strings);
        sort.sort(strings);
        sort.show(strings);
        System.out.println(count);
    }
}
