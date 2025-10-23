package com.cs.comps

/**
 * Assignment B — Part 3 (Priority Queue, Min-Heap)
 *
 * Layoff priority (min-heap: "smallest" comes out first):
 *   1) LOWER performance score first.
 *      - Real scores 1..5 must come BEFORE "insufficient data" (null).
 *      - Among real scores, 1 (worst) laid off before 5 (best).
 *   2) HIGHER costToCompany next.
 *      - Treat missing/zero cost as LOWEST (so it never boosts priority).
 *
 * No extra tie-breakers. For *exact ties*, just let order be arbitrary
 * (you may pre-shuffle the input list to “flip a coin” among equals).
 */

// ----- Keys ( fill these) -----
private fun perfKey(e: Employee): Int {
    // TODO:
    // - If e.performanceScore != null, return that Int (1..5), so LOWER pops earlier.
    // - If null, return Int.MAX_VALUE so "insufficient" comes AFTER any real score.
    TODO("perfKey not implemented")
}

private fun costKey(e: Employee): Int {
    // TODO:
    // - Prefer HIGHER cost next.
    // - For a MIN-heap, invert positive cost: return -e.costToCompany.
    // - If cost is missing/zero, return Int.MAX_VALUE so it never boosts priority.
    TODO("costKey not implemented")
}

// ----- Binary Min-Heap (students implement) -----
private class MinHeap<T>(private val less: (T, T) -> Boolean) {
    private val a = ArrayList<T>()

    fun isEmpty(): Boolean {
        // TODO
        TODO("isEmpty not implemented")
    }

    fun size(): Int {
        // TODO
        TODO("size not implemented")
    }

    fun peek(): T {
        // TODO
        TODO("peek not implemented")
    }

    fun push(x: T) {
        // TODO: add to end, then siftUp(lastIndex)
        TODO("push not implemented")
    }

    fun pop(): T {
        // TODO:
        // - Remove/return a[0]
        // - Move last element to a[0], shrink, then siftDown(0)
        TODO("pop not implemented")
    }

    private fun parent(i: Int) = (i - 1) / 2
    private fun left(i: Int) = 2 * i + 1
    private fun right(i: Int) = 2 * i + 2

    private fun siftUp(i0: Int) {
        // TODO: while i>0 and less(a[i], a[parent(i)]), swap and climb
        TODO("siftUp not implemented")
    }

    private fun siftDown(i0: Int) {
        // TODO:
        // pick the smaller child using `less`, swap if child < current, then continue
        TODO("siftDown not implemented")
    }
}

// ----- Public API used by Main.kt -----
fun sortEmployeesByPriority(employees: List<Employee>): List<Employee> {
    // Compute/attach performance scores (uses students' Performance.kt)
    val withScores = employees.map { it.copy(performanceScore = computePerformanceScore(it)) }

    // OPTIONAL: to “flip a coin” among exact ties, uncomment:
    // val input = withScores.shuffled()
    val input = withScores

    // Build a min-heap with our 2-key policy
    val heap = MinHeap<Employee> { a, b ->
        // TODO:
        // - Compare perfKey first (smaller is worse -> should come first)
        // - If equal, compare costKey (smaller means higher cost after inversion)
        // - If still equal, return false (no extra tiebreakers)
        TODO("comparator not implemented")
    }

    // TODO: push everything into the heap
    // input.forEach { heap.push(it) }

    // TODO: pop everything out into result
    val out = ArrayList<Employee>(input.size)
    // while (!heap.isEmpty()) out.add(heap.pop())

    // Temporary so file compiles; remove once you implement:
    TODO("sortEmployeesByPriority not fully implemented")
    // return out
}

