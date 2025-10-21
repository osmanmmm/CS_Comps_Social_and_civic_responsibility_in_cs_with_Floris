package com.cs.comps

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class QueueTest {

    @Test
    fun enqueueDequeue_FIFO_works() {
        val q = LinkedQueue<Int>()
        assertTrue(q.isEmpty())

        q.enqueue(1)
        q.enqueue(2)
        q.enqueue(3)

        assertEquals(3, q.size())
        assertEquals(1, q.dequeue())
        assertEquals(2, q.dequeue())
        assertEquals(3, q.dequeue())

        assertTrue(q.isEmpty())
        assertEquals(0, q.size())
    }

    @Test
    fun peek_returnsFrontWithoutRemoving() {
        val q = LinkedQueue<String>()
        q.enqueue("a")
        q.enqueue("b")

        assertEquals("a", q.peek())
        // still there
        assertEquals(2, q.size())
        assertEquals("a", q.dequeue())
        assertEquals("b", q.dequeue())
        assertTrue(q.isEmpty())
    }

    @Test
    fun dequeueOnEmpty_throws() {
        val q = LinkedQueue<Double>()
        assertThrows<NoSuchElementException> { q.dequeue() }
    }

    @Test
    fun peekOnEmpty_throws() {
        val q = LinkedQueue<Int>()
        assertThrows<NoSuchElementException> { q.peek() }
    }

    @Test
    fun sizeAndIsEmpty_updateCorrectly_withInterleaving() {
        val q = LinkedQueue<Int>()
        assertTrue(q.isEmpty())

        q.enqueue(10)
        assertFalse(q.isEmpty()); assertEquals(1, q.size())

        q.enqueue(20)
        assertEquals(2, q.size())

        assertEquals(10, q.dequeue())
        assertEquals(1, q.size())

        q.enqueue(30)
        assertEquals(2, q.size())

        assertEquals(20, q.dequeue())
        assertEquals(30, q.dequeue())

        assertTrue(q.isEmpty()); assertEquals(0, q.size())
    }

    @Test
    fun supportsNullableElements_whenTypeIsNullable() {
        val q = LinkedQueue<Int?>()
        q.enqueue(null)
        q.enqueue(7)

        assertNull(q.dequeue())
        assertEquals(7, q.dequeue())
        assertTrue(q.isEmpty())
    }
}
