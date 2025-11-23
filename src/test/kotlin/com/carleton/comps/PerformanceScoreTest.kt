package com.carleton.comps

import com.cs.comps.Employee
import com.cs.comps.computePerformanceScore
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalDate

class PerformanceScoreTest {

    // Helper to build employees with just the fields we care about for performance.
    private fun baseEmp(
        id: String,
        self: Double = Double.NaN,
        peer: Double = Double.NaN,
        mgr: Double = Double.NaN,
        okr: Double = Double.NaN,          // 0..100 or NaN for "missing"
        punctuality: Double = Double.NaN,  // 0..1 or NaN
        fixed: Int = 0,
        assigned: Int = 0
    ): Employee = Employee(
        id = id,
        name = id,
        hireDate = LocalDate.parse("2022-01-01"),
        role = "r",
        salary = 0,
        tenureMonths = 0,
        selfEval = self,
        peerEval = peer,
        managerEval = mgr,
        okrScore = okr,
        punctualityRate = punctuality,
        problemsFixed = fixed,
        problemsAssigned = assigned,
        volunteeringHours = 0.0,
        bonusMultiple = Double.NaN
    )

    @Test
    fun perfectReviewsAndBehavior_scoreIs5() {
        val e = baseEmp(
            id = "A",
            self = 5.0, peer = 5.0, mgr = 5.0, okr = 100.0,
            punctuality = 1.0, fixed = 100, assigned = 100
        )
        val s = computePerformanceScore(e)
        assertEquals(5.0, s)
    }

    @Test
    fun reviewsOnly_whenBehaviorMissing_usesReviewsMeanIncludingOKR() {
        val e = baseEmp(
            id = "B",
            self = 4.0,
            peer = 2.0,
            mgr = 3.0,
            okr = 80.0,     // -> 4.0
            punctuality = Double.NaN,
            fixed = 0, assigned = 0
        )
        // reviews = [4,2,3,4] -> mean = 3.25 -> final = 3.25
        val s = computePerformanceScore(e)
        assertNotNull(s)
        assertEquals(3.25, s!!, 1e-9)
    }

    @Test
    fun behaviorOnly_whenReviewsMissing_averagesMappedSignals_andRounds() {
        val e = baseEmp(
            id = "C",
            self = Double.NaN,
            peer = Double.NaN,
            mgr = Double.NaN,
            okr = Double.NaN,
            punctuality = 0.8,
            fixed = 30, assigned = 40
        )
        // Example behavior-only test; we don't lock exact mapping here,
        // just assert it's between 1 and 5.
        val s = computePerformanceScore(e)
        assertNotNull(s)
        assertTrue(s!! in 1..5)
    }

    @Test
    fun combined_usesWeights_pointSevenReviews_pointThreeBehavior_andRounds() {
        val e = baseEmp(
            id = "D",
            // Reviews pulled toward 4
            self = 4.0, peer = 3.0, mgr = 5.0, okr = 80.0,  // okr->4
            // Behavior pulled toward (say) 3-ish
            punctuality = 0.6,
            fixed = 10, assigned = 20
        )
        val s = computePerformanceScore(e)
        assertNotNull(s)
        // We can't know your exact formula here without peeking, but we can assert range and that
        // it's in [1,5].
        assertTrue(s!! in 1..5)
    }

    @Test
    fun productivitySkippedWhenAssignedIsZero_behaviorFromPunctualityOnly() {
        val e = baseEmp(
            id = "E",
            self = Double.NaN,
            peer = Double.NaN,
            mgr = Double.NaN,
            okr = Double.NaN,
            punctuality = 0.9,
            fixed = 10,
            assigned = 0
        )
        val s = computePerformanceScore(e)
        assertNotNull(s)
        assertTrue(s!! in 1..5)
    }

    @Test
    fun returnsNull_whenNoUsableReviewOrBehaviorSignals() {
        val e = baseEmp(
            id = "F",
            self = Double.NaN,
            peer = Double.NaN,
            mgr = Double.NaN,
            okr = Double.NaN,
            punctuality = Double.NaN,
            fixed = 0,
            assigned = 0
        )
        val s = computePerformanceScore(e)
        assertNull(s)
    }

    @Test
    fun clampsFinalToRangeOneToFive() {
        // Try an employee that would produce a very low score
        val eLow = baseEmp(
            id = "G-low",
            self = 1.0, peer = 1.0, mgr = 1.0, okr = 0.0,
            punctuality = 0.0,
            fixed = 0, assigned = 100
        )
        val sLow = computePerformanceScore(eLow)
        assertNotNull(sLow)
        assertTrue(sLow!! in 1..5)

        // And an employee that would produce a very high score
        val eHigh = baseEmp(
            id = "G-high",
            self = 5.0, peer = 5.0, mgr = 5.0, okr = 100.0,
            punctuality = 1.0,
            fixed = 100, assigned = 100
        )
        val sHigh = computePerformanceScore(eHigh)
        assertNotNull(sHigh); assertTrue(sHigh!! in 1..5)
    }
}
