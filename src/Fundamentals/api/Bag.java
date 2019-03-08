package Fundamentals.api;

import java.util.Iterator;

public class Bag<T> implements Iterable<T> {
    @Override
    public Iterator<T> iterator() {
        return null;
    }

    public void add(T t) {

    }

    public boolean isEmpty() {
        return false;
    }

    public int size() {
        return 0;
    }
}
