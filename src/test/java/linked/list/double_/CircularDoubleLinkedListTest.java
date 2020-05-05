package linked.list.double_;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.NoSuchElementException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.of;

/**
 *
 * @author Dmitry Pavlov
 * @since 05.05.2020
 */
class CircularDoubleLinkedListTest {

    @Test
    public void shouldCreateEmptyList() {
        // when
        var list = new CircularDoubleLinkedList<Integer>();
        // then
        assertEquals(list.size(), 0);
    }

    @Test
    public void shouldInsertFirstElementWithCorrectValueInEmptyList() {
        // given
        var list = new CircularDoubleLinkedList<Integer>();
        // when
        list.insertFirst(1);
        // then
        assertEquals("1", getStringRepresentationOf(list));
    }

    @Test
    public void shouldInsertFirstElementWithCorrectValueInNotEmptyList() {
        // given
        var list = new CircularDoubleLinkedList<Integer>();
        list.insertFirst(1);
        // when
        list.insertFirst(2);
        // then
        assertEquals("21", getStringRepresentationOf(list));
    }

    @Test
    public void shouldRaiseExceptionOnInsertingNullValueViaInsertFirst() {
        // given
        var list = new CircularDoubleLinkedList<Integer>();
        // then
        assertThrows(IllegalArgumentException.class, () -> list.insertFirst(null));
    }

    @Test
    public void shouldInsertLastElementWithCorrectValueInEmptyList() {
        // given
        var list = new CircularDoubleLinkedList<Integer>();
        // when
        list.insertLast(1);
        // then
        assertEquals("1", getStringRepresentationOf(list));
    }

    @Test
    public void shouldInsertLastElementWithCorrectValueInNotEmptyList() {
        // given
        var list = new CircularDoubleLinkedList<Integer>();
        list.insertLast(1);
        // when
        list.insertLast(2);
        // then
        assertEquals("12", getStringRepresentationOf(list));
    }

    @Test
    public void shouldRaiseExceptionOnInsertingNullValueViaInsertLast() {
        // given
        var list = new CircularDoubleLinkedList<Integer>();
        // then
        assertThrows(IllegalArgumentException.class, () -> list.insertLast(null));
    }

    private static Stream<Arguments> shouldInsertValueWithSpecifiedLocation() {
        return Stream.of(
                of(createList(), 0, 5, "5"),
                of(createList(1), 0, 5, "51"),
                of(createList(1), 1, 5, "15"),
                of(createList(1, 2), 1, 5, "152"),
                of(createList(1, 2), 2, 5, "125"),
                of(createList(1, 2, 3, 4), 3, 5, "12354"),
                of(createList(1, 2, 3, 4), 4, 5, "12345")
        );
    }

    @ParameterizedTest
    @MethodSource
    public void shouldInsertValueWithSpecifiedLocation(CircularDoubleLinkedList<Integer> list,
                                                       int location,
                                                       Integer value,
                                                       String expectedListRepresentation) {
        // when
        list.insertAt(location, value);
        // then
        assertEquals(expectedListRepresentation, getStringRepresentationOf(list));
    }

    @Test
    public void shouldRaiseExceptionOnInsertingNullValueViaInsertAt() {
        // given
        var list = new CircularDoubleLinkedList<Integer>();
        // then
        assertThrows(IllegalArgumentException.class, () -> list.insertAt(0,null));
    }

    private static Stream<Arguments> shouldRaiseExceptionOnInsertingAtWrongLocation() {
        return Stream.of(
                of(createList(), -1, 5),
                of(createList(), 2, 5),
                of(createList(1), -1, 5),
                of(createList(1), 3, 5)
        );
    }

    @ParameterizedTest
    @MethodSource
    public void shouldRaiseExceptionOnInsertingAtWrongLocation(CircularDoubleLinkedList<Integer> list,
                                                               int location,
                                                               Integer value) {
        assertThrows(IndexOutOfBoundsException.class, () -> list.insertAt(location,value));
    }

    private static Stream<Arguments> shouldReturnLocationForTheList() {
        return Stream.of(
                of(createList(1,2,3), 1, 0),
                of(createList(1,2,3), 2, 1),
                of(createList(1,2,3), 3, 2),
                of(createList(1), 1, 0)
        );
    }

    @ParameterizedTest
    @MethodSource
    public void shouldReturnLocationForTheList(CircularDoubleLinkedList<Integer> list,
                                               Integer value,
                                               int expectedLocation) {
        assertEquals(expectedLocation, list.search(value));
    }

    private static Stream<Arguments> shouldRaiseExceptionOnSearchingTheValueThatDoesNotPresentedInTheList() {
        return Stream.of(
                of(createList(1,2,3), 5),
                of(createList(1), 2),
                of(createList(), 2)
        );
    }

    @ParameterizedTest
    @MethodSource
    public void shouldRaiseExceptionOnSearchingTheValueThatDoesNotPresentedInTheList(CircularDoubleLinkedList<Integer> list,
                                                                                     Integer value) {
        assertThrows(NoSuchElementException.class, () -> list.search(value));
    }

    @Test
    public void shouldVisitAllNodesOfTheListFromTheBeginningToTheEnd() {
        // given
        var list = new CircularDoubleLinkedList<Integer>();
        list.insertLast(1);
        list.insertLast(2);
        list.insertLast(3);
        var builder = new StringBuilder();
        // when
        list.visit(builder::append);
        // then
        assertEquals("123", builder.toString());
    }

    @Test
    public void shouldVisitAllNodesOfTheListFromEndToTheBeginning() {
        // given
        var list = new CircularDoubleLinkedList<Integer>();
        list.insertLast(1);
        list.insertLast(2);
        list.insertLast(3);
        var builder = new StringBuilder();
        // when
        list.reverseVisit(builder::append);
        // then
        assertEquals("321", builder.toString());
    }

    @Test
    public void shouldDeleteFirstElementInSingleElementList() {
        // given
        var list = new CircularDoubleLinkedList<Integer>();
        list.insertFirst(1);
        // when
        list.deleteFirst();
        // then
        assertEquals("", getStringRepresentationOf(list));
    }

    @Test
    public void shouldDeleteFirstElementInDoubleElementList() {
        // given
        var list = new CircularDoubleLinkedList<Integer>();
        list.insertFirst(1);
        list.insertFirst(2);
        // when
        list.deleteFirst();
        // then
        assertEquals("1", getStringRepresentationOf(list));
    }

    @Test
    public void shouldRaiseExceptionOnDeletingElementInEmptyListViaDeleteFirst() {
        // given
        var list = new CircularDoubleLinkedList<Integer>();
        // then
        assertThrows(IllegalArgumentException.class, () -> list.deleteFirst());
    }

    @Test
    public void shouldDeleteLastElementInSingleElementList() {
        // given
        var list = new CircularDoubleLinkedList<Integer>();
        list.insertFirst(1);
        // when
        list.deleteLast();
        // then
        assertEquals("", getStringRepresentationOf(list));
    }

    @Test
    public void shouldDeleteLastElementInDoubleElementList() {
        // given
        var list = new CircularDoubleLinkedList<Integer>();
        list.insertFirst(1);
        list.insertFirst(2);
        list.insertFirst(3);
        // when
        list.deleteLast();
        // then
        assertEquals("32", getStringRepresentationOf(list));
    }

    @Test
    public void shouldRaiseExceptionOnDeletingElementInEmptyListViaDeleteLast() {
        // given
        var list = new CircularDoubleLinkedList<Integer>();
        // then
        assertThrows(IllegalArgumentException.class, () -> list.deleteLast());
    }

    private static Stream<Arguments> shouldDeleteWithSpecifiedLocation() {
        return Stream.of(
                of(createList(1), 0, ""),
                of(createList(1, 2), 0, "2"),
                of(createList(1, 2), 1, "1"),
                of(createList(1, 2, 3, 4), 2, "124"),
                of(createList(1, 2, 3, 4), 1, "134"),
                of(createList(1, 2, 3, 4), 3, "123")
        );
    }

    @ParameterizedTest
    @MethodSource
    public void shouldDeleteWithSpecifiedLocation(CircularDoubleLinkedList<Integer> list,
                                                  int location,
                                                  String expectedListRepresentation) {
        // when
        list.deleteAt(location);
        // then
        assertEquals(expectedListRepresentation, getStringRepresentationOf(list));
    }

    @Test
    public void shouldRaiseExceptionOnDeletingElementInEmptyListViaDeleteAt() {
        // given
        var list = new CircularDoubleLinkedList<Integer>();
        // then
        assertThrows(IndexOutOfBoundsException.class, () -> list.deleteAt(0));
    }

    private static Stream<Arguments> shouldRaiseExceptionOnDeletingElementWithWrongLocation() {
        return Stream.of(
                of(createList(1,2,3), 5),
                of(createList(1), 2),
                of(createList(), -1)
        );
    }

    @ParameterizedTest
    @MethodSource
    public void shouldRaiseExceptionOnDeletingElementWithWrongLocation(CircularDoubleLinkedList<Integer> list,
                                                                       int location) {
        assertThrows(IndexOutOfBoundsException.class, () -> list.deleteAt(location));
    }

    @Test
    public void shouldDeleteAllElements() {
        // given
        var list = new CircularDoubleLinkedList<Integer>();
        list.insertFirst(1);
        list.insertFirst(2);
        list.insertFirst(3);
        // when
        list.deleteAll();
        // then
        assertEquals("", getStringRepresentationOf(list));
    }

    public static <T> String getStringRepresentationOf(CircularDoubleLinkedList<T> list) {
        var builder = new StringBuilder();
        list.visit(builder::append);
        return builder.toString();
    }

    public static <T> CircularDoubleLinkedList<T> createList(T... array) {
        var list = new CircularDoubleLinkedList<T>();
        for (T element : array) {
            list.insertLast(element);
        }
        return list;
    }


}