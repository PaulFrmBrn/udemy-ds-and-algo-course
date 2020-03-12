package recursion;

public class Fibonacci {

    int at(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("index should be >= 0");
        }
        if (index == 0 || index == 1) {
            return index;
        }
        if (index == 2) {
            return 1;
        }
        return at(index - 1) + at(index - 2);
    }
}
