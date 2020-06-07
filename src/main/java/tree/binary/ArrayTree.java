package tree.binary;

import java.util.function.Consumer;


/**
 * @author Dmitry Pavlov
 * @since 09.06.2020
 */
public class ArrayTree<T> implements Tree<T> {

    public ArrayTree(int size) {
    }

    @Override
    public void insert(T value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(T value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void traverse(Consumer<T> consumer, TraversalType traversalType) {
        throw new UnsupportedOperationException();
    }

    public void traverse(Consumer<T> consumer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean contains(T value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void erase() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException();
    }

}
