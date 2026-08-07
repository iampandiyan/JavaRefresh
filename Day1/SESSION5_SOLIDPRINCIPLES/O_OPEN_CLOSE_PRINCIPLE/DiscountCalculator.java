package SESSION5_SOLIDPRINCIPLES.O_OPEN_CLOSE_PRINCIPLE;

// VIOLATION: adding a new discount type means editing this method,
// re-testing all the existing branches, and risking regressions.
class DiscountCalculator {
    double calculate(String customerType, double amount) {
        if (customerType.equals("REGULAR")) return amount;
        else if (customerType.equals("PREMIUM")) return amount * 0.9;
        else if (customerType.equals("VIP")) return amount * 0.8;
        // adding "STUDENT" tomorrow means modifying THIS existing method
        else throw new IllegalArgumentException("Unknown customer type");
    }
     double calculate(DiscountPolicy policy, double amount) {
        return policy.apply(amount); // closed for modification — never needs to change again
    }
}
