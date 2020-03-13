import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.of;

class FibonacciTest {

    private Fibonacci sut;

    @BeforeEach
    void init() {
        sut = new Fibonacci();
    }

    private static Stream<Arguments> shouldCalculateFibonacciNumber() {
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
    void shouldCalculateFibonacciNumber(int index, int expectedFibonacciNumber) {
        assertEquals(expectedFibonacciNumber, sut.at(index));
    }

    private static Stream<Integer> shouldFailWithException() {
        return Stream.of(-1, -7);
    }

    @ParameterizedTest
    @MethodSource
    void shouldFailWithException(int index) {
        assertThrows(IllegalArgumentException.class, () -> sut.at(index));
    }


}
