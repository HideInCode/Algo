package Sorting.PriorityQueues;

import java.util.Arrays;

/**
 * 二叉堆
 */
public class MaxPQ<K extends Comparable<K>> {
    private K[] items;
    private int size;
    
    public MaxPQ(int max) {
        this.items = (K[]) new Comparable[max + 1];
    }
    
    private boolean less(int i, int j) {
        return items[i].compareTo(items[j]) < 0;
    }
    
    private void exch(int i, int j) {
        K temp = items[i];
        items[i] = items[j];
        items[j] = temp;
    }
    
    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k, k / 2);
            k /= 2;
        }
    }
    
    private void sink(int k) {
        while (2 * k <= size) {
            int left = 2 * k;
            int right = 2 * k + 1;
            if (less(left, right)) {
                left = right;
            }
            if (less(left, k)) {
                break;
            }
            exch(left, k);
            k = left;
        }
    }
    
    public void insert(K k) {
        items[size + 1] = k;
        swim(size + 1);
        size++;
    }
    
    public K max() {
        return items[1];
    }
    
    public K delMax() {
        K max = items[1];
        items[1] = items[size];
        size--;
        items[size + 1] = null;
        sink(1);
        return max;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public int size() {
        return size;
    }
    
    public static void main(String[] args) {
        MaxPQ<String> queue = new MaxPQ<>(10);
        String str = "yuioodfgjl";
        String[] strings = str.split("");
        for (String string : strings) {
            queue.insert(string);
        }
        System.out.println(Arrays.toString(queue.items));
        String max = queue.max();
        System.out.println(max);
    }
}
