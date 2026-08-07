# Builder Pattern Notes

- `SwiftMessage.java` uses the Builder pattern to construct a message object with multiple fields.
- The builder separates object creation from object representation: you set fields on the builder and then call `build()`.
- This avoids telescoping constructors and makes the code more readable than a long constructor call.
- `BuilderDemo.java` shows how to construct a `SwiftMessage`, then print the built object values.
- The built object can remain immutable because all fields are set before creation.
- Use the Builder pattern when a class has many optional fields, or when you want to make object construction more expressive and less error-prone.
- Production example: building complex request objects, configuration objects, or DTOs with many optional parameters.

## Q&A

Q: Why use the Builder pattern?
A: Builder is useful when a class has many fields or optional parameters. It makes construction readable and avoids huge constructors. It also supports immutability by building the object once all needed values are set.

Q: Could you please explain the flow with what will be the value of variable in each call?
A: Using `BuilderDemo.java`:

Setup (before calls)
- No `SwiftMessage` exists yet.

Step-by-step
1. `new SwiftMessage.Builder("MT103", "BANKABC")` creates a `Builder` instance where:
	- `messageType` = "MT103"
	- `sender` = "BANKABC"
	- `receiver` = null
	- `reference` = null
	- variable: `builder` refers to this Builder instance
2. `builder.receiver("BANKXYZ")` sets `builder.receiver` = "BANKXYZ" and returns the same `builder` instance.
3. `builder.reference("REF-12345")` sets `builder.reference` = "REF-12345".
4. `SwiftMessage message = builder.build()` creates a new `SwiftMessage` whose fields are copied from the builder:
	- `message.getMessageType()` → "MT103"
	- `message.getSender()` → "BANKABC"
	- `message.getReceiver()` → "BANKXYZ"
	- `message.getReference()` → "REF-12345"

Notes
- The `builder` still exists after `build()` with the same internal field values; the built `message` is a separate immutable object.

