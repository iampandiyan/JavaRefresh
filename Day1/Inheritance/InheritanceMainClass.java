package Inheritance;

public class InheritanceMainClass {
    public static void main(String[] args) {
        Animal a=new Dog();
        System.out.println(a.sound()); //"Bark" — runtime resolution
        System.out.println(Animal.category()); // "Generic Animal" — compile-time resolution

        Base ref=new Derived();
        System.out.println(ref.value); //10 — compile-time resolution
        System.out.println(((Derived)ref).value); //20 — runtime resolution
    }

}
