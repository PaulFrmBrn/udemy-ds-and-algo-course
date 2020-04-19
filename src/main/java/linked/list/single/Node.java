package linked.list.single;

import static java.util.Objects.requireNonNull;

public class Node<T> {

    private final T value;
    private Node<T> Next;

    public Node(T value, Node<T> next) {
        this.value = value;
        Next = next;
    }

    public static <T> Node<T> of(T value) {
        return new Node<>(value, null);
    }

    public static <T> Node<T> of(T value, Node<T> next) {
        requireNonNull(value, "value");
        return new Node<>(value, next);
    }

    public T getValue() {
        return value;
    }

    public Node<T> getNext() {
        return Next;
    }

    public void setNext(Node<T> next) {
        Next = next;
    }
}