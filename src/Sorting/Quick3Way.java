package Sorting;

import java.util.ArrayList;
import java.util.List;

import static Sorting.Example.exch;

public class Quick3Way {

    private static void sort(Comparable[] a, int low, int high) {
        if (high <= low) {
            return;
        }

        int lowCounter = low;
        int equalCounter = low + 1;
        int highCounter = high;

        Comparable val = a[low];

        while (equalCounter <= highCounter) {
            int cmp = a[equalCounter].compareTo(val);

            if (cmp < 0) {
                exch(a, lowCounter++, equalCounter++);
            } else if (cmp > 0) {
                exch(a, equalCounter, highCounter--);
            } else {
                equalCounter++;
            }
        }

        sort(a, low, lowCounter - 1);

        sort(a, highCounter + 1, high);
    }


    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("a");

        list.add("dd");
        list.add("a");
        list.add("a");


        list.add("cc");
        list.add("b");
        list.add("cc");
        list.add("b");
        list.add("dd");
        list.add("b");

        String[] strings = new String[list.size()];

        for (int i = 0; i < strings.length; i++) {
            strings[i] = list.get(i);
        }


        sort(strings, 0, strings.length - 1);

        for (String string : strings) {
            System.out.println(string);
        }
    }
}
