package tree.binary;

import java.util.NoSuchElementException;
import java.util.function.Consumer;

import static java.util.Objects.requireNonNull;

/**
 * @author Dmitry Pavlov
 * @since 09.06.2020
 */
public class ArrayTree<T> implements Tree<T> {

    private final Object[] tree;
    private int lastUsed;

    public ArrayTree(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Size should be greater than 0");
        }
        this.tree = new Object[size+1];
        this.lastUsed = 0;
    }

    @Override
    public void insert(T value) {
        requireNonNull(value, "value");
        if (isFull()) {
            throw new IllegalStateException("Tree is full");
        }
        tree[++lastUsed] = value;
    }

    @Override
    public void delete(T value) {

        requireNonNull(value, "value");
        if (isEmpty()) {
            throw new IllegalStateException("Tree is empty");
        }

        var indexToDelete = 0;
        for (int i = 1; i <= lastUsed; i++) {
            if (value.equals(tree[i])) {
                indexToDelete = i;
                break;
            }
        }

        if (indexToDelete == 0) {
            throw new NoSuchElementException("No such element in the tree");
        }

        if (indexToDelete != lastUsed) {
            tree[indexToDelete] = tree[lastUsed];
        }
        tree[lastUsed--] = null;

    }

    @Override
    public void traverse(Consumer<T> consumer, TraversalType traversalType) {

        requireNonNull(consumer, "consumer");
        requireNonNull(traversalType, "traversalType");

        switch (traversalType) {
            case PRE_ORDER: {
                preOrderTraverse(1, consumer);
            }
            break;
            case IN_ORDER: {
                inOrderTraverse(1, consumer);
            }
            break;
            case POST_ORDER: {
                postOrderTraverse(1, consumer);
            }
            break;
            case LEVEL_ORDER: {
                levelOrderTraverse(consumer);
            }
            break;
            default: {
                throw new IllegalArgumentException("This type of traversal is not supported");
            }
        }

    }

    public void traverse(Consumer<T> consumer) {
        traverse(consumer, TraversalType.LEVEL_ORDER);
    }

    void preOrderTraverse(int index, Consumer<T> consumer) {
        if (index > lastUsed) {
            return;
        }
        consumer.accept((T)tree[index]);
        preOrderTraverse(index * 2, consumer);
        preOrderTraverse(index * 2 + 1, consumer);
    }

    void inOrderTraverse(int index, Consumer<T> consumer) {
        if (index > lastUsed) {
            return;
        }
        inOrderTraverse(index * 2, consumer);
        consumer.accept((T)tree[index]);
        inOrderTraverse(index * 2 + 1, consumer);
    }

    void postOrderTraverse(int index, Consumer<T> consumer) {
        if (index > lastUsed) {
            return;
        }
        postOrderTraverse(index * 2, consumer);
        postOrderTraverse(index * 2 + 1, consumer);
        consumer.accept((T)tree[index]);
    }

    void levelOrderTraverse(Consumer<T> consumer) {
        for (int i = 1; i <= lastUsed; i++) {
            consumer.accept((T)tree[i]);
        }
    }

    @Override
    public boolean contains(T value) {
        requireNonNull(value, "value");
        for (int i = 1; i <= lastUsed; i++) {
            if (value.equals(tree[i])) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void erase() {
        for (int i = 1; i <= lastUsed; i++) {
            tree[i] = null;
        }
        lastUsed = 0;
    }

    @Override
    public boolean isEmpty() {
        return lastUsed == 0;
    }

    private boolean isFull() {
        return lastUsed == tree.length - 1;
    }

}
