package SESSION5_SOLIDPRINCIPLES.L_LISKOV_SUBSTITUTION_PRINCIPLE;

public class LspViolationDemo {
    static void resize(Rectangle r) {
        r.setWidth(5);
        r.setHeight(10);
        System.out.println("Expected area of 50, got " + r.area());
    }
    public static void main(String[] args) {
             resize(new Square()); // prints 100, not 50 — LSP violated
    }
}
