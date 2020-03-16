package recursion;

public class Factorial {

    int of(int base) {
        if (base < 0) {
            throw new IllegalArgumentException("base < 0");
        }
        return base != 0
            ? base * of(base - 1)
            : 1;
    }
}
