# StampedLock Demo Notes

## What this code does

This demo uses a `StampedLock` to protect a pair of coordinates `x` and `y`.

- `move(deltaX, deltaY)` updates the coordinates using a write lock.
- `distanceFromOrigin()` reads the coordinates using an optimistic read first.

The main method starts one writer thread and one reader thread.

## Code flow explained

1. `main()` creates a `StampedLockDemo` object.
2. `Writer` thread calls `move(3, 4)`, then sleeps, then calls `move(-1, 2)`.
3. `Reader` thread calls `distanceFromOrigin()` five times with short pauses.

### `move(double deltaX, double deltaY)`

- Calls `lock.writeLock()` to get an exclusive stamp.
- This means no one else can read or write while the move is happening.
- Updates `x` and `y`.
- Calls `lock.unlockWrite(stamp)` to release the write lock.

### `distanceFromOrigin()`

- Calls `lock.tryOptimisticRead()`.
- This is a fast, non-blocking read attempt.
- It reads the current values of `x` and `y` without blocking.
- Then it calls `lock.validate(stamp)` to check if a write happened while reading.
- If no write happened, it returns the distance.
- If a write happened, it falls back to a real read lock:
  - calls `lock.readLock()`
  - reads `x` and `y` again
  - calls `lock.unlockRead(stamp)`

## What `StampedLock` means in plain language

A `StampedLock` is like a special key card system with three modes:

- **Write lock**: a single exclusive key. Only one person can hold it at a time.
- **Read lock**: a shared key. Many people can hold it at once.
- **Optimistic read**: a quick look without a key. If nobody writes while looking, the data is fine.

### Optimistic read explained simply

Optimistic read is like peek-reading a note from across the room.

- You do not reserve the room or interrupt anyone.
- You take a quick snapshot of the data.
- Later, you ask: "Did anyone change it while I was looking?"
- If no one changed it, you are done.
- If someone changed it, you go back and read properly.

This is useful when reads happen often and writes are rare.

## Why this is helpful

- Writes are exclusive and must be safe.
- Reads are usually safe and should be fast.
- `tryOptimisticRead()` gives a fast path when writes are not happening.
- If a write happens during the read, the code falls back to a safe read.

## Layman example

Imagine a library where:

- A writer needs silence and exclusive access to rewrite a page.
- A reader can usually glance at a page quickly without disturbing anyone.
- If the reader sees a possible change, they double-check by entering the room properly.

`StampedLock` is the system that manages these safe reader and writer steps.

## What the demo output tells you

You may see lines like:

- `Writer moved to (3, 4)`
- `Reader distance: 5.0`
- `Writer moved to (2, 6)`
- `Reader distance: 6.324555320336759`

This shows the writer updating the coordinates, and the reader measuring distance before and after the update.

## Simple summary

- `writeLock()` is for safe updates.
- `tryOptimisticRead()` is for fast, quick reads.
- `validate(stamp)` checks whether the fast read was still valid.
- If not valid, fall back to `readLock()` and read again.

This keeps the data safe while still letting reads happen quickly when possible.
