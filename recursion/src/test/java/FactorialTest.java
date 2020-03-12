import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.of;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;


class FactorialTest {

    private static Stream<Arguments> shouldCalculateFactorial() {
        return Stream.of(
                of(1, 1),
                of(2, 2),
                of(3, 6),
                of(4, 24),
                of(5, 120)
        );
    }

    Factorial sut;

    @BeforeEach
    void init() {
        sut = new Factorial();
    }

    @ParameterizedTest
    @MethodSource
    void shouldCalculateFactorial(int base, int expectedFactorialValue) {
        assertEquals(expectedFactorialValue, sut.of(base));
    }

}
