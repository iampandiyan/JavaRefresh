# Interface Segregation Principle (ISP)

ISP states that clients should not be forced to depend on interfaces they do not use.

## Overview

- `Worker` (in `Worker.java`) is a fat interface that requires `code()`, `eat()`, and `sleep()`.
- `RobotWorker` implements `Worker` and is forced to provide `eat()` and `sleep()` even though they are meaningless; it throws `UnsupportedOperationException` which is a sign of a bad design.
- The corrected design provides smaller interfaces: `Codeable`, `Eatable`, `Sleepable`. `HumanWorker` implements all three; `RobotWorkerFixed` implements only `Codeable`.

## IspDemo.java

The demo prints two sections:

1. ISP Violation Example
   - Instantiates `HumanWorker` and `RobotWorker` as `Worker`.
   - Shows that calling `eat()`/`sleep()` on `RobotWorker` throws `UnsupportedOperationException`.
2. ISP Compliant Example
   - Uses the segregated interfaces and only calls methods that make sense for each object.

### Expected (illustrative) output

=== ISP Violation Example ===
Human is coding
Human is eating
Human is sleeping
Robot is coding
Robot eat() error: Robot does not eat
Robot sleep() error: Robot does not sleep

=== ISP Compliant Example ===
Human is coding
Robot is coding
Human is eating
Human is sleeping

## Flow & variable values

Q: Could you explain the flow with what will be the value of variable in each call?

- Violation block:
  - `Worker robot = new RobotWorker();` — `robot` references `RobotWorker`.
  - `robot.code()` prints `Robot is coding`.
  - `robot.eat()` throws `UnsupportedOperationException` with message `Robot does not eat`.
  - `robot.sleep()` throws `UnsupportedOperationException` with message `Robot does not sleep`.

- Compliant block:
  - `Codeable robotCoder = new RobotWorkerFixed();` — only `code()` is available and valid.
  - `robotCoder.code()` prints `Robot is coding`.
  - No unsupported calls are made on the robot.

## Notes

- Prefer many small, role-specific interfaces over a single large interface when different clients require different subsets of behavior.
- This avoids runtime exceptions and makes implementations clearer and easier to test.
