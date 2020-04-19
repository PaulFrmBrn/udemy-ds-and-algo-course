package linked.list.double_;

import static java.util.Objects.requireNonNull;

public class Node<T> {

    private final T value;
    private Node<T> prev;
    private Node<T> next;

    public Node(T value, Node<T> prev, Node<T> next) {
        this.value = value;
        this.prev = prev;
        this.next = next;
    }

    public static <T> Node<T> of(T value) {
        return new Node<>(value, null, null);
    }

    public static <T> Node<T> of(T value, Node<T> prev, Node<T> next) {
        requireNonNull(value, "value");
        return new Node<>(value, prev, next);
    }

    public T getValue() {
        return value;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public Node<T> getPrev() {
        return prev;
    }

    public void setPrev(Node<T> prev) {
        this.prev = prev;
    }
}