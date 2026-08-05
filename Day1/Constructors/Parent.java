package Constructors;

public class Parent {
static {System.out.println("1. Parent static block");}
{System.out.println("3. Parent instance block");}
Parent() {
    System.out.println("4. Parent constructor");
}
}