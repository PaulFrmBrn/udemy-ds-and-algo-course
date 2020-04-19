package linked.list.single;

import linked.list.LinkedList;

import java.util.NoSuchElementException;
import java.util.function.Consumer;

import static java.util.Objects.requireNonNull;

public class CircularSingleLinkedList<T> implements LinkedList<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public CircularSingleLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void insertFirst(T value) {

        validateValue(value);

        if (isEmpty()) {
            Node<T> newNode = Node.of(value);
            newNode.setNext(newNode);
            head = tail = newNode;
        } else {
            head = Node.of(value, head);
            tail.setNext(head);
        }
        size++;
    }

    @Override
    public void insertLast(T value) {

        validateValue(value);

        if (isEmpty()) {
            Node<T> newNode = Node.of(value);
            newNode.setNext(newNode);
            head = tail = newNode;
        } else {
            tail.setNext(Node.of(value, head));
            tail = tail.getNext();
        }
        size++;
    }

    @Override
    public void insertAt(int location, T value) {

        validateValue(value);
        if (location < 0 || location > size) {
            throw new IndexOutOfBoundsException("Location should be >= 0 && <= size");
        }

        if (location == 0) {
            insertFirst(value);
            return;
        }

        if (location == size) {
            insertLast(value);
            return;
        }

        var current = head;
        int currentLocation = 0;
        do {
            if (currentLocation == location - 1) {
                current.setNext(Node.of(value, current.getNext()));
                size++;
                return;
            }
            current = current.getNext();
            currentLocation++;
        } while (current != head);

    }

    @Override
    public int search(T value) {
        validateValue(value);

        if (!isEmpty()) {
            var current = head;
            int currentLocation = 0;
            do {
                if (value.equals(current.getValue())) {
                    return currentLocation;
                }
                current = current.getNext();
                currentLocation++;
            } while (current != head);
        }
        throw new NoSuchElementException("There is no element with value: " + value);
    }

    @Override
    public void visit(Consumer<T> consumer) {
        requireNonNull(consumer, "consumer");

        if (!isEmpty()) {
            var current = head;
            do {
                consumer.accept(current.getValue());
                current = current.getNext();
            } while (current != head);
        }
    }

    @Override
    public void deleteFirst() {

        if (isEmpty()) {
            throw new IllegalArgumentException("Can not delete node from empty list");
        }

        if (isSingleElement()) {
            head.setNext(null);
            head = tail = null;
            size = 0;
        } else {
            head = head.getNext();
            tail.setNext(head);
            size--;
        }

    }

    @Override
    public void deleteLast() {

        if (head == null) {
            throw new IllegalArgumentException("Can not delete node from empty list");
        }

        if (isSingleElement()) {
            head.setNext(null);
            head = tail = null;
            size = 0;
            return;
        }
        var current = head;
        while (current.getNext() != tail) {
            current = current.getNext();
        }
        current.setNext(head);
        tail = current;
        size--;

    }

    @Override
    public void deleteAt(int location) {

        if (location < 0 || location >= size) {
            throw new IndexOutOfBoundsException("Location should be >= 0 && < size");
        }

        if (location == 0) {
            deleteFirst();
            return;
        }

        if (location == size-1) {
            deleteLast();
            return;
        }

        var current = head;
        int currentLocation = 0;
        do {
            if (currentLocation == location - 1) {
                current.setNext(current.getNext().getNext());
                size--;
                return;
            }
            current = current.getNext();
            currentLocation++;
        } while (current != head);

    }

    @Override
    public void deleteAll() {
        head = null;
        tail = null;
        size = 0;
    }
    private void validateValue(T value) {
        if (value == null) {
            throw new IllegalArgumentException("value can not be null");
        }
    }

    private boolean isEmpty() {
        return head == null;
    }

    private boolean isSingleElement() {
        return head == tail && head != null;
    }

}