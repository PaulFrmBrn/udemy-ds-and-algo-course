package array;

import java.util.Optional;
import java.util.function.Consumer;

/**
 * One-dimensional array
 *
 * @param <T> - type of values that allow to put into array
 */
public class Array1d<T> implements Array<T, Coordinates1d> {

    private final Object[] array;

    private Array1d(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("size should be => 0");
        }
        this.array = new Object[size];
    }

    /**
     * Factory method for array creation
     *
     * @param size - array's size
     * @param <T> - type of values that allow to put into array
     * @return array of the given size
     */
    public static <T> Array1d<T> of(int size) {
        return new Array1d<>(size);
    }

    @Override
    public T insert(Coordinates1d coordinates, T value) {
        validateIndex(coordinates);
        if (array[coordinates.getIndex()] != null) {
            throw new ArrayStoreException("this cell is already occupied");
        }
        array[coordinates.getIndex()] = value;
        return value;
    }

    @Override
    public Optional<T> at(Coordinates1d coordinates) {
        validateIndex(coordinates);
        T value = (T) array[coordinates.getIndex()];
        return Optional.ofNullable(value);
    }

    @Override
    public Optional<Coordinates1d> search(T value) {
        validateValue(value);
        for (int i = 0; i < array.length; i++) {
            if (value.equals(array[i])) {
                return Optional.of(Coordinates1d.of(i));
            }
        }
        return Optional.empty();
    }

    @Override
    public void delete(Coordinates1d coordinates) {
        validateIndex(coordinates);
        array[coordinates.getIndex()] = null;
    }

    @Override
    public void visit(Consumer<T> consumer) {
        for (int i = 0; i < array.length; i++) {
            consumer.accept((T)array[i]);
        }
    }

    private void validateValue(T value) {
        if (value == null) {
            throw new IllegalArgumentException("value can not be null");
        }
    }

    private void validateIndex(Coordinates1d coordinates) {
        if (coordinates == null) {
            throw new IllegalArgumentException("Coordinates should not be null");
        }
        if (coordinates.getIndex() > array.length) {
            throw new ArrayIndexOutOfBoundsException("Index out of boarders");
        }
    }

}
