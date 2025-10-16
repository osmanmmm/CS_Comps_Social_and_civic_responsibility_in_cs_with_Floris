# Assignment B — Stacks, Queues, and Priority Queues (Kotlin)

## Overview
This assignment models different layoff policies with core data structures to highlight their social and ethical impacts:

- **Part 1 (LIFO)**: *Last Hired, First Fired* — implement a **Stack**.
- **Part 2 (FIFO)**: *First Hired, First Fired* — implement a **Queue**.
- **Part 3 (Priority)**: Lay off people who are **more expensive** and **lower-performing** first — implement a **Priority Queue (heap)** with this **five-key order**:

**Priority ordering (highest layoff priority first):**
1) **costToCompany** — higher first (**DESC**)  
2) **performanceScore** — lower first (**ASC**; treat `null` as worst using `Int.MAX_VALUE`)  



## What you implement

### Part 1 — LIFO (Stack)
Implement a singly-linked `Stack<T>`:
- `push(item: T)`, `pop(): T`, `peek(): T?`, `isEmpty(): Boolean`, `size(): Int`.

Simulates **Last Hired, First Fired** (most recent hire pops first).

### Part 2 — FIFO (Queue)
Implement a singly-linked `LinkedQueue<T>` with head/tail:
- `enqueue(x: T)`, `dequeue(): T`, `peek(): T?`, `isEmpty(): Boolean`, `size(): Int`.

Simulates **First Hired, First Fired** (queue employees sorted by `hireDate` ascending).

### Part 3 — Priority Queue + Performance Score
1) **Performance score** (`Performance.kt`): implement  
   `computePerformanceScore(e: Employee): Int?` → **Int 1..5** (or `null` if insufficient)
   - **Reviews (R):** average available values from `selfEvaluation`, `peer360Feedback`, `managerFeedback`, and `okr/20`.
   - **Behavior (B):** `productivity = problemsFixed / problemsAssigned` (only if assigned > 0) and `punctualityRate`.  
     Map any value in `[0,1]` to `[1,5]` with `1 + 4*v`. Average available parts.
   - **Combine:** if both exist → `0.7*R + 0.3*B`; if only one exists → use it.  
     Round to nearest int, clamp to `[1,5]`, and handle divide-by-zero/NaN safely.

2) **Priority policy** (`PriorityQueue.kt`): implement  
   `sortEmployeesByCostPerfSalary(employees: List<Employee>): List<Employee>` using a heap 

> Heaps aren’t stable, but because you **explicitly code these tie-breakers**, the output should be deterministic for identical prefixes.

---

## How to build & run

> The program expects the CSV at `data/assignment_b1.csv`. **Run from the repo root.**  
> Program arguments: `lifo`, `fifo`, `priority`.

### Option 1 — Run the provided jar (no build required)

**macOS / Linux**
```bash
java -cp app.jar com.cs.comps.MainKt lifo
java -cp app.jar com.cs.comps.MainKt fifo
java -cp app.jar com.cs.comps.MainKt priority

Expected confirmations: 
LIFO: OK
FIFO: OK
PRIORITY: OK (N items) 
