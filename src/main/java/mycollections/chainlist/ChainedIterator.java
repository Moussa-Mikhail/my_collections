package mycollections.chainlist;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Moussa
 */
public class ChainedIterator implements Iterator<Object> {
    private final Iterator<Iterator<?>> nestedIterator;
    private Iterator<?> currentIterator;

    public ChainedIterator(List<Iterable<?>> list) {
        List<Iterator<?>> iterators = list.stream().map(Iterable::iterator).collect(Collectors.toList());
        nestedIterator = iterators.iterator();
        currentIterator = nestedIterator.next();
    }

    @Override
    public boolean hasNext() {
        return currentIterator.hasNext() || nestedIterator.hasNext();
    }

    @Override
    public Object next() {
        if (!currentIterator.hasNext()) {
            currentIterator = nestedIterator.next();
        }
        return currentIterator.next();
    }
}
