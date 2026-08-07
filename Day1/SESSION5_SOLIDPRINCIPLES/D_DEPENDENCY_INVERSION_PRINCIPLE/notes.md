# Dependency Inversion Principle (DIP)

The Dependency Inversion Principle prescribes that high-level modules should not depend on low-level modules; both should depend on abstractions.

## Overview

- `OrderService` violates DIP: it constructs and depends on a concrete `MySqlOrderRepository` directly.
- `OrderServiceFixed` follows DIP: it depends only on the `OrderRepository` abstraction and receives concrete implementations via constructor injection.
- Concrete implementations in this folder: `MySqlOrderRepositoryFixed`, `InMemoryOrderRepository`.

## DipDemo.java

The demo prints two sections:

1. DIP Violation Example
   - Creates `OrderService` which is tightly coupled to `MySqlOrderRepository`.
   - `placeOrder` calls `save` on the concrete repository.
2. DIP Compliant Example
   - Creates `OrderServiceFixed` and injects different `OrderRepository` implementations.
   - Demonstrates swapping `MySqlOrderRepositoryFixed` and `InMemoryOrderRepository` without editing `OrderServiceFixed`.

### Expected (illustrative) output

=== DIP Violation Example ===
Saving order to MySQL database

=== DIP Compliant Example ===
Saving order to MySQL database
Saving order to in-memory database

## Flow & variable values

Q: Could you explain the flow with what will be the value of variable in each call?

- `viol.placeOrder("order-123")` (violation)
  - `viol` is an `OrderService` instance which contains a `repository` field referencing a `MySqlOrderRepository` instance.
  - `placeOrder` calls `repository.save("order-123")` which prints `Saving order to MySQL database`.

- `new OrderServiceFixed(new MySqlOrderRepositoryFixed()).placeOrder("order-456")` (compliant)
  - `OrderServiceFixed.repository` references a `MySqlOrderRepositoryFixed` (via constructor injection).
  - `placeOrder` calls `repository.save("order-456")` → prints `Saving order to MySQL database`.

- `new OrderServiceFixed(new InMemoryOrderRepository()).placeOrder("order-789")` (compliant)
  - `repository` references `InMemoryOrderRepository`.
  - `placeOrder` calls `repository.save("order-789")` → prints `Saving order to in-memory database`.

## Notes

- Benefits of following DIP here:
  - Swap persistence strategies (MySQL, in-memory, mock) without changing business logic code.
  - Easier to unit test `OrderServiceFixed` by passing a test double implementation of `OrderRepository`.
