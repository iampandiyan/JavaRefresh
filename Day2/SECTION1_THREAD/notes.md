# Thread Lifecycle Demo Notes

This demo shows how a Java thread moves through different stages of life and how two pieces of code can wait for each other.

## What the code does

1. It creates a shared object called `lock`.
2. It creates a worker thread, but the worker does not start immediately.
3. Inside the worker thread:
   - The thread takes control of the `lock` object using `synchronized(lock)`.
   - It prints `Worker thread is waiting...`.
   - It calls `lock.wait()`, which means the worker goes to sleep and gives up the lock.
   - Later, when another thread wakes it up, it prints `Worker thread resumed.`.
4. In the main thread:
   - It prints the worker thread state before `start()` (should be `NEW`).
   - It starts the worker thread and then prints its state again (usually `RUNNABLE`).
   - It waits briefly so the worker can reach `wait()`.
   - It prints the worker state after the worker has entered `wait()` (should be `WAITING`).
   - It takes the lock, calls `lock.notify()`, and awakens the worker thread.
   - After a short pause, it prints the worker state again, which should now be `TERMINATED`.

## The thread concepts in simple terms

### What is a thread?
A thread is like a separate worker inside your program. Your program can have many workers doing different jobs at the same time.

### Thread states you see in this demo
- `NEW`: The worker exists, but it has not started working yet.
- `RUNNABLE`: The worker has started and is ready to work. It may or may not be actually running at this exact moment, but it is able to run.
- `WAITING`: The worker is paused and waiting for someone else to tell it to continue.
- `TERMINATED`: The worker has finished its job and is done.

### What does `synchronized(lock)` mean?
It means "use this lock to make sure only one thread can do the next part at a time." Think of it like one person holding a key. While they hold the key, nobody else can use the same locked section.

### What does `lock.wait()` do?
This is the worker saying: "I am going to wait here until someone else wakes me up." It also gives the lock away so another thread can take it.

### What does `lock.notify()` do?
This is the main thread saying: "Okay, worker, you can wake up now and keep going." It does not make the worker run instantly, but it allows the worker to continue when it can.

## Why this demo is useful

- It shows how threads can be started, paused, and resumed.
- It shows how two threads coordinate using a shared object (`lock`).
- It makes thread states visible with `worker.getState()`.

## Walkthrough of the actual code values

- Before the worker starts: `worker.getState()` = `NEW`.
- Right after `worker.start()`: `worker.getState()` = `RUNNABLE`.
- After the worker calls `wait()`: `worker.getState()` = `WAITING`.
- After the main thread calls `notify()` and the worker finishes: `worker.getState()` = `TERMINATED`.

## Real-time example: clock and main task
This is a second, more real-life example that shows how two threads can run at the same time without waiting for each other until needed.

- `RealTimeThreadDemo` creates a thread called `ClockThread`.
- The clock thread prints `Clock tick 1`, `Clock tick 2`, ..., one second apart.
- At the same time, the main program prints `Main task step 1`, `Main task step 2`, `Main task step 3`, one and a half seconds apart.
- After the main task is done, it calls `clockThread.join()` to wait for the clock thread to finish.

### What this shows in simple terms
- The clock is one worker, and the main program is another worker.
- They run independently, just like two people doing different jobs in the same office.
- The main program does not stop the clock from ticking.
- At the end, the main program waits until the clock worker is done before it finishes too.

### Why this is a good real-world example
This example is like a kitchen where one person keeps time on a stopwatch (`ClockThread`) while another person is cooking a few steps (`main` thread). They both do their own work in parallel.

## Layman takeaway
Think of the worker thread as a helper that starts when asked, waits for a signal when it reaches a pause point, and finishes when it gets the signal. The main program is the boss that starts the helper, watches its progress, and wakes it up when needed.
