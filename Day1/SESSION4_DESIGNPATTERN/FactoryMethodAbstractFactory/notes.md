# Factory Method / Abstract Factory Pattern Notes

- `VideoProviderFactory.java` encapsulates provider creation so the client does not directly instantiate concrete providers.
- `FalAiProvider.java` and `TogetherAiProvider.java` implement `VideoProvider` and provide different behavior behind the same interface.
- `FactoryDemo.java` demonstrates selecting a provider via the factory rather than using `new` directly in client code.
- The factory pattern separates object creation from object use, making it easy to change or extend implementations without modifying clients.
- Use this pattern when the exact class to instantiate should be decided at runtime, or when you want to hide the construction details.
- Production examples: selecting database drivers, choosing cloud provider clients, creating UI components for different platforms, or building service objects from configuration.

## Q&A

Q: Why do we use the factory pattern?
A: We use it to hide creation details and centralize object construction. The client only depends on an interface and a factory, not concrete implementation classes.
