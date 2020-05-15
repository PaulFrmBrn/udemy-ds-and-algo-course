package stack;

/**
 * Stack interface
 *
 * Describes methods for simple stack based on LIFO approach
 *
 * Null values can not be stored in the stack
 *
 * @author Dmitry Pavlov
 * @since 15.05.2020
 */
public interface Stack<T> {

    /**
     * pushes value on top of the stack
     * @throws IllegalArgumentException if value is null
     * @throws IllegalStateException if stack is full
     */
    void push(T value);

    /**
     * pops value from top of the stack
     * @throws IllegalStateException if stack is empty
     */
    T pop();

    /**
     * pops value from top of the stack
     * @throws IllegalStateException if stack is empty
     */
    T peek();

    /**
     * @return true if stack is empty, false - otherwise
     */
    boolean isEmpty();

    /**
     * @return true if stack is full, false - otherwise
     */
    boolean isFull();

    /**
     * deletes all elements from stack, so {@link Stack#isEmpty()} returns {@link Boolean#TRUE} after calling this method
     */
    void erase();

}
