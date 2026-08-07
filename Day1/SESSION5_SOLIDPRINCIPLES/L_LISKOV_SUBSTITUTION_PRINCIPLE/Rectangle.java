package SESSION5_SOLIDPRINCIPLES.L_LISKOV_SUBSTITUTION_PRINCIPLE;

public class Rectangle {
    protected int width;
    protected int height;
    void setWidth(int width) {
        this.width = width;
    }
    void setHeight(int height) {
        this.height = height;
    }
    int area() {
        return width * height;
    }
}
