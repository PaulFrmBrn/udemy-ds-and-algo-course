package tree.binary.search;

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
import static tree.binary.search.Tree.TraversalType.IN_ORDER;
import static tree.binary.search.Tree.TraversalType.LEVEL_ORDER;
import static tree.binary.search.Tree.TraversalType.POST_ORDER;
import static tree.binary.search.Tree.TraversalType.PRE_ORDER;

/**
 * @author Dmitry Pavlov
 * @since 28.06.2020
 */
class BinarySearchTreeTest {

    @Test
    public void shouldCreateEmptyTree() {
        // when
        var tree = new BinarySearchTree<Integer>();
        // then
        assertTrue(tree.isEmpty());
    }

    @Test
    public void shouldInsertCorrectValueInEmptyTree() {
        // given
        var tree = new BinarySearchTree<Integer>();
        // when
        tree.insert(100);
        // then
        assertEquals("100", getDefaultRepresentation(tree));
    }

    private static Stream<Arguments> shouldInsertCorrectValueInNotEmptyTree() {
        return Stream.of(
                of(10, "100 80 200 70 90 150 300 50 250 400 40 60 10"),
                of(140, "100 80 200 70 90 150 300 50 140 250 400 40 60")
        );
    }

    @ParameterizedTest
    @MethodSource
    public void shouldInsertCorrectValueInNotEmptyTree(Integer value, String expectedRepresentation) {
        // given
        var tree = getDefaultTree();
        // when
        tree.insert(value);
        // then
        assertEquals(expectedRepresentation, getDefaultRepresentation(tree));
    }

    @Test
    public void shouldRaiseExceptionOnInsertingNullValue() {
        // given
        var tree = new BinarySearchTree<Integer>();
        // then
        assertThrows(NullPointerException.class, () -> tree.insert(null));
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
        var modifiedTree = getDefaultTree();
        modifiedTree.insert(170);
        modifiedTree.insert(160);
        modifiedTree.insert(180);
        return Stream.of(
                of(getSingleNodeTree(100), 100,  ""),
                of (getDefaultTree(), 60, "100 80 200 70 90 150 300 50 250 400 40"),
                of (getDefaultTree(), 70, "100 80 200 50 90 150 300 40 60 250 400"),
                of (getDefaultTree(), 100, "150 80 200 70 90 300 50 250 400 40 60"),
                of (modifiedTree, 100, "150 80 200 70 90 170 300 50 160 180 250 400 40 60")
        );
    }

    @ParameterizedTest
    @MethodSource
    public void shouldDeleteValueFromNotEmptyTree(BinarySearchTree<Integer> tree, Integer valueToDelete, String expectedRepresentation) {
        // when
        tree.delete(valueToDelete);
        // then
        assertEquals(expectedRepresentation, getDefaultRepresentation(tree));
    }

    @Test
    public void shouldRaiseExceptionOnDeletingValueFromEmptyTree() {
        // given
        var tree = new BinarySearchTree<Integer>();
        // then
        assertThrows(NoSuchElementException.class, () -> tree.delete(1));
    }

    @Test
    public void shouldRaiseExceptionOnDeletingValueWhichDoesNotExist() {
        // given
        var tree = getDefaultTree();
        // then
        assertThrows(NoSuchElementException.class, () -> tree.delete(4));
    }

    private static Stream<Arguments> shouldReturnContainsTrueIfTreeContainsValue() {
        return Stream.of(
                of(getSingleNodeTree(100), 100),
                of(getDefaultTree(), 100),
                of(getDefaultTree(), 90),
                of(getDefaultTree(), 400)
        );
    }

    @ParameterizedTest
    @MethodSource
    public void shouldReturnContainsTrueIfTreeContainsValue(Tree<Integer> tree, Integer value) {
        assertTrue(tree.contains(value));
    }

    private static Stream<Arguments> shouldReturnContainsFalseIfTreeDoesNotContainValue() {
        return Stream.of(
                of(new BinarySearchTree<Integer>(), 1),
                of(getSingleNodeTree(100), 80),
                of(getDefaultTree(), 140)
        );
    }

    @ParameterizedTest
    @MethodSource
    public void shouldReturnContainsFalseIfTreeDoesNotContainValue(Tree<Integer> tree, Integer value) {
        assertFalse(tree.contains(value));
    }

    @Test
    public void shouldReturnTrueForIsEmptyOnEmptyTree() {
        assertTrue(new BinarySearchTree<Integer>().isEmpty());
    }

    @Test
    public void shouldReturnFalseForIsEmptyOnNotEmptyTree() {
        assertFalse(getSingleNodeTree(1).isEmpty());
    }

    private static Stream<Arguments> shouldEraseTree() {
        return Stream.of(
                of(new BinarySearchTree<Integer>()),
                of(getSingleNodeTree(1)),
                of(getDefaultTree())
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

                of(new BinarySearchTree<Integer>(), PRE_ORDER,  ""),
                of(getSingleNodeTree(100), PRE_ORDER,  "100"),
                of(getDefaultTree(), PRE_ORDER,  "100 80 70 50 40 60 90 200 150 300 250 400"),

                of(new BinarySearchTree<Integer>(), IN_ORDER,  ""),
                of(getSingleNodeTree(100), IN_ORDER,  "100"),
                of(getDefaultTree(), IN_ORDER,  "40 50 60 70 80 90 100 150 200 250 300 400"),

                of(new BinarySearchTree<Integer>(), POST_ORDER,  ""),
                of(getSingleNodeTree(100), POST_ORDER,  "100"),
                of(getDefaultTree(), POST_ORDER,  "40 60 50 70 90 80 150 250 400 300 200 100"),

                of(new BinarySearchTree<Integer>(), LEVEL_ORDER,  ""),
                of(getSingleNodeTree(100), LEVEL_ORDER,  "100"),
                of(getDefaultTree(), LEVEL_ORDER,  "100 80 200 70 90 150 300 50 250 400 40 60")

        );
    }

    @ParameterizedTest
    @MethodSource
    public void shouldTraverseTreeInRightOrder(Tree<Integer> tree, Tree.TraversalType traversalType, String expectedRepresentation) {
        // given
        var builder = new StringBuilder();
        Consumer<Integer> consumer = (value) -> builder.append(value).append(" ");
        // when
        tree.traverse(consumer, traversalType);
        // then
        assertEquals(expectedRepresentation, builder.toString().trim());
    }

    private <T extends Comparable<T>> String getDefaultRepresentation(Tree<T> tree) {
        var builder = new StringBuilder();
        tree.traverse((value) -> builder.append(value).append(" "));
        return builder.toString().trim();
    }

    private static Tree<Integer> getSingleNodeTree(Integer value) {
        var tree = new BinarySearchTree<Integer>();
        tree.insert(value);
        return tree;
    }

    private static Tree<Integer> getDefaultTree() {
        var tree = new BinarySearchTree<Integer>();
        tree.insert(100);
        tree.insert(80);
        tree.insert(200);
        tree.insert(70);
        tree.insert(90);
        tree.insert(150);
        tree.insert(300);
        tree.insert(50);
        tree.insert(250);
        tree.insert(400);
        tree.insert(40);
        tree.insert(60);
        return tree;
    }


}