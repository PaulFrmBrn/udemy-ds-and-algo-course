import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.of;


class FactorialTest {

    Factorial sut;

    @BeforeEach
    void init() {
        sut = new Factorial();
    }

    private static Stream<Arguments> shouldCalculateFactorial() {
        return Stream.of(
                of(1, 1),
                of(2, 2),
                of(3, 6),
                of(4, 24),
                of(5, 120)
        );
    }

    @ParameterizedTest
    @MethodSource
    void shouldCalculateFactorial(int base, int expectedFactorialValue) {
        assertEquals(expectedFactorialValue, sut.of(base));
    }

    private static Stream<Integer> shouldFailWithException() {
        return Stream.of(0, -1, -7);
    }

    @ParameterizedTest
    @MethodSource
    void shouldFailWithException(int base) {
        assertThrows(IllegalArgumentException.class, () -> sut.of(base));
    }

}
