# Strategy Pattern Notes

- `RetryStrategy.java` defines an interface for retry behavior.
- `ExponentialBackoffStrategy.java` and `ImmediateRetryStrategy.java` provide different retry algorithms.
- `StrategyDemo.java` demonstrates choosing a retry strategy and using it through the same interface.
- The pattern decouples the algorithm from the client, so the client can select or replace strategies at runtime.
- Use Strategy when you have multiple ways to perform the same task and want to choose the best one dynamically.
- Production examples: retry policy selection, discount rules, routing algorithms, sorting strategies, and payment gateway selection.

## Q&A

Q: What is the Strategy pattern and why use it?
A: Strategy lets you define a family of related algorithms and make them interchangeable at runtime. It keeps the client from hard-coding a specific algorithm and makes it easy to select behavior based on context.
