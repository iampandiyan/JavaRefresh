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
