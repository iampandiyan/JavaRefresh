# Decorator Pattern Notes

- `DataSource.java` defines the core contract: `String read()`.
- `DataSourceDecorator.java` is the reusable abstract wrapper that holds a `DataSource` and delegates calls to it.
- `EncryptedDataSource.java` is a concrete decorator that adds behavior by decrypting the wrapped source result.
- `DecoratorDemo.java` shows wrapping a raw source with `EncryptedDataSource` and then reading through the decorator.
- `CoffeeDecoratorDemo.java` provides a simpler example: `SimpleCoffee` is wrapped by `MilkDecorator`, then by `SugarDecorator`, each adding description and cost.
- The key idea is preserving the original object while adding behavior on top of it, using delegation instead of mutation.
- When you call a decorated object method, the call passes through the wrapper, then to the wrapped object, and the wrapper augments the return value.
- Use Decorator when you need optional, combinable behavior at runtime, and when you want to avoid a class explosion of subclass combinations.
- Production examples: adding encryption, compression, logging, caching, validation, or metrics around a core data or service object.

## Q&A

Q: I am not able to understand the decorator pattern. Could you please explain with another example simple and add code and explain the code as well?
A: A simple example is coffee ordering. `SimpleCoffee` is the original object, `MilkDecorator` wraps `SimpleCoffee` and adds milk behavior, and `SugarDecorator` wraps `MilkDecorator` and adds sugar behavior. The original object is preserved; each decorator wraps the previous object and adds its own behavior by delegating method calls to the wrapped object.

Q: Here the original object variable is not modified anywhere?
A: No, the original object is not modified. `plainCoffee` remains the same `SimpleCoffee` instance. The decorators create new wrapper objects (`milkCoffee`, `sweetMilkCoffee`) that hold references to the wrapped object and add behavior on top.

Q: Will `Coffee milkCoffee = new MilkDecorator(plainCoffee);` create a new object?
A: Yes, it creates a new `MilkDecorator` instance. It wraps the original `plainCoffee` but does not change it. The cost is computed when `milkCoffee.getCost()` is called by adding `wrappedCoffee.getCost()` plus the decorator’s extra amount.
