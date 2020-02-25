import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.of;


public class FactorialRecursiveImplementationTest {

    private static Stream<Arguments> test_getFactorial() {
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
    public void test_getFactorial(int base, int expectedFactorialValue) {
        assertEquals(0, expectedFactorialValue);
    }

}
