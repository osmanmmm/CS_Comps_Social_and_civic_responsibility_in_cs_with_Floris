package com.cs.comps

import java.io.File
import java.time.LocalDate

data class Employee(
    val id: String,
    val name: String,
    val hireDate: LocalDate,
    val role: String,
    val salary: Int,
    val tenureMonths: Int,
    val punctualityRate: Double,
    val problemsFixed: Int,
    val problemsAssigned: Int,
    val volunteeringHours: Int,
    val costToCompany: Int,

    val dept: String,
    val manager: String,
    val location: String,
    val employmentType: String,

    // raw review/behavior signals
    val selfEvaluation: Double,
    val peer360Feedback: Double,
    val managerFeedback: Double,
    val okr: Double,

    val gender: String,
    val disabilityStatus: String,
    val sponsorship: String,

    // computed later by students
    val performanceScore: Int?
)

/** Quote-aware CSV loader — do not modify. */
fun loadEmployees(path: String): List<Employee> {
    val lines = File(path).readLines().filter { it.isNotBlank() }
    require(lines.isNotEmpty()) { "Empty CSV: $path" }

    fun normHeader(s: String) = s.trim().lowercase().replace(' ', '_')
    val header = csvSplit(lines.first())
    val idx = header.mapIndexed { i, h -> normHeader(h) to i }.toMap()
    fun col(name: String) = idx[name] ?: error("Missing column: $name")

    val iId=col("id"); val iName=col("name"); val iHire=col("hire_date"); val iRole=col("role")
    val iSal=col("salary"); val iTen=col("tenure_months"); val iPun=col("punctuality_rate")
    val iFix=col("problems_fixed"); val iAsg=col("problems_assigned"); val iVol=col("volunteering_hours")
    val iCtC=col("cost_to_the_company")
    val iDept=col("dept"); val iMgr=col("manager"); val iLoc=col("location"); val iEmp=col("employment_type")
    val iSelf=col("self_evaluation"); val iPeer=col("360_peer_feedback"); val iMgrFb=col("manager_feedback")
    val iOkr=col("okr"); val iGen=col("gender"); val iDis=col("disability_status"); val iSpon=col("sponsorship")

    fun s(t: List<String>, i: Int) = t[i].trim()
    fun toIntSimple(x: String) = x.replace(",", "").toInt()
    fun toDoubleSimple(x: String) = x.replace(",", "").toDouble()

    return lines.drop(1).map { line ->
        val t = csvSplit(line)
        Employee(
            id = s(t,iId), name = s(t,iName), hireDate = LocalDate.parse(s(t,iHire)), role = s(t,iRole),
            salary = toIntSimple(s(t,iSal)), tenureMonths = toIntSimple(s(t,iTen)),
            punctualityRate = toDoubleSimple(s(t,iPun)), problemsFixed = toIntSimple(s(t,iFix)),
            problemsAssigned = toIntSimple(s(t,iAsg)), volunteeringHours = toIntSimple(s(t,iVol)),
            costToCompany = toIntSimple(s(t,iCtC)),
            dept = s(t,iDept), manager = s(t,iMgr), location = s(t,iLoc), employmentType = s(t,iEmp),
            selfEvaluation = toDoubleSimple(s(t,iSelf)), peer360Feedback = toDoubleSimple(s(t,iPeer)),
            managerFeedback = toDoubleSimple(s(t,iMgrFb)), okr = toDoubleSimple(s(t,iOkr)),
            gender = s(t,iGen), disabilityStatus = s(t,iDis), sponsorship = s(t,iSpon),
            performanceScore = null
        )
    }
}

/** internal helper — do not modify */
private fun csvSplit(line: String): List<String> {
    val out = mutableListOf<String>()
    val sb = StringBuilder()
    var inQuotes = false
    var i = 0
    while (i < line.length) {
        val c = line[i]
        when {
            c == '"' -> {
                if (inQuotes && i + 1 < line.length && line[i + 1] == '"') { sb.append('"'); i++ }
                else inQuotes = !inQuotes
            }
            c == ',' && !inQuotes -> { out.add(sb.toString()); sb.clear() }
            else -> sb.append(c)
        }
        i++
    }
    out.add(sb.toString())
    return out.map { it.trim() }
}
