package stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Dmitry Pavlov
 * @since 16.05.2020
 */
class LinkedListStackTest {

    @Test
    public void shouldCreateEmptyStack() {
        // when
        var stack = new LinkedListStack<String>();
        // then
        assertTrue(stack.isEmpty());
    }

    @Test
    public void shouldPushCorrectValueInEmptyStack() {
        // given
        var stack = new LinkedListStack<String>();
        // when
        stack.push("a");
        // then
        assertEquals(stack.peek(), "a");
    }

    @Test
    public void shouldPushCorrectValueInNotEmptyStack() {
        // given
        var stack = new LinkedListStack<String>();
        // when
        stack.push("a");
        stack.push("b");
        // then
        assertEquals(stack.peek(), "b");
    }

    @Test
    public void shouldRaiseExceptionOnPushNullValue() {
        // given
        var stack = new LinkedListStack<String>();
        // then
        assertThrows(NullPointerException.class, () -> stack.push(null));
    }

    @Test
    public void shouldPopValueFromNotEmptyStack() {
        // given
        var stack = new LinkedListStack<String>();
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
        var stack = new LinkedListStack<String>();
        // then
        assertThrows(IllegalStateException.class, stack::pop);
    }

    @Test
    public void shouldPeekValueFromNotEmptyStack() {
        // given
        var stack = new LinkedListStack<String>();
        // when
        stack.push("a");
        // then
        assertEquals(stack.peek(), "a");
        assertEquals(stack.peek(), "a");
    }

    @Test
    public void shouldRaiseExceptionOnPeekValueFromEmptyStack() {
        // given
        var stack = new LinkedListStack<String>();
        // then
        assertThrows(IllegalStateException.class, stack::peek);
    }

    @Test
    public void shouldReturnTrueForIsEmptyOnEmptyStack() {
        // when
        var stack = new LinkedListStack<String>();
        // then
        assertTrue(stack.isEmpty());
    }
    @Test
    public void shouldReturnFalseForIsEmptyOnNotEmptyStack() {
        // given
        var stack = new LinkedListStack<String>();
        // when
        stack.push("a");
        // then
        assertFalse(stack.isEmpty());
    }

    @Test
    public void shouldEraseEmptyStack() {
        // given
        var stack = new LinkedListStack<String>();
        // when
        stack.erase();
        // then
        assertFalse(stack.isFull());
        assertTrue(stack.isEmpty());
    }

    @Test
    public void shouldEraseNotEmptyStack() {
        // given
        var stack = new LinkedListStack<String>();
        stack.push("a");
        // when
        stack.erase();
        // then
        assertFalse(stack.isFull());
        assertTrue(stack.isEmpty());
    }
    
}