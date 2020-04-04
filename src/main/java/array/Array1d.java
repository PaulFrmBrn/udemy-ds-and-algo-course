package array;

import java.util.Optional;
import java.util.function.Consumer;

/**
 * One-dimensional array
 *
 * @param <T> - type of values that allow to put into array
 */
public class Array1d<T> implements Array<T, Coordinates1d> {

    /**
     * Factory method for array creation
     *
     * @param size - array's size
     * @param <T> - type of values that allow to put into array
     * @return array of the given size
     */
    public static <T> Array1d<T> of(int size) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T insert(Coordinates1d coordinates, T value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<T> at(Coordinates1d coordinates) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Coordinates1d> search(T value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Coordinates1d coordinates) {
        throw new UnsupportedOperationException();
    }

    /**
     * Visit the array (print for example)
     *
     * @param consumer - array's cell visitor
     */
    public void visit(Consumer<T> consumer) {
        throw new UnsupportedOperationException();
    }

}
