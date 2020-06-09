package tree.binary;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

import static java.util.Objects.requireNonNull;

/**
 * @author Dmitry Pavlov
 * @since 06.06.2020
 */
public class LinkedListTree<T> implements Tree<T> {

    private Node<T> root;

    @Override
    public void insert(T value) {

        requireNonNull(value, "value");

        if (isEmpty()) {
            root = new Node<>(value, null, null, null);
            return;
        }

        var queue = new LinkedList<Node<T>>();
        queue.add(root);
        while (!queue.isEmpty()) {
            var current = queue.remove();
            if (current != null) {
                if (current.getLeft() == null) {
                    current.setLeft(new Node<>(value, current, null, null));
                    return;
                }
                if (current.getRight() == null) {
                    current.setRight(new Node<>(value, current, null, null));
                    return;
                }
                queue.add(current.getLeft());
                queue.add(current.getRight());
            }
        }

    }

    @Override
    public void delete(T value) {

        if (root == null) {
            throw new IllegalStateException("Can not delete value from empty tree");
        }

        Node<T> nodeToDelete = null;

        var queue = new LinkedList<Node<T>>();
        queue.add(root);
        while (!queue.isEmpty()) {
            var current = queue.remove();
            if (current != null) {
                if (current.getValue().equals(value)) {
                    nodeToDelete = current;
                    break;
                }
                queue.add(current.getLeft());
                queue.add(current.getRight());
            }
        }

        if (nodeToDelete == null) {
            throw new NoSuchElementException("There is no such value in the tree");
        }

        if (nodeToDelete.isLeaf()) {
            if (nodeToDelete.getParent() == null) { // root
                erase();
            } else {
                deleteLeaf(nodeToDelete);
            }
            return;
        }

        queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            var current = queue.remove();
            if (current != null) {
                if (current.isLeaf()) {
                    nodeToDelete.setValue(current.getValue());
                    deleteLeaf(current);
                    return;
                }
                queue.add(current.getLeft());
                queue.add(current.getRight());
            }
        }

    }

    private void deleteLeaf(Node<T> node) {
        if (!node.isLeaf()) {
            throw new IllegalArgumentException("The node is not a leaf");
        }
        if (node.getParent().getLeft() == node) {
            node.getParent().setLeft(null);
        } else {
            node.getParent().setRight(null);
        }
        node.setParent(null);

    }

    @Override
    public void traverse(Consumer<T> consumer, TraversalType traversalType) {

        requireNonNull(consumer, "consumer");
        requireNonNull(traversalType, "traversalType");

        switch (traversalType) {
            case PRE_ORDER: {
                preOrderTraverse(root, consumer);
            }
            break;
            case IN_ORDER: {
                inOrderTraverse(root, consumer);
            }
            break;
            case POST_ORDER: {
                postOrderTraverse(root, consumer);
            }
            break;
            case LEVEL_ORDER: {
                levelOrderTraverse(root, consumer);
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

    void preOrderTraverse(Node<T> current, Consumer<T> consumer) {
        if (current == null) {
            return;
        }
        consumer.accept(current.getValue());
        preOrderTraverse(current.getLeft(), consumer);
        preOrderTraverse(current.getRight(), consumer);
    }

    void inOrderTraverse(Node<T> current, Consumer<T> consumer) {
        if (current == null) {
            return;
        }
        inOrderTraverse(current.getLeft(), consumer);
        consumer.accept(current.getValue());
        inOrderTraverse(current.getRight(), consumer);
    }

    void postOrderTraverse(Node<T> current, Consumer<T> consumer) {
        if (current == null) {
            return;
        }
        postOrderTraverse(current.getLeft(), consumer);
        postOrderTraverse(current.getRight(), consumer);
        consumer.accept(current.getValue());
    }

    void levelOrderTraverse(Node<T> root, Consumer<T> consumer) {
        if (root == null) {
            return;
        }
        var queue = new LinkedList<Node<T>>();
        queue.add(root);
        while (!queue.isEmpty()) {
            var current = queue.remove();
            if (current != null) {
                consumer.accept(current.getValue());
                queue.add(current.getLeft());
                queue.add(current.getRight());
            }
        }
    }

    @Override
    public boolean contains(T value) {

        requireNonNull(value, "value");

        if (isEmpty()) {
            return false;
        }

        var queue = new LinkedList<Node<T>>();
        queue.add(root);
        while (!queue.isEmpty()) {
            var current = queue.remove();
            if (current != null) {
                if (current.getValue().equals(value)) {
                    return true;
                }
                queue.add(current.getLeft());
                queue.add(current.getRight());
            }
        }
        return false;

    }

    @Override
    public void erase() {
        root = null;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    public static class Node<T> {

        private T value;
        private Node<T> parent;
        private Node<T> left;
        private Node<T> right;

        public Node(T value, Node<T> parent, Node<T> left, Node<T> right) {
            this.value = requireNonNull(value, "value");
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node<T> getParent() {
            return parent;
        }

        public void setParent(Node<T> parent) {
            this.parent = parent;
        }

        public Node<T> getLeft() {
            return left;
        }

        public void setLeft(Node<T> left) {
            this.left = left;
        }

        public Node<T> getRight() {
            return right;
        }

        public void setRight(Node<T> right) {
            this.right = right;
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }
    }

}
