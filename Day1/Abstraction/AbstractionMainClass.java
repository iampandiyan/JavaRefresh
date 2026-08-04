package Abstraction;
public class AbstractionMainClass {
public static void main(String[] args) {
        PaymentProcessor creditCardProcessor = new CreditCardProcessor("USD");
        creditCardProcessor.processPayment(100.0);
        PaymentProcessor noOpProcessor = PaymentProcessor.noOp();
        noOpProcessor.processPayment(50.0);
        noOpProcessor.logTransaction(50.0); // This will log the transaction with the audit prefix
        noOpProcessor.processPayment(-100);
        creditCardProcessor.processPayment(-50.0);
}
}
