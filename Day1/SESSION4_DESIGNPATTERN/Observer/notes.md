# Observer Pattern Notes

- `OrderObserver.java` defines the observer interface: `onOrderStatusChanged(String orderId, String newStatus)`.
- `OrderPublisher.java` is the subject that keeps a list of observers and notifies them when an order status changes.
- `EmailOrderObserver.java` and `SmsOrderObserver.java` are concrete listeners that react to the same event in different ways.
- `ObserverDemo.java` shows the runtime flow:
  1. create `OrderPublisher`
  2. subscribe observers
  3. call `publisher.updateStatus(...)`
  4. publisher broadcasts the event to all observers
- In your implementation, one update is sent to every subscribed observer; each observer can react independently.
- The original object does not change; the publisher simply informs registered observers of the event.
- Use Observer when you need decoupled event notification, publish/subscribe behavior, or when multiple listeners should react to the same change.
- Production examples: UI event listeners, logging listeners, notification services, real-time dashboards, and message broadcasting.

## Q&A

Q: Could you please explain the flow. Where does it start and how it initiate in each step?
A: The flow starts in `ObserverDemo.main(...)`. It creates `OrderPublisher`, subscribes `EmailOrderObserver` and `SmsOrderObserver`, then calls `publisher.updateStatus(...)`. Inside the publisher, `updateStatus(...)` loops through all observers and calls `onOrderStatusChanged(...)` on each. Each observer receives the same event and handles it independently.

Q: Are you saying that if there is change in one update may impact multiple flows? All the flows are added in publisher as a single group, so when there is a update in one flow, it will trigger impact on all flows in the same group?
A: Yes, your current implementation broadcasts one update to every observer in the publisher’s list. That means the same event is delivered to all observers, but each observer can react differently. It does not force them to do the same thing, just to receive the same notification.

Q: Could you please explain the flow with what will be the value of variable in each call?
A: Using `ObserverDemo.java` and `OrderPublisher`:

Setup
- `publisher` = new `OrderPublisher()` with `observers` = empty list.

Step-by-step
1. `publisher.subscribe(new EmailOrderObserver())`:
  - a new `EmailOrderObserver` instance is created and added to `publisher.observers` at index 0.
2. `publisher.subscribe(new SmsOrderObserver())`:
  - new `SmsOrderObserver` instance added to `publisher.observers` at index 1.
3. `publisher.updateStatus("ORD-1001", "PROCESSING")`:
  - inside `updateStatus`, `observers.forEach` iterates the list.
  - First iteration: `o` = `EmailOrderObserver` instance; call `o.onOrderStatusChanged("ORD-1001","PROCESSING")` → prints `Email observer: Order ORD-1001 changed to PROCESSING`.
  - Second iteration: `o` = `SmsOrderObserver` instance; call `o.onOrderStatusChanged(...)` → prints `SMS observer: Order ORD-1001 changed to PROCESSING`.
4. `publisher.updateStatus("ORD-1001", "SHIPPED")` repeats the same loop with `status` = "SHIPPED"; both observers receive the new value.

Key variables
- `publisher.observers` = list of observer instances
- `o` = current observer in the loop (Email then SMS)
- `id` and `status` parameters are the strings passed through to each observer (`"ORD-1001"`, then statuses)

Notes
- The publisher does not mutate observer internals; it simply broadcasts data to each registered listener.

