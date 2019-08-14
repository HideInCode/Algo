package Fundamentals.api;

import java.util.Iterator;

public interface Stack<T> extends Iterable<T> {
    @Override
    public Iterator<T> iterator();

    public void push(T t);

    public T pop();

    public boolean isEmpty();

    public int size();
}
