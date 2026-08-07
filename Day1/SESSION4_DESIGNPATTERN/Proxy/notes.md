# Proxy Pattern Notes

- `TransactionalInvocationHandler.java` implements `InvocationHandler` and adds transaction logic around method execution.
- `ProxyDemo.java` demonstrates creating a dynamic proxy for `OrderService` and delegating calls to `OrderServiceImpl` through the proxy.
- The flow is:
  1. create real service (`OrderServiceImpl`)
  2. create dynamic proxy with `Proxy.newProxyInstance(...)`
  3. call proxy methods
  4. proxy forwards the call to `TransactionalInvocationHandler.invoke(...)`
  5. handler runs pre-processing (`BEGIN TX`)
  6. handler invokes the real method
  7. handler runs post-processing (`COMMIT` or `ROLLBACK`)
- Proxy allows adding behavior without changing the real service implementation.
- Use Proxy when you need to control access, add security, logging, transactions, caching, or remote communication transparently.
- Production examples: Spring AOP transaction proxies, authentication proxies, remote service stubs, and caching proxies.

## Q&A

Q: What is the Proxy pattern? How does it work? Why do we need this? How is the flow? When do we need it in production?
A: The Proxy pattern provides a stand-in object that controls access to another object. In your example, `proxyService` is a dynamic proxy implementing `OrderService`, and `TransactionalInvocationHandler` intercepts each method call to add transaction semantics (`BEGIN TX` / `COMMIT` / `ROLLBACK`) around the real `OrderServiceImpl` method.

Q: Could you please explain the flow with what will be the value of variable in each call?
A: Yes — below is the step-by-step flow and values for the two example calls in `ProxyDemo`.

Setup (before calls)
- `realService` = instance of `ProxyDemo.OrderServiceImpl`
- `proxyService` = dynamic proxy instance (class like `com.sun.proxy.$Proxy...`) implementing `OrderService`
- `handler.target` (inside `TransactionalInvocationHandler`) = reference to `realService`

Call A — `proxyService.placeOrder("ORD-2026")`
1. JVM dispatches the call to the proxy. Handler invocation parameters:
  - `proxy` = the proxy instance (`proxyService`)
  - `method` = reflective `Method` for `OrderService.placeOrder(String)`
  - `args` = Object[] { "ORD-2026" }
  - `handler.target` = `realService`
2. Inside `TransactionalInvocationHandler.invoke(...)`:
  - prints: `BEGIN TX`
  - calls `Object r = method.invoke(target, args);`
    - this calls `realService.placeOrder("ORD-2026")`
    - inside `placeOrder`: prints `Placing order: ORD-2026`
    - since method is `void`, `method.invoke(...)` returns `null`
  - after invoke:
    - `r` = `null`
    - prints: `COMMIT`
    - returns `r` (`null`) to the caller
3. Visible sequence on stdout:
  - `BEGIN TX`
  - `Placing order: ORD-2026`
  - `COMMIT`

Call B — `proxyService.cancelOrder("ORD-2026")`
1. Handler invocation parameters:
  - `proxy` = the proxy instance
  - `method` = Method for `OrderService.cancelOrder(String)`
  - `args` = Object[] { "ORD-2026" }
  - `handler.target` = `realService`
2. Inside `invoke(...)`:
  - prints: `BEGIN TX`
  - `method.invoke(target, args)` -> calls `realService.cancelOrder("ORD-2026")`
    - prints: `Cancelling order: ORD-2026`
    - returns `null`
  - `r` = `null`
  - prints: `COMMIT`
  - returns `null`
3. Visible sequence on stdout:
  - `BEGIN TX`
  - `Cancelling order: ORD-2026`
  - `COMMIT`

Error path (if the real method throws)
- `method.invoke(target, args)` will throw an exception (wrapped in `InvocationTargetException`).
- In the handler catch block:
  - `e` holds the thrown exception
  - handler prints: `ROLLBACK`
  - handler rethrows the exception to the caller

Key variables summary
- `proxy`: the dynamic proxy object the caller holds
- `method`: the reflective `Method` instance for the invoked interface method
- `args`: the original arguments array (`{"ORD-2026"}` in the examples)
- `handler.target`: the real implementation instance (`OrderServiceImpl`)
- return value `r`: whatever `method.invoke(...)` returns (here `null` for `void` methods)

So the proxy intercepts the call, sees the same `args` you passed, delegates to the `handler.target`, and returns the real method result (after pre/post processing). The original `realService` instance and its state are preserved; the proxy only controls invocation and adds cross-cutting behavior.
