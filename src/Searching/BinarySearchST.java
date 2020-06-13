package Searching;

import Fundamentals.api.Queue;
import Fundamentals.imp.QueueByLinkedList;

/**
 * 基于二分搜索的符号表
 * @param <K>
 * @param <V>
 */
public class BinarySearchST<K extends Comparable<K>, V> {

    private K[] keys;
    private V[] values;
    private int N;

    public BinarySearchST(int capacity) {
        keys = (K[]) new Comparable[capacity];
        values = (V[]) new Object[capacity];
    }

    private void resize(int max) {
        K[] newKeys = (K[]) new Comparable[max];
        for (int i = 0; i < N; i++) {
            newKeys[i] = keys[i];
        }

        keys = newKeys;

        V[] newValues = (V[]) new Object[max];

        for (int i = 0; i < N; i++) {
            newValues[i] = values[i];
        }
        values = newValues;
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int rank(K key) {

        int low = 0;
        int high = N - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int cmp = key.compareTo(keys[mid]);

            if (cmp < 0) {
                high = mid - 1;
            } else if (cmp > 0) {
                low = mid + 1;
            } else {
                return mid;
            }
        }


        return low;
    }

    public V get(K key) {
        if (isEmpty()) {
            return null;
        }
        int i = rank(key);

        if ((i < N) && (keys[i].compareTo(key) == 0)) {
            return values[i];
        } else {
            return null;
        }

    }


    //查找键,找到就更新,找不到就增加数组元素.
    public void put(K key, V value) {
        int i = rank(key);
        if ((i < N) && (keys[i].compareTo(key) == 0)) {
            values[i] = value;
            return;
        }

        if (N == keys.length) {
            resize(keys.length * 2);
        }

        //把修改点之后的元素向后移动一格.给新元素腾出一格(保持有序).
        for (int j = N; j > i; j--) {
            keys[j] = keys[j - 1];
            values[j] = values[j - 1];
        }

        keys[i] = key;
        values[i] = value;
        N++;
    }

    public void delete(K key) {

    }

    
    public int rank(K key, int low, int high) {

        if (low > high) {
            return low;
        }
        int mid = low + (low + high) / 2;
        int cmp = key.compareTo(keys[mid]);

        if (cmp < 0) {
            return rank(key, mid + 1, high);
        } else if (cmp > 0) {
            return rank(key, low, mid - 1);
        } else {
            return mid;
        }

    }

    public K min() {
        return keys[0];
    }

    public K max() {
        return keys[N - 1];
    }

    public K select(int k) {
        return keys[k];
    }

    public K ceiling(K key) {
        int i = rank(key);
        return keys[i];
    }

    public K floor(K key) {
        return null;
    }

    public boolean contains(K key) {
        for (int i = 0; i < N; i++) {
            if (keys[i].compareTo(key) == 0) {
                return true;
            }
        }

        return false;
    }

    public Iterable<K> keys(K low, K high) {
        Queue<K> queue = new QueueByLinkedList<>();

        for (int i = rank(low); i < rank(high); i++) {
            queue.enqueue(keys[i]);
        }

        if (contains(high)) {
            queue.enqueue(keys[rank(high)]);
        }

        return queue;
    }
    
    public static void main(String[] args) {
        BinarySearchST<String, String> test = new BinarySearchST<>(5);
        test.put("a", "asd");
        test.put("b", "bsd");
        test.put("c", "csd");
        System.out.println(test.get("b"));
        System.out.println(test.rank("b "));
        
    }

}

