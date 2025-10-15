package com.cs.comps

private const val DATA_FILE = "data/assignment_b1.csv"

private fun runFifo() {
    val q = LinkedQueue<Employee>()                          
    loadEmployees(DATA_FILE).sortedBy { it.hireDate }.forEach { q.enqueue(it) }
    while (!q.isEmpty()) q.dequeue()
    println("FIFO: OK")
}

private fun runLifo() {
    val st = Stack<Employee>()                              
    loadEmployees(DATA_FILE).sortedBy { it.hireDate }.forEach { st.push(it) }
    while (!st.isEmpty()) st.pop()
    println("LIFO: OK")
}

private fun runPriority() {
    val ordered = sortEmployeesByCostPerfSalary(loadEmployees(DATA_FILE))   
    println("PRIORITY: OK (${ordered.size} items)")
}

fun main(args: Array<String>) {
    when (args.firstOrNull()?.lowercase()) {
        "fifo"     -> runFifo()
        "lifo", "" -> runLifo()
        "priority" -> runPriority()
        else       -> runLifo()
    }
}
