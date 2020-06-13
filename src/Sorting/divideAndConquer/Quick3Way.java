package Sorting.divideAndConquer;

import Sorting.Sort;

public class Quick3Way extends Sort {

    private void sort(Comparable[] a, int low, int high) {
        if (low >= high) {
            return;
        }
    
        int left = low;
        int right = high;
        int cur = low + 1;
        Comparable pivot = a[low];
        while (cur <= right) {
            int cmp = a[cur].compareTo(pivot);
            if (cmp < 0) {
                exch(a, cur, left);
                left++;
                
            } else if (cmp > 0) {
                exch(a, cur, right);
                right--;
                cur--;
            } else {
                cur++;
            }
        }
        sort(a, low, left - 1);
        sort(a, right + 1, high);
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
