package Polymorphism;

public class OverloadResolutionDemo {
    static void print(int x) {
        System.out.println("int: " + x);
    }
    static void print(double x) {
        System.out.println("double: " + x);
    }
    static void print(Long x) {
        System.out.println("Long: " + x);
    }
    static void print(Integer x) {
        System.out.println("Integer: " + x);
    }
    static void print(Object x) {
        System.out.println("Object: " + x);
    }

    public static void main(String[] args) {
        print(10); // Calls print(int)
        print(10.5); // Calls print(double)
        print(10L); // Calls print(Long)
        print(new Integer(10)); // Calls print(Integer)
        print("Hello"); // Calls print(Object)
    }

}
