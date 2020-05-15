package stack;

import static java.util.Objects.requireNonNull;

/**
 * @author Dmitry Pavlov
 * @since 15.05.2020
 */
public class LinkedListStack<T> implements Stack<T> {

    private Node<T> head;

    @Override
    public void push(T value) {
        head = new Node<>(value, head);
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        var value = head.getValue();
        head = head.getNext();
        return value;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return head.getValue();
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public void erase() {
        head = null;
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
