package Abstraction;
abstract class BasePaymentProcessor implements PaymentProcessor {
   protected final String currency;
   protected BasePaymentProcessor(String currency) {
        this.currency = currency;
    }
}
