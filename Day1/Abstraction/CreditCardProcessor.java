package Abstraction;
public class CreditCardProcessor extends BasePaymentProcessor {
    public CreditCardProcessor(String currency) {
        super(currency);
    }

    @Override
    public void processPayment(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Payment amount must be positive.");
        }
        System.out.println("Processing credit card payment of " + amount + " " + currency);
        logTransaction(amount);
    }


} 


