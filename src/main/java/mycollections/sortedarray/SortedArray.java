package mycollections.sortedarray;

import java.util.*;

/**
 * @author Moussa
 */
public class SortedArray<T> implements Collection<T> {
    private final List<T> list = new ArrayList<>();
    private final Comparator<T> comparator;

    public SortedArray(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public SortedArray(Comparator<T> comparator, Collection<T> collection) {
        this.comparator = comparator;
        addAll(collection);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return find(o) != -1;
    }

    @SuppressWarnings("unchecked")
    public int find(Object o) {
        int left = 0;
        int right = size() - 1;
        while (left <= right) {
            int middle = (left + right) / 2;
            int compare = comparator.compare((T) o, list.get(middle));
            if (compare == 0) {
                return middle;
            } else if (compare < 0) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return -1;
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }

    @Override
    public Object[] toArray() {
        return list.toArray();
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return list.toArray(a);
    }

    @Override
    public boolean add(T t) {
        int index = find(t);
        if (index == -1) {
            list.add(t);
        } else {
            list.add(index, t);
        }
        return true;
    }

    @Override
    public boolean remove(Object o) {
        list.remove(o);
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        for (T t : c) {
            add(t);
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean changed = false;
        for (Object o : c) {
            changed = changed || remove(o);
        }
        return changed;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean changed = false;
        for (T elem : list) {
            if (!c.contains(elem)) {
                changed = changed || remove(elem);
            }
        }
        return changed;
    }

    @Override
    public void clear() {
        list.clear();
    }
}