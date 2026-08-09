# Exception Handling

This folder contains demos for Java exception handling techniques: wrapping, custom runtime exceptions, validation exceptions, and try-with-resources suppressed exceptions.

## Files of interest

- `TryWithResourcesDemo.java` — shows how suppressed exceptions are attached when a close() throws while a primary exception is thrown during resource use.
- `SwiftMessageParseException`, `SwiftMessageValidationException`, `SwiftMessageException` — custom runtime exceptions used by `ExceptionDemo`.
- `ExceptionDemo.java` — a lightweight processor that simulates parse/validation and demonstrates caught/wrapped exceptions and accessing their details.

## What `ExceptionDemo` shows

- Wrapping low-level exceptions: parse errors are caught and rethrown as `SwiftMessageParseException`, preserving the cause (`getCause()`).
- Carrying structured validation errors in an exception: `SwiftMessageValidationException` contains a list of validation error messages (`getValidationErrors()`).
- Differentiating concerns: parsing errors (unexpected failures) vs validation errors (business-rule failures).
- Delegating suppressed-exception inspection to `TryWithResourcesDemo` to demonstrate `e.getSuppressed()`.

## Step-by-step flow and variable values

Q: Could you explain the flow with what will be the value of variable in each call?

- `p.process("MT103:some-body")`
  - `raw` = `"MT103:some-body"` → `parse` returns `SwiftMessage(type="MT103", body="some-body")` → `validate` sees no `BADVAL` → prints `Processed message type=MT103`.

- `p.process("BADPARSE")`
  - `raw` = `"BADPARSE"` → `parse` throws `IllegalStateException("I/O during parse")` → caught and rethrown as `SwiftMessageParseException("MT103", cause)`
  - Caller catches `SwiftMessageParseException`, inspects `ex.getMessage()`, `ex.getMessageType()` and `ex.getCause()`.

- `p.process("MT103:body-with-BADVAL")`
  - `raw` contains `BADVAL` → `parse` returns message → `validate` throws `SwiftMessageValidationException` with the list of errors.
  - Caller catches the validation exception and prints `vex.getValidationErrors()`.

## Notes

- In this codebase the custom exceptions extend `RuntimeException` for simplicity; in production you may choose checked exceptions to force handling at compile time.
- Always preserve the original cause when wrapping: use constructors that call `super(message, cause)` so stack traces remain informative.
- Use try-with-resources to have predictable resource cleanup and be aware of suppressed exceptions.
