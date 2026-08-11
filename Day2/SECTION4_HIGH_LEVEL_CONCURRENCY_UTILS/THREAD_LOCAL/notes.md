# ThreadLocal Demos — Notes

This file explains the code flow and concepts for `ThreadLocal` demos in this folder. The explanations are written in plain language for learners.

How to run (from this folder):

```bash
javac *.java
java Day2.SECTION4_HIGH_LEVEL_CONCURRENCY_UTILS.THREAD_LOCAL.ThreadLocalDemo
```

## What is `ThreadLocal` (layman)
- `ThreadLocal` provides a separate variable copy for each thread. Think of it as a personal locker for each thread: the same locker key (the `ThreadLocal` variable) opens a different locker for each thread.
- When a thread sets a value on the `ThreadLocal`, only that thread can read that value using `get()`.
- Useful for per-thread context like request IDs, user sessions, formatters, or reusable non-thread-safe objects.

## Common pitfall (important)
- Threads in a thread pool are reused. If you set a `ThreadLocal` value and forget to remove it, the next task that runs on the same thread may accidentally see the previous value.
- Always `remove()` the `ThreadLocal` in a `finally` block when using pooled threads.

## `ThreadLocalDemo.java` — code flow
1. A static `ThreadLocal<String> correlationId` is declared. Although it's a single static variable, each thread gets its own stored value.
2. `main()` creates two threads (`t1`, `t2`) that call `processRequest("REQ-1")` and `processRequest("REQ-2")` respectively.
3. `processRequest(id)` sets the thread's `correlationId` with `correlationId.set(id)`.
4. It calls `doWork()` which prints the current thread name and the `correlationId.get()` value — this reads the thread's own value.
5. In the `finally` block `correlationId.remove()` clears the value to avoid leaks when threads are pooled.
6. `main()` waits for both threads to finish; each prints its own `correlationId`.

### Layman walk-through
- Imagine two customer service agents (threads) each with a sticky note for the current customer's ID. When Agent A handles Customer REQ-1, they write REQ-1 on their sticky note. Agent B writes REQ-2 on their own sticky note. They never look at each other's notes.
- Even though there is only one `ThreadLocal` variable in code, the runtime gives each agent their own sticky note.

## Why use `ThreadLocal` instead of shared variables
- Avoids explicit passing of context through many method calls.
- Keeps data isolated per-thread without synchronization overhead.
- Good for per-request context in server-side code where each request is handled on a thread.

## When not to use `ThreadLocal`
- If you need data shared between threads, `ThreadLocal` is wrong; use other concurrency structures (queues, atomics, synchronized blocks).
- If you rely on thread-local state across thread handoffs (e.g., tasks submitted to thread pool), be careful — copy or clear the state as needed.

## Example improvements and tips
- Use `ThreadLocal.withInitial(...)` to provide a default value lazily.
- When storing mutable objects (e.g., `SimpleDateFormat`), prefer `ThreadLocal` to avoid expensive object creation or expensive synchronization.
- For pooled threads, always clear `ThreadLocal` values in `finally`.

If you'd like, I can:
- Add inline comments to `ThreadLocalDemo.java` clarifying each line, or
- Add an additional demo that shows the leak when `remove()` is omitted, so learners can see the problem and the fix.

File added: [Day2/SECTION4_HIGH_LEVEL_CONCURRENCY_UTILS/THREAD_LOCAL/notes.md](Day2/SECTION4_HIGH_LEVEL_CONCURRENCY_UTILS/THREAD_LOCAL/notes.md)
