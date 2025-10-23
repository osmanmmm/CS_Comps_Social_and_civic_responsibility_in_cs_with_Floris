package com.cs.comps

/**
 * TODO: Compute a 1..5 Int performance score for an employee, or return null
 * if there isn’t enough usable data.
 *
 * Consider:
 *  - Reviews (R): selfEvaluation, peer360Feedback, managerFeedback, okr/20
 *  - Behavior (B): productivity = problemsFixed / problemsAssigned (>0), punctualityRate
 * Map any value in [0,1] to [1,5] via 1 + 4*v; combine:
 *   both -> 0.7*R + 0.3*B; only one -> use it; round; clamp to [1,5].
 */
fun computePerformanceScore(e: Employee): Int? {
    // Leave this as a stub so the app runs even before students implement it.
    return null
}
