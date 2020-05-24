package queue;

import static java.util.Objects.requireNonNull;

/**
 * @author Dmitry Pavlov
 * @since 24.05.2020
 */
public class LinkedListQueue<T> implements Queue<T> {

    private Node<T> head;
    private Node<T> tail;

    @Override
    public void enqueue(T value) {
        requireNonNull(value, "value");
        var newNode = new Node<>(value, null);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.setNext(newNode);
            tail = newNode;
        }
    }

    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        T value = head.getValue();
        head = head.getNext();
        if (head == null) {
            tail = null;
        }
        return value;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return head.getValue();
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public void erase() {
        head = tail = null;
    }

    public static class Node<T> {

        private final T value;
        private Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = requireNonNull(value, "value");
            this.next = next;
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
    }

}
