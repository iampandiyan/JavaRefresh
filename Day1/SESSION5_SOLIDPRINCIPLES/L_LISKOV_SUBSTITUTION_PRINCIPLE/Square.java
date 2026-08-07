package SESSION5_SOLIDPRINCIPLES.L_LISKOV_SUBSTITUTION_PRINCIPLE;

public class Square extends Rectangle {
     // breaks caller's assumption that width/height are independent
    @Override
    void setWidth(int width) {
        super.setWidth(width);
        super.setHeight(width);
    }

    @Override
    void setHeight(int height) {
        super.setHeight(height);
        super.setWidth(height);
    }

}
