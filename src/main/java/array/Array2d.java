package array;

import java.util.Optional;
import java.util.function.Consumer;

/**
 * Two-dimensional array
 *
 * @param <T> - type of values that allow to put into array
 */
public class Array2d<T> implements Array<T, Coordinates2d> {

    /**
     * Factory method for array creation
     *
     * @param numberOfRows - number of array's rows
     * @param numberOfColumns - number of array's columns
     * @param <T> - type of values that allow to put into array
     * @return array of the given number of rows and columns
     */
    public static <T> Array2d<T> of(int numberOfRows, int numberOfColumns) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T insert(Coordinates2d coordinates, T value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<T> at(Coordinates2d coordinates) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Coordinates2d> search(T value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Coordinates2d coordinates) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void visit(Consumer<T> rowConsumer) {
        throw new UnsupportedOperationException();
    }
}
