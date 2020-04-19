package linked.list.double_;

import linked.list.LinkedListWithReverseVisit;

import java.util.NoSuchElementException;
import java.util.function.Consumer;

import static java.util.Objects.requireNonNull;

public class CircularDoubleLinkedList<T> implements LinkedListWithReverseVisit<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public CircularDoubleLinkedList() {
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
            var newNode = Node.of(value);
            newNode.setPrev(newNode);
            newNode.setNext(newNode);
            head = tail = newNode;
        } else {
            var newNode = Node.of(value, tail, head);
            head = newNode;
            tail.setNext(head);
            newNode.getNext().setPrev(newNode);
        }
        size++;
    }

    @Override
    public void insertLast(T value) {

        validateValue(value);

        if (head == null) {
            var newNode = Node.of(value);
            newNode.setPrev(newNode);
            newNode.setNext(newNode);
            head = tail = newNode;
        } else {
            var newNode = Node.of(value, tail, head);
            tail = newNode;
            head.setPrev(tail);
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
        int currentLocation = 0;
        do {
            if (currentLocation == location - 1) {
                var newNode = Node.of(value, current, current.getNext());
                newNode.getPrev().setNext(newNode);
                newNode.getNext().setPrev(newNode);
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
    public void reverseVisit(Consumer<T> consumer) {
        requireNonNull(consumer, "consumer");

        if (!isEmpty()) {
            var current = tail;
            do {
                consumer.accept(current.getValue());
                current = current.getPrev();
            } while (current != tail);
        }
    }

    @Override
    public void deleteFirst() {

        if (head == null) {
            throw new IllegalArgumentException("Can not delete node from empty list");
        }

        var node = head;
        if (isSingleElement()) {
            head = tail = null;
        } else {
            head = head.getNext();
            head.setPrev(tail);
            tail.setNext(head);
        }
        node.setPrev(null);
        node.setNext(null);
        size--;

    }

    @Override
    public void deleteLast() {

        if (head == null) {
            throw new IllegalArgumentException("Can not delete node from empty list");
        }

        var node = tail;
        if (isSingleElement()) {
            head = tail = null;
        } else {
            tail = tail.getPrev();
            head.setPrev(tail);
            tail.setNext(head);
        }
        node.setPrev(null);
        node.setNext(null);
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
        int currentLocation = 0;
        do {
            if (currentLocation == location - 1) {
                var node = current.getNext();

                current.setNext(current.getNext().getNext());
                current.getNext().setPrev(current);

                node.setPrev(null);
                node.setNext(null);
                size--;
                return;
            }
            current = current.getNext();
            currentLocation++;
        } while (current != head);

    }

    @Override
    public void deleteAll() {

        if (!isEmpty()) {
            var current = head;
            do {
                current.setPrev(null);
                current = current.getNext();
            } while (current != head);
            head = null;
            tail = null;
            size = 0;
        }


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