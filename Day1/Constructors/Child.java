package Constructors;

public class Child extends Parent {
    static {System.out.println("2. Child static block");}
    {System.out.println("5. Child instance block");}
    Child() {
        System.out.println("6. Child constructor");
    }

}
