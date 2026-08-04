package Abstraction;
public interface PaymentProcessor {

void processPayment(double amount);

default void logTransaction(double amount) {
     System.out.println(auditPrefix() + "Processed: " + amount);
}

private String auditPrefix(){
    return "AUDIT: ";
}

static PaymentProcessor noOp(){
    return amount -> System.out.println("No operation for amount: " + amount);
}

}
