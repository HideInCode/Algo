package Context;

/**
 * B-tree（多路搜索树，并不是二叉的）是一种常见的数据结构。使用B-tree结构可以显著减少定位记录时所经历的中间过程，从而加快存取速度。
 * B通常认为是Balance的简称。这个数据结构一般用于数据库的索引，综合效率较高。
 * 目前很多数据库产品的索引都是基于B+tree结构。
 * MySQL也采用B+tree,它是B-tree的一个变种，其实特性基本上差不多。
 * <p>
 * 1.定义阶数M
 * 2.每个节点最多有M-1个键和连接,最少有M/2个键和链接;
 * 3.对于根节点,可以有少于M/2,但是至少有两个链接;
 *
 * @param <Key>
 */
public class BTreeSET<Key extends Comparable<Key>> {
    private Page root = new Page(true);
    
    //输入哨兵
    public BTreeSET(Key sentinel) {
        add(sentinel);
    }
    
    public boolean contains(Key key) {
        return contains(root, key);
    }
    
    private boolean contains(Page page, Key key) {
        if (page.isExternal()) {
            return page.contains(key);
        }
        return contains(page.next(key), key);
    }
    
    public void add(Key key) {
        add(root, key);
        
        if (root.isFull()) {
            Page leftHalf = root;
            Page rightHalf = root.split();
            root = new Page(false);
            root.add(leftHalf);
            root.add(rightHalf);
        }
    }
    
    
    public void add(Page page, Key key) {
        if (page.isExternal()) {
            page.add(key);
            return;
        }
        
        Page next = page.next(key);
        add(next, key);
        if (next.isFull()) {
            page.add(next.split());
        }
        next.close();
    }
}
