package linked.list.single;

import linked.list.LinkedList;

import java.util.function.Consumer;

public class SingleLinkedList<T> implements LinkedList<T> {

    @Override
    public int getSize() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void insertFirst(T value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void insertLast(T value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void insertAt(int location, T value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int search(T value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void visit(Consumer<T> consumer) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void deleteFirst() {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void deleteLast() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAt(int location) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException();
    }

}
