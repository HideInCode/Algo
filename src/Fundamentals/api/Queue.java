package Fundamentals.api;

import java.util.Iterator;

public interface Queue<T> extends Iterable<T> {
    @Override
    public Iterator<T> iterator();

    public void enqueue(T t);

    public T dequeue();

    public boolean isEmpty();

    public int size();
}
