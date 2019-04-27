package Sorting;

public class Merge {

    private static Comparable[] aux;

    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int low, int high) {

        //递的终点,归的起点.
        if (high <= low) {
            return;
        }

        int mid = low + (high - low) / 2;

        sort(a, low, mid);
        sort(a, mid + 1, high);
        merge(a, low, mid, high);
    }

    private static boolean less(Comparable a, Comparable b) {

        int result = a.compareTo(b);


        return result == -1 ? true : false;
    }


    public static void merge(Comparable[] a, int low, int mid, int high) {

        for (int i = 0; i <= high; i++) {
            aux[i] = a[i];
        }

        int i = low;
        int j = mid + 1;
        for (int k = low; k < high; k++) {
            if (i > mid) {
                aux[k] = a[j++];
            } else if (j > high) {
                aux[k] = a[i++];
            } else if (less(a[i], a[j])) {
                aux[k] = a[i++];
            } else {
                aux[k] = a[j++];
            }


//            这里不可以这样写,因为i可能连续增加两次,而j不变
//            i++;
//            j++;
        }
    }


    public static void main(String[] args) {
        String[] test = new String[]{
                "a", "b", "c", "b", "c", "d"};

        sort(test);
        for (String s : test) {
            System.out.println(s);
        }

    }
}
