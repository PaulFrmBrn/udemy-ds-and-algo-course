package tree.binary.search;

import java.util.NoSuchElementException;
import java.util.function.Consumer;

/**
 * Tree interface
 *
 * @author Dmitry Pavlov
 * @since 25.06.2020
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
     * @throws NoSuchElementException if there is no such element in the tree (incl. when tree is empty)
     */
    void delete(T value);

    /**
     * Applies specified function to the each and every element of the tree
     */
    void traverse(Consumer<T> consumer, TraversalType traversalType);

    /**
     * Applies specified function to the each and every element of the tree with default traversal type
     */
    void traverse(Consumer<T> consumer);

    /**
     * Returns {@link Boolean#TRUE} if specified value is in the tree, {@link Boolean#FALSE} otherwise
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

    /**
     * @author Dmitry Pavlov
     * @since 25.06.2020
     */
    enum TraversalType {

        PRE_ORDER,
        IN_ORDER,
        POST_ORDER,
        LEVEL_ORDER,
        ;
    }
}