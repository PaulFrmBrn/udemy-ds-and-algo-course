package array;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.IntStream;

/**
 * Two-dimensional array
 *
 * @param <T> - type of values that allow to put into array
 */
public class Array2d<T> implements Array<T, Coordinates2d> {

    private Array1d<T>[] array;

    private Array2d(int rows, int columns) {
        this.array = new Array1d[rows];
        for (int i = 0; i < rows; i++) {
            array[i] = Array1d.of(columns);
        }
    }

    /**
     * Factory method for array creation
     *
     * @param numberOfRows    - number of array's rows
     * @param numberOfColumns - number of array's columns
     * @param <T>             - type of values that allow to put into array
     * @return array of the given number of rows and columns
     */
    public static <T> Array2d<T> of(int numberOfRows, int numberOfColumns) {
        var rows = valid(numberOfRows);
        var columns = valid(numberOfColumns);
        return new Array2d<>(rows, columns);
    }

    @Override
    public T insert(Coordinates2d coordinates, T value) {
        var v = valid(value);
        var row = valid(coordinates);
        var column = Coordinates1d.of(coordinates.getColumn());
        return array[row].insert(column, v);
    }

    @Override
    public Optional<T> at(Coordinates2d coordinates) {
        var row = valid(coordinates);
        var column = Coordinates1d.of(coordinates.getColumn());
        return array[row].at(column);
    }

    @Override
    public Optional<Coordinates2d> search(T value) {
        var v = valid(value);
        return IntStream
                .range(0, array.length)
                .mapToObj(row -> array[row]
                        .search(v)
                        .map(column -> Coordinates2d.of(row, column.getIndex())))
                .flatMap(Optional::stream)
                .findFirst();
    }

    @Override
    public void delete(Coordinates2d coordinates) {
        var row = valid(coordinates);
        var column = Coordinates1d.of(coordinates.getColumn());
        array[row].delete(column);
    }

    @Override
    public void visit(Consumer<T> consumer) {
        for (Array1d<T> a : array) {
            a.visit(consumer);
        }
    }

    private int valid(Coordinates2d coordinates) {
        if (coordinates == null) {
            throw new IllegalArgumentException(Coordinates.class.getSimpleName() + " must be non-null");
        }
        var row = coordinates.getRow();
        if (row < 0 || row >= array.length) {
            throw new ArrayIndexOutOfBoundsException(row);
        }
        return row;
    }

    private static int valid(int size) {
        if (size < 0) {
            throw new NegativeArraySizeException();
        }
        return size;
    }

    private T valid(T value) {
        if (value == null) {
            throw new IllegalArgumentException("Value must be non-null");
        }
        return value;
    }
}
