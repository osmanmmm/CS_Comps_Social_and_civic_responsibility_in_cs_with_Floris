package com.cs.comps

/**
 * TODO: Compute a 1..5 Int performance score for an employee, or return null
 * if there isn’t enough usable data.
 *
 * Consider:
 *  - Reviews: selfEvaluation, peer360Feedback, managerFeedback (≈ 1..5)
 *  - OKR: 0..100 (rescale to 1..5 if you use it)
 *  - Behavior: productivity = problemsFixed / problemsAssigned (only if assigned > 0)
 *              punctualityRate in 0..1 (may map via 1 + 4*v)
 *
 * Requirements:
 *  1) Be robust to division by zero / NaN / Infinity.
 *  2) Round to nearest Int and clamp to [1,5].
 *  3) Briefly document your weights/choices in comments.
 */
fun computePerformanceScore(e: Employee): Int? {
    // TODO: student implementation
    return null
}
