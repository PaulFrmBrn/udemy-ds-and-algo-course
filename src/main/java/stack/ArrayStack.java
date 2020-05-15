package stack;

import java.util.Arrays;

import static java.util.Objects.requireNonNull;

/**
 * @author Dmitry Pavlov
 * @since 15.05.2020
 */
public class ArrayStack<T> implements Stack<T> {

    private static final int EMPTY_STACK_POINTER = -1;

    private final Object[] array;
    private int pointer = EMPTY_STACK_POINTER;

    public ArrayStack(int size) {
        this.array = new Object[size];
    }

    @Override
    public void push(T value) {
        requireNonNull(value, "value");
        if (isFull()) {
            throw new IllegalStateException("Stack is full");
        }
        array[++pointer] = value;
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        T value = (T) array[pointer];
        array[pointer--] = null;
        return value;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return (T) array[pointer];
    }

    @Override
    public boolean isEmpty() {
        return pointer == EMPTY_STACK_POINTER;
    }

    @Override
    public boolean isFull() {
        return pointer == array.length - 1;
    }

    @Override
    public void erase() {
        Arrays.fill(array, null);
        pointer = -1;
    }
}
