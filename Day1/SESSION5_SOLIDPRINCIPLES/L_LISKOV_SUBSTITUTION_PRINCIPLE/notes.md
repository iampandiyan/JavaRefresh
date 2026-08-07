# Liskov Substitution Principle (LSP)

LSP says that derived types should be substitutable for their base types without changing program correctness.

## Overview

- `LspViolationDemo` shows the classic Rectangle/Square issue: `Square` extends `Rectangle` and overrides mutators in a way that breaks callers expecting independent width and height.
- `LspDemo` adds a compliant design using the `Shape` interface with immutable `FixedRectangle` and `FixedSquare` implementations.

## LspDemo.java

The demo prints two sections:

1. Violation Example
   - `resizeViolation(new Rectangle())` sets width to 5 and height to 10, producing area 50.
   - `resizeViolation(new Square())` sets width to 5 then height to 10, producing area 100 because `Square` keeps width and height equal.
2. Compliant Example
   - Uses `Shape` polymorphism and immutable value-based implementations.

### Expected behavior

=== LSP Violation Example ===
Expected area of 50, got 50
Expected area of 50, got 100

=== LSP Compliant Example ===
FixedRectangle area = 50
FixedSquare area = 25

## Flow & variable values

Q: Could you explain the flow with what will be the value of variable in each call?

- `resizeViolation(new Rectangle())`
  - `r` references a `Rectangle`.
  - `setWidth(5)` sets `width = 5`, `height` remains 0.
  - `setHeight(10)` sets `height = 10`.
  - `area()` returns `5 * 10 = 50`.

- `resizeViolation(new Square())`
  - `r` references a `Square`.
  - `setWidth(5)` sets both `width = 5` and `height = 5`.
  - `setHeight(10)` sets both `height = 10` and `width = 10`.
  - `area()` returns `10 * 10 = 100`.

- `printArea(new FixedRectangle(5, 10))`
  - `shape` is a `FixedRectangle`.
  - `area()` returns `5 * 10 = 50`.

- `printArea(new FixedSquare(5))`
  - `shape` is a `FixedSquare`.
  - `area()` returns `5 * 5 = 25`.

## Notes

- The violation happens because `Square` changes the behavior of `setWidth` and `setHeight` when it inherits from `Rectangle`.
- The compliant design avoids mutable contracts and uses a shared `Shape` abstraction.
- When implementing LSP, prefer abstractions that preserve caller assumptions and avoid overriding methods in ways that change their contract.
