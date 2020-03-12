package recursion;

public class Factorial {

    int of(int base) {
        if (base < 0) {
            throw new IllegalArgumentException("base should be >= 0");
        }
        if (base == 0) {
            return 1;
        }
        return base * of(base - 1);
    }
}
