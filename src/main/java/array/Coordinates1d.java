package array;

/**
 * Class for one-dimensional coordinates
 * for navigation in one-dimensional array
 */
public class Coordinates1d implements Coordinates {

    private final int index;

    private Coordinates1d(int index) {
        this.index = index;
    }

    /**
     * Get index
     *
     * @return index
     */
    public int getIndex() {
        return index;
    }

    /**
     * Factory method for Coordinates1d creation
     *
     * @param index index
     * @return one-dimensional coordinates
     */
    public static Coordinates1d of(int index) {
        return new Coordinates1d(index);
    }
}
