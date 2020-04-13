package array;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.IntStream;

/**
 * One-dimensional array
 *
 * @param <T> - type of values that allow to put into array
 */
public class Array1d<T> implements Array<T, Coordinates1d> {

    private final Object[] array;

    private Array1d(int size) {
        this.array = new Object[size];
    }

    /**
     * Factory method for array creation
     *
     * @param size - array's size
     * @param <T>  - type of values that allow to put into array
     * @return array of the given size
     */
    public static <T> Array1d<T> of(int size) {
        var s = valid(size);
        return new Array1d<>(s);
    }

    @Override
    public T insert(Coordinates1d coordinates, T value) {
        var v = valid(value);
        var index = valid(coordinates);

        if (array[index] != null) {
            throw new ArrayStoreException("Cell is already occupied");
        }

        array[index] = v;
        return v;
    }

    @Override
    public Optional<T> at(Coordinates1d coordinates) {
        var index = valid(coordinates);
        return Optional.ofNullable((T) array[index]);
    }

    @Override
    public Optional<Coordinates1d> search(T value) {
        var v = valid(value);

        for (int index = 0; index < array.length; index++) {
            if(v.equals(array[index])){
                return Optional.of(Coordinates1d.of(index));
            }
        }
        return Optional.empty();
    }

    @Override
    public void delete(Coordinates1d coordinates) {
        var index = valid(coordinates);
        array[index] = null;
    }

    @Override
    public void visit(Consumer<T> consumer) {
        for (Object o : array) {
            consumer.accept((T) o);
        }
    }

    private static int valid(int size) {
        if (size < 0) {
            throw new NegativeArraySizeException();
        }
        return size;
    }

    private int valid(Coordinates1d coordinates) {
        if (coordinates == null) {
            throw new IllegalArgumentException(Coordinates1d.class.getSimpleName() + " must be non-null");
        }
        var index = coordinates.getIndex();
        if (index < 0 || index >= array.length) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        return index;
    }

    private T valid(T value) {
        if (value == null) {
            throw new IllegalArgumentException("Value must be non-null");
        }
        return value;
    }

}
