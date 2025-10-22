package com.cs.comps

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalDate

class PerformanceScoreTest {

    private fun baseEmp(
        id: String = "X",
        self: Double = Double.NaN,
        peer: Double = Double.NaN,
        mgr: Double = Double.NaN,
        okr: Double = Double.NaN,
        punctuality: Double = Double.NaN,
        fixed: Int = 0,
        assigned: Int = 0
    ): Employee = Employee(
        id = id, name = id, hireDate = LocalDate.parse("2022-01-01"),
        role = "r", salary = 0, tenureMonths = 0,
        punctualityRate = punctuality, problemsFixed = fixed, problemsAssigned = assigned,
        volunteeringHours = 0, costToCompany = 0,
        dept = "d", manager = "m", location = "loc", employmentType = "full",
        selfEvaluation = self, peer360Feedback = peer, managerFeedback = mgr, okr = okr,
        gender = "g", disabilityStatus = "No", sponsorship = "No", performanceScore = null
    )

    @Test
    fun perfectReviewsAndBehavior_scoreIs5() {
        val e = baseEmp(self = 5.0, peer = 5.0, mgr = 5.0, okr = 100.0,
                        punctuality = 1.0, fixed = 10, assigned = 10)
        assertEquals(5, computePerformanceScore(e))
    }

    @Test
    fun reviewsOnly_whenBehaviorMissing_usesReviewsMean() {
        val e = baseEmp(self = 2.0, peer = 2.0, mgr = 2.0, okr = 40.0,
                        punctuality = Double.NaN, fixed = 0, assigned = 0)
        assertEquals(2, computePerformanceScore(e))
    }

    @Test
    fun behaviorOnly_whenReviewsMissing_averagesMappedSignals_andRounds() {
        val e = baseEmp(punctuality = 0.25, fixed = 10, assigned = 20)
        assertEquals(3, computePerformanceScore(e))
    }

    @Test
    fun combined_usesWeights_pointSevenReviews_pointThreeBehavior_andRoundsHalfUp() {
        val e = baseEmp(self = 3.0, peer = 3.0, mgr = 3.0, okr = 80.0,
                        punctuality = 0.5, fixed = 1, assigned = 4)
        assertEquals(3, computePerformanceScore(e))
    }

    @Test
    fun productivitySkippedWhenAssignedIsZero() {
        val e = baseEmp(punctuality = 0.9, fixed = 10, assigned = 0)
        assertEquals(5, computePerformanceScore(e))
    }

    @Test
    fun returnsNull_whenNoUsableReviewOrBehaviorSignals() {
        val e = baseEmp(self = Double.NaN, peer = Double.NaN, mgr = Double.NaN, okr = Double.NaN,
                        punctuality = Double.NaN, fixed = 0, assigned = 0)
        assertNull(computePerformanceScore(e))
    }

    @Test
    fun clampsFinalToRangeOneToFive() {
        val eLow = baseEmp(punctuality = 0.0, fixed = 0, assigned = 0)
        val eHigh = baseEmp(self = 5.0, peer = 5.0, mgr = 5.0, okr = 100.0)
        val sLow = computePerformanceScore(eLow)
        val sHigh = computePerformanceScore(eHigh)
        assertNotNull(sLow); assertTrue(sLow!! in 1..5)
        assertNotNull(sHigh); assertTrue(sHigh!! in 1..5)
    }
}
