package recursion;

public class Fibonacci {

    public int at(int index) {
        if (index < 0) {
            throw new IllegalArgumentException();
        }
        return index == 0 || index == 1
            ? index
            : at(index - 1) + at(index - 2);
    }
}
