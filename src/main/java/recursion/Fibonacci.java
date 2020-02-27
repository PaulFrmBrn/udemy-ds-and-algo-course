package recursion;

public class Fibonacci {

    public int at(int index) {
        if (index < 1) {
            throw new IllegalArgumentException();
        }
        return index == 1 || index == 2
            ? index - 1
            : at(index - 1) + at(index - 2);
    }
}
