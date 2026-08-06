package SESSION4_DESIGNPATTERN.Proxy;

import java.lang.reflect.Proxy;

public class ProxyDemo {
    public static void main(String[] args) {
        OrderService realService = new OrderServiceImpl();

        OrderService proxyService = (OrderService) Proxy.newProxyInstance(
                OrderService.class.getClassLoader(),
                new Class<?>[]{OrderService.class},
                new TransactionalInvocationHandler(realService)
        );

        proxyService.placeOrder("ORD-2026");
        proxyService.cancelOrder("ORD-2026");
    }

    interface OrderService {
        void placeOrder(String orderId);
        void cancelOrder(String orderId);
    }

    static class OrderServiceImpl implements OrderService {
        @Override
        public void placeOrder(String orderId) {
            System.out.println("Placing order: " + orderId);
        }

        @Override
        public void cancelOrder(String orderId) {
            System.out.println("Cancelling order: " + orderId);
        }
    }
}
