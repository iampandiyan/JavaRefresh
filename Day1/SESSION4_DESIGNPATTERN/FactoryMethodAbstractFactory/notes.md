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

Q: Could you please explain the flow with what will be the value of variable in each call?
A: Using `FactoryDemo.java` (simplified):

Setup
- No provider instances exist initially.

Step-by-step
1. `VideoProvider falAiProvider = VideoProviderFactory.create("FalAi");`
	- `VideoProviderFactory.create` examines the input string and returns `new FalAiProvider()`.
	- `falAiProvider` refers to a `FalAiProvider` instance.
2. `falAiProvider.generateVideo("Create a video about AI.")` calls the provider implementation which returns:
	- `"Fal AI generated video for prompt: Create a video about AI."`
	- that string is printed by the demo.
3. `VideoProvider togetherAiProvider = VideoProviderFactory.create("TogetherAi");`
	- returns `new TogetherAiProvider()`; `togetherAiProvider` refers to that instance.
4. `togetherAiProvider.generateVideo("Create a video about teamwork.")` returns
	- `"Together AI generated video for prompt: Create a video about teamwork."`

Notes
- The factory decides which concrete class to instantiate; the client only holds `VideoProvider` references and doesn't need to know the concrete types.

