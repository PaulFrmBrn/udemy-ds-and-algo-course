package tree.binary;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.of;
import static tree.binary.TraversalType.IN_ORDER;
import static tree.binary.TraversalType.LEVEL_ORDER;
import static tree.binary.TraversalType.POST_ORDER;
import static tree.binary.TraversalType.PRE_ORDER;

/**
 * @author Dmitry Pavlov
 * @since 09.06.2020
 */
class ArrayTreeTest {

    @Test
    public void shouldCreateEmptyTree() {
        // when
        var tree = new ArrayTree<Integer>(1);
        // then
        assertTrue(tree.isEmpty());
    }

    @Test
    public void shouldRaiseExceptionOnCreatingTreeWithIllegalSize() {
        assertThrows(IllegalArgumentException.class, () -> new ArrayTree<Integer>(-1));
    }

    @Test
    public void shouldInsertCorrectValueInEmptyTree() {
        // given
        var tree = new ArrayTree<Integer>(1);
        // when
        tree.insert(1);
        // then
        assertEquals("1", getDefaultRepresentation(tree));
    }

    private static Stream<Arguments> shouldInsertCorrectValueInNotEmptyTree() {
        return Stream.of(
                of(getThreeNodeTree(), "1 2 3 4"),
                of(getEightNodeTree(), "20 100 3 50 15 250 35 222 4")
        );
    }

    @ParameterizedTest
    @MethodSource
    public void shouldInsertCorrectValueInNotEmptyTree(ArrayTree<Integer> tree, String expectedRepresentation) {
        // when
        tree.insert(4);
        // then
        assertEquals(expectedRepresentation, getDefaultRepresentation(tree));
    }

    @Test
    public void shouldRaiseExceptionOnInsertingNullValue() {
        // given
        var tree = new ArrayTree<Integer>(1);
        // then
        assertThrows(NullPointerException.class, () -> tree.insert(null));
    }

    @Test
    public void shouldRaiseExceptionOnInsertingValueInFullTree() {
        // given
        var tree = new ArrayTree<Integer>(1);
        // when
        tree.insert(1);
        // then
        assertThrows(IllegalStateException.class, () -> tree.insert(2));
    }

    @Test
    public void shouldDeleteValueFromSingleNodeTree() {
        // given
        var tree = getSingleNodeTree(1);
        // when
        tree.delete(1);
        // then
        assertTrue(tree.isEmpty());
    }

    private static Stream<Arguments> shouldDeleteValueFromNotEmptyTree() {
        return Stream.of(
                of(getThreeNodeTree(), 2,  "1 3"),
                of(getThreeNodeTree(), 3,  "1 2"),
                of(getThreeNodeTree(), 1,  "3 2"),
                of(getEightNodeTree(), 222, "20 100 3 50 15 250 35"),
                of(getEightNodeTree(), 15, "20 100 3 50 222 250 35"),
                of(getEightNodeTree(), 250, "20 100 3 50 15 222 35"),
                of(getEightNodeTree(), 20, "222 100 3 50 15 250 35"),
                of(getEightNodeTree(), 100, "20 222 3 50 15 250 35"),
                of(getEightNodeTree(), 3, "20 100 222 50 15 250 35")
        );
    }

    @ParameterizedTest
    @MethodSource
    public void shouldDeleteValueFromNotEmptyTree(ArrayTree<Integer> tree, Integer valueToDelete, String expectedRepresentation) {
        // when
        tree.delete(valueToDelete);
        // then
        assertEquals(expectedRepresentation, getDefaultRepresentation(tree));
    }

    @Test
    public void shouldRaiseExceptionOnDeletingValueFromEmptyTree() {
        // given
        var tree = new ArrayTree<Integer>(1);
        // then
        assertThrows(IllegalStateException.class, () -> tree.delete(1));
    }

    @Test
    public void shouldRaiseExceptionOnDeletingValueWhichDoesNotExist() {
        // given
        var tree = getThreeNodeTree();
        // then
        assertThrows(NoSuchElementException.class, () -> tree.delete(4));
    }

    private static Stream<Arguments> shouldReturnContainsTrueIfTreeContainsValue() {
        return Stream.of(
                of(getSingleNodeTree(1), 1),
                of(getThreeNodeTree(), 1),
                of(getThreeNodeTree(), 2),
                of(getThreeNodeTree(), 3),
                of(getEightNodeTree(), 222),
                of(getEightNodeTree(), 15),
                of(getEightNodeTree(), 250),
                of(getEightNodeTree(), 20),
                of(getEightNodeTree(), 100),
                of(getEightNodeTree(), 3)
        );
    }

    @ParameterizedTest
    @MethodSource
    public void shouldReturnContainsTrueIfTreeContainsValue(Tree<Integer> tree, Integer value) {
        assertTrue(tree.contains(value));
    }

    private static Stream<Arguments> shouldReturnContainsFalseIfTreeDoesNotContainValue() {
        return Stream.of(
                of(new ArrayTree<Integer>(1), 5),
                of(getSingleNodeTree(1), 5),
                of(getThreeNodeTree(), 5),
                of(getEightNodeTree(), 5)
        );
    }

    @ParameterizedTest
    @MethodSource
    public void shouldReturnContainsFalseIfTreeDoesNotContainValue(Tree<Integer> tree, Integer value) {
        assertFalse(tree.contains(value));
    }

    @Test
    public void shouldReturnTrueForIsEmptyOnEmptyTree() {
        assertTrue(new ArrayTree<Integer>(1).isEmpty());
    }

    @Test
    public void shouldReturnFalseForIsEmptyOnNotEmptyTree() {
        assertFalse(getSingleNodeTree(1).isEmpty());
    }

    private static Stream<Arguments> shouldEraseTree() {
        return Stream.of(
                of(new ArrayTree<Integer>(1)),
                of(getSingleNodeTree(1)),
                of(getThreeNodeTree())
        );
    }

    @ParameterizedTest
    @MethodSource
    public void shouldEraseTree(Tree<Integer> tree) {
        // when
        tree.erase();
        // then
        assertTrue(tree.isEmpty());
    }

    private static Stream<Arguments> shouldTraverseTreeInRightOrder() {
        return Stream.of(

                of(new ArrayTree<Integer>(1), PRE_ORDER,  ""),
                of(getSingleNodeTree(1), PRE_ORDER,  "1"),
                of(getThreeNodeTree(), PRE_ORDER,  "1 2 3"),
                of(getEightNodeTree(), PRE_ORDER,  "20 100 50 222 15 3 250 35"),

                of(new ArrayTree<Integer>(1), IN_ORDER,  ""),
                of(getSingleNodeTree(1), IN_ORDER,  "1"),
                of(getThreeNodeTree(), IN_ORDER,  "2 1 3"),
                of(getEightNodeTree(), IN_ORDER,  "222 50 100 15 20 250 3 35"),

                of(new ArrayTree<Integer>(1), POST_ORDER,  ""),
                of(getSingleNodeTree(1), POST_ORDER,  "1"),
                of(getThreeNodeTree(), POST_ORDER,  "2 3 1"),
                of(getEightNodeTree(), POST_ORDER,  "222 50 15 100 250 35 3 20"),

                of(new ArrayTree<Integer>(1), LEVEL_ORDER,  ""),
                of(getSingleNodeTree(1), LEVEL_ORDER,  "1"),
                of(getThreeNodeTree(), LEVEL_ORDER,  "1 2 3"),
                of(getEightNodeTree(), LEVEL_ORDER,  "20 100 3 50 15 250 35 222")

        );
    }

    @ParameterizedTest
    @MethodSource
    public void shouldTraverseTreeInRightOrder(Tree<Integer> tree, TraversalType traversalType, String expectedRepresentation) {
        // given
        var builder = new StringBuilder();
        Consumer<Integer> consumer = (value) -> builder.append(value).append(" ");
        // when
        tree.traverse(consumer, traversalType);
        // then
        assertEquals(expectedRepresentation, builder.toString().trim());
    }

    private <T> String getDefaultRepresentation(ArrayTree<T> tree) {
        var builder = new StringBuilder();
        tree.traverse((value) -> builder.append(value).append(" "));
        return builder.toString().trim();
    }

    private static Tree<Integer> getSingleNodeTree(Integer value) {
        var tree = new ArrayTree<Integer>(2);
        tree.insert(value);
        return tree;
    }

    private static Tree<Integer> getThreeNodeTree() {
        var tree = new ArrayTree<Integer>(4);
        tree.insert(1);
        tree.insert(2);
        tree.insert(3);
        return tree;
    }

    private static Tree<Integer> getEightNodeTree() {
        var tree = new ArrayTree<Integer>(9);
        tree.insert(20);
        tree.insert(100);
        tree.insert(3);
        tree.insert(50);
        tree.insert(15);
        tree.insert(250);
        tree.insert(35);
        tree.insert(222);
        return tree;
    }

}