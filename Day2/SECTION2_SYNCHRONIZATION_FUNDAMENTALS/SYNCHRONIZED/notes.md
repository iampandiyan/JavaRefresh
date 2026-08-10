# Synchronized Demo Notes

This demo shows how Java uses `synchronized` to protect shared data when more than one thread is working at the same time.

## What the code does

1. It creates a `SynchronizedDemo` object with a shared number called `counter`.
2. The `increment()` method increases `counter` by 1.
3. `increment()` is marked with `synchronized`, which means only one thread can be inside it at a time.
4. The main method creates two threads, `thread1` and `thread2`.
5. Each thread runs the same task: call `demo.increment()` 1000 times.
6. The main thread starts both threads.
7. It prints `demo.counter` immediately, then waits for `thread1` to finish using `join()`.
8. Finally, it prints `demo.counter` again.

## Why `synchronized` matters

Imagine two people sharing the same pen and notebook. If both write at the same time, the page becomes a mess. `synchronized` is like saying, "Only one person may use the pen at a time." That keeps the notebook clean.

In this code:
- `counter` is the shared notebook.
- each thread is a person trying to write (increase the number).
- `synchronized` makes sure one thread finishes updating `counter` before the other thread starts.

## Why the first print can be wrong

The line:

```java
System.out.println("Expected 1000, got: " + demo.counter);
```

happens immediately after starting the threads, before they have finished their work.
That is why it can show `0` or some other small number.

A better expectation after both threads finish is `2000`, because:
- `thread1` does 1000 increments,
- `thread2` does 1000 increments,
- together they should increase `counter` by 2000.

## Why `thread1.join()` is used

`thread1.join()` makes the main program wait until `thread1` is done.
Without `join()`, the main program might print the value before the threads finish.

In the real world, `join()` is like saying, "I will wait here until my helper comes back." It makes the order of events predictable.

## What happens without `synchronized`

If `increment()` were not synchronized, both threads could try to change `counter` at the same time.
That can cause mistakes because the two threads may interfere with each other.

In everyday terms:
- two people try to write the same line at once,
- the result can be broken or incomplete,
- and the final number may be less than expected.

## Layman takeaway

- `synchronized` is a safety fence around code that changes shared data.
- It prevents two threads from running that code at the same time.
- `join()` is a simple way to wait for a thread to finish before continuing.

## Real-life analogy

Think of a single bathroom in an office:
- `counter` is the bathroom.
- `synchronized` is the lock on the door.
- only one person can use it at a time.
- `join()` is waiting outside until the person leaves.

That is how Java keeps shared work safe and predictable.
