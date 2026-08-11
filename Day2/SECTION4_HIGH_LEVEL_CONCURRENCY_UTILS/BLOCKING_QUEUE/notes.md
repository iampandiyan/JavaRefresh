# Blocking Queue Demos — Notes

This file explains the code flow and concepts for the blocking-queue demos in this folder. Explanations are written for a non-expert audience (layman-friendly).

**How to run** (from this folder):

```bash
javac *.java
java Day2.SECTION4_HIGH_LEVEL_CONCURRENCY_UTILS.BLOCKING_QUEUE.LinkedBlockingQueueDemo
java Day2.SECTION4_HIGH_LEVEL_CONCURRENCY_UTILS.BLOCKING_QUEUE.PriorityBlockingQueueDemo
java Day2.SECTION4_HIGH_LEVEL_CONCURRENCY_UTILS.BLOCKING_QUEUE.SynchronousQueueDemo
java Day2.SECTION4_HIGH_LEVEL_CONCURRENCY_UTILS.BLOCKING_QUEUE.DelayQueueDemo
```

**Contents**
- **LinkedBlockingQueue**: capacity-bounded FIFO queue where producers block when full.
- **PriorityBlockingQueue**: unbounded queue that orders elements by priority.
- **SynchronousQueue**: zero-capacity hand-off; each put waits for a take.
- **DelayQueue**: timed queue where elements become available only after a delay.


**LinkedBlockingQueueDemo**

- Code flow:
  1. Main creates a `LinkedBlockingQueue` with capacity 2.
  2. A `Producer` thread attempts to `put()` integers 1..5. `put()` blocks when the queue is full.
  3. A `Consumer` thread sleeps briefly so the producer hits the full queue, then repeatedly `take()`s values and processes them.
  4. Producer resumes when consumer removes items, demonstrating blocking and release behavior.

- Layman explanation:
  - Think of a small waiting line with only two chairs. When all chairs are filled, the next person must wait until someone leaves.
  - `put()` is like trying to sit; if no chair is free, the producer waits. `take()` is like standing up and freeing a chair.

- When to use:
  - When you want a thread-safe FIFO buffer with optional capacity limits to throttle producers.

- Expected behavior:
  - Producer will print attempts to `put` and may block for a short time; consumer prints items as it takes them.


**PriorityBlockingQueueDemo**

- Code flow:
  1. A `PriorityBlockingQueue<Task>` is created.
  2. Several `Task` objects with different numeric priorities are `put()` into the queue.
  3. The demo repeatedly `take()`s tasks; `take()` returns the highest-priority (lowest numeric value in this demo) element first.

- Layman explanation:
  - Imagine a triage desk where patients with higher urgency are served first, regardless of arrival order.
  - Items are ordered by priority rather than arrival time.

- When to use:
  - When tasks should be processed in priority order (scheduling, task dispatchers).

- Note:
  - `PriorityBlockingQueue` is unbounded by default; if you need back-pressure, combine with other controls.


**SynchronousQueueDemo**

- Code flow:
  1. A `SynchronousQueue<String>` is created.
  2. `Producer` calls `put("item-1")` and blocks because `SynchronousQueue` has no capacity.
  3. `Consumer` sleeps a bit, then calls `take()` and immediately receives `item-1`. Both threads can proceed.
  4. Producer then attempts a non-blocking `offer("item-2")` which may fail if no consumer is waiting; consumer later does a `poll()` which may return null.

- Layman explanation:
  - Picture two people passing a single item directly to each other — there is no table to leave it on. The giver must wait until the taker is present.
  - Useful when you want producers and consumers to rendezvous directly.

- When to use:
  - Hand-off patterns where one thread must hand work directly to another without buffering (work-stealing, thread pools, handoff designs).


**DelayQueueDemo**

- Code flow:
  1. A `DelayQueue<DelayedTask>` is created and several `DelayedTask` instances are `put()` with different delays (e.g., 300ms, 700ms, 1000ms).
  2. `take()` is called repeatedly; it blocks until the head element's delay has expired, then returns it.
  3. The demo prints the time elapsed when each task is taken, showing scheduled ordering.

- Layman explanation:
  - Think of a list of alarms set to ring at different times. You can't act on an alarm until its time arrives.
  - `DelayQueue` holds items but only makes them available after their scheduled delay.

- When to use:
  - Scheduled work, retry-with-delay, time-based ordering of tasks (simple scheduled queues without needing a full scheduler framework).


**Common tips & caveats**
- Blocking methods (`put`, `take`) will block the calling thread; use timeouts or interrupts for robust systems.
- Some queues (e.g., `PriorityBlockingQueue`) are unbounded — remember to add flow control if producers can outpace consumers.
- For hand-offs and tight coordination, `SynchronousQueue` is powerful but easy to misuse; ensure a consumer is ready when a producer offers.
- `DelayQueue` requires elements implementing `Delayed`; delays should use a monotonic time source (e.g., `System.nanoTime()` internally) to avoid skew issues.


If you'd like, I can also:
- Add short, one-line comments inside each demo source to explain critical lines, or
- Run these demos and paste the runtime output here.

File added: [Day2/SECTION4_HIGH_LEVEL_CONCURRENCY_UTILS/BLOCKING_QUEUE/notes.md](Day2/SECTION4_HIGH_LEVEL_CONCURRENCY_UTILS/BLOCKING_QUEUE/notes.md)
