package mycollections.linkedlist;

import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * @author Moussa
 */
public class LinkedListIterator<T> implements ListIterator<T> {
    private final LinkedList<T> list;
    private Node<T> lastReturned = null;
    private int index;
    private Node<T> prev;

    public LinkedListIterator(LinkedList<T> list) {
        this.list = list;
        this.index = 0;
        this.prev = new Node<>(null);
        this.prev.next = list.getNode(0);
    }

    public LinkedListIterator(LinkedList<T> list, int index) {
        this.list = list;
        this.index = index;
        this.prev = list.getNode(index - 1);
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        lastReturned = prev.next;
        T value = lastReturned.value;
        prev = prev.next;
        index++;
        return value;
    }

    @Override
    public boolean hasNext() {
        return index < list.size();
    }

    @Override
    public T previous() {
        if (!hasPrevious()) {
            throw new NoSuchElementException();
        }
        lastReturned = prev;
        T value = lastReturned.value;
        prev = prev.prev;
        index--;
        return value;
    }

    @Override
    public boolean hasPrevious() {
        return index > 0;
    }

    @Override
    public int nextIndex() {
        return index;
    }

    @Override
    public int previousIndex() {
        return index - 1;
    }

    @Override
    public void remove() {
        if (lastReturned == null) {
            throw new IllegalStateException("No element to remove");
        }
        list.unlink(lastReturned);
        lastReturned = null;
    }

    @Override
    public void set(T t) {
        if (lastReturned == null) {
            throw new IllegalStateException("No element to set");
        }
        lastReturned.value = t;
        lastReturned = null;
    }

    @Override
    public void add(T t) {
        list.add(index, t);
        index++;
    }
}
