package SESSION5_SOLIDPRINCIPLES.L_LISKOV_SUBSTITUTION_PRINCIPLE;

public class LspDemo {
    public static void main(String[] args) {
        System.out.println("=== LSP Violation Example ===");
        resizeViolation(new Rectangle());
        resizeViolation(new Square());

        System.out.println("\n=== LSP Compliant Example ===");
        printArea(new FixedRectangle(5, 10));
        printArea(new FixedSquare(5));
    }

    private static void resizeViolation(Rectangle r) {
        r.setWidth(5);
        r.setHeight(10);
        System.out.println("Expected area of 50, got " + r.area());
    }

    private static void printArea(Shape shape) {
        System.out.println(shape.getClass().getSimpleName() + " area = " + shape.area());
    }
}
