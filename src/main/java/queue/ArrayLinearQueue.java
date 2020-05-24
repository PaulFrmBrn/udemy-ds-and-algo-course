package queue;

import java.util.Arrays;

import static java.util.Objects.requireNonNull;

/**
 * @author Dmitry Pavlov
 * @since 24.05.2020
 */
public class ArrayLinearQueue<T> implements Queue<T> {

    private static final int EMPTY_QUEUE_POINTER = -1;

    private final Object[] array;
    private int start;
    private int end;

    public ArrayLinearQueue(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("size should be > 0");
        }
        this.start = EMPTY_QUEUE_POINTER;
        this.end = EMPTY_QUEUE_POINTER;
        this.array = new Object[size];
    }

    @Override
    public void enqueue(T value) {
        requireNonNull(value, "value");
        if (isFull()) {
            throw new IllegalStateException("Queue is full");
        }
        if (end == -1) {
            start = end = 0;
            array[end] = value;
        } else {
            array[++end] = value;
        }
    }

    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        T value = (T) array[start];
        array[start++] = null;
        if (start > end) {
            start = end = EMPTY_QUEUE_POINTER;
        }
        return value;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return (T) array[start];
    }

    @Override
    public boolean isFull() {
        return end == array.length - 1;
    }

    @Override
    public boolean isEmpty() {
        return start == EMPTY_QUEUE_POINTER;
    }

    @Override
    public void erase() {
        Arrays.fill(array, null);
        start = EMPTY_QUEUE_POINTER;
        end = EMPTY_QUEUE_POINTER;
    }
}
