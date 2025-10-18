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
    while (!st.isEmpty()) {
        val x = st.pop()
        println(
            "Laid off: ${x.id} ${x.name} – Hire: ${x.hireDate} – CTC: ${x.costToCompany} – " +
            "Perf: ${x.performanceScore ?: "insufficient"}"
        )
    }
}


// Create a function to runPriorityQueue 








// ---------- Run all three base demos ----------
private fun runAll() {
    println("=== LIFO ===")
    runLifo()
    println("=== FIFO ===")
    runFifo()
    println("=== DUMP ===")
    dumpAll()
}

// ---------- Main ----------
fun main(args: Array<String>) {
    when (args.firstOrNull()?.lowercase()) {
        null, "", "lifo" -> runLifo()
        "fifo" -> runFifo()
        "dump" -> dumpAll()
        "all" -> runAll()
        else -> runLifo()
    }
}
