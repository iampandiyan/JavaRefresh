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
A: The Proxy pattern provides a stand-in object that controls access to another object. In your example, `proxyService` is a proxy for `OrderServiceImpl`, and `TransactionalInvocationHandler` intercepts method calls. It adds behavior before and after the real method runs, such as `BEGIN TX`, invoking the target method, then `COMMIT` or `ROLLBACK`. We need it to separate cross-cutting concerns from business logic, like transactions, security, logging, or caching. In production, it is used for transaction management, security checks, remote service stubs, and caching.
