package Searching;

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

        //没有直接结束
        if (!contains(key)) {
            return;
        }


        //有就一个个的找
        int i = hash(key);
        while (!key.equals(keys[i])) {
            i = (i + 1) % M;
        }

        //找到就置空
        keys[i] = null;
        values[i] = null;
        i = (i + 1) % M;


        //把不为空的重新拿出来放进去,
        //有些key并不是放在散列码所在的位置(因为重复的往后移了),所以要拿出来重新计算hashCode再放进去
        while (keys[i] != null) {
            Key keyToRedo = keys[i];
            Value valToRedo = values[i];
            keys[i] = null;
            values[i] = null;

            N--;
            put(keyToRedo, valToRedo);
            i = (i + 1) % M;
        }

        N--;

        //过大就缩减空间
        if (N > 0 && N == M / 8) {
            resize(M / 2);
        }
    }


    public static void main(String[] args) {
        LinearProbingHashST<String, String> map = new LinearProbingHashST<>(100);

        map.put("a", "b");
        map.put("c", "b");
        map.put("d", "b");

        map.delete("r");
        System.out.println(map.get("a"));


    }
}
