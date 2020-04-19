package linked.list.double_;

import linked.list.LinkedListWithReverseVisit;

import java.util.NoSuchElementException;
import java.util.function.Consumer;

import static java.util.Objects.requireNonNull;

public class DoubleLinkedList<T> implements LinkedListWithReverseVisit<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public DoubleLinkedList() {
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
            var newNode = Node.of(value, null, head);
            head = newNode;
            newNode.getNext().setPrev(newNode);
        }
        size++;
    }

    @Override
    public void insertLast(T value) {

        validateValue(value);

        if (head == null) {
            head = tail = Node.of(value);
        } else {
            var newNode = Node.of(value, tail, null);
            tail = newNode;
            newNode.getPrev().setNext(newNode);
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
                var newNode = Node.of(value, current, current.getNext());
                newNode.getPrev().setNext(newNode);
                newNode.getNext().setPrev(newNode);
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
    public void reverseVisit(Consumer<T> consumer) {
        requireNonNull(consumer, "consumer");
        for (var current = tail; current != null; current = current.getPrev()) {
            consumer.accept(current.getValue());
        }
    }

    @Override
    public void deleteFirst() {

        if (head == null) {
            throw new IllegalArgumentException("Can not delete node from empty list");
        }

        head = head.getNext();
        if (head == null) {
            tail = null;
        } else {
            head.setPrev(null);
        }
        size--;

    }

    @Override
    public void deleteLast() {

        if (head == null) {
            throw new IllegalArgumentException("Can not delete node from empty list");
        }

        tail = tail.getPrev();
        if (tail == null) {
            head = null;
        } else {
            tail.setNext(null);
        }
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

        if (location == size - 1) {
            deleteLast();
            return;
        }

        var current = head;
        var currentLocation = 0;
        while (current != null) {

            if (currentLocation == location - 1) {

                var node = current.getNext();

                current.setNext(current.getNext().getNext());
                current.getNext().setPrev(current);

                node.setPrev(null);
                node.setNext(null);
                size--;
                return;
            }

            currentLocation++;
            current = current.getNext();
        }

    }

    @Override
    public void deleteAll() {

        var current = head;
        while (current != null) {
            current.setPrev(null);
            current = current.getNext();
        }
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