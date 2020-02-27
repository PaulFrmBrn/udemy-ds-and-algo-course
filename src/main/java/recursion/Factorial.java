package recursion;

public class Factorial {

    int of(int base) {
        return base != 0
            ? base * of(base - 1)
            : 1;
    }
}
