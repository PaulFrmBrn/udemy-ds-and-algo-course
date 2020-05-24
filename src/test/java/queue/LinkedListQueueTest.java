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
class LinkedListQueueTest {

    @Test
    public void shouldCreateEmptyQueue() {
        // when
        var queue = new LinkedListQueue<String>();
        // then
        assertTrue(queue.isEmpty());
    }

    @Test
    public void shouldEnqueueCorrectValueInEmptyQueue() {
        // given
        var queue = new LinkedListQueue<String>();
        // when
        queue.enqueue("a");
        // then
        assertEquals(queue.peek(), "a");
    }

    @Test
    public void shouldEnqueueCorrectValueInNotEmptyQueue() {
        // given
        var queue = new LinkedListQueue<String>();
        // when
        queue.enqueue("a");
        queue.enqueue("b");
        // then
        assertEquals(queue.peek(), "a");
    }

    @Test
    public void shouldRaiseExceptionOnEnqueueNullValue() {
        // given
        var queue = new LinkedListQueue<String>();
        // then
        assertThrows(NullPointerException.class, () -> queue.enqueue(null));
    }

    @Test
    public void shouldDequeueValueFromNotEmptyQueue() {
        // given
        var queue = new LinkedListQueue<String>();
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
        var queue = new LinkedListQueue<String>();
        // then
        assertThrows(IllegalStateException.class, queue::dequeue);
    }

    @Test
    public void shouldPeekValueFromNotEmptyQueue() {
        // given
        var queue = new LinkedListQueue<String>();
        // when
        queue.enqueue("a");
        // then
        assertEquals(queue.peek(), "a");
        assertEquals(queue.peek(), "a");
    }

    @Test
    public void shouldRaiseExceptionOnPeekValueFromEmptyQueue() {
        // given
        var queue = new LinkedListQueue<String>();
        // then
        assertThrows(IllegalStateException.class, queue::peek);
    }

    @Test
    public void shouldReturnTrueForIsEmptyOnEmptyQueue() {
        // given
        var queue = new LinkedListQueue<String>();
        // then
        assertTrue(queue.isEmpty());
    }
    @Test
    public void shouldReturnFalseForIsEmptyOnNotEmptyQueue() {
        // given
        var queue = new LinkedListQueue<String>();
        queue.enqueue("a");
        // then
        assertFalse(queue.isEmpty());
    }


    @Test
    public void shouldReturnFalseForIsFull() {
        // given
        var queue = new LinkedListQueue<String>();
        queue.enqueue("a");
        // then
        assertFalse(queue.isFull());
    }

    @Test
    public void shouldEraseEmptyQueue() {
        // given
        var queue = new LinkedListQueue<String>();
        // when
        queue.erase();
        // then
        assertFalse(queue.isFull());
        assertTrue(queue.isEmpty());
    }

    @Test
    public void shouldEraseNotEmptyQueue() {
        // given
        var queue = new LinkedListQueue<String>();
        queue.enqueue("a");
        // when
        queue.erase();
        // then
        assertFalse(queue.isFull());
        assertTrue(queue.isEmpty());
    }

}