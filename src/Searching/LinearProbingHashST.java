package Searching;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 开放地址法之一:线性探测法
 * @param <Key>
 * @param <Value>
 */
public class LinearProbingHashST<Key, Value> {
    private int N;//键值对的总个数.
    private int M;//数组的大小.


    //并行数组
    private Key[] keys;
    private Value[] values;

    public LinearProbingHashST() {
        keys = (Key[]) new Object[M];
        values = (Value[]) new Object[M];

    }

    public LinearProbingHashST(int m) {
        this.M = m;
        keys = (Key[]) new Object[m];
        values = (Value[]) new Object[m];
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }


    private void resize(int cap) {
        LinearProbingHashST<Key, Value> t;
        t = new LinearProbingHashST<>(cap);
        for (int i = 0; i < M; i++) {
            if (keys[i] != null) {
                t.put(keys[i], values[i]);
            }
        }
        keys = t.keys;
        values = t.values;
        M = t.M;
    }


    /**
     * 线性探测法
     * 插入一个新的键值对
     * 1.计算出key的散列值,即数组下标.
     * 2.若散列值发生冲突且key不一样,找数组的下一位.
     * 3.若找到key一样的就替换掉,找不到一样的就找下下一个为null的位置插入
     *
     * @param key
     * @param value
     */
    public void put(Key key, Value value) {
        //数组位于1/8到1/2之间性能最好
        if (N >= M / 2) {
            resize(2 * M);
        }
        int i;

        //如果key存在的情况下,找到这个key
        for (i = hash(key); keys[i] != null; i = (i + 1) % M) {
            if (keys[i].equals(key)) {
                values[i] = value;
                return;
            }
        }

        //没有重复key的情况下,在找到的null出插入
        keys[i] = key;
        values[i] = value;
        N++;
    }


    public Value get(Key key) {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % M) {
            if (keys[i].equals(key)) {
                return values[i];
            }
        }

        return null;
    }

    public boolean contains(Key key) {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % M) {
            if (keys[i].equals(key)) {
                return true;
            }

        }

        return false;
    }

    public void delete(Key key) {
        if (key == null) {
            return;
        }
        if (!contains(key)) {
            return;
        }
    
        int index = 0;
        while (!key.equals(keys[index])) {
            index = (index + 1) % M;
        }
        
        keys[index] = null;
        values[index] = null;
    
        index = (index + 1) % M;
        while (keys[index] != null) {
            Key toMove = keys[index];
            Value toMoveVal = values[index];
            keys[index] = null;
            values[index] = null;
            N--;
            put(toMove, toMoveVal);
            index = (index + 1) % M;
        }
        N--;
        //数组位于1/8到1/2之间性能最好
        if (N > 0 && N == M / 8) {
            resize(M / 2);
        }
        LinkedList<Integer> test = new LinkedList<>();
        List<List<Integer>> fuck = new LinkedList<>();
        fuck.add(test);
    }
    
    @Override
    public String toString() {
        return "keys:"+Arrays.toString(keys) + "\nvalues:" + Arrays.toString(values);
    }
    
    public static void main(String[] args) {
        LinearProbingHashST<String, String> map = new LinearProbingHashST<>(100);

        map.put("a", "b");
        map.put("c", "b");
        map.put("d", "b");

        map.delete("r");
        System.out.println(map.get("a"));
    
        System.out.println(map);
        
        int maxValue = Integer.MAX_VALUE;
        System.out.println(Math.abs(maxValue));
        System.out.println("polygenelubricants".hashCode());
        System.out.println("Aa".hashCode());
        System.out.println("BB".hashCode());
    }
}
