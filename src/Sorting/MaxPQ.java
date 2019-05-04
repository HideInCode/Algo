package Sorting;


/**
 * k表示当前节点也是在数组中的索引,父节点为k/2取左,子节点为k*2取右.
 *
 * @param <Key>
 */
public class MaxPQ<Key extends Comparable<Key>> {


    //不使用pq[1]
    private Key[] pq;

    private int N = 0;

    public MaxPQ() {
    }

    public MaxPQ(int max) {
        pq = (Key[]) new Comparable[max];
    }

    public MaxPQ(Key[] a) {
    }

    void insert(Key v) {
        pq[++N] = v;
        swim(N);
    }

    public Key max() {
        return pq[1];
    }

    public Key delMax() {

        Key max = pq[1];
        exch(1, N--);
        pq[N + 1] = null;

        sink(1);

        return max;

    }

    public boolean isEmpty() {

        return false;
    }

    public int size() {
        return 0;
    }


    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j) {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    private void exch(Comparable[] a, int i, int j) {
        int first = i - 1;
        int second = j - 1;

        Comparable temp = a[first];
        a[first] = a[second];
        a[second] = temp;
    }

    private boolean less(Comparable[] a, int i, int j) {
        int first = i - 1;
        int second = j - 1;


        return a[first].compareTo(a[second]) < 0;
    }

    /**
     * 存在节点比父节点大,破坏了有序性,就要让这个节点上浮.
     *
     * @param curNodeIndex 树结构在数组中的索引.
     */

    private void swim(int curNodeIndex) {

        while (curNodeIndex > 1 && less(curNodeIndex, curNodeIndex / 2)) {
            exch(curNodeIndex, curNodeIndex / 2);
            curNodeIndex = curNodeIndex / 2;
        }
    }


    /**
     * 索引为k的节点,比他的子节点小,就要下沉.
     *
     * @param curNodeIndex 当前树节点
     */
    private void sink(int curNodeIndex) {
        while (2 * curNodeIndex <= pq.length) {
            int leftChildren = curNodeIndex * 2;
            if (leftChildren < pq.length && less(leftChildren, leftChildren + 1)) {
                //这里变成了右节点
                leftChildren = leftChildren + 1;
            }
            if (!less(curNodeIndex, leftChildren)) {
                break;
            }
            exch(curNodeIndex, leftChildren);
            curNodeIndex = leftChildren;
        }
    }


    public void sort(Comparable[] a) {
        int N = a.length;
        for (int k = N / 2; k >= 1; k--) {
            sink(a, k, N);
        }

        while (N > 1) {
            exch(a, 1, N--);
            sink(a, 1, N);
        }
    }

    private void sink(Comparable[] a, int curNodeIndex, int length) {
        while (2 * curNodeIndex <= length) {
            int leftChildren = curNodeIndex * 2;

            //不越界 左边比右边小
            if (leftChildren < length && less(a, leftChildren, leftChildren + 1)) {
                //这里变成了右节点
                leftChildren = leftChildren + 1;
            }

            //当前节点>=子节点
            if (!less(a, curNodeIndex, leftChildren)) {
                break;
            }
            exch(a, curNodeIndex, leftChildren);
            curNodeIndex = leftChildren;
        }
    }


    public static void main(String[] args) {
        String[] strings = new String[]{"d", "c", "b", "a"};


        MaxPQ maxPQ = new MaxPQ();
        maxPQ.sort(strings);

        for (String string : strings) {
            System.out.println(string);
        }
    }
}
