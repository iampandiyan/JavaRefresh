package SESSION5_SOLIDPRINCIPLES.O_OPEN_CLOSE_PRINCIPLE;

public class RegularDiscount implements DiscountPolicy {
    @Override
    public double apply(double amount) {
        return amount;
    }

}
