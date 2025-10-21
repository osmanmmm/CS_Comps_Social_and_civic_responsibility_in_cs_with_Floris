package com.cs.comps

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class StackTest {

    @Test
    fun pushPop_LIFO_works() {
        val s = Stack<Int>()
        s.push(1)
        s.push(2)
        s.push(3)

        assertEquals(3, s.pop())
        assertEquals(2, s.pop())
        assertEquals(1, s.pop())

        assertTrue(s.isEmpty())
        assertEquals(0, s.size())
    }

    @Test
    fun peekOnEmpty_throws() {
        val s = Stack<Int>()
        assertThrows<NoSuchElementException> { s.peek() }
    }

    @Test
    fun popOnEmpty_throws() {
        val s = Stack<Int>()
        assertThrows<NoSuchElementException> { s.pop() }
    }

    @Test
    fun smoke_pushThenPop_sameValue() {
        val s = Stack<Int>()
        s.push(42)
        assertEquals(42, s.peek())
        assertEquals(42, s.pop())
        assertTrue(s.isEmpty())
    }
}
