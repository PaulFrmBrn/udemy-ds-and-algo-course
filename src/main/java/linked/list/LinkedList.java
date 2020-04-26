package linked.list;

import java.util.NoSuchElementException;
import java.util.function.Consumer;

/**
 * Linked List interface
 *
 * Describes methods for one directional Linked List
 *
 * When created contains no elements, so {@link LinkedList#size()} returns 0
 *
 * Location has zero based value
 *
 * All insert operation are applicable on empty list
 *
 * Null values can not be stored in the list
 *
 * Maximum size is restricted with {@link Integer#MAX_VALUE}
 *
 * NOT threadsafe
 *
 * @author Dmitry Pavlov
 * @since 19.04.2020
 */
public interface LinkedList<T> {

    /**
     * Returns the size of the list
     */
    int size();

    /**
     * Inserts element at the beginning of the list
     * @throws IllegalArgumentException if value is null
     */
    void insertFirst(T value);

    /**
     * Inserts element at the end of the list
     * @throws IllegalArgumentException if value is null
     */
    void insertLast(T value);

    /**
     * Inserts element at the specified location of the list
     * @throws IndexOutOfBoundsException if location is not in [0, size) range
     * @throws IllegalArgumentException if value is null
     */
    void insertAt(int location, T value);

    /**
     * Returns location of the element with specified value
     * @throws NoSuchElementException if there is no element with specified value in the list
     * @throws IllegalArgumentException if value is null
     */
    int search(T value);

    /**
     * Applies specified function to the each and every element of the list
     */
    void visit(Consumer<T> consumer);

    /**
     * Deletes element at the beginning of the list
     * @throws IllegalArgumentException if there are no element in the list
     */
    void deleteFirst();

    /**
     * Deletes element at the end of the list
     * @throws IllegalArgumentException if there are no element in the list
     */
    void deleteLast();

    /**
     * Deletes element at the specified location of the list
     * @throws IllegalArgumentException if there are no element in the list
     * @throws IndexOutOfBoundsException if location is not in [0, size) range
     */
    void deleteAt(int location);

    /**
     * Deletes all elements from the list
     */
    void deleteAll();

}
