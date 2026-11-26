# CSA09 Programming in Java — Assignment 2 (CO2)

Student name: **Fill in your name**  
Register number: **Fill in your register number**  
Java version: **Record output of `java -version`**

## Task folders
- Task01: String, StringBuilder, StringBuffer benchmark
- Task02: ArrayList, LinkedList, ArrayDeque comparison
- Task03: HashSet and equals/hashCode contract
- Task04: HashMap, TreeMap, LinkedHashMap
- Task05: Generic Leaderboard and Comparator
- Task06: PriorityQueue hospital triage
- Task07: Diagnose and validate a redesign using ArrayDeque for front-removal workload

## Compile and run
Compile each task separately from the repository root, for example:

```bash
javac -d out Task01/Q1_StringBenchmark.java
java -cp out Q1_StringBenchmark
```

Repeat for the other task folders and class names:
`Q2_ListBenchmark`, `Q3_HashSetContract`, `Q4_PharmacyMaps`,
`Q5_GenericLeaderboard`, `Q6_HospitalTriage`, `Q7_RedesignBenchmark`.

## Submission checklist
1. Run each task yourself and save screenshots in its Task folder.
2. Record Java version, computer details and raw benchmark outputs.
3. Repeat measurements; do not invent timings or use one run as conclusive evidence.
4. Write your predictions, Big-O comparisons, alternative concepts and explanations in your report.
5. Make at least one meaningful Git commit per task.
6. Q7 is one valid redesign example: ArrayDeque for an Up Next queue where front removal is frequent. Explain the trade-off: no efficient indexed access.

This repository contains Java implementations and experiment programs. It does not fabricate screenshots or experimental results; collect those by running the code on your own computer.
