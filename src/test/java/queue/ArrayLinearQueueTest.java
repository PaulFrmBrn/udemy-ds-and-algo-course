package queue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Dmitry Pavlov
 * @since 24.05.2020
 */
class ArrayLinearQueueTest {

    @Test
    public void shouldCreateEmptyQueue() {
        // when
        var queue = new ArrayLinearQueue<String>(1);
        // then
        assertTrue(queue.isEmpty());
    }

    @Test
    public void shouldRaiseExceptionOnCreatingQueueWithZeroSize() {
        assertThrows(IllegalArgumentException.class, () -> new ArrayLinearQueue<String>(0));
    }

    @Test
    public void shouldEnqueueCorrectValueInEmptyQueue() {
        // given
        var queue = new ArrayLinearQueue<String>(1);
        // when
        queue.enqueue("a");
        // then
        assertEquals(queue.peek(), "a");
    }

    @Test
    public void shouldEnqueueCorrectValueInNotEmptyQueue() {
        // given
        var queue = new ArrayLinearQueue<String>(2);
        // when
        queue.enqueue("a");
        queue.enqueue("b");
        // then
        assertEquals(queue.peek(), "a");
    }

    @Test
    public void shouldRaiseExceptionOnEnqueueNullValue() {
        // given
        var queue = new ArrayLinearQueue<String>(1);
        // then
        assertThrows(NullPointerException.class, () -> queue.enqueue(null));
    }

    @Test
    public void shouldRaiseExceptionOnEnqueueValueInFullQueue() {
        // given
        var queue = new ArrayLinearQueue<String>(1);
        queue.enqueue("a");
        // then
        assertThrows(IllegalStateException.class, () -> queue.enqueue("b"));
    }

    @Test
    public void shouldDequeueValueFromNotEmptyQueue() {
        // given
        var queue = new ArrayLinearQueue<String>(2);
        // when
        queue.enqueue("a");
        queue.enqueue("b");
        // then
        assertEquals(queue.dequeue(), "a");
        assertEquals(queue.dequeue(), "b");
        assertTrue(queue.isEmpty());
    }

    @Test
    public void shouldRaiseExceptionOnDequeueValueFromEmptyQueue() {
        // given
        var queue = new ArrayLinearQueue<String>(1);
        // then
        assertThrows(IllegalStateException.class, queue::dequeue);
    }

    @Test
    public void shouldPeekValueFromNotEmptyQueue() {
        // given
        var queue = new ArrayLinearQueue<String>(2);
        // when
        queue.enqueue("a");
        // then
        assertEquals(queue.peek(), "a");
        assertEquals(queue.peek(), "a");
    }

    @Test
    public void shouldRaiseExceptionOnPeekValueFromEmptyQueue() {
        // given
        var queue = new ArrayLinearQueue<String>(1);
        // then
        assertThrows(IllegalStateException.class, queue::peek);
    }

    @Test
    public void shouldReturnTrueForIsEmptyOnEmptyQueue() {
        // given
        var queue = new ArrayLinearQueue<String>(1);
        // then
        assertTrue(queue.isEmpty());
    }
    @Test
    public void shouldReturnFalseForIsEmptyOnNotEmptyQueue() {
        // given
        var queue = new ArrayLinearQueue<String>(1);
        queue.enqueue("a");
        // then
        assertFalse(queue.isEmpty());
    }

    @Test
    public void shouldReturnTrueForIsFullOnFullQueue() {
        // given
        var queue = new ArrayLinearQueue<String>(1);
        queue.enqueue("a");
        // then
        assertTrue(queue.isFull());
    }

    @Test
    public void shouldReturnFalseForIsFullOnNotFullQueue() {
        // given
        var queue = new ArrayLinearQueue<String>(2);
        queue.enqueue("a");
        // then
        assertFalse(queue.isFull());
    }

    @Test
    public void shouldReturnTrueForIsFullIfHasOneElementAfterDequeueingOtherNMinusOne() {

        // given
        var queue = new ArrayLinearQueue<String>(3);
        queue.enqueue("a");
        queue.enqueue("b");
        queue.enqueue("c");
        queue.dequeue();
        queue.dequeue();
        // then
        assertTrue(queue.isFull());

    }

    @Test
    public void shouldEraseEmptyQueue() {
        // given
        var queue = new ArrayLinearQueue<String>(2);
        // when
        queue.erase();
        // then
        assertFalse(queue.isFull());
        assertTrue(queue.isEmpty());
    }

    @Test
    public void shouldEraseNotEmptyQueue() {
        // given
        var queue = new ArrayLinearQueue<String>(2);
        queue.enqueue("a");
        // when
        queue.erase();
        // then
        assertFalse(queue.isFull());
        assertTrue(queue.isEmpty());
    }

}