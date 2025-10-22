package com.cs.comps

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.NoSuchElementException

class StackTest {

    @Test
    fun pushPop_LIFO_works() {
        val s = Stack<Int>()
        s.push(1); s.push(2); s.push(3)
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
    fun sizeAndIsEmpty_updateCorrectly_withInterleaving() {
        val s = Stack<Int>()
        assertTrue(s.isEmpty())

        s.push(10); s.push(20)
        assertEquals(2, s.size()); assertFalse(s.isEmpty())

        assertEquals(20, s.peek())     // non-mutating
        assertEquals(2, s.size())

        assertEquals(20, s.pop())
        assertEquals(1, s.size())

        s.push(30)
        assertEquals(2, s.size())

        assertEquals(30, s.pop())
        assertEquals(10, s.pop())
        assertTrue(s.isEmpty()); assertEquals(0, s.size())
    }

    @Test
    fun manyPushesAndPops_stillLIFO_andStable() {
        val s = Stack<Int>()
        for (i in 1..1000) s.push(i)
        for (i in 1000 downTo 1) assertEquals(i, s.pop())
        assertTrue(s.isEmpty())
    }
}
