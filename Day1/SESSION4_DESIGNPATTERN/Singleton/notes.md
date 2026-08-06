# Singleton Pattern Notes

- The Singleton pattern ensures only one instance of a class exists and provides a global access point.
- A common implementation uses a holder or static instance and a private constructor.
- `SingletonDemo.java` demonstrates different styles of getting a single instance.
- Singletons are useful for shared configuration, logging, cache managers, or any resource that should have a single global object.
- Use with care: singletons can create hidden dependencies and make testing harder if overused.

## Q&A

Q: Why do we use Singleton?
A: We use Singleton when we need exactly one shared instance of a class across the application, such as configuration managers, loggers, or caches.
