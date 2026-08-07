package SESSION5_SOLIDPRINCIPLES.L_LISKOV_SUBSTITUTION_PRINCIPLE;

public class FixedSquare implements Shape {
    private int side;

    public FixedSquare(int side) {
        this.side = side;
    }

    @Override
    public int area() {
        return side * side;
    }

}
