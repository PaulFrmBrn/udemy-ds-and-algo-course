package array;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class Array2dTest {

    @Test
    void shouldCreateEmptyArrayByFactoryMethod() {
        // given
        int size = 10;
        // when
        Array2d<Integer> array = Array2d.of(size, size);
        // then
        assertNotNull(array);
    }

    @Test
    void shouldVisitEmptyArray() {
        // given
        int size = 10;
        Array2d<Integer> array = Array2d.of(size, size);
        // expect
        assertDoesNotThrow(() -> array.visit(i -> System.out.print(i + ", ")));
    }

    @Test
    void shouldFillArrayWithGivenNumbers() {
        // given
        int start = 0;
        int end = 3;
        int size = 3;
        Array2d<Integer> array = Array2d.of(size, size);
        // when
        IntStream.range(start, end).forEach(i ->
                IntStream.range(start, end).forEach(j ->
                        array.insert(Coordinates2d.of(i, j), j)));
        // then
        StringBuilder sb = new StringBuilder();
        array.visit(row -> row.visit(sb::append));
        assertEquals("012012012", sb.toString());
    }

    @Test
    void shouldReturnInsertedValue() {
        // given
        int index = 2;
        int value = 18;
        int size = 10;
        Array2d<Integer> array = Array2d.of(size, size);
        // when
        int insertedValue = array.insert(Coordinates2d.of(index, index), value);
        // then
        assertEquals(value, insertedValue);
    }

    @Test
    void shouldThrowExceptionCellIsAlreadyOccupied() {
        // given
        int start = 0;
        int end = 9;
        int size = 10;
        Array2d<Integer> array = Array2d.of(size, size);
        IntStream.range(start, end)
                .forEach(i -> array.insert(
                        Coordinates2d.of(i, i),
                        10 * i));
        // expect
        assertThrows(IllegalArgumentException.class,
                () -> array.insert(Coordinates2d.of(1, 1), 100));
    }

    @Test
    void shouldThrowAnExceptionIfIndexOutOfBound() {
        // given
        int size = 10;
        Array2d<Integer> array = Array2d.of(size, size);
        // expect
        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> array.insert(Coordinates2d.of(2 * size, size), 120));
    }


    @Test
    void shouldVisitNonEmptyArray() {
        // given
        int start = 0;
        int end = 4;
        int size = 5;
        Array2d<Integer> array = Array2d.of(size, size);
        IntStream.range(start, end)
                .forEach(i -> array.insert(
                        Coordinates2d.of(i, i),
                        10 * i));
        String expectedPresentation = "0, null, null, null, null, \n" +
                                      "null, 1, null, null, null, \n" +
                                      "null, null, 2, null, null, \n" +
                                      "null, null, null, 3, null, \n" +
                                      "null, null, null, null, null, \n";
        StringBuilder sb = new StringBuilder();
        // when
        array.visit(row -> {
            row.visit(cell -> sb.append(cell).append(", "));
            sb.append("\n");
        });
        // then
        String presentation = sb.toString();
        assertEquals(expectedPresentation, presentation);
    }

    @ParameterizedTest
    @CsvSource({"1,10", "5,50"})
    void shouldGiveCellValueByIndex(Integer index, Integer expectedValue) {
        // given
        int start = 0;
        int end = 9;
        int size = 10;
        Array2d<Integer> array = Array2d.of(size, size);
        IntStream.range(start, end)
                .forEach(i -> array.insert(
                        Coordinates2d.of(i, i),
                        10 * i));
        // when
        Optional<Integer> value = array.at(Coordinates2d.of(index, index));
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
        Array2d<Integer> array = Array2d.of(size, size);
        IntStream.range(start, end)
                .forEach(i -> array.insert(
                        Coordinates2d.of(i, i),
                        10 * i));
        // expext
        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> array.at(Coordinates2d.of(2 * size, size)));
    }

    @Test
    void shouldFindAnExistingValueInArray() {
        // given
        int start = 0;
        int end = 9;
        int size = 10;
        Array2d<Integer> array = Array2d.of(size, size);
        IntStream.range(start, end)
                .forEach(i -> array.insert(
                        Coordinates2d.of(i, i),
                        10 * i));
        // when
        Optional<Coordinates2d> index = array.search(30);
        // then
        var i = index.orElseThrow();
        assertAll(
                () -> assertEquals(3, i.getRow()),
                () -> assertEquals(3, i.getColumn()));
    }

    @Test
    void shouldNotFindAnAbsentValueInArray() {
        // given
        int start = 0;
        int end = 9;
        int size = 10;
        Array2d<Integer> array = Array2d.of(size, size);
        IntStream.range(start, end)
                .forEach(i -> array.insert(
                        Coordinates2d.of(i, i),
                        10 * i));
        // when
        Optional<Coordinates2d> index = array.search(400);
        // then
        assertTrue(index.isEmpty());
    }

    @Test
    void shouldDeleteValueByIndex() {
        // given
        int start = 0;
        int end = 9;
        int size = 10;
        Array2d<Integer> array = Array2d.of(size, size);
        IntStream.range(start, end)
                .forEach(i -> array.insert(
                        Coordinates2d.of(i, i),
                        10 * i));
        // when
        Coordinates2d coordinates = Coordinates2d.of(3, 3);
        array.delete(coordinates);
        Optional<Integer> value = array.at(coordinates);
        // then
        assertTrue(value.isEmpty());
    }

    @Test
    void shouldThrowExceptionWhileDeletingByNonexistentIndex() {
        // given
        int start = 0;
        int end = 9;
        int size = 10;
        Array2d<Integer> array = Array2d.of(size, size);
        IntStream.range(start, end)
                .forEach(i -> array.insert(
                        Coordinates2d.of(i, i),
                        10 * i));
        // expect
        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> array.delete(Coordinates2d.of(2 * size, size)));
    }
}
