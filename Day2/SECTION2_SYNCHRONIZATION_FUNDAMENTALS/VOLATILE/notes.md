# Volatile Publication Demo

## What this demo shows

This demo shows how `volatile` works when one part of a program writes data and another part reads it.

- `Config` is a small container with two values: `timeout` and `endpoint`.
- `config` is a static field marked as `volatile`.
- `publish()` fills a new `Config` object and stores it in `config`.
- `consume()` reads `config` and prints the values.
- `main()` runs `publish()` first, then `consume()`.

## Why `volatile` is used here

Think of `volatile` like a special sign on a mailbox.

- Without the sign, a reader might not notice the latest letter right away.
- With the sign, the reader is forced to check the mailbox every time.

In Java, `volatile` tells the computer:

- "Always write the latest value to shared memory when I change this variable." 
- "Always read the latest value from shared memory when I use this variable."

That means one thread can safely publish a new `Config`, and another thread can see it.

## What the code actually guarantees

`volatile Config config;` means the reference to the `Config` object is special.

- When `publish()` does `config = c;`, Java ensures the write is visible to other threads.
- When `consume()` does `Config c = config;`, Java ensures it reads the newest reference.

Because `publish()` sets the fields before assigning `config`, the fields are also visible after the volatile write.

So this is safe:

- create `Config` object
- set `timeout` and `endpoint`
- assign it to `config`

And later, `consume()` sees both values correctly.

## Layman explanation of the concept

Imagine two people sharing a desk drawer.

- Person A writes a note and puts it in the drawer.
- Person B later opens the drawer to read the note.

If the drawer is not checked carefully, B might see an old note or nothing at all.

Marking the drawer with `volatile` is like saying:

- "Always put the note where everyone can see it immediately."
- "Always open the drawer and read the latest note when you need it."

This prevents one person from accidentally using stale or old information.

## Important detail

The `volatile` keyword is on `config`, the object reference.

It does not make the fields inside `Config` themselves volatile.

But because the code fully initializes `Config` before writing the `volatile` field, the reader still sees the correct `timeout` and `endpoint` values.

## How to run this demo

From the folder containing `VolatilePublicationDemo.java`:

```bash
javac VolatilePublicationDemo.java
java Day2.SECTION3_VOLATILE.VolatilePublicationDemo
```

Expected output:

```
Publishing config...
Consuming config...
Timeout: 1000, Endpoint: 8080
```
