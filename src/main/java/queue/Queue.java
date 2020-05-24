package queue;

/**
 * Queue interface
 *
 * Describes methods for simple queue based on FIFO approach
 *
 * Null values can not be stored in the queue
 *
 * @author Dmitry Pavlov
 * @since 21.05.2020
 */
public interface Queue<T> {

    /**
     * enqueues value at the start of the queue
     * @throws IllegalArgumentException if value is null
     * @throws IllegalStateException if queue is full
     */
    void enqueue(T value);

    /**
     * dequeues value from end of the queue
     * @throws IllegalStateException if queue is empty
     */
    T dequeue();

    /**
     * returns value from start of the queue
     * @throws IllegalStateException if stack is empty
     */
    T peek();

    /**
     * @return true if queue is empty, false - otherwise
     */
    boolean isFull();

    /**
     * @return true if queue is full, false - otherwise
     */
    boolean isEmpty();

    /**
     * deletes all elements from queue, so {@link Queue#isEmpty()} returns {@link Boolean#TRUE} after calling this method
     */
    void erase();

}
