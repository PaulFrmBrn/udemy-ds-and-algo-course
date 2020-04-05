package array;

/**
 * Class for two-dimensional coordinates
 * for navigation in two-dimensional array
 */
public class Coordinates2d implements Coordinates {

    private final int row;
    private final int column;

    private Coordinates2d(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Get row
     *
     * @return row
     */
    public int getRow() {
        return row;
    }

    /**
     * Get column
     *
     * @return column
     */
    public int getColumn() {
        return column;
    }

    /**
     * Factory method for Coordinates2d creation
     *
     * @param row - row
     * @param column - column
     * @return two-dimensional coordinates
     */
    public static Coordinates2d of(int row, int column) {
        return new Coordinates2d(row, column);
    }
}
