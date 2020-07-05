package tree.binary.search;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

import static java.util.Objects.requireNonNull;

/**
 * @author Dmitry Pavlov
 * @since 25.06.2020
 */
public class BinarySearchTree<T extends Comparable<T>> implements Tree<T>{

    private Node<T> root;

    @Override
    public void insert(T value) {

        requireNonNull(value, "value");

        var newNode = new Node<>(value, null, null, null);

        if (isEmpty()) {
            this.root = newNode;
            return;
        }

        var current = root;
        while (true) {
            if (value.compareTo(current.getValue()) < 0) {
                if (current.getLeft() == null) {
                    current.setLeft(newNode);
                    newNode.setParent(current);
                    return;
                } else {
                    current = current.getLeft();
                }
            } else if (value.compareTo(current.getValue()) > 0) {
                if (current.getRight() == null) {
                    current.setRight(newNode);
                    newNode.setParent(current);
                    return;
                } else {
                    current = current.getRight();
                }
            } else  {
                return;
            }
        }

    }

    @Override
    public void delete(T value) {

        requireNonNull(value, "value");
        if (isEmpty()) {
            throw new NoSuchElementException("Tree is empty");
        }

        var target = find(value);
        if (target == null) {
            throw new NoSuchElementException("node with value is not found");
        }

        if (target == root && root.isLeaf()) {
            erase();
            return;
        }

        if (target.isLeaf()) {
            deleteLeaf(target);
            return;
        }

        if (!target.hasBoth()) {
            deleteSingleChilded(target);
            return;
        }

        var successor = findSuccessor(target);
        if (successor.isLeaf()) {
            target.setValue(successor.getValue());
            deleteLeaf(successor);
        } else {
            target.setValue(successor.getValue());
            deleteSingleChilded(successor);
        }

    }

    private void deleteSingleChilded(Node<T> target) {

        if (target.getLeft() == null && target.getRight() != null) {

            if (target.getParent().getLeft() == target) {
                target.getParent().setLeft(target.getRight());
            } else {
                target.getParent().setRight(target.getRight());
            }
            target.getRight().setParent(target.getParent());
            target.setParent(null);
            target.setRight(null);

        } else if (target.getLeft() != null && target.getRight() == null) {

            if (target.getParent().getLeft() == target) {
                target.getParent().setLeft(target.getLeft());
            } else {
                target.getParent().setRight(target.getLeft());
            }
            target.getLeft().setParent(target.getParent());
            target.setParent(null);
            target.setLeft(null);

        } else {

            throw new IllegalArgumentException("target node is not single childed");

        }

    }

    private void deleteLeaf(Node<T> node) {
        if (node.getParent().getLeft() == node) {
            node.getParent().setLeft(null);
            node.setParent(null);
        } else if (node.getParent().getRight() == node) {
            node.getParent().setRight(null);
            node.setParent(null);
        } else {
            throw new IllegalStateException("parent node has no link to node");
        }
        return;
    }

    private Node<T> findSuccessor(Node<T> node) {

        requireNonNull(node, "node");

        var current = node.getRight();
        while (current.getLeft() != null) {
            current = current.getLeft();
        }
        return current;

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

    @Override
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
        if (isEmpty()) {
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
        return find(value) != null;

    }

    private Node<T> find(T value) {

        var current = root;
        while (current != null) {

            if (current.getValue().equals(value)) {
                return current;
            }

            if (current.getLeft() != null && value.compareTo(current.getValue()) <= 0) {
                current = current.getLeft();
            } else if (current.getRight() != null && value.compareTo(current.getValue()) > 0) {
                current = current.getRight();
            } else {
                return null;
            }

        }
        return null;

    }

    @Override
    public void erase() {

        if (isEmpty()) {
            return;
        }
        var queue = new LinkedList<Node<T>>();
        queue.add(root);
        while (!queue.isEmpty()) {
            var current = queue.remove();
            if (current != null) {
                current.setParent(null);
                queue.add(current.getLeft());
                queue.add(current.getRight());
            }
        }

        root = null;

    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    public static class Node<T extends Comparable<T>> {

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

        public boolean hasBoth() {
            return left != null && right != null;
        }
    }
}
