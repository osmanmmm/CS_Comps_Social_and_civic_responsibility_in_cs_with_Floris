package com.cs.comps

import java.util.PriorityQueue
import java.util.Comparator

/**
 * Build a heap-based layoff order using PriorityQueue.
 * Ordering (highest layoff priority first):
 *  1) costToCompany — DESC
 *  2) performanceScore — ASC (null treated as worst)
 *  3) salary — DESC
 *  4) hireDate — ASC
 *  5) id — ASC
 */
fun sortEmployeesByCostPerfSalary(employees: List<Employee>): List<Employee> {
    // Attach or compute performanceScore for each employee
    val withScores = employees.map { it.copy(performanceScore = computePerformanceScore(it)) }

    // TODO: Create a Comparator<Employee> that enforces the ordering listed in the KDoc.
    val cmp: Comparator<Employee> = TODO("Create comparator: cost DESC, perf ASC (null last), salary DESC, hireDate ASC, id ASC")

    // TODO: Create a PriorityQueue<Employee> using the comparator and add all employees-with-scores to it.
    val pq: PriorityQueue<Employee> = TODO("Create PriorityQueue with 'cmp' and add all elements from 'withScores'")

    // TODO: Remove elements from the priority queue one-by-one into 'out' in priority order, then return it.
    val out = ArrayList<Employee>()
    TODO("While queue is not empty, poll and append to 'out'")
    return out
}
