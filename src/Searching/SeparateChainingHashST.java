package Searching;

/**
 * 拉链法解决碰撞冲突
 * 还是用数组存储,这次存储的是链表首地址.
 * 增删改操作都是建立在查询的基础上,通过hashCode查到数组的下标后,顺着链表查询.
 * 增加键值对如果出现碰撞冲突,就在相同下标的链表后面继续添加新节点.
 * 利用空间换时间的策略.
 *
 * @param <Key>
 * @param <Value>
 */
public class SeparateChainingHashST<Key, Value> {

    private int N;//键值对的个数
    private int M;//散列表的大小

    //关键道具,放链表的数组.
    private SequentialSearchST<Key, Value>[] st;

    public SeparateChainingHashST() {

        this(977);
    }

    public SeparateChainingHashST(int m) {
        M = m;
        st = new SequentialSearchST[M];

        for (int i = 0; i < M; i++) {

            //数组的每个元素都存着一个链表的首地址
            st[i] = new SequentialSearchST<>();
        }
    }


    //利用除长度,求余数.得散列值.
    private int hash(Key key) {
//        return ((M - 1) & key.hashCode()) % M;
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public Value get(Key key) {
        return st[hash(key)].get(key);
    }

    public void put(Key key, Value value) {
        st[hash(key)].put(key, value);
    }


    //空函数
    public Iterable<Key> keys() {
        return null;
    }

    //空函数
    public void delete() {

    }
}

