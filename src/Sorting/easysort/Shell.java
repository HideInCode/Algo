package Sorting.easysort;

import Sorting.Sort;

/**
 * 逐渐缩小增量的插入排序
 * 也可以认为插入排序是增量为1的希尔排序
 */
public class Shell extends Sort {
    @Override
    public void sort(Comparable[] comparables) {
        int increment = 1;
        while (increment < (comparables.length / 3)) {
            increment = increment * 3 + 1;
        }

        while (increment >= 1) {
            for (int i = increment; i < comparables.length; i++) {
                for (int j = i; j >= increment; j -= increment) {
                    if (less(comparables[j], comparables[j - increment])) {
                        exch(comparables, j, j - increment);
                    }
                }
            }
            increment = increment / 3;
        }
    }

    public static void main(String[] args) {
        Shell sort = new Shell();
        String str = "sortedexample";
        String[] strings = str.split("");

        sort.show(strings);
        sort.sort(strings);
        sort.show(strings);
    }
}
