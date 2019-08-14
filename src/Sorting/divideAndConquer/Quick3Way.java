package Sorting.divideAndConquer;

import Sorting.Sort;

public class Quick3Way extends Sort {

    private void sort(Comparable[] a, int low, int high) {
        if (high <= low) {
            return;
        }

        int lowIndex = low;
        int equalIndex = low + 1;
        int highIndex = high;

        Comparable val = a[low];
        while (equalIndex <= highIndex) {
            int cmp = a[equalIndex].compareTo(val);

            if (cmp < 0) {//如果相等的比低位小
                exch(a, lowIndex++, equalIndex++);
            } else if (cmp > 0) {
                exch(a, equalIndex, highIndex--);
            } else {
                equalIndex++;
            }
        }

        sort(a, low, lowIndex - 1);

        sort(a, highIndex + 1, high);
    }


    @Override
    public void sort(Comparable[] comparables) {
        sort(comparables, 0, comparables.length - 1);
    }


    public static void main(String[] args) {
        Quick3Way sort = new Quick3Way();
        String[] randStrings = sort.getRandStrings();
        sort.show(randStrings);
        sort.sort(randStrings);

        sort.show(randStrings);
    }
}
