package com.cs.comps

private const val DATA_FILE = "data/assignment_b1.csv"

private fun dumpAll() {
    val employees = loadEmployees(DATA_FILE)
        .map { it.copy(performanceScore = computePerformanceScore(it)) }
    println("Loaded ${employees.size} employees")
    employees.forEach { println(it) }
}

private fun runFifo() {
    val employees = loadEmployees(DATA_FILE)
        .map { it.copy(performanceScore = computePerformanceScore(it)) }
        .sortedBy { it.hireDate }

    val q = LinkedQueue<Employee>()
    employees.forEach { q.enqueue(it) }
    while (!q.isEmpty()) {
        val x = q.dequeue()
        println("Laid off: ${x.id} ${x.name} – Hire Date: ${x.hireDate} – Cost to Company: ${x.costToCompany} – Performance: ${x.performanceScore ?: "insufficient data"}")
    }
}

private fun runLifo() {
    val employees = loadEmployees(DATA_FILE)
        .map { it.copy(performanceScore = computePerformanceScore(it)) }
        .sortedBy { it.hireDate }

    val st = Stack<Employee>()
    employees.forEach { st.push(it) }
    while (!st.isEmpty()) {
        val x = st.pop()
        println("Laid off: ${x.id} ${x.name} – Hire Date: ${x.hireDate} – Cost to Company: ${x.costToCompany} – Performance: ${x.performanceScore ?: "insufficient data"}")
    }
}

// NEW: priority (min-heap) runner
private fun runPrio() {
    runPriorityLayoffs(DATA_FILE)
}

private fun runAll() {
    println("=== LIFO ===")
    runLifo()
    println("=== FIFO ===")
    runFifo()
    println("=== PRIO ===")
    runPrio()
    println("=== DUMP ===")
    dumpAll()
}

fun main(args: Array<String>) {
    when (args.firstOrNull()?.lowercase()) {
        null, "", "lifo" -> runLifo()
        "fifo" -> runFifo()
        "prio" -> runPrio()   // min-heap policy
        "dump" -> dumpAll()
        "all"  -> runAll()
        else   -> runLifo()
    }
}
