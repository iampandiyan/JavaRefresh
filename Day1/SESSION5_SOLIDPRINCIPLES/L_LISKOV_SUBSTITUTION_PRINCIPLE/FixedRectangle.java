package SESSION5_SOLIDPRINCIPLES.L_LISKOV_SUBSTITUTION_PRINCIPLE;

public class FixedRectangle implements Shape {
    private int width;
    private int height;

    public FixedRectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public int area() {
        return width * height;
    }

}
