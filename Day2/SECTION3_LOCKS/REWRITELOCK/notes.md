# ReadWriteLock Demo Notes

## What this code does

This demo shows a simple cache protected by a `ReadWriteLock`.

- `read(String key)` uses the read lock.
- `write(String key, String value)` uses the write lock.

A read lock allows many readers at the same time.
A write lock allows only one writer at a time.

## Code flow explained

1. `main()` creates a `ReadWriteLockDemo` instance.
2. It starts a writer thread that writes three entries to the cache.
3. It also starts two reader threads that read from the cache several times.
4. When a reader calls `read(key)`:
   - it acquires the read lock.
   - it fetches the value from the map.
   - it releases the read lock.
5. When the writer calls `write(key, value)`:
   - it acquires the write lock.
   - it puts the value into the map.
   - it releases the write lock.

## What the locks guarantee

- `readLock()` means multiple readers can read the cache together.
- `writeLock()` means only one writer can update the cache at a time.
- While a writer holds the write lock, readers must wait.
- While readers hold the read lock, a writer must wait.

This prevents readers from seeing partially updated data and prevents two writers from changing the cache at the same time.

## Layman explanation of the concept

Imagine a library reading room with one writing desk:

- Many people can sit quietly and read the books at the same time.
- But if someone wants to rewrite a book, they need exclusive use of the desk.
- While the book is being rewritten, no one else can read it.

A read-write lock is like that rule:

- Reading is shared and cheap.
- Writing is exclusive and must be done alone.

## Why this is useful

In many programs, read operations happen more often than write operations.

- `ReadWriteLock` lets many reads happen in parallel.
- It still protects the data when a write happens.

This helps performance when reading is common and writing is rare.

## What the demo output means

You may see lines like:

- `Writer wrote: key-1=value-1`
- `Reader-1 read: key-1=value-1`
- `Reader-2 read: key-1=value-1`

This means the writer updated the cache, and the readers were able to read that value.

If the readers run while the writer is updating, the writer waits until the readers finish before changing the cache.

## Simple summary

- `ReadWriteLock` has two keys: one for readers, one for writers.
- Many readers can use the read key at once.
- Only one writer can use the write key.
- The writer waits until all readers are done.
- The readers wait while a writer is updating.

This keeps the cache safe and still lets many reads happen quickly.
