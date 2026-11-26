# CSA09 – Java Assignment 5 (CO5)

Java 17 Maven project for Assignment 5: Files, buffered I/O, serialization and JDBC.

## Setup
1. Install JDK 17+ and Maven.
2. Create a local MySQL database and run `sql/schema.sql`.
3. Set environment variables `DB_URL` (example: `jdbc:mysql://localhost:3306/transport`), `DB_USER`, and `DB_PASSWORD`.
4. Build: `mvn clean package`
5. Run a task, e.g. `mvn exec:java -Dexec.mainClass="TaskB1.FileStoreDemo"`.

Each task has its own Java source folder. Update this README with your name, register number, Java version, actual run commands, screenshots, and measured results before submission. Do not submit invented benchmark values: run the programs on your computer and record the output.

## Task mapping
- Task01: delimited file design and pass record model
- Task02: buffered vs unbuffered line counting
- Task03: generic DAO pattern
- Task04: Java serialization and transient fields
- Task05: JDBC transaction and PreparedStatement login
- TaskB1: file store tests and scaling measurements
- TaskB2: SQL injection demonstration and malformed-file handling
- TaskB3: Statement / PreparedStatement / batch benchmark and transaction rollback
- TaskB4: file-to-database migration and DatabaseMetaData
