package Encapsulation;

class EncapsulationMainClass {
    public static void main(String[] args) {
        Account account = new Account(1000.0);
        account.deposit(500.0);
        System.out.println("Current balance: " + account.getBalance());
        account.withdraw(200.0);
        System.out.println("Current balance: " + account.getBalance());
        System.out.println("Transaction History: " + account.getTransactionHistory());
        account.withdraw(2000.0); // This will throw an exception due to insufficient funds
    }
}