package com.cs.comps

class LinkedQueue<T> {
    // TODO: node
    private class Node<E>(val v: E, var next: Node<E>? = null)

    // TODO: state
    private var head: Node<T>? = null
    private var tail: Node<T>? = null
    private var n = 0

    fun isEmpty(): Boolean {
        // TODO
        TODO("isEmpty not implemented")
    }

    fun size(): Int {
        // TODO
        TODO("size not implemented")
    }

    fun enqueue(x: T) {
        // TODO
        TODO("enqueue not implemented")
    }

    fun dequeue(): T {
        // TODO
        TODO("dequeue not implemented")
    }

    fun peek(): T? {
        // TODO
        TODO("peek not implemented")
    }
}
