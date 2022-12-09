package mycollections.linkedlist;

/**
 * @author Moussa
 */
@SuppressWarnings("java:S1104")
public class Node<T> {
    public T value;
    public Node<T> next;
    public Node<T> prev;

    public Node(T value) {
        this.value = value;
        this.next = null;
    }
}
