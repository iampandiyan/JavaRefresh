package SESSION5_SOLIDPRINCIPLES.O_OPEN_CLOSE_PRINCIPLE;

public class OpenCloseDemo {
    public static void main(String[] args) {
        DiscountCalculator calc = new DiscountCalculator();
        double amount = 100.0;

        System.out.println("=== Violation: String-based discount selector ===");
        try {
            System.out.println("REGULAR -> " + calc.calculate("REGULAR", amount));
            System.out.println("PREMIUM -> " + calc.calculate("PREMIUM", amount));
            System.out.println("VIP -> " + calc.calculate("VIP", amount));
            // this will throw if the string branch is not present
            System.out.println("STUDENT -> " + calc.calculate("STUDENT", amount));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n=== Open/Closed: Policy-based discount ===");
        System.out.println("REGULAR -> " + calc.calculate(new RegularDiscount(), amount));
        System.out.println("PREMIUM -> " + calc.calculate(new PremiumDiscount(), amount));
        System.out.println("VIP -> " + calc.calculate(new VipDiscount(), amount));
        System.out.println("STUDENT -> " + calc.calculate(new StudentDiscount(), amount));
    }
}
