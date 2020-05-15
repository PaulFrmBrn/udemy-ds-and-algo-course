package stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Dmitry Pavlov
 * @since 15.05.2020
 */
class ArrayStackTest {

    @Test
    public void shouldCreateEmptyStack() {
        // when
        var stack = new ArrayStack<String>(1);
        // then
        assertTrue(stack.isEmpty());
    }

    @Test
    public void shouldPushCorrectValueInEmptyStack() {
        // given
        var stack = new ArrayStack<String>(1);
        // when
        stack.push("a");
        // then
        assertEquals(stack.peek(), "a");
    }

    @Test
    public void shouldPushCorrectValueInNotEmptyStack() {
        // given
        var stack = new ArrayStack<String>(2);
        // when
        stack.push("a");
        stack.push("b");
        // then
        assertEquals(stack.peek(), "b");
    }

    @Test
    public void shouldRaiseExceptionOnPushNullValue() {
        // given
        var stack = new ArrayStack<String>(1);
        // then
        assertThrows(NullPointerException.class, () -> stack.push(null));
    }

    @Test
    public void shouldRaiseExceptionOnPushValueInFullStack() {
        // given
        var stack = new ArrayStack<String>(1);
        stack.push("a");
        // then
        assertThrows(IllegalStateException.class, () -> stack.push("b"));
    }

    @Test
    public void shouldPopValueFromNotEmptyStack() {
        // given
        var stack = new ArrayStack<String>(2);
        // when
        stack.push("a");
        stack.push("b");
        // then
        assertEquals(stack.pop(), "b");
        assertEquals(stack.pop(), "a");
    }

    @Test
    public void shouldRaiseExceptionOnPopValueFromEmptyStack() {
        // given
        var stack = new ArrayStack<String>(1);
        // then
        assertThrows(IllegalStateException.class, stack::pop);
    }

    @Test
    public void shouldPeekValueFromNotEmptyStack() {
        // given
        var stack = new ArrayStack<String>(2);
        // when
        stack.push("a");
        // then
        assertEquals(stack.peek(), "a");
        assertEquals(stack.peek(), "a");
    }

    @Test
    public void shouldRaiseExceptionOnPeekValueFromEmptyStack() {
        // given
        var stack = new ArrayStack<String>(1);
        // then
        assertThrows(IllegalStateException.class, stack::peek);
    }

    @Test
    public void shouldReturnTrueForIsEmptyOnEmptyStack() {
        // when
        var stack = new ArrayStack<String>(1);
        // then
        assertTrue(stack.isEmpty());
    }
    @Test
    public void shouldReturnFalseForIsEmptyOnNotEmptyStack() {
        // given
        var stack = new ArrayStack<String>(1);
        // when
        stack.push("a");
        // then
        assertFalse(stack.isEmpty());
    }

    @Test
    public void shouldReturnTrueForIsFullOnFullStack() {
        // given
        var stack = new ArrayStack<String>(1);
        // when
        stack.push("a");
        // then
        assertTrue(stack.isFull());
    }

    @Test
    public void shouldReturnFalseForIsFullOnNotFullStack() {
        // given
        var stack = new ArrayStack<String>(2);
        // when
        stack.push("a");
        // then
        assertFalse(stack.isFull());
    }

    @Test
    public void shouldEraseEmptyStack() {
        // given
        var stack = new ArrayStack<String>(2);
        // when
        stack.erase();
        // then
        assertFalse(stack.isFull());
        assertTrue(stack.isEmpty());
    }

    @Test
    public void shouldEraseNotEmptyStack() {
        // given
        var stack = new ArrayStack<String>(2);
        stack.push("a");
        // when
        stack.erase();
        // then
        assertFalse(stack.isFull());
        assertTrue(stack.isEmpty());
    }

}