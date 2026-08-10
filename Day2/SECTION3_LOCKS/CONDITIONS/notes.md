# Condition Demo Notes

## What this code does

This demo shows a `producer` and a `consumer` sharing a buffer safely using `ReentrantLock` and `Condition`.

- The producer adds items to the buffer.
- The consumer removes items from the buffer.
- The buffer can hold up to 5 items.
- If the buffer is full, the producer waits.
- If the buffer is empty, the consumer waits.

## Code flow explained

1. `main()` creates a `ConditionDemo` object.
2. It starts a producer thread and a consumer thread.
3. The producer thread calls `produce(i)` for values 0 through 9.
4. If the buffer is full inside `produce()`:
   - the producer calls `notFull.await()`.
   - this puts the producer to sleep until the buffer has space.
5. When the producer adds an item:
   - it calls `notEmpty.signal()`.
   - this wakes one waiting consumer.
6. The consumer thread calls `consume()` ten times.
7. If the buffer is empty inside `consume()`:
   - the consumer calls `notEmpty.await()`.
   - this puts the consumer to sleep until there is an item.
8. When the consumer removes an item:
   - it calls `notFull.signal()`.
   - this wakes one waiting producer.

## What `Condition` is in simple terms

A `Condition` is like a waiting line for a specific event.

- `notFull` is the line for producers waiting for space.
- `notEmpty` is the line for consumers waiting for items.

Instead of both threads just looking at the buffer again and again, they wait quietly until the right event happens.

## Why `Condition` is useful

Without conditions, the producer and consumer would have to keep checking the buffer repeatedly.
This wastes time and CPU.

With conditions:

- A producer waits only when the buffer is full.
- A consumer waits only when the buffer is empty.
- They are woken up exactly when they can continue.

## How the lock works

The code uses a `ReentrantLock` to make sure only one thread changes the buffer at a time.

- `lock.lock()` gives a thread exclusive access.
- `lock.unlock()` releases that access.

The conditions are created from the same lock:

- `notFull = lock.newCondition()`
- `notEmpty = lock.newCondition()`

This keeps the waiting and signaling safe.

## Layman explanation

Imagine a small kitchen with one plate:

- The cook puts food on the plate.
- The eater takes food from the plate.
- If the plate is full, the cook waits.
- If the plate is empty, the eater waits.

`Condition` is like a sign that tells the cook or eater when to wake up.

- `notFull` means “wake up when there is room on the plate.”
- `notEmpty` means “wake up when there is food on the plate.”

The lock is like a rule that only one person can touch the plate at a time.

## What the output means

You will see lines like:

- `Produced: 0`
- `Consumed: 0`

The order may vary, but the program never produces more than 5 items without consumption, and it never consumes from an empty buffer.

## Simple summary

- `ReentrantLock` keeps the buffer safe from two threads changing it at once.
- `Condition` lets threads wait for specific events instead of looping.
- `notFull.await()` waits until the buffer has space.
- `notEmpty.await()` waits until the buffer has an item.
- `signal()` wakes one waiting thread to continue.
