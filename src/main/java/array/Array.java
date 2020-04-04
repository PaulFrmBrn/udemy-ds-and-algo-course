package array;

import java.util.Optional;
import java.util.function.Consumer;

/**
 * Interface for an array with undefined number of dimensions
 *
 * @param <T> - type of values that allow to put into array
 * @param <C> - coordinates class for navigation through array
 */
public interface Array<T, C> {

    /**
     * Insert value in array
     *
     * @param coordinates - index of inserting
     * @param value - value to insert
     * @return inserted value
     */
    T insert(C coordinates, T value);

    /**
     * Access a particular element of array
     *
     * @param coordinates - position of an element
     */
    Optional<T> at(C coordinates);

    /**
     * Search for an element in array
     *
     * @param value - value for searching
     */
    Optional<C> search(T value);

    /**
     * Delete value from array
     *
     * @param coordinates - position for deleting
     */
    void delete(C coordinates);
}
