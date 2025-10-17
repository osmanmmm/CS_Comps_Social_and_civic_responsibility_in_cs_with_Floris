package com.cs.comps

private const val DATA_FILE = "data/assignment_b1.csv"

/**
 * Priority policy (min-heap "smallest" pops first):
 *   1) Lower performance score first.
 *      - Real (non-null) scores must come BEFORE "insufficient data" (null).
 *      - Among real scores, 1 (worst) is laid off before 5 (best).
 *   2) Higher costToCompany next.
 *      - Treat missing/zero cost as the LOWEST (so it never pushes someone up).
 * No other tiebreakers — exact ties are ignored .
 *
 
 */
fun sortEmployeesByPriority(employees: List<Employee>): List<Employee> {
    // Attach/compute performance scores
    val withScores = employees.map { it.copy(performanceScore = computePerformanceScore(it)) }

    // --- Keys 
    fun perfKey(e: Employee): Int {
        // TODO:
        // - If e.performanceScore != null, return that value (1..5) so LOWER pops earlier.
        // - If null, return Int.MAX_VALUE so "insufficient data" comes AFTER any real score.
        TODO("perfKey")
    }

    fun costKey(e: Employee): Int {
        // TODO:
        // - Prefer HIGHER cost next.
        // - For a MIN-heap, invert positive cost: return -e.costToCompany.
        // - If cost is missing/zero, return Int.MAX_VALUE so it never boosts priority.
        TODO("costKey")
    }
}
