package Searching;

/**
 * 有序符号表
 *
 * @param <K> 有序唯一的key
 * @param <V>
 */
public abstract class ST<K extends Comparable<K>, V> {
    //创建一张符号表
    public ST() {
    }

    //将键值对放进去,值为空时删除key
    public void put(K key, V val) {

    }

    //根据key获取value
    public V get(K key) {
        return null;
    }

    //删除表中的key和对应的值
    public void delete(K key) {
        put(key, null);
    }

    //表中是否包含key
    boolean contains(K key) {
        return get(key) != null;
    }

    //是否为空
    boolean isEmpty() {
        return size() == 0;
    }

    //返回表中的键值对数量
    public int size() {
        return 0;
    }

    //low-->high之间所有的键的数量
    public abstract int size(K low, K high);

    //表中的所有键的集合,已排序
    public abstract Iterable<K> keys();

    //low-->high之间所有的键
    public abstract Iterable<K> keys(K low, K high);

    abstract K min();

    abstract K max();

    //上确界
    abstract K floor(K key);

    //下确届
    abstract K ceiling(K key);

    //排名,小于key的键的数量
    abstract int rank(K key);

    //排名为k的key
    abstract K select(int k);


    public abstract void deleteMin();

    public abstract void deleteMax();
}

