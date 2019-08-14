package Sorting.easysort;

import Sorting.Sort;

public class BubbleSort extends Sort {
    @Override
    public void sort(Comparable[] comparables) {
        for (int i = 0; i < comparables.length; i++) {
            for (int j = 0; j < comparables.length - 1 - i; j++) {
                if (less(comparables[j + 1], comparables[j])) {
                    exch(comparables, j + 1, j);
                }
            }
        }
    }


    public static void main(String[] args) {
        BubbleSort sort = new BubbleSort();
        String[] randStrings = sort.getRandStrings();
        sort.show(randStrings);
        sort.sort(randStrings);

        sort.show(randStrings);
    }
}
