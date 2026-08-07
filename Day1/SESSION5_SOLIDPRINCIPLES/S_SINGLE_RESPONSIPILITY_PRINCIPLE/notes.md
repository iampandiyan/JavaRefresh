# Single Responsibility Principle (SRP)

This folder demonstrates the Single Responsibility Principle: a class should have one, and only one, reason to change.

## Overview

- `BadUserService` violates SRP by handling validation, persistence and notification in one class.
- `GoodUserService` follows SRP by delegating each responsibility to a focused collaborator: `UserValidator`, `UserRepository`, and `NotificationService`.

## SRPDemo.java

The demo runs two examples:

1. SRP Violation (`BadUserService.register`)
2. SRP Compliant (`GoodUserService.register`) using the existing collaborators

### Expected console (illustrative)

=== SRP Violation example ===
Bad service error: Invalid email

=== SRP Compliant example ===
INSERT INTO users VALUES (Bob, bob@example.com)
Sending welcome email to bob@example.com

## Step-by-step flow and variable values

Q: Could you please explain the flow with what will be the value of variable in each call?

- BadUserService.register("Alice", "aliceexample.com")
  - Entry: `name` = "Alice", `email` = "aliceexample.com" (missing `@`)
  - Validation: check `email == null || !email.contains("@")` → true, throws `IllegalArgumentException`
  - Outcome: exception thrown; persistence and notification never run.

- GoodUserService.register("Bob", "bob@example.com")
  1. `validator.validate(email)`
     - Input: `email` = "bob@example.com"
     - `UserValidator.validate` checks contains `@` → passes (no exception).
     - No state mutated.
  2. `repository.save(name, email)`
     - Input: `name` = "Bob", `email` = "bob@example.com"
     - Simulated DB action prints: `INSERT INTO users VALUES (Bob, bob@example.com)`
  3. `notificationService.sendWelcomeEmail(email)`
     - Input: `email` = "bob@example.com"
     - Prints: `Sending welcome email to bob@example.com`

## Notes

- Benefits of SRP in this demo:
  - Easier to test `UserValidator` independently.
  - Swap `UserRepository` implementation (in-memory, JDBC, mock) without changing business logic.
  - Notification strategies can evolve (email, SMS, push) independently.

- When to apply: split classes when you can identify a distinct responsibility that may change for different reasons (persistence vs validation vs messaging).
