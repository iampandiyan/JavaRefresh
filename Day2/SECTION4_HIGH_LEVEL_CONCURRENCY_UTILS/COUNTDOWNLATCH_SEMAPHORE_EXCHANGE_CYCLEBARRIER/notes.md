# Concurrency Utilities Notes

## SemaphoreDemo code flow

1. `main()` creates a `SemaphoreDemo` instance.
2. It starts four worker threads named `Worker-1` through `Worker-4`.
3. Each worker calls `useConnection(workerId)`.
4. Inside `useConnection()`:
   - `connectionPool.acquire()` asks for one permit from the semaphore.
   - If a permit is available, the worker proceeds.
   - If no permit is available, the worker waits until one is released.
   - The worker prints that it is using the connection and sleeps for 500 ms.
   - In `finally`, it calls `connectionPool.release()` to return the permit.

### What this shows

A `Semaphore` limits how many threads can use a resource at the same time.
In this demo, the semaphore has 2 permits, so only two workers can use the connection concurrently.

## LatchAndBarrierDemo code flow

1. `main()` first creates a `CountDownLatch` for 3 workers.
2. It starts 3 worker threads that each print an initialization message and call `initLatch.countDown()`.
3. `initLatch.await()` blocks the main thread until all 3 workers have called `countDown()`.
4. When the latch reaches zero, main prints `All Workers Initialized - main Proceeds`.

5. Next, `main()` creates a `CyclicBarrier` for 3 workers with a barrier action that prints a message.
6. It starts 3 worker threads that each print a checkpoint message and call `barrier.await()`.
7. The workers wait at the barrier until all 3 have reached it.
8. Once all workers arrive, the barrier action prints a message and all workers continue.

## CountDownLatch explained for a layman

A `CountDownLatch` is like a group of people waiting for a starting signal.

- The latch starts with a count of how many signals are needed.
- Each worker gives one signal when it finishes its piece.
- The waiting thread (like the main thread) waits until all signals are received.
- Once the count reaches zero, the waiting thread wakes up.

Use case: wait for several setup tasks to finish before proceeding.

## CyclicBarrier explained for a layman

A `CyclicBarrier` is like a group of runners waiting at the starting line.

- Each runner reaches the line and waits.
- Nobody starts until everyone is ready.
- When the last runner arrives, the barrier opens and all runners go together.
- The barrier is "cyclic" because it can be used again for another round.

Use case: coordinate multiple threads to reach the same point before moving on together.

## Semaphore explained for a layman

A `Semaphore` is like a parking lot with a limited number of spaces.

- The lot has a fixed number of spaces (permits).
- A car needs a space before it can park.
- If all spaces are full, the next car waits.
- When a car leaves, it frees a space for another car.

Use case: limit how many threads can access a shared resource at once.

## Exchanger explained for a layman

An `Exchanger` is like a swap meet where two people exchange items.

- Each person brings one item.
- They both wait until their partner arrives.
- When two people meet, they swap their items and leave.

Use case: let two threads exchange data directly.

## Summary of how these pieces differ

- `CountDownLatch`: one-time wait until a fixed number of events happen.
- `CyclicBarrier`: wait until a group of threads all reach a point, then release them together; reusable.
- `Semaphore`: limit how many threads can use something at once.
- `Exchanger`: let two threads swap information with each other.

## Why this matters

These utilities help threads cooperate without interfering with each other.
They are useful when:

- one thread must wait for others (`CountDownLatch`),
- threads must start together (`CyclicBarrier`),
- only a few threads may use a resource at once (`Semaphore`),
- two threads need to swap data (`Exchanger`).

## Exchanger demo and explanation

### What the `ExchangerDemo` shows

- A `Producer` and a `Consumer` prepare items independently.
- Each uses an `Exchanger<String>` to swap an item with the other thread.
- The `exchange()` call blocks until the partner thread also calls `exchange()`.

### Code flow (call-by-call)

1. `main()` creates an `ExchangerDemo` and starts `Producer` and `Consumer` threads.
2. `Producer` prepares `A-1`, calls `exchange("A-1")` and waits.
3. `Consumer` prepares `B-1`, calls `exchange("B-1")` and the two threads swap values.
4. Both threads print the value they received and continue with next item.

### Layman explanation

An `Exchanger` is like two people meeting to swap letters:

- Person A brings letter A-1 and waits at the meeting point.
- Person B brings letter B-1 and arrives.
- They exchange letters and leave, each now holding the other's letter.

This is useful when two threads need to hand off data to each other directly without a shared queue.

