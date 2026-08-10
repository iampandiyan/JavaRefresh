# ReentrantLock Demo Notes

## What this code does

This demo shows two threads trying to use the same lock.

- `Worker-1` and `Worker-2` both call `doWithoutTimeout()`.
- Inside that method, each thread tries to get the lock with `lock.tryLock(1, TimeUnit.SECONDS)`.
- If a thread gets the lock, it prints a message, sleeps for 2 seconds, and then releases the lock.
- If a thread does not get the lock within 1 second, it prints a different message and skips the locked work.

## Code flow step by step

1. `main()` creates a `ReentrantLockDemo` object.
2. It creates two threads: `Worker-1` and `Worker-2`.
3. Both threads start at almost the same time and call `doWithoutTimeout()`.
4. Inside `doWithoutTimeout()`:
   - The thread tries to acquire the lock with a 1-second wait.
   - If the lock is available immediately, the thread gets it and proceeds.
   - If the lock is held by the other thread, it waits up to 1 second for the lock.
5. If the thread acquires the lock:
   - It prints `Lock acquired, performing operation`.
   - It sleeps for 2 seconds to simulate work while holding the lock.
   - After the work is done, it unlocks and prints `Lock released`.
6. If the thread cannot acquire the lock within 1 second:
   - It prints `Could not acquire lock, operation skipped`.
   - It does not perform the protected operation.

## What the lock means in simple terms

A lock is like a single key to a room.

- Only one person can hold the key at a time.
- If one person has the key, the other person must wait.
- In the code, the room is the protected work inside the `if(acquired)` block.

The lock ensures that only one thread does that work at a time.

## What `tryLock(1, TimeUnit.SECONDS)` means

This is like saying:

- "I will wait up to 1 second for the key."
- If the key becomes available before 1 second, take it and enter.
- If the key is still not available after 1 second, give up and do something else.

That is why the second thread may print:

- `Could not acquire lock, operation skipped`

This means the second thread waited, but the first thread held the lock too long.

## Why one thread may skip the work

Because the first thread holds the lock for 2 seconds.

- `Worker-1` gets the lock first.
- It sleeps for 2 seconds while holding the lock.
- `Worker-2` waits up to 1 second.
- Since `Worker-1` still has the lock, `Worker-2` gives up.

So the second thread enters the method, checks the lock, waits, and then skips the locked section.

## How the output maps to the flow

- `Lock acquired, performing operation`: a thread got the lock and started work.
- `Could not acquire lock, operation skipped`: the other thread did not get the lock in time.
- `Lock released`: the thread that got the lock finished and let it go.

## Layman analogy

Imagine a bathroom key shared by two people:

- Person A takes the key and stays in the bathroom for 2 minutes.
- Person B arrives and waits for up to 1 minute.
- If Person A is still inside after 1 minute, Person B leaves and says, “I could not get in.”

That is exactly what happens in this program.
