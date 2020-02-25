import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.of;

public class FibonacciRecursiveImplementationTest {

    private static Stream<Arguments> test_getFibonacciNumber() {
        return Stream.of(
                of(0, 0),
                of(1, 1),
                of(2, 1),
                of(3, 2),
                of(4, 3),
                of(5, 5),
                of(6, 8),
                of(7, 13),
                of(8, 21),
                of(9, 34)
        );
    }

    @ParameterizedTest
    @MethodSource
    public void test_getFibonacciNumber(int index, int expectedFibonacciNumber) {
        assertEquals(0, expectedFibonacciNumber);
    }

}
