# Singleton Pattern Notes

- The Singleton pattern ensures only one instance of a class exists and provides a global access point.
- A common implementation uses a holder or static instance and a private constructor.
- `SingletonDemo.java` demonstrates different styles of getting a single instance.
- Singletons are useful for shared configuration, logging, cache managers, or any resource that should have a single global object.
- Use with care: singletons can create hidden dependencies and make testing harder if overused.

## Q&A

Q: Why do we use Singleton?
A: We use Singleton when we need exactly one shared instance of a class across the application, such as configuration managers, loggers, or caches.

Q: Could you please explain the flow with what will be the value of variable in each call?
A: Using `SingletonDemo.java` and `ConfigManager` (holder-based) as the example:

Setup
- No `ConfigManager` instance exists yet; `Holder` class not loaded.

Step-by-step
1. First call: `ConfigManager config1 = ConfigManager.getInstance();`
	- this triggers loading of `ConfigManager.Holder` and initialization of `Holder.INSTANCE = new ConfigManager()`.
	- `config1` references the newly created `ConfigManager` instance.
2. Second call: `ConfigManager config2 = ConfigManager.getInstance();`
	- returns the already-created `Holder.INSTANCE`.
	- `config2` == `config1` (same object reference). `config1 == config2` evaluates to `true`.
3. In `SingletonDemo`, other singleton variants (`EagerSingleton`, `LazySingleton`, `SynchronizedSingleton`, `EnumSingleton`) similarly return the same instance for repeated `getInstance()`/access; their `hashCode()`/identity checks show equality.

Key variables
- `Holder.INSTANCE`: the single `ConfigManager` instance
- `config1`, `config2`: both reference the same object after first initialization

Notes
- The first `getInstance()` call performs initialization; subsequent calls return the stored instance without re-creation.

