package Searching;

public abstract class SET<Key> {
    public SET() {
    }

    public abstract void add(Key key);

    public abstract void delete(Key key);

    public abstract boolean contains(Key key);

    public abstract boolean isEmpty();

    public abstract int size();

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append('}');
        return sb.toString();
    }
}
