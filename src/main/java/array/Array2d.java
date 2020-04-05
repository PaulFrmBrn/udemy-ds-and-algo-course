package array;

import java.util.Optional;
import java.util.function.Consumer;

/**
 * Two-dimensional array
 *
 * @param <T> - type of values that allow to put into array
 */
public class Array2d<T> implements Array<T, Coordinates2d> {

    private final Object[][] array;
    private final int numberOfRows;
    private final int numberOfColumns;

    private Array2d(int numberOfRows, int numberOfColumns) {
        if (numberOfRows < 0) {
            throw new IllegalArgumentException("numberOfRows should be => 0");
        }

        if (numberOfColumns < 0) {
            throw new IllegalArgumentException("numberOfColumns should be => 0");
        }
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
        this.array = new Object[numberOfRows][numberOfColumns];
    }

    /**
     * Factory method for array creation
     *
     * @param numberOfRows - number of array's rows
     * @param numberOfColumns - number of array's columns
     * @param <T> - type of values that allow to put into array
     * @return array of the given number of rows and columns
     */
    public static <T> Array2d<T> of(int numberOfRows, int numberOfColumns) {
        return new Array2d<>(numberOfRows, numberOfColumns);
    }

    @Override
    public T insert(Coordinates2d coordinates, T value) {
        validateIndex(coordinates);
        if (array[coordinates.getRow()][coordinates.getColumn()] != null) {
            throw new ArrayStoreException("this cell is already occupied");
        }
        array[coordinates.getRow()][coordinates.getColumn()] = value;
        return value;
    }

    @Override
    public Optional<T> at(Coordinates2d coordinates) {
        validateIndex(coordinates);
        T value = (T) array[coordinates.getRow()][coordinates.getColumn()];
        return Optional.ofNullable(value);
    }

    @Override
    public Optional<Coordinates2d> search(T value) {
        validateValue(value);
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                if (value.equals(array[i][j])) {
                    return Optional.of(Coordinates2d.of(i, j));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public void delete(Coordinates2d coordinates) {
        validateIndex(coordinates);
        array[coordinates.getRow()][coordinates.getColumn()]= null;
    }

    public void visit(Consumer<T> consumer) {
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                consumer.accept((T) array[i][j]);
            }
        }
    }

    private void validateValue(T value) {
        if (value == null) {
            throw new IllegalArgumentException("value can not be null");
        }
    }

    private void validateIndex(Coordinates2d coordinates) {
        if (coordinates == null) {
            throw new IllegalArgumentException("Coordinates should not be null");
        }
        if (coordinates.getRow() > numberOfRows) {
            throw new ArrayIndexOutOfBoundsException("Row index out of boarders");
        }
        if (coordinates.getColumn() > numberOfColumns) {
            throw new ArrayIndexOutOfBoundsException("Column index out of boarders");
        }
    }

}
