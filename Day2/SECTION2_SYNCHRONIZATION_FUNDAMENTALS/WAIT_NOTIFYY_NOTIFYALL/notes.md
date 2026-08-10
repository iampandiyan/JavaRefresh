# Producer-Consumer Wait/Notify Demo

## What this code does

This demo shows two threads working together:

- One thread produces numbers and puts them into a shared list called `buffer`.
- The other thread consumes numbers from the same `buffer`.

The `buffer` has a limit of 5 items. If the producer tries to add more than 5 items, it has to wait. If the consumer tries to take an item when the buffer is empty, it also has to wait.

## Code flow explained step by step

1. `main()` creates a `ProducerConsumerDemo` object.
2. It starts a producer thread and a consumer thread.
3. The producer thread runs a loop from 0 to 9 and calls `produce(i)` for each value.
4. Inside `produce(int value)`:
   - The method is `synchronized`, so only one thread can run it or `consume()` at the same time.
   - If the buffer is full (`buffer.size() == capacity`), the producer calls `wait()`.
   - `wait()` pauses the producer and releases the lock so the consumer can run.
   - When the producer can proceed, it adds the value to the buffer, prints `Produced: x`, and calls `notifyAll()`.
5. The consumer thread also runs a loop from 0 to 9 and calls `consume()`.
6. Inside `consume()`:
   - The method is also `synchronized`.
   - If the buffer is empty, the consumer calls `wait()` and pauses until an item is available.
   - When an item exists, it removes the number from the buffer, prints `Consumed: x`, and calls `notifyAll()`.

## Why `wait()` is needed

Imagine a kitchen with one plate and two people:

- The cook cannot place a dish if the plate is already full.
- The eater cannot take a dish if the plate is empty.

In code, `wait()` is the action of stepping away from the counter and saying, “I cannot continue until the other person changes the state.”

So:

- Producer waits when the buffer is full.
- Consumer waits when the buffer is empty.

This keeps both threads from fighting over the shared buffer and from wasting CPU by checking again and again too quickly.

## Why `notifyAll()` is used

`notifyAll()` is like raising your hand and saying, “Hey, anybody waiting on this shared object: you can check again now.”

After the producer adds an item, it calls `notifyAll()` so a waiting consumer can wake up.
After the consumer removes an item, it calls `notifyAll()` so a waiting producer can wake up.

Using `notifyAll()` is safer than `notify()` in this example because there may be more than one thread waiting on the same object, and `notifyAll()` wakes them all up to check whether they can proceed.

## Realtime example with `notify()` and `notifyAll()`

This new demo shows a producer and two consumers working with the same queue.

- The producer sends 8 messages into a queue with capacity 3.
- Two consumers receive messages from the queue.
- The producer uses `lock.notify()` after sending a message.
- The consumers use `lock.notifyAll()` after receiving a message.

This is a more realistic scenario because:

- The queue can become full, forcing the producer to wait.
- The queue can become empty, forcing consumers to wait.
- Multiple consumers may be waiting at the same time.

### Why `notify()` is used in the producer

In `sendMessage()`:

- `notify()` wakes only one waiting thread.
- That is enough because the producer only needs to wake a single consumer when it adds one new message.
- If there are multiple consumers waiting, one of them will take the new message.

This is a “lighter” signal than `notifyAll()`.

### Why `notifyAll()` is used in the consumer

In `receiveMessage()`:

- After a consumer removes a message, it calls `notifyAll()`.
- There may be one producer waiting because the queue was full.
- There may also be another consumer waiting because the queue was empty.
- `notifyAll()` wakes everyone so each waiting thread can re-check the queue state.

This avoids the chance that the wrong thread remains blocked while the correct thread could run.

## Call-by-call flow in `RealtimeWaitNotifyDemo`

1. `main()` creates the demo and starts three threads: `Producer`, `Consumer-1`, and `Consumer-2`.
2. `Producer` calls `sendMessage("msg-1")`.
   - If the queue is not full, it adds the message.
   - It prints: `[Producer] sent: msg-1 (queue size: 1)`.
   - It calls `lock.notify()` to wake one waiting thread.
3. `Consumer-1` or `Consumer-2` calls `receiveMessage()`.
   - If the queue is empty, it prints that it is waiting and calls `lock.wait()`.
   - Once there is a message, it dequeues it.
   - It prints a line like `[Consumer-1] received: msg-1 (queue size: 0)`.
   - It calls `lock.notifyAll()` so the producer or the other consumer can wake up.
4. When the queue reaches size 3, the producer hits the `while (queue.size() == capacity)` check.
   - It prints: `[Producer] waiting, queue full (3/3)`.
   - It calls `lock.wait()` and pauses until a consumer removes a message.
5. When the queue becomes empty, a consumer hits the `while (queue.isEmpty())` check.
   - It prints: `[Consumer-X] waiting, queue empty`.
   - It calls `lock.wait()` and pauses until the producer sends a message.
6. Every `wait()` releases the lock so another thread can enter the synchronized block.
7. Every `notify()` or `notifyAll()` wakes waiting thread(s) to retry the condition.

## What each printed value means

- `queue size: N` shows how many messages are stored right now.
- `[Producer] waiting, queue full (3/3)` means the producer is paused because the buffer is full.
- `[Consumer-X] waiting, queue empty` means that consumer is paused because there is nothing to take.
- `sent: msg-i` means a message entered the queue.
- `received: msg-i` means a message left the queue.

## What this teaches in plain language

- `wait()` means “I cannot move forward until something changes.”
- `notify()` means “I added one thing; only one waiting thread needs to wake up.”
- `notifyAll()` means “I changed the state; everyone waiting should check whether they can move now.”

In a live system, this helps a producer and multiple consumers stay in sync without losing or repeating messages.

## Running the demo

From this folder:

```bash
javac RealtimeWaitNotifyDemo.java
java Day2.SECTION2_SYNCHRONIZATION_FUNDAMENTALS.WAIT_NOTIFYY_NOTIFYALL.RealtimeWaitNotifyDemo
```

You should see the producer sending messages and the two consumers receiving them in a real-time style.

The program prints lines like:

- `Produced: 0`
- `Consumed: 0`

Because the producer and consumer run at different speeds, the order may change, but the buffer never overflows or becomes negative.

## Layman summary

- `wait()` means “I’m stuck, let me stop and give control to the other party.”
- `notifyAll()` means “Something changed, everyone waiting should check again.”
- `synchronized` means “Only one thread can touch this shared buffer at a time.”

Together, these tools let two threads safely share work without losing or duplicating items.
