# Campus Smart-Parking System — Java Assignment

This project contains a simple object-oriented Java implementation based on the
12 tasks in the supplied Campus Smart-Parking System assignment.

## Source structure

- `src/parking/model/Vehicle.java` — abstract vehicle, registration identity,
  fee calculation, `equals`, `hashCode`, and `toString`.
- `TwoWheeler.java`, `Car.java`, `ElectricCar.java` — inheritance and
  specialized hourly rates.
- `Chargeable.java` — charging capability interface.
- `ParkingTicket.java` — immutable ticket data and constructor chaining.
- `src/parking/service/ParkingFacility.java` — jagged multi-floor array,
  first-free-slot allocation, release, lookup map, and defensive copying.
- `TicketManager.java` — shared auto-increment ticket sequence.
- `src/parking/app/MainApplication.java` — demonstration of allocation,
  tickets, fee calculation, campus pass exemption, EV charging, and release.

## Compile and run

From this project folder, run:

```bash
javac -d out src/parking/model/*.java src/parking/service/*.java src/parking/app/*.java
java -cp out parking.app.MainApplication
```

Requires a Java version that supports the standard language features used here
(Java 8 or later).

## Assignment task mapping

- Tasks 1–2: domain objects, inputs/outputs, data types, and jagged-array design
  are reflected in the model and facility classes.
- Task 3: `ParkingFacility.allocate()` and `release()`.
- Tasks 4–5: `ParkingTicket` constructors and `TicketManager` static sequence.
- Tasks 6–9: `Vehicle` hierarchy, overriding, polymorphism, and `Chargeable`.
- Task 10: package separation and encapsulated fields / defensive copies.
- Task 11: run the demo and add your own test cases and execution screenshots.
- Task 12: the abstract `Vehicle` and `Chargeable` interface allow future
  vehicle types and charging-capable classes to be added.

## Important

This is a learning implementation, not a complete production parking platform.
Review the code, run it, and verify the behavior against your instructor's
marking rubric. Add execution screenshots and any required test evidence to
your GitHub repository.
