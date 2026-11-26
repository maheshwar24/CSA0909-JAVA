# CSA09 – Programming in Java – Assignment 4 (CO4)

**Student name:** TODO  
**Register number:** TODO  
**Course:** Programming in Java (CSA09)  
**Java version:** 17 or later  
**Repository:** `CSA09-<RegisterNumber>-Assignment4`

This repository contains source code and design notes for all six tasks in the supplied SIMATS Engineering Assignment 4. Replace the TODO student details before submission.

## Project structure

- `src/main/java/assignment/task01/` – Fee window and separate service layer
- `docs/Task02.md` – event table, component hierarchy, pseudocode and event trace
- `src/main/java/assignment/task03/` – responsive Swing library search screen
- `src/main/java/assignment/task04/` – corrected drawing pad (complete-code TODOs)
- `src/main/java/assignment/task05/` – four event-handling styles and converter
- `src/main/java/assignment/task06/` – layered Swing/JDBC event registration application
- `sql/task06.sql` – MySQL schema with duplicate-registration constraint

## Requirements

- JDK 17+
- Maven 3.9+
- MySQL 8+ (Task 1 database use and Task 6)

## Compile

From the repository root:

```bash
mvn clean package
```

## Run each program

Run one main class at a time from your IDE, or use Maven's compiled classpath:

```bash
mvn dependency:build-classpath -Dmdep.outputFile=target/classpath.txt
java -cp "target/classes:$(cat target/classpath.txt)" assignment.task01.FeeWindow
java -cp "target/classes:$(cat target/classpath.txt)" assignment.task03.LibrarySearchScreen
java -cp "target/classes:$(cat target/classpath.txt)" assignment.task04.DrawPad
java -cp "target/classes:$(cat target/classpath.txt)" assignment.task05.ConverterLambda
java -cp "target/classes:$(cat target/classpath.txt)" assignment.task06.EventRegistrationApp
```

On Windows, replace `:` in the classpath with `;`.

Task 2 is a design task in the assignment and is documented in `docs/Task02.md`. Task 5's other three implementations can be run using their corresponding class names listed in that package.

## Database setup

1. Start MySQL.
2. Execute `sql/task06.sql`.
3. Set environment variables before launching the application:

```text
DB_URL=jdbc:mysql://localhost:3306/csa09_assignment4
DB_USER=root
DB_PASSWORD=your_password
```

The code uses these environment variables. Do not commit real database passwords.

## Screenshots and evidence

Run each UI, perform the interactions specified in the assignment, and save genuine screenshots in the corresponding task folder (for example `evidence/Task04/`). Include screenshots:
- Task 3 at three different window sizes.
- Task 4 before and after minimising/restoring.
- Task 5 valid and invalid amount.
- Task 6 normal, invalid and duplicate registration, plus filtering/export.

A screenshot must demonstrate a stated requirement; do not submit empty or unrelated screenshots.

## GitHub submission

Create a public repository or a private repository with faculty added as collaborator. Suggested progressive commits:

```bash
git add . && git commit -m "Add Task 1 fee service design"
git add . && git commit -m "Add Task 2 event design"
git add . && git commit -m "Add Task 3 library search GUI"
git add . && git commit -m "Add Task 4 drawing pad repair"
git add . && git commit -m "Add Task 5 event handling styles"
git add . && git commit -m "Add Task 6 event registration app"
git push
```

Before submission, replace student details, run every program, capture your own screenshots, and add your GitHub URL to the assignment report.
