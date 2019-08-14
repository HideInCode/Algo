package Fundamentals.api;

import java.util.Iterator;

public interface Bag<T> extends Iterable<T> {
    @Override
    public Iterator<T> iterator();

    public void add(T t);

    public boolean isEmpty();

    public int size();
}
