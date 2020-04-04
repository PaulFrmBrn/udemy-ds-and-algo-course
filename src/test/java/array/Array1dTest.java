package array;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class Array1dTest {

    @Test
    void shouldCreateEmptyArrayByFactoryMethod() {
        // given
        int size = 10;
        // when
        Array1d<Integer> array = Array1d.of(size);
        // then
        assertNotNull(array);
    }

    @Test
    void shouldVisitEmptyArray() {
        // given
        int size = 10;
        Array1d<Integer> array = Array1d.of(size);
        // expect
        assertDoesNotThrow(() -> array.visit(i -> System.out.print(i + ", ")));
    }

    @Test
    void shouldFillArrayWithGivenNumbers() {
        // given
        int start = 0;
        int end = 9;
        int size = 10;
        Array1d<Integer> array = Array1d.of(size);
        // when
        IntStream.range(start, end)
                .forEach(i -> array.insert(
                        Coordinates1d.of(i),
                        10 * i));
        // then
        StringBuilder sb = new StringBuilder();
        array.visit(sb::append);
        assertEquals("01020304050607080null", sb.toString());
    }

    @Test
    void shouldReturnInsertedValue() {
        // given
        int index = 2;
        int value = 18;
        int size = 10;
        Array1d<Integer> array = Array1d.of(size);
        // when
        int insertedValue = array.insert(Coordinates1d.of(index), value);
        // then
        assertEquals(value, insertedValue);
    }

    @Test
    void shouldThrowExceptionCellIsAlreadyOccupied() {
        // given
        int start = 0;
        int end = 9;
        int size = 10;
        Array1d<Integer> array = Array1d.of(size);
        IntStream.range(start, end)
                .forEach(i -> array.insert(
                        Coordinates1d.of(i),
                        10 * i));
        // expect
        assertThrows(IllegalArgumentException.class,
                () -> array.insert(Coordinates1d.of(1), 100));
    }

    @Test
    void shouldThrowAnExceptionIfIndexOutOfBound() {
        // given
        int size = 10;
        Array1d<Integer> array = Array1d.of(size);
        // expect
        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> array.insert(Coordinates1d.of(2 * size), 120));
    }


    @Test
    void shouldVisitNonEmptyArray() {
        // given
        int start = 0;
        int end = 9;
        int size = 10;
        Array1d<Integer> array = Array1d.of(size);
        IntStream.range(start, end)
                .forEach(i -> array.insert(
                        Coordinates1d.of(i),
                        10 * i));
        String expectedPresentation = "0, 1, 2, 3, 4, 5, 6, 7, 8, null ";
        StringBuilder sb = new StringBuilder();
        // when
        array.visit(cell -> sb.append(cell).append(", "));
        // then
        String presentation = sb.toString();
        assertEquals(expectedPresentation, presentation);
    }

    @ParameterizedTest
    @CsvSource({"1, 10", "5, 50"})
    void shouldGiveCellValueByIndex(Integer index, Integer expectedValue) {
        // given
        int start = 0;
        int end = 9;
        int size = 10;
        Array1d<Integer> array = Array1d.of(size);
        IntStream.range(start, end)
                .forEach(i -> array.insert(
                        Coordinates1d.of(i),
                        10 * i));
        // when
        Optional<Integer> value = array.at(Coordinates1d.of(index));
        // then
        var v = value.orElseThrow();
        assertEquals(expectedValue, v);
    }

    @Test
    void shouldGiveCellValueByIndex() {
        // given
        int start = 0;
        int end = 9;
        int size = 10;
        Array1d<Integer> array = Array1d.of(size);
        IntStream.range(start, end)
                .forEach(i -> array.insert(
                        Coordinates1d.of(i),
                        10 * i));
        // expext
        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> array.at(Coordinates1d.of(2 * size)));
    }

    @Test
    void shouldFindAnExistingValueInArray() {
        // given
        int start = 0;
        int end = 9;
        int size = 10;
        Array1d<Integer> array = Array1d.of(size);
        IntStream.range(start, end)
                .forEach(i -> array.insert(
                        Coordinates1d.of(i),
                        10 * i));
        // when
        Optional<Coordinates1d> index = array.search(30);
        // then
        index.ifPresent(
                it -> assertEquals(3, it.getIndex()));
    }

    @Test
    void shouldNotFindAnAbsentValueInArray() {
        // given
        int start = 0;
        int end = 9;
        int size = 10;
        Array1d<Integer> array = Array1d.of(size);
        IntStream.range(start, end)
                .forEach(i -> array.insert(
                        Coordinates1d.of(i),
                        10 * i));
        // when
        Optional<Coordinates1d> index = array.search(400);
        // then
        assertTrue(index.isEmpty());
    }

    @Test
    void shouldDeleteValueByIndex() {
        // given
        int start = 0;
        int end = 9;
        int size = 10;
        Array1d<Integer> array = Array1d.of(size);
        IntStream.range(start, end)
                .forEach(i -> array.insert(
                        Coordinates1d.of(i),
                        10 * i));
        // when
        Coordinates1d index = Coordinates1d.of(3);
        array.delete(index);
        Optional<Integer> value = array.at(index);
        // then
        assertTrue(value.isEmpty());
    }

    @Test
    void shouldThrowExceptionWhileDeletingByNonexistentIndex() {
        // given
        int start = 0;
        int end = 9;
        int size = 10;
        Array1d<Integer> array = Array1d.of(size);
        IntStream.range(start, end)
                .forEach(i -> array.insert(
                        Coordinates1d.of(i),
                        10 * i));
        // expect
        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> array.delete(Coordinates1d.of(2 * size)));
    }
}