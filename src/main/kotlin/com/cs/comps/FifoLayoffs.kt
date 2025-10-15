package com.cs.comps

class Stack<T> {
    // TODO: node
    private class Node<E>(val v: E, var next: Node<E>? = null)

    // TODO: state
    private var head: Node<T>? = null
    private var n = 0

    fun push(item: T) {
        // TODO
        TODO("push not implemented")
    }

    fun pop(): T {
        // TODO
        TODO("pop not implemented")
    }

    fun peek(): T? {
        // TODO
        TODO("peek not implemented")
    }

    fun isEmpty(): Boolean {
        // TODO
        TODO("isEmpty not implemented")
    }

    fun size(): Int {
        // TODO
        TODO("size not implemented")
    }
}
