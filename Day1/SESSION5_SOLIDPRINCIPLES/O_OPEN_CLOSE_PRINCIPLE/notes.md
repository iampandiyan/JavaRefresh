# Open/Closed Principle (OCP)

The Open/Closed Principle states that software entities (classes, modules, functions) should be open for extension but closed for modification.

## Overview

- `DiscountCalculator.calculate(String, double)` shows a violation: adding a new customer type requires editing the method.
- `DiscountPolicy` and the concrete implementations (`RegularDiscount`, `PremiumDiscount`, `VipDiscount`, `StudentDiscount`) demonstrate an OCP-compliant design: new discounts can be added by creating new `DiscountPolicy` implementations without modifying `DiscountCalculator`.

## OpenCloseDemo.java

The demo prints two sections:

1. Violation: selects discount by `String` and may throw when an unknown type is passed.
2. Open/Closed: uses `DiscountPolicy` implementations and `DiscountCalculator.calculate(DiscountPolicy, double)`.

### Expected (illustrative) output

=== Violation: String-based discount selector ===
REGULAR -> 100.0
PREMIUM -> 90.0
VIP -> 80.0
Error: Unknown customer type

=== Open/Closed: Policy-based discount ===
REGULAR -> 100.0
PREMIUM -> 90.0
VIP -> 80.0
STUDENT -> 85.0

## Flow & variable values

Q: Could you explain the flow with what will be the value of variable in each call?

- `calc.calculate("PREMIUM", 100.0)`
  - `customerType` = "PREMIUM", `amount` = 100.0
  - String-branch returns `amount * 0.9` → 90.0

- `calc.calculate(new PremiumDiscount(), 100.0)`
  - `policy` = `PremiumDiscount` instance, `amount` = 100.0
  - `DiscountCalculator.calculate(DiscountPolicy, double)` calls `policy.apply(amount)` → `PremiumDiscount.apply` returns 90.0

## Notes

- To add a `SENIOR` discount: create `SeniorDiscount implements DiscountPolicy` (no changes to `DiscountCalculator`).
- Prefer the policy-based approach when new behavior types are expected to be added over time.
