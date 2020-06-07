package tree.binary;

import java.util.NoSuchElementException;
import java.util.function.Consumer;

/**
 * Tree interface
 *
 * @author Dmitry Pavlov
 * @since 09.06.2020
 */
public interface Tree<T> {

    /**
     * inserts new value in the tree
     * @throws NullPointerException if value is null
     */
    void insert(T value);

    /**
     * deletes value from the tree
     * @throws NullPointerException if value is null
     * @throws NoSuchElementException if there is no such element in the tree
     * @throws IllegalStateException if there is empty
     */
    void delete(T value);

    /**
     * Applies specified function to the each and every element of the tree
     */
    void traverse(Consumer<T> consumer, TraversalType traversalType);

    /**
     * Returns {@link Boolean#TRUE} if specified value is in the tree, {@link Boolean#FALSE} otherwise
     * @throws IllegalArgumentException if value is null
     * @throws NullPointerException if value is null
     */
    boolean contains(T value);

    /**
     * Deletes all elements from the tree
     */
    void erase();

    /**
     * Returns {@link Boolean#TRUE} if tree is empty, {@link Boolean#FALSE} otherwise
     */
    boolean isEmpty();
}
