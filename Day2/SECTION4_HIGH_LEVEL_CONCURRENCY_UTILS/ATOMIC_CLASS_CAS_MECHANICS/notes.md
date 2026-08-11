# AtomicInteger Demo Notes

## What this code does

This demo shows how `AtomicInteger` can safely update a shared counter in multiple threads.

- The class uses `AtomicInteger counter = new AtomicInteger(0)`.
- `increamentManually()` manually performs a compare-and-set loop.
- Two threads each call `increamentManually()` 1000 times.
- At the end, the final counter value is printed.

## Code flow explained

1. `main()` creates an `AtomicDemo` object.
2. It starts `Thread-1` and `Thread-2`.
3. Each thread loops 1000 times and calls `demo.increamentManually()`.
4. Inside `increamentManually()`:
   - Read the current value: `current = counter.get()`.
   - Compute the next value: `next = current + 1`.
   - Try to set the counter to the new value only if it has not changed:
     `counter.compareAndSet(current, next)`.
   - If the value changed in between, repeat the loop.
5. After both threads finish, `main()` prints the final value.

## Why `AtomicInteger` is useful

`AtomicInteger` lets multiple threads update a number safely without using `synchronized` or locks.

It does this by using a special operation called compare-and-set (CAS).

### What CAS means in plain language

CAS is like this:

- "I think the value is 5.
- If it is still 5, change it to 6.
- If it is not 5 anymore, read the new value and try again."

This is a safe way for several threads to share one number.

## Why the manual loop is shown

The method `increamentManually()` is written to show how `incrementAndGet()` works internally.

It uses a loop because two threads may try to update the counter at the same time.

If a thread loses the race, it retries with the latest value.

## Layman explanation

Imagine a shared cash register where two cashiers count one more dollar at a time.

- Each cashier looks at the current total.
- Calculates the new total.
- Attempts to update the register only if no one else changed it first.
- If someone else already changed it, the cashier looks again and retries.

That way, nobody accidentally overwrites another cashier’s update.

## What the demo output means

- `Final counter value: 2000` is the expected result.
- It proves that both threads successfully incremented the counter without loss.

If this were not atomic, the final value could be less than 2000 because some increments would be lost.

## Simple summary

- `AtomicInteger` is a thread-safe number.
- `get()` reads the value.
- `compareAndSet(expected, updated)` tries to update only if the value is unchanged.
- The code retries until the update succeeds.
- This avoids locks while still preventing lost updates.
