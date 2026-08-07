package SESSION5_SOLIDPRINCIPLES.O_OPEN_CLOSE_PRINCIPLE;
// Adding StudentDiscount later requires ZERO changes to DiscountCalculator below.
public class StudentDiscount implements DiscountPolicy {
    @Override
    public double apply(double amount) {
        return amount * 0.85;
    }

}
