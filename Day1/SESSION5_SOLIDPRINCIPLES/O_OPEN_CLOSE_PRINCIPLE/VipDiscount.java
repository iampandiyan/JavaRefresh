package SESSION5_SOLIDPRINCIPLES.O_OPEN_CLOSE_PRINCIPLE;

public class VipDiscount implements DiscountPolicy {
    @Override
    public double apply(double amount) {
        return amount * 0.8;
    }

}
