package SESSION4_DESIGNPATTERN.Decorator;

public class CoffeeDecoratorDemo {
    public static void main(String[] args) {
        Coffee plainCoffee = new SimpleCoffee();
        System.out.println(plainCoffee.getDescription() + " costs " + plainCoffee.getCost());

        Coffee milkCoffee = new MilkDecorator(plainCoffee);
        System.out.println(milkCoffee.getDescription() + " costs " + milkCoffee.getCost());

        Coffee sweetMilkCoffee = new SugarDecorator(milkCoffee);
        System.out.println(sweetMilkCoffee.getDescription() + " costs " + sweetMilkCoffee.getCost());
    }

    interface Coffee {
        String getDescription();
        double getCost();
    }

    static class SimpleCoffee implements Coffee {
        @Override
        public String getDescription() {
            return "Simple coffee";
        }

        @Override
        public double getCost() {
            return 2.0;
        }
    }

    static abstract class CoffeeDecorator implements Coffee {
        protected final Coffee wrappedCoffee;

        CoffeeDecorator(Coffee wrappedCoffee) {
            this.wrappedCoffee = wrappedCoffee;
        }
    }

    static class MilkDecorator extends CoffeeDecorator {
        MilkDecorator(Coffee wrappedCoffee) {
            super(wrappedCoffee);
        }

        @Override
        public String getDescription() {
            return wrappedCoffee.getDescription() + " + milk";
        }

        @Override
        public double getCost() {
            return wrappedCoffee.getCost() + 0.5;
        }
    }

    static class SugarDecorator extends CoffeeDecorator {
        SugarDecorator(Coffee wrappedCoffee) {
            super(wrappedCoffee);
        }

        @Override
        public String getDescription() {
            return wrappedCoffee.getDescription() + " + sugar";
        }

        @Override
        public double getCost() {
            return wrappedCoffee.getCost() + 0.2;
        }
    }
}
