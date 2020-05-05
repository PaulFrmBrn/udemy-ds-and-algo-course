package linked.list;

import java.util.function.Consumer;

/**
 * Linked List interface with Reverse visit method.
 *
 * Adds one additional method to the {@link LinkedList} - {@link LinkedListWithReverseVisit#reverseVisit(Consumer)}
 *
 * NOT threadsafe
 *
 * @author Dmitry Pavlov
 * @since 05.05.2020
 */
public interface LinkedListWithReverseVisit<T> extends LinkedList<T> {

    /**
     * Applies specified function to the each and every element of the list from the end of the list to the beginning  of the list
     */
    void reverseVisit(Consumer<T> consumer);

}
