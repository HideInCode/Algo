package Context;

//todo 定义页的api
public class Page<Key> {
    //创建并打开一个页
    public Page(boolean bottom) {
    }
    
    //关闭页
    public void close() {
        
    }
    
    //将键插入到外部的页中
    public void add(Key key) {
    
    }
    
    //打开p,向这个内部页中插入一个条目并将p和p中的最小键相关联
    public void add(Page page) {
    
    }
    
    
    //这是一个外部页
    public boolean isExternal() {
        return false;
    }
    
    //键key在页中
    public boolean contains(Key key) {
        return false;
    }
    
    //可能含有键key的页
    public Page next(Key key) {
        return null;
    }
    
    //页是否溢出
    public boolean isFull() {
        return false;
    }
    
    //将较大的中间键移动到一个新页中
    public Page split() {
        return null;
    }
    
    //页中所有键的迭代器
    public Iterable<Key> keys() {
        return null;
    }
    
}
