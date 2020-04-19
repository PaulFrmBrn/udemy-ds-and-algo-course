package linked.list.single;

import linked.list.LinkedList;

import java.util.NoSuchElementException;
import java.util.function.Consumer;

import static java.util.Objects.requireNonNull;

public class SingleLinkedList<T> implements LinkedList<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public SingleLinkedList() {
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

        if (head == null) {
            head = tail = Node.of(value);
        } else {
            head = Node.of(value, head);
        }
        size++;
    }

    @Override
    public void insertLast(T value) {

        validateValue(value);

        if (head == null) {
            head = tail = Node.of(value);
        } else {
            tail.setNext(Node.of(value));
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
        var currentLocation = 0;
        while (current != null) {

            if (currentLocation == location - 1) {
                current.setNext(Node.of(value, current.getNext()));
                size++;
                return;
            }

            currentLocation++;
            current = current.getNext();
        }

    }

    @Override
    public int search(T value) {
        validateValue(value);
        var currentLocation = 0;
        for (var current = head; current != null; current = current.getNext()) {

            if (value.equals(current.getValue())) {
                return currentLocation;
            }
            currentLocation++;

        }
        throw new NoSuchElementException("There is no element with value: " + value);
    }

    @Override
    public void visit(Consumer<T> consumer) {
        requireNonNull(consumer, "consumer");
        for (var current = head; current != null; current = current.getNext()) {
            consumer.accept(current.getValue());
        }
    }

    @Override
    public void deleteFirst() {

        if (head == null) {
            throw new IllegalArgumentException("Can not delete node from empty list");
        }

        head = head.getNext();
        size--;
        if (head == null) {
            tail = null;
        }

    }

    @Override
    public void deleteLast() {

        if (head == null) {
            throw new IllegalArgumentException("Can not delete node from empty list");
        }

        if (head == tail) {
            head = tail = null;
            size = 0;
            return;
        }
        var current = head;
        while (current.getNext() != tail) {
            current = current.getNext();
        }
        current.setNext(null);
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
        var currentLocation = 0;
        while (current != null) {

            if (currentLocation == location - 1) {
                current.setNext(current.getNext().getNext());
                size--;
                return;
            }

            currentLocation++;
            current = current.getNext();
        }

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

}
