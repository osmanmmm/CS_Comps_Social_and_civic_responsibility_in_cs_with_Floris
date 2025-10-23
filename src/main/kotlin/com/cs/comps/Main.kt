package com.cs.comps

private const val DATA_FILE = "data/assignment_b1.csv"

// ---------- Utilities ----------
private fun withScores(list: List<Employee>) =
    list.map { it.copy(performanceScore = computePerformanceScore(it)) }

// ---------- Dump ----------
private fun dumpAll() {
    val employees = withScores(loadEmployees(DATA_FILE))
    println("Loaded ${employees.size} employees")
    employees.forEach { println(it) }
}

// ---------- FIFO ----------
private fun runFifo() {
    val employees = withScores(loadEmployees(DATA_FILE)).sortedBy { it.hireDate }
    val q = LinkedQueue<Employee>()
    employees.forEach { q.enqueue(it) }

    println("=== FIFO (First In, First Out) ===")
    while (!q.isEmpty()) {
        val x = q.dequeue()
        println(
            "Laid off: ${x.id} ${x.name} – Hire: ${x.hireDate} – CTC: ${x.costToCompany} – " +
            "Perf: ${x.performanceScore ?: "insufficient"}"
        )
    }
}

// ---------- LIFO ----------
private fun runLifo() {
    val employees = withScores(loadEmployees(DATA_FILE)).sortedBy { it.hireDate }
    val st = Stack<Employee>()
    employees.forEach { st.push(it) }

    println("=== LIFO (Last In, First Out) ===")
    while (!st.isEmpty()) {
        val x = st.pop()
        println(
            "Laid off: ${x.id} ${x.name} – Hire: ${x.hireDate} – CTC: ${x.costToCompany} – " +
            "Perf: ${x.performanceScore ?: "insufficient"}"
        )
    }
}

// ---------- Priority (min-heap; ties randomized) ----------
private fun runPriority() {
    val employees = loadEmployees(DATA_FILE)
    val ordered = sortEmployeesByPriority(employees)

    println("=== PRIORITY (min-heap; ties randomized) ===")
    ordered.forEach { x ->
        println(
            "Laid off: ${x.id} ${x.name} – Hire: ${x.hireDate} – CTC: ${x.costToCompany ?: "n/a"} – " +
            "Perf: ${x.performanceScore ?: "insufficient"}"
        )
    }
}

// ---------- Run all ----------
private fun runAll() {
    runLifo()
    runFifo()
    runPriority()
    dumpAll()
}

// ---------- Main ----------
fun main(args: Array<String>) {
    fun runSafely(name: String, block: () -> Unit) {
        try { block() }
        catch (e: NotImplementedError) {
            println("❗ '$name' not implemented yet: ${e.message}")
        }
    }

    when (args.firstOrNull()?.lowercase()) {
        null, "", "lifo"     -> runSafely("LIFO")     { runLifo() }
        "fifo"               -> runSafely("FIFO")     { runFifo() }
        "priority"           -> runSafely("PRIORITY") { runPriority() }
        "dump"               -> dumpAll()
        "all" -> {
            runSafely("LIFO")     { runLifo() }
            runSafely("FIFO")     { runFifo() }
            runSafely("PRIORITY") { runPriority() }
            dumpAll()
        }
        else -> { println("Usage: lifo | fifo | priority | dump | all") }
    }
}

