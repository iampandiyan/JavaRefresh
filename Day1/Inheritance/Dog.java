package Inheritance;

public class Dog extends Animal {
    static String category(){ // HIDES, does not override
        return "Dog";
    }
    @Override
    String sound(){ // OVERRIDES — polymorphic}
        return "Bark";
    }
}
