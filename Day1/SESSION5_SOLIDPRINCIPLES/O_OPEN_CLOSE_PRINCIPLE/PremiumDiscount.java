package SESSION5_SOLIDPRINCIPLES.O_OPEN_CLOSE_PRINCIPLE;

public class PremiumDiscount implements DiscountPolicy {
    @Override
    public double apply(double amount) {
        return amount * 0.9;
    }

}
