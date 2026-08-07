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

Q: Could you please explain the flow with what will be the value of variable in each call?
A: Using `StrategyDemo.java`:

Setup
- No retry strategy state is kept by the demo; it creates strategies and calls `simulateRetry`.

Step-by-step for `ExponentialBackoffStrategy` (first demo block):
1. `simulateRetry(new ExponentialBackoffStrategy())` is called; inside the loop `attempt` runs from 1 to 7.
2. For each attempt:
	- `attempt` value is the loop index (1,2,...)
	- `error` = new `RuntimeException("Temporary failure")`
	- `shouldRetry = strategy.shouldRetry(attempt, error)` returns `attempt < 5` (true for 1..4, false for 5)
	- printed: `Attempt X: shouldRetry=true/false`
3. The loop breaks when `shouldRetry` is false (at attempt 5), so printed attempts: 1..4 true, then 5 false stops.

Step-by-step for `ImmediateRetryStrategy`:
1. `simulateRetry(new ImmediateRetryStrategy())` runs attempts 1..7 similarly.
2. `shouldRetry = attempt < 3` → true for attempts 1 and 2, false at attempt 3; loop stops at attempt 3.

Key variables
- `attempt`: current loop index
- `error`: the exception instance created each iteration
- `shouldRetry`: boolean returned by the strategy implementation

Notes
- The demo shows how swapping strategies changes the runtime decisions without changing the `simulateRetry` code.

